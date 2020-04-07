package life.genny.qwanda.message;

import java.util.Arrays;

import com.google.gson.annotations.Expose;

public class QCmdViewTableMessage extends QCmdViewMessage {
	
	 static final String CODE = "TABLE_VIEW";
	
	public QCmdViewTableMessage(Object root) {
		super(CODE, root);
	}

	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;


	
	@Expose
	 QCmdTableMessage[] columns;
	// @Expose
	//  QCmdViewMessageAction[] actions;
	
//	@Expose
//	 String contextRoot;

	/**
	 * @return the columns
	 */
	public QCmdTableMessage[] getColumns() {
		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(QCmdTableMessage[] columns) {
		this.columns = columns;
	}
	

	// /**
	//  * @return the actions
	//  */
	// public QCmdViewMessageAction[] getActions() {
	// 	return actions;
	// }

	// /**
	//  * @param actions the actions to set
	//  */
	// public void setActions(QCmdViewMessageAction[] actions) {
	// 	this.actions = actions;
	// }

//	/**
//	 * @return the contextRoot
//	 */
//	public String getContextRoot() {
//		return contextRoot;
//	}
//
//	/**
//	 * @param contextRoot the contextRoot to set
//	 */
//	public void setContextRoot(String contextRoot) {
//		this.contextRoot = contextRoot;
//	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QCmdViewTableMessage [columns=" + Arrays.toString(columns) + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	


}
