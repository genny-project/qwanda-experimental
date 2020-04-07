package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

public class QCmdRedirectMessage extends QCmdMessage{
	 static final String CMD_TYPE = "CMD_REDIRECT";
	 static final String CODE = "REDIRECT";

	@Expose
	 String redirect_url = "";
	
	public QCmdRedirectMessage(String redirectUrl) {
		super(CMD_TYPE, CODE);
		this.redirect_url = redirectUrl;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}
	
}
