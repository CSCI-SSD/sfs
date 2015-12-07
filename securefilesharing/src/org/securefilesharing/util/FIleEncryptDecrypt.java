/**
 * 
 */
package org.securefilesharing.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author kartheek
 *
 */
public class FIleEncryptDecrypt {
	
	/**
	 * Encrypts and then copies the contents of a given file.
	 */
	public static void encrypt(File in, File out, SecretKeySpec aeskeySpec) throws IOException, InvalidKeyException {
		Cipher aesCipher;
		try {
			aesCipher = Cipher.getInstance(SecureSharingConstants.AES_ALGORITHM);
			aesCipher.init(Cipher.ENCRYPT_MODE, aeskeySpec);
			
			if (!out.exists()) {
				out.createNewFile();
			}
			
			FileInputStream is = new FileInputStream(in);
			CipherOutputStream os = new CipherOutputStream(new FileOutputStream(out), aesCipher);

			copy(is, os);

			os.close();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}
	
	public static  void copy(InputStream is, OutputStream os) throws IOException {
		int i;
		byte[] b = new byte[1024];
		while ((i = is.read(b)) != -1) {
			os.write(b, 0, i);
		}
	}

}
