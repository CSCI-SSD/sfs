package org.securefilesharing.contoller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.securefilesharing.beans.LoginBean;
import org.securefilesharing.service.Service;
import org.securefilesharing.util.FIleEncryptDecrypt;
import org.securefilesharing.util.SecureSharingConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
public class FileUploadController {
	
	private Service service;
	
    @RequestMapping(value="/upload.view")
    public String singleUpload(){
    	return "upload";
    }
    @RequestMapping(value="/upload.view", method=RequestMethod.POST )
    public String singleSave(@RequestParam("file") MultipartFile file,  ModelMap model,  HttpServletRequest request, 
	        HttpServletResponse response ){
    	
    	LoginBean loginBean =  (request.getSession().getAttribute(SecureSharingConstants.LOGIN_BEAN) != null ?  (LoginBean) request.getSession().getAttribute(SecureSharingConstants.LOGIN_BEAN) : null);
		
		if(loginBean == null) {
			model.addAttribute("message", "Please login");
			return "login";
		}	
    	String fileName = null;
    	if (!file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                File UploadedFile = new File(SecureSharingConstants.DOC_PATH + fileName);
                if (UploadedFile.exists()) {
                	model.addAttribute("message", "alredy file exist");
                	model.addAttribute("view", "upload");
        			return "main"; 
                }
                
                BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(UploadedFile));
                buffStream.write(bytes);
                buffStream.close();
                
                FIleEncryptDecrypt.encrypt(new File(SecureSharingConstants.DOC_PATH + fileName), new File(SecureSharingConstants.DOC_PATH+ SecureSharingConstants.ENCRYPT+ fileName), loginBean.getAeskeySpec());
                
                service.uploadFile(fileName, loginBean.getEmail());
                
                model.addAttribute("message", "You have successfully uploaded " + fileName);
            	model.addAttribute("view", "upload");
    			return "main";
                
                
            } catch (Exception e) {
                return "You failed to upload " + fileName + ": " + e.getMessage();
            }
        } else {
            return "Unable to upload. File is empty.";
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
      
    
   
} 