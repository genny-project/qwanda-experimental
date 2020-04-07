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

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

import life.genny.qwanda.datatype.LocalDateTimeAdapter;

/**
 * gps is the abstract base class for all gps
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

public class GPS  implements Serializable {
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

	
	/**
	A field that stores the latitude.
	<p>
	*/
	@NotNull
	@Column(name = "latitude", updatable = false, nullable = false)
	@Expose
	 Double latitude;

    /**
    A field that stores the longitude.
    <p>
    */
    @NotNull
    @Column(name = "longitude", updatable = false, nullable = false)
    @Expose
     Double longitude;

    /**
    A field that stores the timestamp.
    <p>
    */
    @NotNull
    @Size(max = 32)
    @Column(name = "timestamp", updatable = false, nullable = false)
    @Expose
     String timestamp;

    /**
    A field that stores the accuracy.
    <p>
    */
    @NotNull
    @Column(name = "accuracy", updatable = false, nullable = true)
    @Expose
     Double accuracy;

    /**
    A field that stores the bearing/heading.
    <p>
    */
    @NotNull
    @Column(name = "bearing", updatable = false, nullable = true)
    @Expose
     Double bearing;

    /**
    A field that stores the altitude.
    <p>
    */
    @NotNull
    @Column(name = "altitude", updatable = false, nullable = true)
    @Expose
     Double altitude;
    
    /**
    A field that stores the altitude accuracy.
    <p>
    */
    @NotNull
    @Column(name = "altitude_accuracy", updatable = false, nullable = true)
    @Expose
     Double altitude_accuracy;
    
    /**
    A field that stores the speed.
    <p>
    */
    @NotNull
    @Column(name = "speed", updatable = true, nullable = true)
    @Expose
     Double speed;
    
	/**
	A field that stores the human readable targetcode associated with this answer.
	<p>
	*/
	@NotNull
	@Size(max = 64)
	@Column(name = "targetcode", updatable = true, nullable = true)
	@Expose
	 String targetCode;

    @Column(name = "targetid", updatable = true, nullable = true)
    @Expose
	 Long targetId;

	
	/**
	  * Constructor.
	  * 
	  * @param none
	  */
	@SuppressWarnings("unused")
	 GPS()
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
	public GPS(final String targetCode,final String latitude, final String longitude)
	{
		this.targetCode = targetCode;
		this.setLatitude(Double.parseDouble(latitude));
		this.setLongitude(Double.parseDouble(longitude));
		autocreateCreated();
	}

	
	/**
	  * Constructor.
	  * 
	  * @param targetCode The unique code for the target associated with this Answer
	  * @param aCode The unique code for the attribute associated with this Answer
	  * @param value The associated String value
	  */
	public GPS(final String targetCode,final Double latitude, final Double longitude)
	{
		this.targetCode = targetCode;
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		autocreateCreated();
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
	 * @return the targetCode
	 */
	public String getTargetCode() {
		return targetCode;
	}

	/**
	 * @param targetCode the targetCode to set
	 */
	public void setTargetCode(final String targetCode) {
		this.targetCode = targetCode;
	}



  /**
   * @return the latitude
   */
  public Double getLatitude() {
    return latitude;
  }



  /**
   * @param latitude the latitude to set
   */
   void setLatitude(final Double latitude) {
    this.latitude = latitude;
  }



  /**
   * @return the longitude
   */
  public Double getLongitude() {
    return longitude;
  }



  /**
   * @param longitude the longitude to set
   */
   void setLongitude(final Double longitude) {
    this.longitude = longitude;
  }



  /**
   * @return the bearing
   */
  public Double getBearing() {
    return bearing;
  }



  /**
   * @param bearing the bearing to set
   */
   void setBearing(final Double bearing) {
    this.bearing = bearing;
  }



  /**
 * @return the timestamp
 */
public String getTimestamp() {
	return timestamp;
}

/**
 * @param timestamp the timestamp to set
 */
public void setTimestamp(String timestamp) {
	this.timestamp = timestamp;
}

/**
 * @return the accuracy
 */
public Double getAccuracy() {
	return accuracy;
}

/**
 * @param accuracy the accuracy to set
 */
public void setAccuracy(Double accuracy) {
	this.accuracy = accuracy;
}

/**
 * @return the altitude
 */
public Double getAltitude() {
	return altitude;
}

/**
 * @param altitude the altitude to set
 */
public void setAltitude(Double altitude) {
	this.altitude = altitude;
}

/**
 * @return the altitude_accuracy
 */
public Double getAltitude_accuracy() {
	return altitude_accuracy;
}

/**
 * @param altitude_accuracy the altitude_accuracy to set
 */
public void setAltitude_accuracy(Double altitude_accuracy) {
	this.altitude_accuracy = altitude_accuracy;
}

/**
 * @return the speed
 */
public Double getSpeed() {
	return speed;
}

/**
 * @param speed the speed to set
 */
public void setSpeed(Double speed) {
	this.speed = speed;
}

/**
   * @return the targetId
   */
  public Long getTargetId() {
    return targetId;
  }



  /**
   * @param targetId the targetId to set
   */
  public void setTargetId(final Long targetId) {
    this.targetId = targetId;
  }



  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "GPS [" + created + ", lat/long=" + latitude + ","
        + longitude + ", bearing=" + bearing + ", targetCode=" + targetCode + "]";
  }


	
}
