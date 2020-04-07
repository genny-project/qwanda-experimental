package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

public class QCmdTabViewMessage extends QCmdViewMessage {

	 static final String CMD_TYPE = "CMD_VIEW";
	 static final String VIEW_TYPE = "TAB_VIEW";
	 static final long serialVersionUID = 1L;

	/*
	 * @Expose  QTabView[] views;
	 */

	@Expose
	 QTabView[] tabs;


	public QCmdTabViewMessage(final QTabView[] tabs) {
		super(CMD_TYPE, VIEW_TYPE);
		// this.views = views;
		this.tabs = tabs;
	}
	
	public QCmdTabViewMessage(final Object root, final QTabView[] tabs) {
		super(VIEW_TYPE, root);
		this.tabs = tabs;
	}

	/*
	 * public void setViews(QTabView[] views) { this.views = views; }
	 * 
	 * public QTabView[] getViews() { return this.views; }
	 */

	/**
	 * @return the tabs
	 */
	public QTabView[] getTabs() {
		return tabs;
	}

	/**
	 * @param tabs
	 * the tabs to set
	 */
	public void setTabs(QTabView[] tabs) {
		this.tabs = tabs;
	}


}
