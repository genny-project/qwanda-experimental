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

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Company is the  base class for a Company
 * managed in the Qwanda library.
 * A Company object is inherited from a BaseEntity class and supports the core
 * features of an entity that has a location and contact details.
 * A Company is associated with a set of attributes (and in many cases the associated answers).
 * Some Company fields may duplicate their attribute fields.
 * A Company may be a source and a target attribute.  
 * A Company may have a unique UUID (e.g. Keycloak)
 * This extra
 * Company information includes :
 * <ul>
 * <li>The unique UUID that may be associated with an external Id Management System 
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
@DiscriminatorValue("company")

public class Company extends BaseEntity implements Serializable, BaseEntityIntf {
	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;

	 static final String DEFAULT_CODE_PREFIX = "COM_";


	
	/**
	  * Constructor.
	  * 
	  * @param none
	  */
	@SuppressWarnings("unused")
	 Company()
	{
		// dummy for hibernate
	}
	
	/**
	  * Constructor.
	  * 
	  * @param aCode The unique code for this Company
	  * @param aName The human readable summary name
	  */
	public Company(String aCode, String aName)
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
