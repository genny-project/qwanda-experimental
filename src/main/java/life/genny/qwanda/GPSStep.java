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
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.Expose;

import life.genny.qwanda.datatype.LocalDateTimeAdapter;

/**
 * gpsstep is the abstract base class for all gps steps
 * managed in the Qwanda library.
 * An GPS step object is used as a means of storing information
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

@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
@Table(name = "gps_step")

public class GPSStep  implements Serializable {
	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")

	@Basic(optional = false)
	@Column(name = "id", updatable = false, nullable = false)
	Long id;
	 
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@XmlTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gpsleg_id", nullable = false)
	GPSLeg gpsLeg;


	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "latitude", column = @Column(name = "start_latitude")) ,
			 @AttributeOverride(name = "longitude", column = @Column(name = "start_longitude")) })
	@Valid
	@Expose
	GPSLocation start;
	
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "latitude", column = @Column(name = "end_latitude")) ,
		 @AttributeOverride(name = "longitude", column = @Column(name = "end_longitude")) })
	@Valid
	@Expose
	GPSLocation end;
	
	@Expose
	Double distance_m;
	
	@Expose
	Double duration_s;
	
	@Expose
	String htmlInstruction;
	
	/**
	  * Constructor.
	  * 
	  * @param none
	  */
	@SuppressWarnings("unused")
	 GPSStep()
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
	public GPSStep(final GPSLocation start, final GPSLocation end, final Double distance, final Double duration)
	{

		this.start = start;
		this.end = end;
		this.distance_m = distance;
		this.duration_s = duration;
	}

	/**
	 * @return the start
	 */
	public GPSLocation getStart() {
		return start;
	}



	/**
	 * @return the end
	 */
	public GPSLocation getEnd() {
		return end;
	}


	/**
	 * @return the distance
	 */
	public Double getDistance() {
		return distance_m;
	}



	/**
	 * @return the duration
	 */
	public Double getDuration() {
		return duration_s;
	}


	/**
	 * @return the gpsLeg
	 */
	public GPSLeg getGpsLeg() {
		return gpsLeg;
	}

	/**
	 * @param gpsLeg the gpsLeg to set
	 */
	public void setGpsLeg(GPSLeg gpsLeg) {
		this.gpsLeg = gpsLeg;
	}

	/**
	 * @return the htmlInstruction
	 */
	public String getHtmlInstruction() {
		return htmlInstruction;
	}

	/**
	 * @param htmlInstruction the htmlInstruction to set
	 */
	public void setHtmlInstruction(String htmlInstruction) {
		this.htmlInstruction = htmlInstruction;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GPSStep [start=" + start + ", end=" + end + ", distance_m=" + distance_m + ", duration_s=" + duration_s
				+ ", htmlInstruction=" + htmlInstruction + "]";
	}



	
}
