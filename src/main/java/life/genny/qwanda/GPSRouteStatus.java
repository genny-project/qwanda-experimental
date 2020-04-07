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


package life.genny.qwanda;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.Expose;

import life.genny.qwanda.datatype.LocalDateTimeAdapter;

/**
 * gpsroutestatus is the abstract base class for all gps routestatuss
 * managed in the Qwanda library.
 * An GPS routestatus object is used as a means of storing information
 * about a 2D location to another 2D location on Earth.  This
 * gps information includes:
 * <ul>
 * <li>start location
 * <li>end location
 * <
 * </ul>
 * <p>
 * 
 * <p>
 * 
 * 
 * @author      Adam Crow
 * @author      Byron Aguirre
 * @version     %I%, %G%
 * @since       1.0
 */


public class GPSRouteStatus  implements Serializable {
	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	
	 static DecimalFormat df2 = new DecimalFormat(".##");

	@Embedded
	@Expose
	GPSLocation current;
	
	
	@Expose
	Double distance_m;
	
	@Expose
	Double duration_s;
	
	@Expose
	Double percentage;
		
	/**
	  * Constructor.
	  * 
	  * @param none
	  */
	@SuppressWarnings("unused")
	 GPSRouteStatus()
	{
		// dummy for hibernate
	}

	/**
	  * Constructor.
	  * 
	  * @param targetCode The unique code for the target associated with this Answer
	  * @param aCode The unique code for the attribute associated with this Answer
	  * @param value The associated String value
	  */
	public GPSRouteStatus(final GPSLocation current, final Double distance, final Double duration, final Double percentage)
	{

		this.current = current;
		this.percentage = percentage;
		this.distance_m = distance;
		this.duration_s = duration;
	}

	/**
	 * @return the current
	 */
	public GPSLocation getCurrent() {
		return current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(GPSLocation current) {
		this.current = current;
	}

	/**
	 * @return the distance_m
	 */
	public Double getDistance_m() {
		return distance_m;
	}

	/**
	 * @param distance_m the distance_m to set
	 */
	public void setDistance_m(Double distance_m) {
		this.distance_m = distance_m;
	}

	/**
	 * @return the duration_s
	 */
	public Double getDuration_s() {
		return duration_s;
	}

	/**
	 * @param duration_s the duration_s to set
	 */
	public void setDuration_s(Double duration_s) {
		this.duration_s = duration_s;
	}

	/**
	 * @return the percentage
	 */
	public Double getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GPSRouteStatus [current=" + current + ", distance_m=" + distance_m + ", duration_s=" + duration_s
				+ ", percentage= " + df2.format((100.0*percentage)) + "%]";
	}

	

	
}
