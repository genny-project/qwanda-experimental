package life.genny.qwanda;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class TaskAsk implements Serializable {
	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	
	 Ask ask;
	 String formCode;
	 Boolean answered = false;
	 Boolean tableRow = false;
	 Boolean formTrigger = false;
	 Boolean createOnTrigger = false;
	 String value = "";
	
	public TaskAsk(Ask ask, String formCode, Boolean answered, Boolean tableRow, Boolean formTrigger, Boolean createOnTrigger) {
		super();
		this.ask = ask;
		this.formCode = formCode;
		this.answered = answered;
		this.tableRow = tableRow;
		this.formTrigger = formTrigger;
		this.createOnTrigger = createOnTrigger;
	}

	/**
	 * @return the ask
	 */
	public Ask getAsk() {
		return ask;
	}

	/**
	 * @param ask the ask to set
	 */
	public void setAsk(Ask ask) {
		this.ask = ask;
	}

	/**
	 * @return the formCode
	 */
	public String getFormCode() {
		return formCode;
	}

	/**
	 * @param formCode the formCode to set
	 */
	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	/**
	 * @return the answered
	 */
	public Boolean getAnswered() {
		return answered;
	}

	/**
	 * @param answered the answered to set
	 */
	public void setAnswered(Boolean answered) {
		this.answered = answered;
	}

	/**
	 * @return the tableRow
	 */
	public Boolean getTableRow() {
		return tableRow;
	}

	/**
	 * @param tableRow the tableRow to set
	 */
	public void setTableRow(Boolean tableRow) {
		this.tableRow = tableRow;
	}

	/**
	 * @return the formTrigger
	 */
	public Boolean getFormTrigger() {
		return formTrigger;
	}

	/**
	 * @param formTrigger the formTrigger to set
	 */
	public void setFormTrigger(Boolean formTrigger) {
		this.formTrigger = formTrigger;
	}

	
	/**
	 * @return the createOnTrigger
	 */
	public Boolean getCreateOnTrigger() {
		return createOnTrigger;
	}

	/**
	 * @param createOnTrigger the createOnTrigger to set
	 */
	public void setCreateOnTrigger(Boolean createOnTrigger) {
		this.createOnTrigger = createOnTrigger;
	}

	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
//		return "TaskAsk [ask=" + ask + ", formCode=" + formCode + ", answered=" + answered + ", tableRow=" + tableRow
//				+ ", formTrigger=" + formTrigger + ", createOnTrigger=" + createOnTrigger+"]";
		return StringUtils.abbreviate(formCode, "...", 20)
				+ String.format(": %-15s",StringUtils.abbreviate(ask.getTargetCode(), "...", 15))
				+ String.format(": %25s",StringUtils.abbreviate(ask.getAttributeCode(), "...", 25))
				+":"+(this.createOnTrigger?"TempBE":"TrgtBE")
				+":"+(this.getFormTrigger()?"Trigger":"NonTrig")
				+":"+(this.getAsk().getMandatory()?"M":"O")
				+":"+(this.answered?("ANSWERED - "+StringUtils.abbreviate(this.value, "...", 20)):" ")
				;
	}
	
	
	

}
