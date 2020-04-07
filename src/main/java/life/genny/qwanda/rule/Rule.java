/*
 * (C) Copyright 2017 GADA Technology (http://www.outcome-hub.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Contributors: Adam Crow Byron Aguirre
 */

package life.genny.qwanda.rule;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import life.genny.qwanda.CodedEntity;

/**
 * Rule represents a distinct abstract Rule managed in the Qwanda library. An rule may be used
 * directly in processing meaning for an event using inference and induction This rule information
 * includes:
 * <ul>
 * <li>The Human Readable name for this rule (used for summary lists)
 * <li>The unique code for the rule
 * <li>The description of the rule
 * <li>The author of the rule
 * </ul>
 * <p>
 * Attributes represent facts about a target.
 * <p>
 * 
 * 
 * @author Adam Crow
 * @author Byron Aguirre
 * @version %I%, %G%
 * @since 1.0
 */


@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
@Table(name = "rule", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
@Entity
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)


public class Rule extends CodedEntity implements Serializable {

  /**
   * 
   */
   static final long serialVersionUID = 1L;

   static final String DEFAULT_CODE_PREFIX = "RUL_";


  @NotNull
  @Size(min = 0, max = 10000)
  @Expose
  public String rule;


  /**
   * Constructor.
   * 
   * @param none
   */

  public Rule() {
    super();
    // dummy for hibernate
  }

  // assume valid rule
  public Rule(final String aCode, final String aName, final String rule) {
    super(aCode, aName);
    setRule(rule);
  }



  /**
   * @return the rule
   */
  public String getRule() {
    return rule;
  }


  /**
   * @param rule the rule to set
   */
  public void setRule(final String rule) {
    this.rule = rule;
  }


  /**
   * getDefaultCodePrefix This method is overrides the Base class
   * 
   * @return the default Code prefix for this class.
   */
  static public String getDefaultCodePrefix() {
    return DEFAULT_CODE_PREFIX;
  }


  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Rule:" + getCode() + "(" + getName() + ") ";
  }

}
