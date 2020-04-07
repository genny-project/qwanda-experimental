package life.genny.qwanda.message;

import java.util.Arrays;

import com.google.gson.annotations.Expose;

public class QCmdViewMessage extends QCmdMessage {
	
	 static final String CMD_TYPE = "CMD_VIEW";

	@Expose
	 Object root;	
	@Expose
	 Object contextRoot;	
	@Expose
	 String view_type;
	@Expose
	 Object data;
	@Expose
	 Boolean isPopup;
	@Expose 
	 QCmdViewMessageAction[] actions;
	
	/* used for detail-view */
	@Expose
	 String layoutCode;
	
	/* used for detail-view */
	@Expose
	 String parentCode;
	
	
	/* used for list view, bucket view */
	public QCmdViewMessage(final String view_type, final Object root) {
		super(CMD_TYPE, view_type);
		setRoot(root);
		setView_type(view_type);
	}
	
	/* used for split view */
	public QCmdViewMessage(final String view_type, final Object root, final Object data) {
		super(CMD_TYPE, view_type);
		setRoot(root);
		setView_type(view_type);
		setData(data);
	}
	
	/* used for detail view */
	public QCmdViewMessage(final String view_type, final Object root, final String layoutCode, final String parentCode) {
		super(CMD_TYPE, view_type);
		setRoot(root);
		setView_type(view_type);
		setLayoutCode(layoutCode);
		setParentCode(parentCode);
	} 

	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	public Object getRoot() {
		return root;
	}

	public void setRoot(Object root) {
		this.root = root;
	}
	
	
	/**
	 * @return the contextRoot
	 */
	public Object getContextRoot() {
		return contextRoot;
	}

	/**
	 * @param contextRoot the contextRoot to set
	 */
	public void setContextRoot(Object contextRoot) {
		this.contextRoot = contextRoot;
	}

	public String getView_type() {
		return view_type;
	}

	public void setView_type(String view_type) {
		this.view_type = view_type;
	}

	public Boolean getIsPopup() {
		return this.isPopup;
	}

	public void setIsPopup(Boolean isPopup) {
		this.isPopup = isPopup;
		if(isPopup) {
			this.setCmd_type("CMD_POPUP");
		}
		else {
			this.setCmd_type("CMD_VIEW");
		}
	}
	
	
	/**
	 * @return the actions
	 */
	public QCmdViewMessageAction[] getActions() {
		return actions;
	}

	/**
	 * @param actions the actions to set
	 */
	public void setActions(QCmdViewMessageAction[] actions) {
		this.actions = actions;
	}

	/**
	 * @return the layoutCode
	 */
	public String getLayoutCode() {
		return layoutCode;
	}

	/**
	 * @param layoutCode the layoutCode to set
	 */
	public void setLayoutCode(String layoutCode) {
		this.layoutCode = layoutCode;
	}

	/**
	 * @return the parentCode
	 */
	public String getParentCode() {
		return parentCode;
	}

	/**
	 * @param parentCode the parentCode to set
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QCmdViewMessage [root=" + root + ", view_type=" + view_type + ", data=" + data + ", isPopup=" + isPopup
				+ ", actions=" + Arrays.toString(actions) + ", layoutCode=" + layoutCode + ", parentCode=" + parentCode
				+ "]";
		
		
		
	}
	

}
