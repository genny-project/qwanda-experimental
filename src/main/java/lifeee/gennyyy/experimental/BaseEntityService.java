package lifeee.gennyyy.experimental;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.jpa.QueryHints;
import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import life.genny.qwanda.Link;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.attribute.EntityAttribute;
import life.genny.qwanda.entity.BaseEntity;
import life.genny.qwanda.entity.EntityEntity;
import life.genny.qwanda.entity.SearchEntity;
import life.genny.qwanda.message.QSearchEntityMessage;

@ApplicationScoped
public class BaseEntityService {
  protected static final Logger log =
      org.apache.logging.log4j.LogManager.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());

  public static final String REALM_HIDDEN = "hidden";

  Map<String, String> ddtCacheMock = new ConcurrentHashMap<>();

  @Inject
  EntityManager em;
  
  List<String> allowedConditions = Arrays.asList("=", "<", ">", "<=", ">=", "LIKE", "!=", "<>", "&+", "&0");
  List<String> allowedLinkWeightConditions = Arrays.asList("=", "<", ">", "<=", ">=");

  Set<String> realms = new HashSet<>(Arrays.asList("genny", "hidden"));
  String realmsStr = "internmatch";



  public List<BaseEntity> findBySearchBE2(@NotNull final String hql) {
    List<BaseEntity> results = null;

    Query query = null;
    Set<String> attributeCodes = new HashSet<>(Arrays.asList("PRI_FIRSTNAME", "PRI_LASTNAME"));
    Filter filter = getEntityManager().unwrap(Session.class).enableFilter("filterAttribute");
    filter.setParameterList("attributeCodes", attributeCodes);
    query = getEntityManager().createQuery(hql);
    query.setFirstResult(0).setMaxResults(1000);

    results = query.setHint(QueryHints.HINT_READONLY, true).getResultList();
    log.debug("RESULTS=" + results);
    return results;
  }

  public List<Object> findBySearchBE3(@NotNull final String hql) {
    List<Object> results = null;
    log.debug("Object result:" + hql);
    Query query = null;
    query = getEntityManager().createQuery(hql);
    query.setFirstResult(0).setMaxResults(1000);

    results = query.setHint(QueryHints.HINT_READONLY, true).getResultList();
    log.debug("RESULTS=" + results);
    return results;
  }

  public Long findBySearchBECount(@NotNull final BaseEntity searchBE) {
    Long count = 0L;
    final String userRealmStr = getRealm();

    String stakeholderCode = searchBE.getValue("SCH_STAKEHOLDER_CODE", null);
    String sourceStakeholderCode = searchBE.getValue("SCH_SOURCE_STAKEHOLDER_CODE", null);
    String linkCode = searchBE.getValue("SCH_LINK_CODE", null);
    String linkValue = searchBE.getValue("SCH_LINK_VALUE", null);
    Double linkWeight = searchBE.getValue("SCH_LINK_WEIGHT", 0.0);
    String linkWeightFilter = searchBE.getValue("SCH_LINK_FILTER", ">");

    String sourceCode = searchBE.getValue("SCH_SOURCE_CODE", null);
    String targetCode = searchBE.getValue("SCH_TARGET_CODE", null);

    searchBE.removeAttribute("PRI_INDEX");
    searchBE.removeAttribute("PRI_TOTAL_RESULTS"); // ugly

    // Check for bad linkWeight filtering
    if (!allowedLinkWeightConditions.stream().anyMatch(str -> str.trim().equals(linkWeightFilter))) {
      log.error("Error! Illegal link Weight condition!(" + linkWeightFilter + ") for user ");
      return 0L;
    }

    SearchSettings ss = this.buildQuerySettings(searchBE, 0);

    Set<String> realms = new HashSet<>();

    realms.add(getRealm());

    // realms.add("genny");
    String realmsStr = getRealmsStr(realms);

    String sql = createSearchSQL("count(distinct ea.pk.baseEntity)", stakeholderCode, sourceStakeholderCode, linkCode, linkValue,
        linkWeight, linkWeightFilter, sourceCode, targetCode, ss.filterStrings, ss.filterStringsQ, "", ss.codeFilter, realmsStr);

    Query query = null;

    query = getEntityManager().createQuery(sql);

    // query.setParameter("realms", realms); This would not work

    if (sourceCode != null) {
      query.setParameter("sourceCode", sourceCode);
    }
    if (targetCode != null) {
      query.setParameter("targetCode", targetCode);
    }
    if (linkCode != null) {
      query.setParameter("linkCode", linkCode);
    }
    if (linkValue != null) {
      query.setParameter("linkValue", linkValue);
    }
    if (linkWeight > 0.0) {
      query.setParameter("linkWeight", linkWeight);
    }

    if (stakeholderCode != null) {
      query.setParameter("stakeholderCode", stakeholderCode);
    }
    if (sourceStakeholderCode != null) {
      query.setParameter("sourceStakeholderCode", sourceStakeholderCode);
    }
    //
    for (Tuple2<String, Object> value : ss.valueList) {
      log.debug("Value: " + value._1 + " =: " + value._2);
      if (value._2 instanceof Boolean) {
        Boolean result = (Boolean) value._2;
        query.setParameter(value._1, result);
      } else if (value._2 instanceof LocalDateTime) {
        LocalDateTime result = (LocalDateTime) value._2;
        query.setParameter(value._1, result);
      } else if (value._2 instanceof LocalDate) {
        LocalDate result = (LocalDate) value._2;
        query.setParameter(value._1, result);
      } else if (value._2 instanceof LocalTime) {
        LocalTime result = (LocalTime) value._2;
        query.setParameter(value._1, result);
      } else {
        query.setParameter(value._1, value._2);
      }
    }
    // Long count1 = 100L;
    Object count1 = query.getSingleResult();
    log.info("The Count Object is :: " + count1.toString());
    count = (Long) count1;

    return count;
  }

  protected String getRealm() {

    return "internmatch"; // TODO HACK, is overridden
  }

  private String getRealmsStr(Set<String> realms) {
    String ret = "";
    for (String realm : realms) {
      ret += "'" + realm + "',";
    }
    return ret.substring(0, ret.length() - 1);
  }

  private <T> String getHQL(Range rangeValue, String attributeCodeEA, String valueType, Integer filterIndex,
      List<Tuple2<String, Object>> valueList) {
    String ret = "";
    if (rangeValue.hasLowerBound() && rangeValue.hasUpperBound()) {
      if (rangeValue.lowerBoundType().equals(BoundType.CLOSED) && rangeValue.lowerBoundType().equals(BoundType.CLOSED)) {
        ret = " and " + attributeCodeEA + "." + valueType + ">=:v" + filterIndex + " ";
        valueList.add(Tuple.of("vd" + filterIndex++, rangeValue.lowerEndpoint()));
        ret += " and " + attributeCodeEA + "." + valueType + "<=:v" + filterIndex + " ";
        valueList.add(Tuple.of("vd" + filterIndex, rangeValue.upperEndpoint()));
      } else if (rangeValue.lowerBoundType().equals(BoundType.CLOSED) && rangeValue.lowerBoundType().equals(BoundType.OPEN)) {
        ret = " and " + attributeCodeEA + "." + valueType + ">=:v" + filterIndex++ + " ";
        valueList.add(Tuple.of("vd" + filterIndex++, rangeValue.lowerEndpoint()));
        ret += " and " + attributeCodeEA + "." + valueType + "<:v" + filterIndex + " ";
        valueList.add(Tuple.of("vd" + filterIndex, rangeValue.upperEndpoint()));
      } else if (rangeValue.lowerBoundType().equals(BoundType.OPEN) && rangeValue.lowerBoundType().equals(BoundType.CLOSED)) {
        ret = " and " + attributeCodeEA + "." + valueType + ">:v" + filterIndex++ + " ";
        valueList.add(Tuple.of("vd" + filterIndex++, rangeValue.lowerEndpoint()));
        ret += " and " + attributeCodeEA + "." + valueType + "<=:v" + filterIndex + " ";
        valueList.add(Tuple.of("vd" + filterIndex, rangeValue.upperEndpoint()));
      } else if (rangeValue.lowerBoundType().equals(BoundType.OPEN) && rangeValue.lowerBoundType().equals(BoundType.OPEN)) {
        ret = " and " + attributeCodeEA + "." + valueType + ">:v" + filterIndex++ + " ";
        valueList.add(Tuple.of("vd" + filterIndex++, rangeValue.lowerEndpoint()));
        ret += " and " + attributeCodeEA + "." + valueType + "<:v" + filterIndex + " ";
        valueList.add(Tuple.of("vd" + filterIndex, rangeValue.upperEndpoint()));
      }
    } else if (rangeValue.hasLowerBound() && !rangeValue.hasUpperBound()) {
      if (rangeValue.lowerBoundType().equals(BoundType.CLOSED)) {
        ret = " and " + attributeCodeEA + "." + valueType + ">=:v" + filterIndex + " ";
      } else {
        ret = " and " + attributeCodeEA + "." + valueType + ">=:v" + filterIndex + " ";
      }
      valueList.add(Tuple.of("vd" + filterIndex, rangeValue.lowerEndpoint()));
    } else if (rangeValue.hasUpperBound() && !rangeValue.hasLowerBound()) {
      if (rangeValue.upperBoundType().equals(BoundType.CLOSED)) {
        ret = " and " + attributeCodeEA + "." + valueType + "<=:v" + filterIndex + " ";
      } else {
        ret = " and " + attributeCodeEA + "." + valueType + "<:v" + filterIndex + " ";
      }
      valueList.add(Tuple.of("vd" + filterIndex, rangeValue.upperEndpoint()));
    }
    filterIndex++;
    return ret;
  }

  public Long findBySearchBECount(@NotNull final QSearchEntityMessage searchBE) {
    Long count = 0L;
    final String userRealmStr = getRealm();

    Integer pageStart = searchBE.getParent().getValue("SCH_PAGE_START", 0);
    Integer pageSize = searchBE.getParent().getValue("SCH_PAGE_SIZE", 100);
    String stakeholderCode = searchBE.getParent().getValue("SCH_STAKEHOLDER_CODE", null);
    String sourceStakeholderCode = searchBE.getParent().getValue("SCH_SOURCE_STAKEHOLDER_CODE", null);
    String linkCode = searchBE.getParent().getValue("SCH_LINK_CODE", null);
    String linkValue = searchBE.getParent().getValue("SCH_LINK_VALUE", null);
    Double linkWeight = searchBE.getParent().getValue("SCH_LINK_WEIGHT", 0.0);
    String linkWeightFilter = searchBE.getParent().getValue("SCH_LINK_FILTER", ">");
    String sourceCode = searchBE.getParent().getValue("SCH_SOURCE_CODE", null);
    String targetCode = searchBE.getParent().getValue("SCH_TARGET_CODE", null);

    Integer filterIndex = 0;

    SearchEntity se = searchBE.getParent();

    SearchSettings ss = buildQuerySettings(se, filterIndex);
    filterIndex = ss.filterIndex;
    List<SearchSettings> filters = new ArrayList<SearchSettings>();

    for (BaseEntity filter : searchBE.getItems()) {
      SearchSettings myFilter = (buildQuerySettings(filter, filterIndex));
      filters.add(myFilter);
      filterIndex = myFilter.filterIndex; // update the filterIndex to keep up to date across all the 'Ors'
    }

    Set<String> realms = new HashSet<>();
    realms.add(getRealm());

    realms.add("genny");
    String realmsStr = getRealmsStr(realms);

    ss.orderString = createOrderString(ss.attributeCodeMap, ss.orderList);
    String sql = createSearchSQL("count(distinct ea.pk.baseEntity)", stakeholderCode, sourceStakeholderCode, linkCode, linkValue,
        linkWeight, linkWeightFilter, sourceCode, targetCode, ss.filterStrings, ss.filterStringsQ, "", ss.codeFilter, realmsStr);

    Query query = null;

    query = getEntityManager().createQuery(sql);

    // query.setParameter("realms", realms); This would not work

    if (sourceCode != null) {
      query.setParameter("sourceCode", sourceCode);
    }
    if (targetCode != null) {
      query.setParameter("targetCode", targetCode);
    }
    if (linkCode != null) {
      query.setParameter("linkCode", linkCode);
    }
    if (linkValue != null) {
      query.setParameter("linkValue", linkValue);
    }
    if (linkWeight > 0.0) {
      query.setParameter("linkWeight", linkWeight);
    }

    if (stakeholderCode != null) {
      query.setParameter("stakeholderCode", stakeholderCode);
    }
    if (sourceStakeholderCode != null) {
      query.setParameter("sourceStakeholderCode", sourceStakeholderCode);
    }
    //
    for (SearchSettings filter : filters) {
      for (Tuple2<String, Object> value : filter.valueList) {
        log.debug("Value: " + value._1 + " =: " + value._2);
        if (value._2 instanceof Boolean) {
          Boolean result = (Boolean) value._2;
          query.setParameter(value._1, result);
        } else if (value._2 instanceof LocalDateTime) {
          LocalDateTime result = (LocalDateTime) value._2;
          query.setParameter(value._1, result);
        } else if (value._2 instanceof LocalDate) {
          LocalDate result = (LocalDate) value._2;
          query.setParameter(value._1, result);
        } else if (value._2 instanceof LocalTime) {
          LocalTime result = (LocalTime) value._2;
          query.setParameter(value._1, result);
        } else {
          query.setParameter(value._1, value._2);
        }
      }
    }
    Object count1 = query.getSingleResult();
    log.info("The Count Object is :: " + count1.toString());
    count = (Long) count1;
    // count = (Long)query.getSingleResult();

    return count;
  }

  public List<BaseEntity> findBySearchBE(@NotNull final QSearchEntityMessage searchBE) {
    List<BaseEntity> results = new ArrayList<BaseEntity>();
    final String userRealmStr = getRealm();

    Integer pageStart = searchBE.getParent().getValue("SCH_PAGE_START", 0);
    Integer pageSize = searchBE.getParent().getValue("SCH_PAGE_SIZE", 100);
    String stakeholderCode = searchBE.getParent().getValue("SCH_STAKEHOLDER_CODE", null);
    String sourceStakeholderCode = searchBE.getParent().getValue("SCH_SOURCE_STAKEHOLDER_CODE", null);
    String linkCode = searchBE.getParent().getValue("SCH_LINK_CODE", null);
    String linkValue = searchBE.getParent().getValue("SCH_LINK_VALUE", null);
    Double linkWeight = searchBE.getParent().getValue("SCH_LINK_WEIGHT", 0.0);
    String linkWeightFilter = searchBE.getParent().getValue("SCH_LINK_FILTER", ">");
    String sourceCode = searchBE.getParent().getValue("SCH_SOURCE_CODE", null);
    String targetCode = searchBE.getParent().getValue("SCH_TARGET_CODE", null);

    // Check for bad linkWeight filtering
    if (!allowedLinkWeightConditions.stream().anyMatch(str -> str.trim().equals(linkWeightFilter))) {
      log.error("Error! Illegal link Weight condition!(" + linkWeightFilter + ") for user ");
      return results;
    }

    Integer filterIndex = 0;

    SearchEntity se = searchBE.getParent();

    SearchSettings ss = buildQuerySettings(se, filterIndex);
    filterIndex = ss.filterIndex;
    List<SearchSettings> filters = new ArrayList<SearchSettings>();

    for (BaseEntity filter : searchBE.getItems()) {
      SearchSettings myFilter = (buildQuerySettings(filter, filterIndex));
      filters.add(myFilter);
      filterIndex = myFilter.filterIndex; // update the filterIndex to keep up to date across all the 'Ors'
    }

    Set<String> realms = new HashSet<String>();
    realms.add(userRealmStr);
    realms.add("genny");
    String realmsStr = getRealmsStr(realms);

    ss.orderString = createOrderString(ss.attributeCodeMap, ss.orderList);

    String sql = createSearchSQL("distinct ea.pk.baseEntity", stakeholderCode, sourceStakeholderCode, linkCode, linkValue, linkWeight,
        linkWeightFilter, sourceCode, targetCode, ss.filterStrings, ss.filterStringsQ, ss.orderString, filters, realmsStr);

    Query query = null;

    // Limit column attributes returned using Hibernate filter
    // If empty then return everything
    if (!ss.attributeCodes.isEmpty()) {
      Filter filter = getEntityManager().unwrap(Session.class).enableFilter("filterAttribute");
      filter.setParameterList("attributeCodes", ss.attributeCodes);
    }

    query = getEntityManager().createQuery(sql);

    query.setFirstResult(pageStart).setMaxResults(pageSize);

    // query.setParameter("realms", realms); This would not work

    if (sourceCode != null) {
      query.setParameter("sourceCode", sourceCode);
    }
    if (targetCode != null) {
      query.setParameter("targetCode", targetCode);
    }
    if (linkCode != null) {
      query.setParameter("linkCode", linkCode);
    }
    if (linkValue != null) {
      query.setParameter("linkValue", linkValue);
    }
    if (linkWeight > 0.0) {
      query.setParameter("linkWeight", linkWeight);
    }
    if (stakeholderCode != null) {
      query.setParameter("stakeholderCode", stakeholderCode);
    }
    if (sourceStakeholderCode != null) {
      query.setParameter("sourceStakeholderCode", sourceStakeholderCode);
    }
    //
    for (SearchSettings filter : filters) {
      for (Tuple2<String, Object> value : filter.valueList) {
        log.debug("Value: " + value._1 + " =: " + value._2);
        if (value._2 instanceof Boolean) {
          Boolean result = (Boolean) value._2;
          query.setParameter(value._1, result);
        } else if (value._2 instanceof LocalDateTime) {
          LocalDateTime result = (LocalDateTime) value._2;
          query.setParameter(value._1, result);
        } else if (value._2 instanceof LocalDate) {
          LocalDate result = (LocalDate) value._2;
          query.setParameter(value._1, result);
        } else if (value._2 instanceof LocalTime) {
          LocalTime result = (LocalTime) value._2;
          query.setParameter(value._1, result);
        } else {
          query.setParameter(value._1, value._2);
        }

      }
    }
    results = query.setHint(QueryHints.HINT_READONLY, true).getResultList();

    // Work out associated Columns
    List<Column> columns = ss.getColumnList();
    List<Column> associatedColumns = new ArrayList<Column>();
    Map<String, Attribute> attributeMap = new ConcurrentHashMap<String, Attribute>();
    Map<String, Integer> columnIndexMap = new ConcurrentHashMap<String, Integer>();

    Integer columnIndex = 0;
    for (Column column : columns) { // TODO: stream
      if (column.linkCode != null) {
        associatedColumns.add(column);
        attributeMap.put(column.fieldCode, findAttributeByCode(column.fieldCode)); // prepare cache to be
                                                                                   // quicker
        columnIndexMap.put(column.fieldCode, columnIndex++);
      } else {
        columnIndexMap.put(column.fieldCode, columnIndex++);
      }
    }

    // Set simple sort index for frontend to use
    Integer index = 0;
    if (associatedColumns.isEmpty()) { // for speed
      for (BaseEntity be : results) {
        be.setIndex(index++);
        // Set the baseentity entityAttribute order
        for (EntityAttribute ea : be.getBaseEntityAttributes()) {
          ea.setIndex(columnIndexMap.get(ea.getAttributeCode()));
        }
      }
    } else {
      Map<String, BaseEntity> associatedBaseEntityMap = new ConcurrentHashMap<String, BaseEntity>(); // store
                                                                                                     // baseentitys
                                                                                                     // as a map
                                                                                                     // to speed
                                                                                                     // up
                                                                                                     // duplicates
      for (BaseEntity be : results) {
        be.setIndex(index++);
        // Now add any extra associated Attributes
        for (Column associatedColumn : associatedColumns) {
          String requiredAttributeCode = associatedColumn.getFieldCode();
          String requiredAttributeName = associatedColumn.getFieldName();
          Attribute requiredAttribute = attributeMap.get(requiredAttributeCode);
          requiredAttribute.setName(requiredAttributeName); // Override the virtual name

          Set<String> associatedBaseEntityCodes = new HashSet<String>();

          if (associatedColumn.getLinkCode().startsWith("LNK_")) {
            // (1) find if any links have the linkCode requested
            if (associatedColumn.getLinkType().equalsIgnoreCase("CHILD")) {
              List<EntityEntity> associatedLinks = be.getLinks().parallelStream() // convert list to
                                                                                  // stream
                  .filter(ti -> ti.getPk().getAttribute().getCode().equals(associatedColumn.getLinkCode())) // check
                                                                                                            // for
                                                                                                            // linkCode
                  .collect(Collectors.toList()); // collect the output and convert streams to a List

              // (2) Now find the associated BaseEntitys
              associatedBaseEntityCodes = associatedLinks.parallelStream() // convert list to stream
                  .map(ee -> ee.getLink().getTargetCode()).collect(Collectors.toSet()); // collect the
                                                                                        // output
                                                                                        // and
                                                                                        // convert
                                                                                        // streams
                                                                                        // to a Set
            } else {
              // find the parents that match the linkCode!
              List<Link> links = findParentLinks(be.getCode(), associatedColumn.getLinkCode());
              // (2) Now find the associated BaseEntitys
              associatedBaseEntityCodes = links.parallelStream() // convert list to stream
                  .map(ee -> ee.getSourceCode()).collect(Collectors.toSet()); // collect the output
                                                                              // and convert
                                                                              // streams to a Set

            }

          } else {
            // The associated baseentity is actually contained in an attribute within the be
            // (1) find if any links have the linkCode requested
            String associatedBaseEntityCode = be.getValue(associatedColumn.getLinkCode(), null);
            if (associatedBaseEntityCode != null) {
              associatedBaseEntityCodes.add(associatedBaseEntityCode);
            }

          }

          // (3) Now get the BaseEntitys (so we can get the value of the attributeCode
          // required
          Set<BaseEntity> associatedBaseEntitySet = associatedBaseEntityCodes.parallelStream().map(abe -> {
            BaseEntity foundBe = associatedBaseEntityMap.getOrDefault(abe, findBaseEntityByCode(abe));
            associatedBaseEntityMap.put(abe, foundBe); // don't worry about putting it back in of already
                                                       // there
            return foundBe;
          }).collect(Collectors.toSet());

          // (3.5) create fake EntityAttributes for any attributes that this baseentity
          // does not have ...
          if (associatedBaseEntitySet.isEmpty()) {
            EntityAttribute virtualEA = new EntityAttribute(be, requiredAttribute, 1.0, requiredAttribute.getDefaultValue());
            virtualEA.setAttributeCode(":" + requiredAttributeCode);
            be.getBaseEntityAttributes().add(virtualEA);
            continue;
          }

          // (4) Now create a Set of EntityAttributes
          Set<EntityAttribute> virtualEntityAttributeList = associatedBaseEntitySet.parallelStream().map(abe -> {
            EntityAttribute virtualEA =
                new EntityAttribute(be, requiredAttribute, 1.0, abe.getValue(requiredAttributeCode, requiredAttribute.getDefaultValue()));
            virtualEA.setAttributeCode(abe.getCode() + ":" + requiredAttributeCode);
            return virtualEA;
          }).collect(Collectors.toSet());

          // (5) Add to the be!
          be.getBaseEntityAttributes().addAll(virtualEntityAttributeList);

        }

        // Set the baseentity entityAttribute order
        for (EntityAttribute ea : be.getBaseEntityAttributes()) {
          ea.setIndex(columnIndexMap.get(ea.getAttributeCode()));
        }

      }
    }
    return results;

  }

  /**
   * @param se
   * @param ss
   * @throws NoResultException
   * @throws IllegalArgumentException
   */
  private SearchSettings buildQuerySettings(BaseEntity se, Integer filterIndex) throws NoResultException, IllegalArgumentException {

    SearchSettings ss = new SearchSettings();
    ss.filterIndex = filterIndex;

    for (EntityAttribute ea : se.getBaseEntityAttributes()) {
      if (ea.getAttributeCode().startsWith("SCH_")) {
        continue;
      } else if (ea.getAttributeCode().startsWith("SRT_")) {
        String sortAttributeCode = ea.getAttributeCode().substring("SRT_".length());
        if (ea.getWeight() == null) {
          ea.setWeight(1000.0); // If no weight is given setting it to be in the last of the sort
        }
        ss.orderList.add(new Order(sortAttributeCode, ea.getValueString().toUpperCase(), ea.getWeight())); // weight

        if (!(sortAttributeCode.equalsIgnoreCase("PRI_CODE") || (sortAttributeCode.equalsIgnoreCase("PRI_CREATED"))
            || (sortAttributeCode.equalsIgnoreCase("PRI_UPDATED")) || (sortAttributeCode.equalsIgnoreCase("PRI_ID"))
            || (sortAttributeCode.equalsIgnoreCase("PRI_NAME")))) {
          // specifies
          // the sort
          // order
          String attributeCodeEA = "ea" + ss.filterIndex;
          ss.filterStrings += ",EntityAttribute " + attributeCodeEA;
          ss.filterStringsQ += " and " + attributeCodeEA + ".pk.baseEntity.id=ea.pk.baseEntity.id and " + attributeCodeEA
              + ".pk.attribute.code='" + sortAttributeCode + "' ";
          if ((ea.getPk() == null) || ea.getPk().getAttribute() == null) {
            Attribute attribute = this.findAttributeByCode(sortAttributeCode);
            ea.getPk().setAttribute(attribute);
          }
          String sortType = "valueString";
          sortType = getSortType(ea);
          ss.attributeCodeMap.put(sortAttributeCode, attributeCodeEA + "." + sortType);
          ss.filterIndex++;
        }
      } else if (ea.getAttributeCode().startsWith("COL_")) {
        String columnAttributeCode = ea.getAttributeCode().substring("COL_".length());
        if (!columnAttributeCode.equalsIgnoreCase("code")) {
          ss.columnList.add(new Column(columnAttributeCode, ea.getAttributeName(), ea.getWeight()));
          ss.attributeCodes.add(columnAttributeCode);
        }
      } else if (ea.getAttributeCode().startsWith("CAL_")) {
        Map<String, String> associatedCodes = QSearchEntityMessage.getAssociationCodes(ea.getAttributeCode());
        String columnAttributeCode = associatedCodes.get("ATTRIBUTE_CODE");

        if (!columnAttributeCode.equalsIgnoreCase("code")) {
          Column calColumn = new Column(columnAttributeCode, ea.getAttributeName(), ea.getWeight());
          calColumn.setLinkCode(associatedCodes.get("LINK_CODE"));
          calColumn.setLinkType(associatedCodes.get("LINK_TYPE"));
          ss.columnList.add(calColumn);
          ss.attributeCodes.add(columnAttributeCode);
        }
      } else {
        String priAttributeCode = ea.getAttributeCode();

        // Check no nasty SQL injection
        // Need named procedures
        // Quick and dirty ; check
        String condition = ea.getAttributeName();
        if (condition != null) {

          final String conditionTest = condition.trim();
          if (!allowedConditions.stream().anyMatch(str -> str.trim().equals(conditionTest))) {
            throw new IllegalArgumentException("Illegal condition!(" + conditionTest + ") [" + ea.getAttributeCode() + "] for user ");
          }

        }

        String valueString = ea.getValueString();
        if (valueString != null) {
          if (valueString.contains(";")) {
            throw new IllegalArgumentException("Error! Illegal condition!(" + valueString + ") [" + ea.getAttributeCode() + "] for user ");
          }
        }

        if (priAttributeCode.equalsIgnoreCase("PRI_CODE")) {
          if (ea.getAttributeName() == null) {
            ss.codeFilter += " and ea.pk.baseEntity.code=:v" + ss.filterIndex + " ";
          } else {
            ss.codeFilter += " and ea.pk.baseEntity.code " + condition + " :v" + ss.filterIndex + " ";
          }
          String attributeCodeEA = "ea" + ss.filterIndex;
          ss.filterStrings += ",EntityAttribute " + attributeCodeEA;
          ss.valueList.add(Tuple.of("v" + ss.filterIndex, ea.getValueString()));
          ss.filterIndex++;
        } else if (priAttributeCode.equalsIgnoreCase("PRI_CREATED")) {
          if (ea.getAttributeName() == null) {
            ss.codeFilter += " and ea.pk.baseEntity.created=:v" + ss.filterIndex + " ";
          } else {
            ss.codeFilter += " and ea.pk.baseEntity.created " + condition + " :v" + ss.filterIndex + " ";
          }
          String attributeCodeEA = "ea" + ss.filterIndex;
          ss.filterStrings += ",EntityAttribute " + attributeCodeEA;
          ss.valueList.add(Tuple.of("v" + ss.filterIndex, ea.getValueDateTime()));
          ss.filterIndex++;
        } else {
          String attributeCodeEA = "ea" + ss.filterIndex;
          ss.setPrefix(attributeCodeEA);
          ss.filterStrings += ",EntityAttribute " + attributeCodeEA;
          ss.filterStringsQ += " and " + attributeCodeEA + ".pk.baseEntity.id=ea.pk.baseEntity.id and " + attributeCodeEA
              + ".pk.attribute.code='" + priAttributeCode + "' ";
          if ((ea.getPk() == null) || ea.getPk().getAttribute() == null) {
            Attribute attribute = this.findAttributeByCode(priAttributeCode);
            ea.getPk().setAttribute(attribute);
          }
          switch (ea.getPk().getAttribute().getDataType().getClassName()) {
            case "java.lang.Integer":
            case "Integer":
              ss.filterStringsQ = updateFilterStringsQ(ss.filterStringsQ, ss.filterIndex, ea, condition, attributeCodeEA, "Integer");
              ss.valueList.add(Tuple.of("v" + ss.filterIndex, ea.getValueInteger()));
              ss.attributeCodeMap.put(priAttributeCode, attributeCodeEA + ".valueInteger");
              break;
            case "java.lang.Long":
            case "Long":
              ss.filterStringsQ = updateFilterStringsQ(ss.filterStringsQ, ss.filterIndex, ea, condition, attributeCodeEA, "Long");
              ss.valueList.add(Tuple.of("v" + ss.filterIndex, ea.getValueLong()));
              ss.attributeCodeMap.put(priAttributeCode, attributeCodeEA + ".valueLong");
              break;
            case "java.lang.Double":
            case "Double":
              ss.filterStringsQ = updateFilterStringsQ(ss.filterStringsQ, ss.filterIndex, ea, condition, attributeCodeEA, "Double");
              ss.valueList.add(Tuple.of("v" + ss.filterIndex, ea.getValueDouble()));
              ss.attributeCodeMap.put(priAttributeCode, attributeCodeEA + ".valueDouble");
              break;
            case "range.LocalDate":
              Range<LocalDate> rangeLocalDate = ea.getValueDateRange();
              ss.filterStringsQ += getHQL(rangeLocalDate, attributeCodeEA, "valueDate", ss.filterIndex, ss.valueList);
              ss.attributeCodeMap.put(priAttributeCode, attributeCodeEA + ".valueDate");
              break;
            case "java.lang.Boolean":
            case "Boolean":
              ss.filterStringsQ += " and " + attributeCodeEA + ".valueBoolean=:v" + ss.filterIndex + " ";
              ss.valueList.add(Tuple.of("v" + ss.filterIndex, ea.getValueBoolean()));
              ss.attributeCodeMap.put(priAttributeCode, attributeCodeEA + ".valueBoolean");
              break;
            case "java.time.LocalDate":
            case "LocalDate":
              ss.filterStringsQ = updateFilterStringsQ(ss.filterStringsQ, ss.filterIndex, ea, condition, attributeCodeEA, "Date");
              ss.valueList.add(Tuple.of("v" + ss.filterIndex, ea.getValueDate()));
              ss.attributeCodeMap.put(priAttributeCode, attributeCodeEA + ".valueDate");
              break;
            case "java.time.LocalDateTime":
            case "LocalDateTime":
              ss.filterStringsQ = updateFilterStringsQ(ss.filterStringsQ, ss.filterIndex, ea, condition, attributeCodeEA, "DateTime");
              ss.valueList.add(Tuple.of("v" + ss.filterIndex, ea.getValueDateTime()));
              ss.attributeCodeMap.put(priAttributeCode, attributeCodeEA + ".valueDate");
              break;
            case "java.time.LocalTime":
            case "LocalTime":
              ss.filterStringsQ = updateFilterStringsQ(ss.filterStringsQ, ss.filterIndex, ea, condition, attributeCodeEA, "Time");
              ss.valueList.add(Tuple.of("v" + ss.filterIndex, ea.getValueTime()));
              ss.attributeCodeMap.put(priAttributeCode, attributeCodeEA + ".valueTime");
              break;
            // case "org.javamoney.moneta.Money":
            // return (T) getValueMoney();
            case "java.lang.String":
            case "String":
            default:
              ss.filterStringsQ = updateFilterStringsQ(ss.filterStringsQ, ss.filterIndex, ea, condition, attributeCodeEA, "String");
              ss.valueList.add(Tuple.of("v" + ss.filterIndex, ea.getValueString()));
              ss.attributeCodeMap.put(priAttributeCode, attributeCodeEA + ".valueString");
          }
          ss.codeFilter = ss.filterStringsQ;
          ss.filterIndex++;

        }
      }
    }

    ss.filterStringsQ = fixFilterStringsQ(ss.filterStringsQ, ss.filterIndex);
    ss.filterStrings = ss.filterStrings.replaceFirst(",", "");
    return ss;
  }

  public List<BaseEntity> findBySearchBE(@NotNull final BaseEntity searchBE) {
    // select distinct ea.pk.baseEntity from EntityAttribute ea , EntityAttribute eb
    // where
    // ea.attributeCode='PRI_LASTNAME'
    // and ea.valueString like '%CROW%'
    // and eb.pk.baseEntity.code = ea.pk.baseEntity.code
    // and eb.attributeCode='PRI_FIRSTNAME'
    //
    // order by ea.valueString ASC,eb.valueString DESC

    // String attributes have Exact Value matching and SORT ASC/DESC

    // Double attributes have Range matching and Sort ASC/DESC

    // Money attributes have Range matching and Sort ASC/DESC

    // Integer and Long attributes have Range matching and Sort ASC/DESC

    List<BaseEntity> results = null;
    final String userRealmStr = getRealm();

    Integer pageStart = searchBE.getValue("SCH_PAGE_START", 0);
    Integer pageSize = searchBE.getValue("SCH_PAGE_SIZE", 100);
    String stakeholderCode = searchBE.getValue("SCH_STAKEHOLDER_CODE", null);
    String sourceStakeholderCode = searchBE.getValue("SCH_SOURCE_STAKEHOLDER_CODE", null);
    String linkCode = searchBE.getValue("SCH_LINK_CODE", null);
    String linkValue = searchBE.getValue("SCH_LINK_VALUE", null);
    Double linkWeight = searchBE.getValue("SCH_LINK_WEIGHT", 0.0);
    String linkWeightFilter = searchBE.getValue("SCH_LINK_FILTER", ">");
    String sourceCode = searchBE.getValue("SCH_SOURCE_CODE", null);
    String targetCode = searchBE.getValue("SCH_TARGET_CODE", null);

    // remove these because they screw up search , should have been prefixed SCH
    searchBE.removeAttribute("PRI_INDEX");
    searchBE.removeAttribute("PRI_TOTAL_RESULTS"); // ugly

    // Construct the filters for the attributes
    String filterStrings = "";
    String filterStringsQ = "";
    String orderString = "";
    String codeFilter = "";

    // Check for bad linkWeight filtering
    if (!allowedLinkWeightConditions.stream().anyMatch(str -> str.trim().equals(linkWeightFilter))) {
      log.error("Error! Illegal link Weight condition!(" + linkWeightFilter + ") for user ");
      return results;
    }

    Integer filterIndex = 0;
    final HashMap<String, String> attributeCodeMap = new HashMap<>();
    final List<Tuple2<String, Object>> valueList = new ArrayList<>();
    final List<Order> orderList = new ArrayList<>(); // attributeCode , ASC/DESC
    final List<Column> columnList = new ArrayList<>(); // column to be searched for and returned
    Set<String> attributeCodes = new HashSet<>();

    for (EntityAttribute ea : searchBE.getBaseEntityAttributes()) {
      if (ea.getAttributeCode().startsWith("SCH_") || ea.getAttributeCode().equals("PRI_INDEX")
          || ea.getAttributeCode().equals("PRI_TOTAL_RESULTS")) {
        continue;
      } else if (ea.getAttributeCode().startsWith("SRT_")) {
        String sortAttributeCode = ea.getAttributeCode().substring("SRT_".length());
        if (ea.getWeight() == null) {
          ea.setWeight(1000.0); // If no weight is given setting it to be in the last of the sort
        }
        orderList.add(new Order(sortAttributeCode, ea.getValueString().toUpperCase(), ea.getWeight())); // weight

        if (!(sortAttributeCode.equalsIgnoreCase("PRI_CODE") || sortAttributeCode.equalsIgnoreCase("PRI_CREATED")
            || sortAttributeCode.equalsIgnoreCase("PRI_UPDATED") || sortAttributeCode.equalsIgnoreCase("PRI_ID")
            || sortAttributeCode.equalsIgnoreCase("PRI_NAME"))) {
          // specifies
          // the sort
          // order
          String attributeCodeEA = "ea" + filterIndex;
          filterStrings += ",EntityAttribute " + attributeCodeEA;
          filterStringsQ += " and " + attributeCodeEA + ".pk.baseEntity.id=ea.pk.baseEntity.id and " + attributeCodeEA
              + ".pk.attribute.code='" + sortAttributeCode + "' ";
          if (ea.getPk() == null || ea.getPk().getAttribute() == null) {
            Attribute attribute = this.findAttributeByCode(sortAttributeCode);
            ea.getPk().setAttribute(attribute);
          }
          String sortType = "valueString";
          sortType = getSortType(ea);
          attributeCodeMap.put(sortAttributeCode, attributeCodeEA + "." + sortType);
          filterIndex++;
        }
      } else if (ea.getAttributeCode().startsWith("COL_")) {
        String columnAttributeCode = ea.getAttributeCode().substring("COL_".length());
        if (!columnAttributeCode.equalsIgnoreCase("code")) {
          columnList.add(new Column(columnAttributeCode, ea.getAttributeName(), ea.getWeight()));
          attributeCodes.add(columnAttributeCode);
        }
      } else {
        String priAttributeCode = ea.getAttributeCode();

        // Check no nasty SQL injection
        // Need named procedures
        // Quick and dirty ; check
        String condition = ea.getAttributeName();
        if (condition != null) {
          final String conditionTest = condition.trim();
          if (!allowedConditions.stream().anyMatch(str -> str.trim().equals(conditionTest))) {
            log.error("Error! Illegal condition!(" + conditionTest + ") [" + ea.getAttributeCode() + "] for user ");
            return results;
          }
        }
        String valueString = ea.getValueString();
        if (valueString != null) {
          if (valueString.contains(";")) {
            log.error("Error! Illegal condition!(" + valueString + ") [" + ea.getAttributeCode() + "] for user ");
            return results;
          }
        }

        if (priAttributeCode.equalsIgnoreCase("PRI_CODE")) {
          if (ea.getAttributeName() == null) {
            codeFilter += " and ea.pk.baseEntity.code=:v" + filterIndex + " ";
          } else {
            codeFilter += " and ea.pk.baseEntity.code " + condition + " :v" + filterIndex + " ";
          }
          valueList.add(Tuple.of("v" + filterIndex, ea.getValueString()));
          filterIndex++;
        } else if (priAttributeCode.equalsIgnoreCase("PRI_CREATED")) {
          if (ea.getAttributeName() == null) {
            codeFilter += " and ea.pk.baseEntity.created=:v" + filterIndex + " ";
          } else {
            codeFilter += " and ea.pk.baseEntity.created " + condition + " :v" + filterIndex + " ";
          }
          valueList.add(Tuple.of("v" + filterIndex, ea.getValueDateTime()));
          filterIndex++;
        } else {
          String attributeCodeEA = "ea" + filterIndex;
          filterStrings += ",EntityAttribute " + attributeCodeEA;
          filterStringsQ += " and " + attributeCodeEA + ".pk.baseEntity.id=ea.pk.baseEntity.id and " + attributeCodeEA
              + ".pk.attribute.code='" + priAttributeCode + "' ";
          if (ea.getPk() == null || ea.getPk().getAttribute() == null) {
            Attribute attribute = this.findAttributeByCode(priAttributeCode);
            ea.getPk().setAttribute(attribute);
          }
          String className = null;

          try {
            className = ea.getPk().getAttribute().getDataType().getClassName();
          } catch (Exception e) {
            log.error("Attribute does not exist in Search query");
            return results;
          }
          switch (className) {
            case "java.lang.Integer":
            case "Integer":
              filterStringsQ = updateFilterStringsQ(filterStringsQ, filterIndex, ea, condition, attributeCodeEA, "Integer");
              valueList.add(Tuple.of("v" + filterIndex, ea.getValueInteger()));
              attributeCodeMap.put(priAttributeCode, attributeCodeEA + ".valueInteger");
              break;
            case "java.lang.Long":
            case "Long":
              filterStringsQ = updateFilterStringsQ(filterStringsQ, filterIndex, ea, condition, attributeCodeEA, "Long");
              valueList.add(Tuple.of("v" + filterIndex, ea.getValueLong()));
              attributeCodeMap.put(priAttributeCode, attributeCodeEA + ".valueLong");
              break;
            case "java.lang.Double":
            case "Double":
              filterStringsQ = updateFilterStringsQ(filterStringsQ, filterIndex, ea, condition, attributeCodeEA, "Double");
              valueList.add(Tuple.of("v" + filterIndex, ea.getValueDouble()));
              attributeCodeMap.put(priAttributeCode, attributeCodeEA + ".valueDouble");
              break;
            case "range.LocalDate":
              Range<LocalDate> rangeLocalDate = ea.getValueDateRange();
              filterStringsQ += getHQL(rangeLocalDate, attributeCodeEA, "valueDate", filterIndex, valueList);
              attributeCodeMap.put(priAttributeCode, attributeCodeEA + ".valueDate");
              break;
            case "java.lang.Boolean":
            case "Boolean":
              filterStringsQ += " and " + attributeCodeEA + ".valueBoolean=:v" + filterIndex + " ";
              valueList.add(Tuple.of("v" + filterIndex, ea.getValueBoolean()));
              attributeCodeMap.put(priAttributeCode, attributeCodeEA + ".valueBoolean");
              break;
            case "java.time.LocalDate":
            case "LocalDate":
              filterStringsQ = updateFilterStringsQ(filterStringsQ, filterIndex, ea, condition, attributeCodeEA, "Date");
              valueList.add(Tuple.of("v" + filterIndex, ea.getValueDate()));
              attributeCodeMap.put(priAttributeCode, attributeCodeEA + ".valueDate");
              break;
            case "java.time.LocalDateTime":
            case "LocalDateTime":
              filterStringsQ = updateFilterStringsQ(filterStringsQ, filterIndex, ea, condition, attributeCodeEA, "DateTime");
              valueList.add(Tuple.of("v" + filterIndex, ea.getValueDateTime()));
              attributeCodeMap.put(priAttributeCode, attributeCodeEA + ".valueDate");
              break;
            case "java.time.LocalTime":
            case "LocalTime":
              filterStringsQ = updateFilterStringsQ(filterStringsQ, filterIndex, ea, condition, attributeCodeEA, "Time");
              valueList.add(Tuple.of("v" + filterIndex, ea.getValueTime()));
              attributeCodeMap.put(priAttributeCode, attributeCodeEA + ".valueTime");
              break;
            case "java.lang.String":
            case "String":
            default:
              filterStringsQ = updateFilterStringsQ(filterStringsQ, filterIndex, ea, condition, attributeCodeEA, "String");
              valueList.add(Tuple.of("v" + filterIndex, ea.getValueString()));
              attributeCodeMap.put(priAttributeCode, attributeCodeEA + ".valueString");
          }
          filterIndex++;

        }
      }
    }

    filterStringsQ = fixFilterStringsQ(filterStringsQ, filterIndex);

    Set<String> realms = new HashSet<>();

    realms.add(getRealm());

    realms.add("genny");
    String realmsStr = getRealmsStr(realms);

    orderString = createOrderString(attributeCodeMap, orderList);

    String sql = createSearchSQL("distinct ea.pk.baseEntity", stakeholderCode, sourceStakeholderCode, linkCode, linkValue, linkWeight,
        linkWeightFilter, sourceCode, targetCode, filterStrings, filterStringsQ, orderString, codeFilter, realmsStr);

    Query query = null;

    // Limit column attributes returned using Hibernate filter
    // If empty then return everything
    if (!attributeCodes.isEmpty()) {
      Filter filter = getEntityManager().unwrap(Session.class).enableFilter("filterAttribute");
      filter.setParameterList("attributeCodes", attributeCodes);
    }

    query = getEntityManager().createQuery(sql);

    query.setFirstResult(pageStart).setMaxResults(pageSize);

    // query.setParameter("realms", realms); This would not work

    if (sourceCode != null) {
      query.setParameter("sourceCode", sourceCode);
    }
    if (targetCode != null) {
      query.setParameter("targetCode", targetCode);
    }
    if (linkCode != null) {
      query.setParameter("linkCode", linkCode);
    }
    if (linkValue != null) {
      query.setParameter("linkValue", linkValue);
    }
    if (linkWeight > 0.0) {
      query.setParameter("linkWeight", linkWeight);
    }
    if (stakeholderCode != null) {
      query.setParameter("stakeholderCode", stakeholderCode);
    }
    if (sourceStakeholderCode != null) {
      query.setParameter("sourceStakeholderCode", sourceStakeholderCode);
    }
    //
    for (Tuple2<String, Object> value : valueList) {
      log.debug("Value: " + value._1 + " =: " + value._2);
      if (value._2 instanceof Boolean) {
        Boolean result = (Boolean) value._2;
        query.setParameter(value._1, result);
      } else if (value._2 instanceof LocalDateTime) {
        LocalDateTime result = (LocalDateTime) value._2;
        query.setParameter(value._1, result);
      } else if (value._2 instanceof LocalDate) {
        LocalDate result = (LocalDate) value._2;
        query.setParameter(value._1, result);
      } else if (value._2 instanceof LocalTime) {
        LocalTime result = (LocalTime) value._2;
        query.setParameter(value._1, result);
      } else {
        query.setParameter(value._1, value._2);
      }
    }
    results = query.setHint(QueryHints.HINT_READONLY, true).getResultList();

    // Set simple sort index for frontend to use
    Integer index = 0;
    for (BaseEntity be : results) {
      be.setIndex(index++);
    }
    return results;

  }

  /**
   * @param filterStringsQ
   * @param filterIndex
   * @param ea
   * @param condition
   * @param attributeCodeEA
   * @param typeName
   * @return
   */
  private String updateFilterStringsQ(String filterStringsQ, Integer filterIndex, EntityAttribute ea, String condition,
      String attributeCodeEA, String typeName) {
    if (ea.getAttributeName() == null) {
      filterStringsQ += " and " + attributeCodeEA + ".value" + typeName + "=:v" + filterIndex + " ";
    } else {
      if (condition.equalsIgnoreCase(SearchEntity.Filter.BIT_MASK_POSITIVE.toString())) {
        filterStringsQ += " and (bitwise_and(" + attributeCodeEA + ".value" + typeName + " , :v" + filterIndex + ") <> 0) ";
      } else if (condition.equalsIgnoreCase(SearchEntity.Filter.BIT_MASK_ZERO.toString())) {
        filterStringsQ += " and (bitwise_and(" + attributeCodeEA + ".value" + typeName + " , :v" + filterIndex + ") = 0) ";
      } else {
        filterStringsQ += " and " + attributeCodeEA + ".value" + typeName + " " + condition + " :v" + filterIndex + " ";
      }
    }
    return filterStringsQ;
  }

  /**
   * @param stakeholderCode
   * @param sourceStakeholderCode
   * @param linkCode
   * @param linkValue
   * @param sourceCode
   * @param targetCode
   * @param filterStrings
   * @param filterStringsQ
   * @param orderString
   * @param codeFilter
   * @param realmsStr
   * @return
   */
  private String createSearchSQL(String prefix, String stakeholderCode, String sourceStakeholderCode, String linkCode, String linkValue,
      Double linkWeight, String linkWeightFilter, String sourceCode, String targetCode, String filterStrings, String filterStringsQ,
      String orderString, String codeFilter, String realmsStr) {

    if (!StringUtils.isBlank(filterStrings)) {
      if (!filterStrings.startsWith(",")) {
        filterStrings = "," + filterStrings;
      }
    }

    String sql = "select " + prefix + " from EntityAttribute ea " + (stakeholderCode != null ? " ,EntityEntity ff " : "")
        + (sourceStakeholderCode != null ? " ,EntityEntity gg " : "")
        // + " EntityAttribute ea JOIN be.baseEntityAttributes bea,"
        + (sourceCode != null || targetCode != null || linkCode != null || linkValue != null ? " ,EntityEntity ee  " : "") + filterStrings
        + " where " + " ea.pk.baseEntity.realm in (" + realmsStr + ")  " + codeFilter
        + (linkCode != null ? " and ee.link.attributeCode=:linkCode and " : "")
        + (linkValue != null ? " and ee.link.linkValue=:linkValue and " : "")
        + (linkWeight > 0.0 ? " and ee.link.weight " + linkWeightFilter + " :linkWeight and " : "")
        + (sourceCode != null ? " and ee.pk.source.code=:sourceCode and ee.pk.targetCode=ea.pk.baseEntity.code and " : "")
        + (targetCode != null ? " and ee.pk.targetCode=:targetCode and ee.pk.source.code=ea.pk.baseEntity.code and " : "")
        + (stakeholderCode != null
            ? " and ((ff.pk.targetCode=:stakeholderCode and ff.pk.source.code=ea.pk.baseEntity.code) or (ff.pk.source.code=:stakeholderCode and ff.pk.targetCode=ea.pk.baseEntity.code)  ) "
            : "")
        + (sourceStakeholderCode != null
            ? " and ((gg.pk.targetCode=:sourceStakeholderCode and gg.pk.source.code=ee.pk.source.code) or (gg.pk.targetCode=:sourceStakeholderCode and gg.pk.targetCode=ee.pk.source.code)  ) "
            : "")
        + filterStringsQ + orderString;

    // Ugly
    if (StringUtils.isBlank(orderString)) {
      sql = sql.trim();
      if (sql.endsWith("and")) {
        sql = sql.substring(0, sql.length() - 3);
      }
    } else {
      sql = sql.trim();
      sql = sql.replaceAll("and  order", " order"); // omg this is ugly

    }

    sql = sql.replaceAll("and  and", "and"); // even more ugly...

    log.info("SQL =[" + sql + "]");

    return sql;
  }

  /**
   * @param stakeholderCode
   * @param sourceStakeholderCode
   * @param linkCode
   * @param linkValue
   * @param sourceCode
   * @param targetCode
   * @param filterStrings
   * @param filterStringsQ
   * @param orderString
   * @param codeFilter
   * @param realmsStr
   * @return
   */
  private String createSearchSQL(String prefix, String stakeholderCode, String sourceStakeholderCode, String linkCode, String linkValue,
      Double linkWeight, String linkWeightFilter, String sourceCode, String targetCode, String filterStrings, String filterStringsQ,
      String orderString, List<SearchSettings> filters, String realmsStr) {
    String sql = "select " + prefix + " from " + filterStrings + " " // EntityAttribute ea "
        + ((stakeholderCode != null) ? " ,EntityEntity ff " : "") + ((sourceStakeholderCode != null) ? " ,EntityEntity gg " : "")
        // + " EntityAttribute ea JOIN be.baseEntityAttributes bea,"
        + (((sourceCode != null) || (targetCode != null) || (linkCode != null) || (linkValue != null)) ? " ,EntityEntity ee  " : "")
        + filterStrings + " where " + " ea.pk.baseEntity.realm in (" + realmsStr + ") ";
    if (!filters.isEmpty()) {
      sql += "and (";
      for (SearchSettings filter : filters) {
        String codeFilter = StringUtils.removeStart(filter.codeFilter, " and ");
        sql += "(" + codeFilter + ") or ";
      }
      sql = StringUtils.removeEnd(sql, "or ");
      sql += " ) ";
    }

    sql += ((linkCode != null) ? " and ee.link.attributeCode=:linkCode and " : "")
        + ((linkValue != null) ? " and ee.link.linkValue=:linkValue and " : "")
        + ((linkWeight > 0.0) ? " and ee.link.weight " + linkWeightFilter + " :linkWeight and " : "")
        + ((sourceCode != null) ? " and ee.pk.source.code=:sourceCode and ee.pk.targetCode=ea.pk.baseEntity.code and " : "")
        + ((targetCode != null) ? " and ee.pk.targetCode=:targetCode and ee.pk.source.code=ea.pk.baseEntity.code and " : "")
        + ((stakeholderCode != null)
            ? " and ((ff.pk.targetCode=:stakeholderCode and ff.pk.source.code=ea.pk.baseEntity.code) or (ff.pk.source.code=:stakeholderCode and ff.pk.targetCode=ea.pk.baseEntity.code)  ) "
            : "")
        + ((sourceStakeholderCode != null)
            ? " and ((gg.pk.targetCode=:sourceStakeholderCode and gg.pk.source.code=ee.pk.source.code) or (gg.pk.targetCode=:sourceStakeholderCode and gg.pk.targetCode=ee.pk.source.code)  ) "
            : "")
        + filterStringsQ + orderString;

    // Ugly
    if (StringUtils.isBlank(orderString)) {
      sql = sql.trim();
      if (sql.endsWith("and")) {
        sql = sql.substring(0, sql.length() - 3);
      }
    } else {
      sql = sql.trim();
      sql = sql.replaceAll("and  order", " order"); // omg this is ugly

    }

    sql = sql.replaceAll("and  and", "and"); // even more ugly...

    log.info("SQL =[" + sql + "]");

    return sql;
  }

  /**
   * @param filterStringsQ
   * @param filterIndex
   * @return
   */
  private String fixFilterStringsQ(String filterStringsQ, Integer filterIndex) {
    if (filterIndex > 0) {
      // This is ugly
      filterStringsQ = filterStringsQ.trim();
      if (filterStringsQ.startsWith("and")) {
        filterStringsQ = filterStringsQ.substring("and".length());
      }
      if (!StringUtils.isBlank(filterStringsQ)) {
        filterStringsQ = " and (" + filterStringsQ.substring(0, filterStringsQ.length()) + ")  ";
      }
    }
    return filterStringsQ;
  }

  /**
   * @param ea
   * @return
   */
  private String getSortType(EntityAttribute ea) {
    String sortType;
    switch (ea.getPk().getAttribute().getDataType().getClassName()) {
      case "java.time.LocalTime":
      case "LocalTime":
        sortType = "valueTime";
        break;
      case "java.time.LocalDate":
      case "LocalDate":
        sortType = "valueDate";
        break;
      case "java.time.LocalDateTime":
      case "LocalDateTime":
        sortType = "valueDateTime";
        break;
      case "java.lang.Boolean":
      case "Boolean":
        sortType = "valueBoolean";
        break;
      case "java.lang.Double":
      case "Double":
        sortType = "valueDouble";
        break;
      case "java.lang.Long":
      case "Long":
        sortType = "valueLong";
        break;
      case "java.lang.Integer":
      case "Integer":
        sortType = "valueInteger";
        break;
      case "java.lang.String":
      case "String":
      default:
        sortType = "valueString";
    }
    return sortType;
  }

  private String createOrderString(HashMap<String, String> attributeCodeMap, List<Order> orderList) {
    if (orderList.isEmpty()) {
      return "";
    }
    String ret = " order by ";
    // Sort
    Collections.sort(orderList, new OrderCompare());

    for (Order order : orderList) {
      String sqlAttribute = null;
      if (order.getFieldName().equalsIgnoreCase("PRI_CREATED")) {
        log.info(">>> Reached CreateOrderString with PRI_CREATED ");
        sqlAttribute = " ea.pk.baseEntity.created";
      } else if (order.getFieldName().equalsIgnoreCase("PRI_CODE")) {
        log.info(">>> Reached CreateOrderString with PRI_CODE ");
        sqlAttribute = " ea.pk.baseEntity.code";
      } else if (order.getFieldName().equalsIgnoreCase("PRI_UPDATED")) {
        log.info(">>> Reached CreateOrderString with PRI_UPDATED ");
        sqlAttribute = " ea.pk.baseEntity.updated";
      } else if (order.getFieldName().equalsIgnoreCase("PRI_ID")) {
        log.info(">>> Reached CreateOrderString with PRI_ID ");
        sqlAttribute = " ea.pk.baseEntity.id";
      } else if (order.getFieldName().equalsIgnoreCase("PRI_NAME")) {
        log.info(">>> Reached CreateOrderString with PRI_NAME ");
        sqlAttribute = " ea.pk.baseEntity.name";
      } else {
        sqlAttribute = attributeCodeMap.get(order.getFieldName());
      }
      if (sqlAttribute != null) {
        ret += sqlAttribute + " " + order.getAscdesc() + ",";
      } else {
        log.debug("ERROR - Cannot map " + order.getFieldName());
      }
    }
    ret = ret.substring(0, ret.length() - 1);

    return ret;
  }

  protected EntityManager getEntityManager() {
    return em;
  }

  class Column implements Comparable<Column> {
    private String fieldName;
    private String fieldCode;
    private Double weight;
    private String linkCode;
    private String linkType;

    public Column(final String fieldCode, final String fieldName, final Double weight) {
      this.fieldName = fieldName;
      this.fieldCode = fieldCode;
      this.weight = weight;
    }

    @Override
    public int compareTo(Column compareColumn) {
      return this.weight.compareTo(compareColumn.getWeight());
    }

    /**
     * @return the fieldName
     */
    public String getFieldName() {
      return fieldName;
    }

    /**
     * @return the fieldCode
     */
    public String getFieldCode() {
      return fieldCode;
    }

    /**
     * @return the weight
     */
    public Double getWeight() {
      return weight;
    }

    /**
     * @return the linkCode
     */
    public String getLinkCode() {
      return linkCode;
    }

    /**
     * @param linkCode the linkCode to set
     */
    public void setLinkCode(String linkCode) {
      this.linkCode = linkCode;
    }

    /**
     * @return the linkType
     */
    public String getLinkType() {
      return linkType;
    }

    /**
     * @param linkType the linkType to set
     */
    public void setLinkType(String linkType) {
      this.linkType = linkType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
      return "Column [" + (fieldName != null ? "fieldName=" + fieldName + ", " : "")
          + (fieldCode != null ? "fieldCode=" + fieldCode + ", " : "") + (weight != null ? "weight=" + weight + ", " : "")
          + (linkCode != null ? "linkCode=" + linkCode + ", " : "") + (linkType != null ? "linkType=" + linkType : "") + "]";
    }

  }

  class Order implements Comparable<Order> {
    private String fieldName;
    private String ascdesc;
    private Double weight;

    public Order(final String fieldName, final String ascdesc, final Double weight) {
      this.fieldName = fieldName;
      this.ascdesc = ascdesc;
      this.weight = weight;
    }

    /**
     * @return the fieldName
     */
    public String getFieldName() {
      return fieldName;
    }

    /**
     * @return the ascdesc
     */
    public String getAscdesc() {
      return ascdesc;
    }

    /**
     * @return the weight
     */
    public Double getWeight() {
      return weight;
    }

    @Override
    public int compareTo(Order o) {
      return this.weight.compareTo(o.getWeight());
    }

  }

  class OrderCompare implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
      // write comparison logic here like below , it's just a sample
      return o1.getWeight().compareTo(o2.getWeight());
    }
  }

  class ColumnCompare implements Comparator<Column> {

    @Override
    public int compare(Column o1, Column o2) {
      // write comparison logic here like below , it's just a sample
      return o1.getWeight().compareTo(o2.getWeight());
    }
  }

  public Attribute findAttributeByCode(@NotNull final String code) throws NoResultException {

    return findAttributeByCode(code, getRealm());
  }

  public Attribute findAttributeByCode(@NotNull final String code, @NotNull final String realm) throws NoResultException {
    Attribute result = null;
    // List<Attribute> results = null;
    String cleanCode = code.trim().toUpperCase();
    try {
      result = (Attribute) getEntityManager().createQuery("SELECT a FROM Attribute a where a.code=:code and a.realm=:realmStr")
          .setParameter("realmStr", realm).setParameter("code", cleanCode).getSingleResult();

    } catch (Exception e) {
      // throw new NoResultException("Attribute Code :"+code+" not found in db");
    }
    // if (results == null || results.isEmpty()) {
    // return null;
    // } else {
    // return results.get(0); // return first one for now TODO
    // }
    return result;
  }

  public List<Link> findParentLinks(@NotNull final String targetCode, final String linkCode) {

    final List<Link> eeResults;
    eeResults = getEntityManager().createQuery(

        "SELECT ee.link FROM EntityEntity ee where  ee.pk.targetCode=:targetCode and ee.pk.attribute.code=:linkAttributeCode and ee.pk.source.realm=:realmStr")
        .setParameter("targetCode", targetCode).setParameter("linkAttributeCode", linkCode).setParameter("realmStr", getRealm())
        .getResultList();

    return eeResults;
  }

  public BaseEntity findBaseEntityByCode(@NotNull final String baseEntityCode) throws NoResultException {

    System.out.println("heyeyey ".concat(baseEntityCode));
    return findBaseEntityByCode(baseEntityCode, true);

  }

  public BaseEntity findBaseEntityByCode(@NotNull final String baseEntityCode, boolean includeEntityAttributes) throws NoResultException {
    String realm = "internmatch";
    BaseEntity result = null;

    // log.info("FIND BASEENTITY BY CODE ["+baseEntityCode+"]in realm
    // "+userRealmStr);
    // if (includeEntityAttributes) {

    // String sql = "SELECT be FROM BaseEntity be LEFT JOIN be.baseEntityAttributes ea where
    // be.code=:baseEntityCode and be.realm in (\"genny\",\"" + userRealmStr + "\") "
    // + privacySQL;
    // String sql = "SELECT be FROM BaseEntity be LEFT JOIN be.baseEntityAttributes ea where
    // be.code=:baseEntityCode and be.realm=:realmStr "
    // + privacySQL;
    // log.info("FIND BASEENTITY BY CODE :"+sql);
    try {

      CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
      CriteriaQuery<BaseEntity> query = cb.createQuery(BaseEntity.class);
      Root<BaseEntity> root = query.from(BaseEntity.class);

      query = query.select(root).where(cb.equal(root.get("code"), baseEntityCode.toUpperCase()), cb.equal(root.get("realm"), realm));
      result = getEntityManager().createQuery(query).getSingleResult();

      //
      //
      // Session session = getEntityManager().unwrap(org.hibernate.Session.class);
      // Criteria criteria = session.createCriteria(BaseEntity.class);
      // result = (BaseEntity) criteria.add(Restrictions.eq("code", baseEntityCode.toUpperCase()))
      // .add(Restrictions.eq("realm", realm)).uniqueResult();

    } catch (Exception e) {

      try {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BaseEntity> query = cb.createQuery(BaseEntity.class);
        Root<BaseEntity> root = query.from(BaseEntity.class);

        query = query.select(root).where(cb.equal(root.get("code"), baseEntityCode.toUpperCase()), cb.equal(root.get("realm"), realm));
        List<BaseEntity> results = getEntityManager().createQuery(query).getResultList();
        if (results.isEmpty()) {
          throw new NoResultException("Cannot find " + baseEntityCode + " in db! with realm " + realm);
        }
        result = results.get(0);
      } catch (NoResultException ee) {

        throw new NoResultException("Cannot find " + baseEntityCode + " in db! with realm " + realm);

      }
    }

    // else {
    // try {
    //
    // result = (BaseEntity) getEntityManager()
    // .createQuery(
    // "SELECT be FROM BaseEntity be where be.code=:baseEntityCode and be.realm=:realmStr ")
    //
    // .setParameter("baseEntityCode", baseEntityCode.toUpperCase()).setParameter("realmStr", realm)
    // .getSingleResult();
    //
    // } catch (Exception e) {
    //// if ("GRP_ALL_CONTACTS".equalsIgnoreCase(baseEntityCode)) {
    //// log.info("GRP_ADMIN_JOBS");
    //// }
    //
    // throw new NoResultException("Cannot find " + baseEntityCode + " in db ");
    // }
    //
    // }

    return result;

  }

}
