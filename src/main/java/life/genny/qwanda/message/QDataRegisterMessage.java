 package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

public class QDataRegisterMessage {

 	 static final long serialVersionUID = 1L;
 	 static final String DATATYPE_REGISTER = "Register";
 	@Expose
 	 String keycloakUrl = null;
 	@Expose
 	 String realm = null;
 	@Expose
 	 String username = null;
 	@Expose
 	 String firstname = null;
 	@Expose
 	 String lastname = null;
 	@Expose
 	 String password = null;
 	@Expose
 	 String email = null;
 	
 	public QDataRegisterMessage()
 	{

 	}
 	
 	public QDataRegisterMessage(final String keycloakUrl, final String realm, final String username, final String password, final String firstname, final String lastname,final String email) {

 		this.keycloakUrl = keycloakUrl;
 		this.realm = realm;
 		this.username = username.toLowerCase();
 		this.password = password;
 		this.firstname = firstname;
 		this.lastname = lastname;
 		this.email = email;
 	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the datatypeRegister
	 */
	public static String getDatatypeRegister() {
		return DATATYPE_REGISTER;
	}

	/**
	 * @return the keycloakUrl
	 */
	public String getKeycloakUrl() {
		return keycloakUrl;
	}

	/**
	 * @return the realm
	 */
	public String getRealm() {
		return realm;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}


 
 }
