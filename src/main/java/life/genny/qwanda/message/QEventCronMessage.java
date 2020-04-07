package life.genny.qwanda.message;

public class QEventCronMessage extends QEventMessage {


	 static final long serialVersionUID = 1L;

	 static final String EVENT_TYPE_CRON = "CRON_EVENT";
   String cronCode;

	public QEventCronMessage(String btnCode) {
		super(EVENT_TYPE_CRON, btnCode);
	}

	public String getCronCode() {
		return this.cronCode;
	}

	public void setCronCode(String cronCode) {
		this.cronCode = cronCode;
	}
}
