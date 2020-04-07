package life.genny.qwanda.payments;

import com.google.gson.annotations.Expose;

public class QPaymentsLocationInfo {
	
	@Expose
	 String addressLine1;
	
	@Expose
	 String addressLine2;
	
	@Expose
	 String city;
	
	@Expose
	 String state;
	
	@Expose
	 String postcode;
	
	@Expose
	 String country;
	
	@Expose
	 String id;

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the location id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsLocationInfo [addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", city="
				+ city + ", state=" + state + ", postcode=" + postcode + ", country=" + country + ", id=" + id + "]";
	}

	/**
	 * 
	 */
	public QPaymentsLocationInfo() {
		super();
	}

	public QPaymentsLocationInfo(String addressLine1, String city, String state, String postcode,
			String country) {
		
		super();
		
		if(addressLine1 != null && !addressLine1.trim().isEmpty()) {
			this.addressLine1 = addressLine1;
		} else {
			throw new IllegalArgumentException("Street address cannot be empty");
		}
		
		if(city != null && !city.trim().isEmpty()) {
			this.city = city;
		} else {
			throw new IllegalArgumentException("City cannot be empty");
		}
		
		if(state != null && !state.trim().isEmpty()) {
			this.state = state;
		} else {
			throw new IllegalArgumentException("State cannot be empty");
		}
		
		if(postcode != null && !postcode.trim().isEmpty()) {
			this.postcode = postcode;
		} else {
			throw new IllegalArgumentException("Postcode cannot be empty");
		}
		
		if(country != null && !country.trim().isEmpty()) {
			this.country = country;
		} else {
			throw new IllegalArgumentException("Country cannot be empty");
		}
		
	}
	

}
