package life.genny.qwanda.message;

public class QCmdGPSMessage extends QCmdMessage{
	 static final String CMD_TYPE = "CMD_GPS";
	 static final String CODE = "REQUEST";

	
	public QCmdGPSMessage(String redirectUrl) {
		super(CMD_TYPE, CODE);
	}

	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;

	
}
