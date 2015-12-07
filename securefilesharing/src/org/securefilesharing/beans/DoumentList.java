/**
 * 
 */
package org.securefilesharing.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author puchakayalak
 *
 */
public class DoumentList {
	private List<Documents> list;

	/**
	 * @return the list
	 */
	public List<Documents> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Documents> list) {
		this.list = list;
	}
	
	public void addDocument(Documents doc) {
		if (list == null) {
			list = new ArrayList<Documents>();
		}
		list.add(doc);
	}
}
