/**
 * 
 */
package org.securefilesharing.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.securefilesharing.beans.LoginBean;
import org.securefilesharing.beans.SignupBean;

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
			preparedStatement = connection.prepareStatement("SELECT EMAIL FROM USERS WHERE EMAIL=?");
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.last();
			if(resultSet.getRow() == 1) {
				return true;
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
			preparedStatement = connection.prepareStatement("");
			//preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.last();
			if(resultSet.getRow() == 1) {
				return "";
			} else {
				return "";
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}
	
}
