/**
 * 
 */
package org.securefilesharing.contoller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.securefilesharing.beans.LoginBean;
import org.securefilesharing.service.Service;
import org.securefilesharing.util.SecureKeyGenerator;
import org.securefilesharing.util.SecureSharingConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author puchakayalak
 *
 */
@Controller
public class FileUploadController1 {

	/**
	 * @author puchakayalak
	 *
	 */
	private Service service;
	
	private SecureKeyGenerator secureKeyGenerator;

	@RequestMapping(value="/upload.view", method=RequestMethod.GET)
    public String provideUploadInfo( ModelMap model,  HttpServletRequest request, 
	        HttpServletResponse response) {
		LoginBean loginBean =  (request.getSession().getAttribute(SecureSharingConstants.LOGIN_BEAN) != null ?  (LoginBean) request.getSession().getAttribute(SecureSharingConstants.LOGIN_BEAN) : null);
		
		if(loginBean == null) {
			model.addAttribute("message", "Please login");
			return "login";
		}	
        return "You can upload a file by posting to this same URL.";
    }

	@RequestMapping(value="/upload.view", method=RequestMethod.POST)
    public String handleFileUpload(@RequestParam("fileName") MultipartFile file, ModelMap model,  HttpServletRequest request, 
	        HttpServletResponse response){
		LoginBean loginBean =  (request.getSession().getAttribute(SecureSharingConstants.LOGIN_BEAN) != null ?  (LoginBean) request.getSession().getAttribute(SecureSharingConstants.LOGIN_BEAN) : null);
		
		if(loginBean == null) {
			model.addAttribute("message", "Please login");
			return "login";
		}
		
		if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(file.getName())));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + file.getName() + "!";
            } catch (Exception e) {
                return "You failed to upload " + file.getName() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + file.getName() + " because the file was empty.";
        }
	}
	
	
	/**
	 * @return the service
	 */
	public Service getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
	}

	/**
	 * @return the secureKeyGenerator
	 */
	public SecureKeyGenerator getSecureKeyGenerator() {
		return secureKeyGenerator;
	}

	/**
	 * @param secureKeyGenerator the secureKeyGenerator to set
	 */
	public void setSecureKeyGenerator(SecureKeyGenerator secureKeyGenerator) {
		this.secureKeyGenerator = secureKeyGenerator;
	}
}
