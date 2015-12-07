/**
 * 
 */
package org.securefilesharing.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

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
			
			KeyGenerator kgen = KeyGenerator.getInstance(SecureSharingConstants.AES_ALGORITHM);
			kgen.init(SecureSharingConstants.AES_Key_Size);
			SecretKey mykey = kgen.generateKey();
			signupBean.setAesKey(mykey.getEncoded());
			signupBean.setAeskeySpec(new SecretKeySpec(signupBean.getAesKey(), SecureSharingConstants.AES_ALGORITHM));
			
			return signupBean;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
