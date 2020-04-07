package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

import life.genny.qwanda.QuestionSourceTarget;

public class QDataQSTMessage extends QDataMessage {

	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	
	@Expose
	 QuestionSourceTarget rootQST;
	
	@Expose
	 QuestionSourceTarget[] items;
	 static final String DATATYPE_QST = QuestionSourceTarget.class.getSimpleName();

	public QDataQSTMessage(final QuestionSourceTarget rootQST,final QuestionSourceTarget[] qstArray) {
		super(DATATYPE_QST);
		setItems(qstArray);
	}

	public QuestionSourceTarget[] getItems() {
		return this.items;
	}

	public void setItems(QuestionSourceTarget[] qstArray) {
		this.items = qstArray;
	}

	/**
	 * @return the rootQST
	 */
	public QuestionSourceTarget getRootQST() {
		return rootQST;
	}

	/**
	 * @param rootQST the rootQST to set
	 */
	public void setRootQST(QuestionSourceTarget rootQST) {
		this.rootQST = rootQST;
	}
	
	

}
