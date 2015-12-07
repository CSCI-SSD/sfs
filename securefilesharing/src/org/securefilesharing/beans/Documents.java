/**
 * 
 */
package org.securefilesharing.beans;

/**
 * @author puchakayalak
 *
 */
public class Documents {
	
	private String fileName;
	
	private String sharedWith;
	
	private String uploadDate;
	
	private String LastUpdatedDate;

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the sharedWith
	 */
	public String getSharedWith() {
		return sharedWith;
	}

	/**
	 * @param sharedWith the sharedWith to set
	 */
	public void setSharedWith(String sharedWith) {
		this.sharedWith = sharedWith;
	}

	/**
	 * @return the uploadDate
	 */
	public String getUploadDate() {
		return uploadDate;
	}

	/**
	 * @param uploadDate the uploadDate to set
	 */
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	/**
	 * @return the lastUpdatedDate
	 */
	public String getLastUpdatedDate() {
		return LastUpdatedDate;
	}

	/**
	 * @param lastUpdatedDate the lastUpdatedDate to set
	 */
	public void setLastUpdatedDate(String lastUpdatedDate) {
		LastUpdatedDate = lastUpdatedDate;
	}
	
	

}
