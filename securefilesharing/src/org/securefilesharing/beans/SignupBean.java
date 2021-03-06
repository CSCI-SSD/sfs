/**
 * 
 */
package org.securefilesharing.beans;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.spec.SecretKeySpec;

/**
 * @author puchakayalak
 *
 */
public class SignupBean {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String reenterPassword;
	
	private PrivateKey privateKey;
	
	private PublicKey publicKey;
	
	private byte[] aesKey;
	
	private SecretKeySpec aeskeySpec;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the privateKey
	 */
	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	/**
	 * @param privateKey the privateKey to set
	 */
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	/**
	 * @return the publicKey
	 */
	public PublicKey getPublicKey() {
		return publicKey;
	}

	/**
	 * @param publicKey the publicKey to set
	 */
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	/**
	 * @return the reenterPassword
	 */
	public String getReenterPassword() {
		return reenterPassword;
	}

	/**
	 * @param reenterPassword the reenterPassword to set
	 */
	public void setReenterPassword(String reenterPassword) {
		this.reenterPassword = reenterPassword;
	}

	/**
	 * @return the aesKey
	 */
	public byte[] getAesKey() {
		return aesKey;
	}

	/**
	 * @param aesKey the aesKey to set
	 */
	public void setAesKey(byte[] aesKey) {
		this.aesKey = aesKey;
	}

	/**
	 * @return the aeskeySpec
	 */
	public SecretKeySpec getAeskeySpec() {
		return aeskeySpec;
	}

	/**
	 * @param aeskeySpec the aeskeySpec to set
	 */
	public void setAeskeySpec(SecretKeySpec aeskeySpec) {
		this.aeskeySpec = aeskeySpec;
	}
	
}
