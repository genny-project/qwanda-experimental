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


package life.genny.qwanda.rule;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * RuleSpreadsheet class handles a standard Drools rule.
 * This information adds:
 * <ul>
 * <li>The RuleSpreadsheet is the Type for the Attribute class
 * </ul>
 * <p>
 * RuleSpreadsheet represent the major way of specifying a Drools rule
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
@DiscriminatorValue("droolsspreadsheet")
public class RuleSpreadsheet extends Rule implements Serializable {
	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	 static final String DEFAULT_CODE_PREFIX = "RXL_";

	
	 byte[] spreadsheet;
	
	/**
	  * Constructor.
	  * 
	  * @param none
	  */
	@SuppressWarnings("unused")
	 RuleSpreadsheet()
	{
		super();
		// dummy for hibernate
	}
	
	/**
	  * Constructor.
	  * 
	  * @param aCode The unique code for this RUle
	  * @param aName The human readable summary name
	  * @param aRule The text of the rule in Drools mven format
	  */
	public RuleSpreadsheet(String aCode, String aName, String filename, byte[] spreadsheet)
	{
		super(aCode, aName, filename);
		this.spreadsheet = spreadsheet;
		
	}

	/**
	 * @return the spreadsheet
	 */
	public byte[] getSpreadsheet() {
		return spreadsheet;
	}

	/**
	 * @param spreadsheet the spreadsheet to set
	 */
	public void setSpreadsheet(byte[] spreadsheet) {
		this.spreadsheet = spreadsheet;
	}
	
	/**
	 * getDefaultCodePrefix This method is overrides the Base class
	 * 
	 * @return the default Code prefix for this class.
	 */
	static public String getDefaultCodePrefix() {
		return DEFAULT_CODE_PREFIX;
	}
	
}
