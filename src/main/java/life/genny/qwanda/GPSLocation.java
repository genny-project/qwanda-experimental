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

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import com.google.gson.annotations.Expose;

/**
 * gpslocation is the abstract base class for all gps location
 * managed in the Qwanda library.
 * An GPS location object is used as a means of storing information
 * about a 2D location on Earth.  This
 * gps information includes:
 * <ul>
 * <li>Latitude
 * <li>Longitude
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

@Embeddable
public class GPSLocation  implements  Serializable,Comparable<Object>  {
	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;

	
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
	  * Constructor.
	  * 
	  * @param none
	  */
	@SuppressWarnings("unused")
	 GPSLocation()
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
	public GPSLocation(final Double latitude, final Double longitude)
	{
		this.setLatitude(latitude);
		this.setLongitude(longitude);

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
	public void setLatitude(Double latitude) {
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
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "location " + latitude + ", " + longitude;
	}


	@Override
	public int compareTo(Object anotherItem) throws ClassCastException {
		if (!(anotherItem instanceof GPSLocation))
			throw new ClassCastException("A FlowRatePeriod object expected.");
		GPSLocation anotherLocation = (GPSLocation)anotherItem;
		// Compare by duration
		  return getLatitude().compareTo(anotherLocation.getLatitude());
	
	}		
	
}
