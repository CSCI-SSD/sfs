/**
 * 
 */
package org.securefilesharing.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.spec.SecretKeySpec;
import javax.sql.DataSource;

import org.securefilesharing.beans.Documents;
import org.securefilesharing.beans.DoumentList;
import org.securefilesharing.beans.LoginBean;
import org.securefilesharing.beans.SignupBean;
import org.securefilesharing.util.SecureSharingConstants;

/**
 * @author puchakayalak
 *
 */
public class ServiceImpl implements Service {
	
	private DataSource dataSource;

	/* (non-Javadoc)
	 * @see org.securefilesharing.service.Service#findUserByEmail(java.lang.String)
	 */
	public boolean findUserByEmail(String email) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement("SELECT EMAIL,STATUS FROM USERS WHERE EMAIL=?");
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			if(resultSet.getRow() == 1) {
				if ("Register".equals(resultSet.getString("STATUS"))) {
					return false;
				} else {
					return true;
				}
				
			} else {
				return false;
			}
		} catch (SQLException sqlException) {
			return false;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public String createUser(SignupBean signupBean) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO USERS (EMAIL,FIRSTNAME,LASTNAME,PASSWORD,STATUS,PUBLICKEY,PRIVATEKEY,SECUREKEY) values (?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, signupBean.getEmail());
			preparedStatement.setString(2, signupBean.getFirstName());
			preparedStatement.setString(3, signupBean.getLastName());
			preparedStatement.setString(4, signupBean.getPassword());
			preparedStatement.setString(5, SecureSharingConstants.USER_STATUS_VALIDATE);
			
			
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(signupBean.getPublicKey());
				oos.flush();
				oos.close();
				preparedStatement.setBinaryStream(6, new ByteArrayInputStream(baos.toByteArray()));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(signupBean.getPrivateKey());
				oos.flush();
				oos.close();
				preparedStatement.setBinaryStream(7, new ByteArrayInputStream(baos.toByteArray()));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(signupBean.getAeskeySpec());
				oos.flush();
				oos.close();
				preparedStatement.setBinaryStream(8, new ByteArrayInputStream(baos.toByteArray()));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.last();
			if(resultSet.getRow() == 1) {
				return "Created User";
			} else {
				return "Unable to Create User";
			}
		} catch (SQLException sqlException) {
			return "";
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public LoginBean validateUserLogin(LoginBean loginBean) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM USERS WHERE EMAIL=? and PASSWORD=?");
			preparedStatement.setString(1, loginBean.getEmail());
			preparedStatement.setString(2, loginBean.getPassword());
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			if(resultSet.getRow() == 1) {
				if (SecureSharingConstants.USER_STATUS_COMPLETE.equals(resultSet.getString("STATUS"))) {
					loginBean.setStatus(SecureSharingConstants.USER_STATUS_COMPLETE);
					loginBean.setFirstName(resultSet.getString("FIRSTNAME"));
					loginBean.setLastName(resultSet.getString("LASTNAME"));
					loginBean.setStatus(resultSet.getString("STATUS"));
					
				    try {
				    	byte[] byteSt = (byte[]) resultSet.getBlob("PUBLICKEY").getBytes(1,(int)resultSet.getBlob("PUBLICKEY").length());
					    ByteArrayInputStream baip = new ByteArrayInputStream(byteSt);
						ObjectInputStream ois = new ObjectInputStream(baip);
						loginBean.setPublicKey((PublicKey) ois.readObject());
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    
				    try {
				    	byte[] byteSt = (byte[]) resultSet.getBlob("PRIVATEKEY").getBytes(1,(int)resultSet.getBlob("PRIVATEKEY").length());
					    ByteArrayInputStream baip = new ByteArrayInputStream(byteSt);
						ObjectInputStream ois = new ObjectInputStream(baip);
						loginBean.setPrivateKey((PrivateKey) ois.readObject());
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    
				    try {
				    	byte[] byteSt = (byte[]) resultSet.getBlob("SECUREKEY").getBytes(1,(int)resultSet.getBlob("SECUREKEY").length());
					    ByteArrayInputStream baip = new ByteArrayInputStream(byteSt);
						ObjectInputStream ois = new ObjectInputStream(baip);
						loginBean.setAeskeySpec((SecretKeySpec) ois.readObject());
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    
					
					
					return loginBean;
				} else if (SecureSharingConstants.USER_STATUS_VALIDATE.equals(resultSet.getString("STATUS"))) {
					loginBean.setStatus(SecureSharingConstants.USER_STATUS_VALIDATE);
					return loginBean;
				}
			} else {
				return null;
			}
		} catch (SQLException sqlException) {
			return null;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return loginBean;
	}

	/**
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public String updateUser(SignupBean signupBean) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE USERS SET FIRSTNAME = ? , LASTNAME=? , PASSWORD=? , STATUS=? WHERE EMAIL=?");
			preparedStatement.setString(5, signupBean.getEmail());
			preparedStatement.setString(1, signupBean.getFirstName());
			preparedStatement.setString(2, signupBean.getLastName());
			preparedStatement.setString(3, signupBean.getPassword());
			preparedStatement.setString(4, SecureSharingConstants.USER_STATUS_VALIDATE);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.last();
			if(resultSet.getRow() == 1) {
				return "Created User";
			} else {
				return "Unable to Create User";
			}
		} catch (SQLException sqlException) {
			return "";
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public String uploadFile(String fileName, String email) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO DOCUMENTS (FILENAME, USERNAME) VALUES (?,?) ");
			preparedStatement.setString(1, fileName);
			preparedStatement.setString(2, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.last();
			if(resultSet.getRow() == 1) {
				return "File Inserted";
			} else {
				return "Unable to insert row";
			}
		} catch (SQLException sqlException) {
			return "";
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public DoumentList getFileList(String email) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement("Select * from  DOCUMENTS where  USERNAME = ?");
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			DoumentList doumentList = new DoumentList();
			while(resultSet.next()) {
				Documents documents = new Documents();
				documents.setFileName(resultSet.getString("FILENAME"));
				doumentList.addDocument(documents);
			}
			return doumentList;
		} catch (SQLException sqlException) {
			return null;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
