package life.genny.qwanda.message;

import java.util.List;

import com.google.gson.annotations.Expose;

public class QBaseMSGMessage {
	
	public QBaseMSGMessage(String source, String target, String priority, String subject, String msgMessageData,
			List<QBaseMSGAttachment> attachmentList) {
		super();
		this.source = source;
		this.target = target;
		this.priority = priority;
		this.subject = subject;
		this.msgMessageData = msgMessageData;
		this.attachmentList = attachmentList;
	}
	public QBaseMSGMessage() {
		super();
	}


	@Expose
	 String source;
	@Expose
	 String target;
	@Expose
	 String priority;
	@Expose
	 String subject;
	@Expose
	 String msgMessageData;
	/*@Expose
	 String[] attachments;*/
	
	@Expose
	 List<QBaseMSGAttachment> attachmentList; 
	
	 String token;
	
	 QBaseMSGMessageType msgMessageType;
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public QBaseMSGMessageType getMsgMessageType() {
		return msgMessageType;
	}
	public void setMsgMessageType(QBaseMSGMessageType msgMessageType) {
		this.msgMessageType = msgMessageType;
	}
	
	public String getMsgMessageData() {
		return msgMessageData;
	}
	public void setMsgMessageData(String msgMessageData) {
		this.msgMessageData = msgMessageData;
	}
	
	/*public String[] getAttachments() {
		return attachments;
	}
	public void setAttachments(String[] attachments) {
		this.attachments = attachments;
	}*/
	
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	
	/**
	 * @return the attachmentList
	 */
	public List<QBaseMSGAttachment> getAttachmentList() {
		return attachmentList;
	}
	/**
	 * @param attachmentList the attachmentList to set
	 */
	public void setAttachmentList(List<QBaseMSGAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QBaseMSGMessage [source=" + source + ", target=" + target + ", priority=" + priority + ", subject="
				+ subject + ", msgMessageData=" + msgMessageData + ", attachmentList=" + attachmentList + ", token="
				+ token + ", msgMessageType=" + msgMessageType + "]";
	}
	
}
