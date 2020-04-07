/*
 * (C) Copyright 2017 GADA Technology (http://www.outcome-hub.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Adam Crow
 *     Byron Aguirre
 */


package life.genny.qwanda.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * Group is the  base class for a Group
 * managed in the Qwanda library.
 * A Group object is inherited from a BaseEntity class and supports the core
 * features of an entity that has a set of default attributes.
 * A group may be a source and a target attribute.
 * A group requires a unique code
 * This extra
 * group information includes :
 * <ul>
 * <li>The unique group code e.g. GRP_CONTACTS
 * </ul>
 * <p>

 * <p>
 * 
 * 
 * @author      Adam Crow
 * @author      Byron Aguirre
 * @version     %I%, %G%
 * @since       1.0
 */

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("group")

public class Group extends BaseEntity implements Serializable, BaseEntityIntf {
	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;

	 static final String DEFAULT_CODE_PREFIX = "GRP_";


	
	/**
	  * Constructor.
	  * 
	  * @param none
	  */
	@SuppressWarnings("unused")
	 Group()
	{
		// dummy for hibernate
	}
	
	/**
	  * Constructor.
	  * 
	  * @param aCode The unique code for this Company
	  * @param aName The human readable summary name
	  */
	public Group(final String aCode, final String aName)
	{
		super(getDefaultCodePrefix()+aCode, aName);
 	}
	
	/**
	 * getDefaultCodePrefix This method is overrides the Base class
	 * 
	 * @return the default Code prefix for this class.
	 */
	@Transient
	@XmlTransient
	@JsonIgnore
	static public String getDefaultCodePrefix() {
		return DEFAULT_CODE_PREFIX;
	}

	/**
	 * getCompanyCode
	 * 
	 * @return the given Company Code (without the prefix)
	 */
	@Transient
	@XmlTransient
	@JsonIgnore
	public String getCompanyCode() {
		return getCode().substring(getDefaultCodePrefix().length()).toLowerCase();
	}
	
}
