package life.genny.qwanda.payments;

import java.util.Map;

import com.google.gson.annotations.Expose;

public class QPaymentsErrorResponse {
	
	@Expose
	 Map<String, Object> errors;
	
	@Expose
	 Map<String, Object> error;

	/**
	 * @return the errors
	 */
	public Map<String, Object> getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 * Returns errors from external payments service
	 */
	public void setErrors(Map<String, Object> errors) {
		this.errors = errors;
	}

	/**
	 * @return the error
	 * Returns errors from payment verticle
	 */
	public Map<String, Object> getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(Map<String, Object> error) {
		this.error = error;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsErrorResponse [errors=" + errors + ", error=" + error + "]";
	}
	

}
