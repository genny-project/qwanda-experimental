package life.genny.qwanda.message;

public class QCmdViewFormMessage extends QCmdViewMessage {

	 static final String CODE = "FORM_VIEW";
	
	public QCmdViewFormMessage(String root) {
		super(CODE, root);
	}

	 static final long serialVersionUID = 1L;
}
