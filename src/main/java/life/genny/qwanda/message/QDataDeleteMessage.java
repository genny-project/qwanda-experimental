package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

public class QDataDeleteMessage extends QDataMessage{

	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	
	@Expose
	 Long[] ids;
	
	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public QDataDeleteMessage(String dataType,Long[] ids) {
		super(dataType);
		this.ids = ids;
		setDelete(true);
		// TODO Auto-generated constructor stub
	}
	
	
}
