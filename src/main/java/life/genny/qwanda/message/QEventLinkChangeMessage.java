package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.Link;

public class QEventLinkChangeMessage extends QEventMessage {
	  
	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	
	 static final String EVENT_TYPE_LINK_CHANGE = "EVT_LINK_CHANGE";
	@Expose
	   Link link;
	@Expose
	   Link oldLink;
	
	public QEventLinkChangeMessage(final Link link, final Link oldLink, String token) {
		super(EVENT_TYPE_LINK_CHANGE, "EVT_LINK_CHANGE");
		this.link = link;
		this.oldLink = oldLink;
		setToken(token);
	}

	/**
	 * @return the link
	 */
	public Link getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(Link link) {
		this.link = link;
	}

	/**
	 * @return the oldLink
	 */
	public Link getOldLink() {
		return oldLink;
	}

	/**
	 * @param oldLink the oldLink to set
	 */
	public void setOldLink(Link oldLink) {
		this.oldLink = oldLink;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QEventLinkChangeMessage [link=" + link + ", oldLink=" + oldLink + "]";
	}


	
	
}
