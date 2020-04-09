package life.genny.qwanda.message;


import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.logging.log4j.Logger;
import io.vertx.core.json.JsonObject;
import life.genny.qwanda.attribute.AttributeText;
import life.genny.qwanda.entity.BaseEntity;
import life.genny.qwanda.entity.SearchEntity;
import life.genny.qwanda.entity.SearchEntity.Filter;
import life.genny.qwanda.entity.SearchEntity.Sort;
import life.genny.qwanda.entity.SearchEntity.StringFilter;
import life.genny.qwanda.exception.BadDataException;
import life.genny.qwandautils.QwandaJsonUtils;

public class QSearchEntityMessage extends QDataBaseEntityMessage  {

	 static final long serialVersionUID = 1L;
	
	/**
	 * Stores logger object.
	 */
	protected static final Logger log = org.apache.logging.log4j.LogManager
			.getLogger(MethodHandles.lookup().lookupClass().getCanonicalName());
	
	public enum LinkType {
		PARENT,
		CHILD
	}

	 SearchEntity parent ;
	
	 static final String DATATYPE_SEARCHENTITY =  QSearchEntityMessage.class.getSimpleName();

	Double colIndex = 1.0;
	Double sortIndex = 1.0;
	
	
	 QSearchEntityMessage() {}
	
	 QSearchEntityMessage(final SearchEntity searchSettings, final SearchEntity[] searches) {
		super(searches);
		this.setData_type(DATATYPE_SEARCHENTITY);
		this.parent = searchSettings;

	}

	/*
	 * This method allows to set the filter for the integer value in the search 
	 * @param attributeCode - the attributeCode which holds integer value where we apply the filter
	 * @param filterType - type of the filter
	 * @param value - filter against (search for)  this value
	 */
	public QSearchEntityMessage addSearchOr(SearchEntity searchEntity)
	{
		List<BaseEntity> searchItems = Arrays.asList(this.getItems());
		searchItems.add(searchEntity);
		this.setItems(searchItems.toArray(new BaseEntity[0]));
		return this;
	}

	/**
	 * @return the parent
	 */
	public SearchEntity getParent() {
		return parent;
	}



	/**
	 * @param parent the parent to set
	 */
	public void setParent(SearchEntity parent) {
		this.parent = parent;
	}

	/*
	 * This method allows to add the attributes to the SearchEntity that is required in the result 
	 * BaseEntities
	 */
	public QSearchEntityMessage addColumn(final String attributeCode, final String columnName)
	{
		AttributeText attributeColumn = new AttributeText("COL_"+attributeCode, columnName);
		try {
			parent.addAttribute(attributeColumn, colIndex);
			colIndex += 1.0;
		} catch (BadDataException e) {
			log.error("Bad Column Initialisation");
		}
		return this;
	}
	
	/*
	 * This method allows to add sorting to the attributes of the search results
	 * It can either sort in ascending or descending order
	 */
	public QSearchEntityMessage addSort(final String attributeCode, final String sortHelpText, final Sort sortType)
	{
		AttributeText attributeSort = new AttributeText("SRT_"+attributeCode, sortHelpText);
		try {
			parent.addAttribute(attributeSort, sortIndex, sortType.toString());
			sortIndex += 1.0;
			
		} catch (BadDataException e) {
			log.error("Bad Sort Initialisation");
		}
		
		return this;
	}

    public static class Builder {

         String code; //This is important, so we'll pass it to the constructor.

         String name;
        
         SearchEntity searchEntity = new SearchEntity("SBE_"+UUID.randomUUID().toString().substring(0, 20),"New Search");

         List<SearchEntity> searchEntityList = new CopyOnWriteArrayList<SearchEntity>();
         List<QSearchEntityMessage> searchEntityMessageList = new CopyOnWriteArrayList<QSearchEntityMessage>();

 
        public Builder(final String code, final String name) {

            this.searchEntity = new SearchEntity(code,name);
            

        }

        public Builder pageSize(int size){
        	if (size > 0) {
            this.searchEntity.setPageSize(size);
        	} else {
        		log.error("Size must be > 0");
        	}

            return this;  //By returning the builder each time, we can create a fluent interface.

        }

        public Builder pageStart(int index){
        	if (index > 0) {
        		 this.searchEntity.setPageStart(index);
        	} else {
        		log.error("Index must be > 0");
        	}
            return this;  //By returning the builder each time, we can create a fluent interface.
        }

     	/*
    	 * This method allows to add filters
     	 */
    	public Builder filter(final String attributeCode, final Filter filterType, final Integer value)
    	{
    		QSearchEntityMessage tmp = new QSearchEntityMessage();
    		SearchEntity se = new SearchEntity();
    		se.addFilter(attributeCode, filterType,value); 
    		tmp.addSearchOr(se);
        	this.searchEntityMessageList.add(tmp);      	
            return this;  //By returning the builder each time, we can create a fluent interface.

    		
    	}   	

     	/*
    	 * This method allows to add filters
     	 */
    	public Builder filter(final String attributeCode, final Filter filterType, final Long value)
    	{
    		QSearchEntityMessage tmp = new QSearchEntityMessage();
    		SearchEntity se = new SearchEntity();
    		se.addFilter(attributeCode, filterType,value); 
    		tmp.addSearchOr(se);
        	this.searchEntityMessageList.add(tmp);      	
            return this;  //By returning the builder each time, we can create a fluent interface.
  		
    	}   	

     	/*
    	 * This method allows to add filters
     	 */
    	public Builder filter(final String attributeCode, final Filter filterType, final LocalDateTime value)
    	{
    		QSearchEntityMessage tmp = new QSearchEntityMessage();
    		SearchEntity se = new SearchEntity();
    		se.addFilter(attributeCode, filterType,value); 
    		tmp.addSearchOr(se);
        	this.searchEntityMessageList.add(tmp);      	
            return this;  //By returning the builder each time, we can create a fluent interface.
    	}   	

     	/*
    	 * This method allows to add filters
     	 */
    	public Builder filter(final String attributeCode, final Boolean value)
    	{
    		QSearchEntityMessage tmp = new QSearchEntityMessage();
    		SearchEntity se = new SearchEntity();
    		se.addFilter(attributeCode,value); 
    		tmp.addSearchOr(se);
        	this.searchEntityMessageList.add(tmp);      	
            return this;  //By returning the builder each time, we can create a fluent interface.
    	}  
 
     	/*
    	 * This method allows to add filters
     	 */
    	public Builder filter(final String attributeCode, final StringFilter filterType, final String value)
    	{
    		QSearchEntityMessage tmp = new QSearchEntityMessage();
    		SearchEntity se = new SearchEntity();
    		se.addFilter(attributeCode, filterType,value); 
    		tmp.addSearchOr(se);
        	this.searchEntityMessageList.add(tmp);      	
            return this;  //By returning the builder each time, we can create a fluent interface.
    	}  
    	/*
    	 * This method allows to add the attributes to the SearchEntity that is required in the result 
    	 * BaseEntities
    	 */

        public Builder column(final String attributeCode, final String columnName) {
        	this.searchEntity.addColumn(attributeCode, columnName);      	
            return this;  //By returning the builder each time, we can create a fluent interface.
        }
 
    	/*
    	 * This method allows to add an associated baseentity attribute to the SearchEntity that is required in the result 
    	 * BaseEntities
    	 */

        public Builder column(final String linkCode, final LinkType linkType, final String attributeCode, final String columnName) {
        	this.searchEntity.addAssociatedColumn(linkCode+"@"+linkType.toString()+"@"+attributeCode, columnName);      	
            return this;  //By returning the builder each time, we can create a fluent interface.
        }

    	/*
    	 * This method allows to add sorting to the attributes of the search results
    	 * It can either sort in ascending or descending order
    	 */
    	public Builder sort(final String attributeCode, final String sortHelpText, final SearchEntity.Sort sortType)
    	{
        	this.searchEntity.addSort(attributeCode, sortHelpText,sortType);      	
            return this;  //By returning the builder each time, we can create a fluent interface.
   		
    	}
  
    	/*
    	 * This method allows to set the stakeholder/user code to the search. It will search for the BaseEntites 
    	 * that the given user is stakeholder of.
    	 * @param stakeholderCode - userCode
    	 */
    	public Builder stakeholderCode(final String stakeholderCode)
    	{
    		AttributeText attribute = new AttributeText("SCH_STAKEHOLDER_CODE", "Stakeholder");
    		try {
    			this.searchEntity.addAttribute(attribute, 1.0, stakeholderCode);
    		} catch (BadDataException e) {
    			log.error("Bad Stakeholder");
    		}
    		
    		return this;
    	}
    	
    	/*
    	 * This method allows to set the stakeholder/user code to the parent/source Basentity involved in the search. 
    	 * It will search for the BaseEntites under the give source BE that the given user is stakeholder of.
    	 * @param sourceStakeholderCode - userCode
    	 */
    	public Builder sourceStakeholderCode(final String sourceStakeholderCode)
    	{
    		AttributeText attribute = new AttributeText("SCH_SOURCE_STAKEHOLDER_CODE", "SourceStakeholder");
    		try {
    			this.searchEntity.addAttribute(attribute, 1.0, sourceStakeholderCode);
    		} catch (BadDataException e) {
    			log.error("Bad Source Stakeholder");
    		}
    		
    		return this;
    	}
    	
    	/*
    	 * This method allows to set the stakeholder/user code to the parent/source Basentity involved in the search. 
    	 * It will search for the BaseEntites under the give source BE that the given user is stakeholder of.
    	 * @param sourceStakeholderCode - userCode
    	 */
    	public Builder linkCode(final String linkCode)
    	{
    		AttributeText attribute = new AttributeText("SCH_LINK_CODE", "LinkCode");
    		try {
    			this.searchEntity.addAttribute(attribute, 1.0, linkCode);
    		} catch (BadDataException e) {
    			log.error("Bad Stakeholder");
    		}
    		
    		return this;
    	}
    	
    	/*
    	 * This method allows to set the link value the result of the search. 
    	 * @param linkValue - linkValue of the sourceCode to the results (BaseEntities) of the search
    	 */
    	public Builder linkValue(final String linkValue)
    	{
    		AttributeText attribute = new AttributeText("SCH_LINK_VALUE", "LinkValue");
    		try {
    			this.searchEntity.addAttribute(attribute, 1.0, linkValue);
    		} catch (BadDataException e) {
    			log.error("Bad Link Value");
    		}
    		
    		return this;
    	}
    	
    	public Builder sourceCode(final String sourceCode)
    	{
    		AttributeText attribute = new AttributeText("SCH_SOURCE_CODE", "SourceCode");
    		try {
    			this.searchEntity.addAttribute(attribute, 1.0, sourceCode);
    		} catch (BadDataException e) {
    			log.error("Bad SourceCode");
    		}
    		return this;
    	}
    	
    	public Builder targetCode(final String targetCode)
    	{
    		AttributeText attribute = new AttributeText("SCH_TARGET_CODE", "TargetCode");
    		try {
    			this.searchEntity.addAttribute(attribute, 1.0, targetCode);
    		} catch (BadDataException e) {
    			log.error("Bad Target Code");
    		}
    		
    		return this;
    	}    	
    	
       	/*
    	 * This method allows to add searches that are ORd
     	 */
    	public Builder searchOr(final SearchEntity searchEntity)
    	{
        	this.searchEntityList.add(searchEntity);      	
            return this;  //By returning the builder each time, we can create a fluent interface.
   		
    	}   
    	
      	/*
    	 * This method allows to add searches that are recursively ORd
     	 */
    	public Builder searchOr(final QSearchEntityMessage searchEntity)
    	{
        	this.searchEntityMessageList.add(searchEntity);      	
            return this;  //By returning the builder each time, we can create a fluent interface.
   		
    	}   	

    	
        public QSearchEntityMessage build(){

            //Here we create the actual searchEntityMessage object, which is always in a fully initialised state when it's returned.
        	SearchEntity[] searchEntitys = searchEntityList.toArray(new SearchEntity[searchEntityList.size()]);
        	
            QSearchEntityMessage searchEntityMessage = new QSearchEntityMessage(searchEntity,searchEntitys);  //Since the builder is in the QSearch class, we can invoke its  constructor.

 
            return searchEntityMessage;

        }

    }
    
    public static Map<String,String> getAssociationCodes(final String columnAttributeCode) {
    	Map<String,String> associatedCodes = new HashMap<String,String>();
    	
    	if (columnAttributeCode.startsWith("CAL_")) {
    		// associated attribute
    		String[] splitData = columnAttributeCode.substring("CAL_".length()).split("@");
    		associatedCodes.put("LINK_CODE", splitData[0]);
    		associatedCodes.put("LINK_TYPE", splitData[1]);
    		associatedCodes.put("ATTRIBUTE_CODE", splitData[2]);
    	} else {
    		// normal
    		String[] splitData = columnAttributeCode.split("@");
    		associatedCodes.put("ATTRIBUTE_CODE", splitData[0]);
    	}
    	
    	return associatedCodes;
    }
    
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JsonObject.mapFrom(this).toString();
	}
	
}
