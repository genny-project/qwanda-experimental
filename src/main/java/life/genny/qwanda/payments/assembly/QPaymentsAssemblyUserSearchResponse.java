package life.genny.qwanda.payments.assembly;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;

public class QPaymentsAssemblyUserSearchResponse implements Serializable{

	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;
	
	@Expose
	 List<QPaymentsAssemblyUserResponse> users;
	
	@Expose
	 QPaymentsAssemblyMetaResponse meta;

	/**
	 * @return the users
	 */
	public List<QPaymentsAssemblyUserResponse> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<QPaymentsAssemblyUserResponse> users) {
		this.users = users;
	}

	/**
	 * @return the meta
	 */
	public QPaymentsAssemblyMetaResponse getMeta() {
		return meta;
	}

	/**
	 * @param meta the meta to set
	 */
	public void setMeta(QPaymentsAssemblyMetaResponse meta) {
		this.meta = meta;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QPaymentsAssemblyUserSearchResponse [users=" + users + ", meta=" + meta + "]";
	}
	

}
