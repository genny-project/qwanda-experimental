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
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.Expose;

/**
 * gpsleg is the abstract base class for all gps legs
 * managed in the Qwanda library.
 * An GPS object is used as a means of storing information
 * from a source about a target GPS.  This
 * gps information includes:
 * <ul>
 * <li>Longitude
 * <li>The time at which the gps was created
 * <li>Latitude
 * <li>Bearing
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

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
@Table(name = "gps")
@Entity
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)

public class GPSLeg  implements Serializable {
	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;

	/**
	 * Stores the hibernate generated Id value for this object
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", updatable = false, nullable = false)
	 Long id;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@XmlTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gpsRoute_id", nullable = false)
	GPSRoute gpsRoute;


	
	
	@ElementCollection(fetch = FetchType.LAZY)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "gpsLeg")
	@Expose
	 List<GPSStep> stepList;

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

	
	/**
	  * Constructor.
	  * 
	  * @param none
	  */
	@SuppressWarnings("unused")
	 GPSLeg()
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
	public GPSLeg(final GPSLocation start, final GPSLocation end, final Double distance_m, final Double duration_s)
	{
		this.start = start;
		this.end = end;
		this.duration_s = duration_s;
		this.distance_m = distance_m;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the stepList
	 */
	public List<GPSStep> getStepList() {
		return stepList;
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
	 * @return the distance_m
	 */
	public Double getDistance_m() {
		return distance_m;
	}

	/**
	 * @return the duration_s
	 */
	public Double getDuration_s() {
		return duration_s;
	}

	/**
	 * @return the gpsRoute
	 */
	public GPSRoute getGpsRoute() {
		return gpsRoute;
	}

	/**
	 * @param gpsRoute the gpsRoute to set
	 */
	public void setGpsRoute(GPSRoute gpsRoute) {
		this.gpsRoute = gpsRoute;
	}

	
	public void add(final GPSStep gpsStep) {
		if (stepList == null) {
			stepList = new CopyOnWriteArrayList<GPSStep>();
		}
		gpsStep.setGpsLeg(this);
		stepList.add(gpsStep);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GPSLeg [start=" + start + ", end=" + end + ", distance_m=" + distance_m + ", duration_s=" + duration_s
				+ "]";
	}

	
}
