package lifeee.gennyyy.experimental;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import com.google.gson.Gson;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.attribute.AttributeText;
import life.genny.qwanda.attribute.EntityAttribute;
import life.genny.qwanda.entity.BaseEntity;
import life.genny.qwanda.exception.BadDataException;
import life.genny.qwanda.message.QDataBaseEntityMessage;

@Path("/search")
public class SearchExperimental {

  @Inject
  public BaseEntityService service;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return "hello";
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/greeting/{name}")
  public String greeting(@PathParam String name) {
      System.out.println(name);
      BaseEntity findBaseEntityByCode = service.findBaseEntityByCode(name);
      return findBaseEntityByCode.toString();
  }
  
  @POST
  @Consumes("application/json")
  @Path("/baseentitys/search")
  @Produces("application/json")
  @Transactional
  public QDataBaseEntityMessage findBySearchBE(final BaseEntity searchBE) {

          String stakeHolderCode = null;

          Attribute stakeHolderAttribute = new AttributeText("SCH_STAKEHOLDER_CODE", "StakeholderCode");
          Attribute sourceStakeHolderAttribute = new AttributeText("SCH_SOURCE_STAKEHOLDER_CODE", "SourceStakeholderCode");
          try {

              if(searchBE.containsEntityAttribute("SCH_SOURCE_STAKEHOLDER_CODE")) {
                  searchBE.addAttribute(sourceStakeHolderAttribute, new Double(1.0),
                          stakeHolderCode);
              }else {
                  searchBE.addAttribute(stakeHolderAttribute, new Double(1.0),
                          stakeHolderCode);
              }

          } catch (BadDataException e) {
            return new QDataBaseEntityMessage();
          }
		Long startTime = System.nanoTime();
          List<BaseEntity> results = service.findBySearchBE(searchBE);
          Long total = -1L;

          System.out.println("###########################################################");

          System.out.println("printingn all the results ");
          
          results.stream().forEach(System.out::println);
          
          try {
            total = service.findBySearchBECount(searchBE);
    		System.out.println("search count takes us to " + ((System.nanoTime() - startTime) / 1e6) + "ms");

          } catch (Exception e) {
              // TODO Auto-generated catch block
              total = -1L;
          }

          BaseEntity[] beArr = null;
          if (!(results==null||results.isEmpty())) {
              beArr = new BaseEntity[results.size()];
              beArr = results.toArray(beArr);
          } else {
          System.out.println("Looks empty");
              beArr = new BaseEntity[0];
              total = 0L;
          }

          // Override returned parent code if it is supplied, otherwise use search BE code
          String parentCode = searchBE.getCode();
          Optional<EntityAttribute> parentAttribute = searchBE.findEntityAttribute("SCH_SOURCE_CODE");
          if (parentAttribute.isPresent()) {
          System.out.println("###3234########################################################");
              parentCode = parentAttribute.get().getValueString();
          }
          QDataBaseEntityMessage msg = new QDataBaseEntityMessage(beArr, parentCode, null);
          msg.setTotal(total);
          String json = JsonUtils.toJson(msg);
          return msg;


  }

}
