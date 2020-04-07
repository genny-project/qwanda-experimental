/*
 * (C) Copyright 2017 GADA Technology (http://www.outcome-hub.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Adam Crow
 *     Byron Aguirre
 */


package life.genny.qwanda;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

/**
 * Layout is the abstract base class for all layout
 * managed in the Qwanda library.
 * An Layout object is used as a means of storing information
 * fabout a layout.  This
 * gps information includes:
 * <ul>
 * <li>code
 * <li>data

 * </ul>
 * <p>
 * 
 * <p>
 * 
 * 
 * @author      Adam Crow
 * @author      Byron Aguirre
 * @author      Loris Campanile
 * @version     %I%, %G%
 * @since       1.0
 */


public class Layout implements Serializable {
	
	/**
	 * 
	 */
	 static final long serialVersionUID = 1L;

	@Expose
	 String download_url;
	
	@Expose
	 String path;
	
	@Expose
	 String name;
	
	@Expose
	 String data;
	
	@Expose
	 String modified_date;
	
	/**
	 * @param code
	 * @param data
	 */
	public Layout(String name, String data, String download_url, String path, String modified_date) {
		
		this.name = name;
		this.data = data;
		this.path = path;
		this.download_url = download_url;
		this.modified_date = modified_date;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	
	/**
	 * @return the download_url
	 */
	public String getDownloadUrl() {
		return download_url;
	}
	/**
	 * @param data the download_url to set
	 */
	public void setDownloadUrl(String download_url) {
		this.download_url = download_url;
	}
	
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param data the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * @return the modified_date
	 */
	public String getModifiedDate() {
		return modified_date;
	}
	/**
	 * @param data the modified_date to set
	 */
	public void setModifiedDate(String modified_date) {
		this.modified_date = modified_date;
	}
}
