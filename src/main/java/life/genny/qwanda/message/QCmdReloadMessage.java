package life.genny.qwanda.message;

public class QCmdReloadMessage extends QCmdMessage {

     static final String CMD_TYPE = "CMD_RELOAD";
	 static final String CODE = "RELOAD";

	public QCmdReloadMessage() {
		super(CMD_TYPE, CODE);
	}

	 static final long serialVersionUID = 1L;
}
