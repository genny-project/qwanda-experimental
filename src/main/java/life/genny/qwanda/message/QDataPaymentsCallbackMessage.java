package life.genny.qwanda.message;

import com.google.gson.annotations.Expose;

public class QDataPaymentsCallbackMessage extends QDataMessage{

	 static final long serialVersionUID = 1L;
	
	 static final String DATATYPE_ASSEMBLY_CALLBACK = QDataPaymentsCallbackMessage.class.getSimpleName();
	
	
	public QDataPaymentsCallbackMessage(String data_type, String objectId, ObjectType object_type, ObjectStatus status) {
		super(DATATYPE_ASSEMBLY_CALLBACK);
		this.objectId = objectId;
		this.object_status = status;
		this.object_type = object_type;
	}
	
	@Expose
	 ObjectType object_type;
	
	@Expose
	 ObjectStatus object_status;
	
	@Expose
	 String objectId;
	
	@Expose
	 String object_message;
	

	public enum ObjectType {
		ITEM,
		DISBURSEMENT
	}
	
	public enum ObjectStatus {
		
		/* Item related callbacks */
				
		ITEM_PAYMENT_CREATION, //manually
		
		ITEM_PAYMENT_PENDING,  //error
		ITEM_PAYMENT_INCOMING, //make payment is done, but its still getting processed //
		ITEM_PAYMENT_HELD, //when payment is held, due to fraud or such , send email/toast that payment is held
		ITEM_PAYMENT_PROBLEM, //problem - dispute, send email/toast
		ITEM_PAYMENT_DEPOSITED, //payment deposited successfully //
		ITEM_PAYMENT_COMPLETED, //when payment is made to the buyer successfully //
		ITEM_PAYMENT_CANCELLED, //When payment is cancelled
		ITEM_PAYMENT_REFUNDED, //when payment is refunded
		
		
		/* Disbursement related callbacks */
		DISBURSEMENT_PAYMENT_SUCCESS;
	}

	public ObjectType getObject_type() {
		return object_type;
	}

	public void setObject_type(ObjectType object_type) {
		this.object_type = object_type;
	}

	public ObjectStatus getObject_status() {
		return object_status;
	}

	public void setObject_status(ObjectStatus object_status) {
		this.object_status = object_status;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	

	public String getObject_message() {
		return object_message;
	}

	public void setObject_message(String object_message) {
		this.object_message = object_message;
	}

	@Override
	public String toString() {
		return "QDataPaymentsCallbackMessage [object_type=" + object_type + ", object_status=" + object_status
				+ ", objectId=" + objectId + ", object_message=" + object_message + "]";
	}

	
}
