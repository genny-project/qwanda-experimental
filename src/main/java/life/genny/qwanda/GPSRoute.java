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
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.Expose;

import life.genny.qwanda.datatype.LocalDateTimeAdapter;

/**
 * gpsRoute is the  base class for all gps ROutes
 * managed in the Qwanda library.
 * An GPS ROute  object is used as a means of storing information
 * from a source about a route.  This
 * route information includes:
 * <ul>
 * <li>distance m
 * <li>duration s
 * <li>start address
 * <li>start location
 * <li>end address
 * <li>end location
 * <li>The time at which the route was created
 * <li>GPSLegList

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
@Table(name = "route")
@Entity
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)

public class GPSRoute  implements Serializable {
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

	
	
	/**
	 * Stores the Created UMT DateTime that this object was created
	 */
//	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @Column(name = "created")
    @Expose
     LocalDateTime created;

	
	@ElementCollection(fetch = FetchType.LAZY)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "gpsRoute")
	@Expose
	 List<GPSLeg> legList;

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
	 GPSRoute()
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
	public GPSRoute(final GPSLocation start, final GPSLocation end)
	{
		this(start,end,0.0,0.0);
	}
	/**
	  * Constructor.
	  * 
	  * @param targetCode The unique code for the target associated with this Answer
	  * @param aCode The unique code for the attribute associated with this Answer
	  * @param value The associated String value
	  */
	public GPSRoute(final GPSLocation start, final GPSLocation end, final Double distance_m, final Double duration_s)
	{
		this.start = start;
		this.end = end;
		this.duration_s = duration_s;
		this.distance_m = distance_m;
		autocreateCreated();
	}
	

	public void add(final GPSLeg gpsLeg) {
		if (legList == null) {
			legList = new CopyOnWriteArrayList<GPSLeg>();
		}
		gpsLeg.setGpsRoute(this);
		legList.add(gpsLeg);
		this.distance_m += gpsLeg.getDistance_m();
		this.duration_s += gpsLeg.getDuration_s();
	}

    /**
	 * @return the created
	 */
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	public LocalDateTime getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(final LocalDateTime created) {
		this.created = created;
	}


    
    @PrePersist
    public void autocreateCreated() {
    	if (getCreated()==null)
    		setCreated( LocalDateTime.now(ZoneId.of("Z")));
    }

    
	@Transient
	@JsonIgnore
	public Date getCreatedDate()
	{
		final Date out = Date.from(created.atZone(ZoneId.systemDefault()).toInstant());
		return out;
	}
	


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the legList
	 */
	public List<GPSLeg> getLegList() {
		return legList;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GPSRoute [start=" + start + ", end=" + end + ", distance_m=" + distance_m + ", duration_s=" + duration_s
				+ "]";
	}


	
}
