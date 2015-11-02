/**
 * 
 */
package org.securefilesharing.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import org.securefilesharing.beans.SignupBean;

/**
 * @author kartheek
 *
 */
public class SecureKeyGenerator {
	
	public SignupBean generateRSAKeys(SignupBean signupBean) {
		KeyPairGenerator keyGen;
		try {
			keyGen = KeyPairGenerator.getInstance(SecureSharingConstants.RSA_ALGORITHM);
			keyGen.initialize(1024);
			KeyPair key = keyGen.generateKeyPair();
			signupBean.setPrivateKey(key.getPrivate());
			signupBean.setPublicKey(key.getPublic());
			return signupBean;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
