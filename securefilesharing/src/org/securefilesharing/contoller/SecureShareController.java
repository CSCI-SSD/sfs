/**
 * 
 */
package org.securefilesharing.contoller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.securefilesharing.beans.DoumentList;
import org.securefilesharing.beans.LoginBean;
import org.securefilesharing.service.Service;
import org.securefilesharing.util.SecureKeyGenerator;
import org.securefilesharing.util.SecureSharingConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author puchakayalak
 *
 */
@Controller
public class SecureShareController {

	/**
	 * @author puchakayalak
	 *
	 */
	private Service service;
	
	private SecureKeyGenerator secureKeyGenerator;

	

	@RequestMapping(value = "/secureshare.view", method = RequestMethod.GET)
	public String viewlist(ModelMap model,  HttpServletRequest request, 
	        HttpServletResponse response) {
		
		LoginBean loginBean =  (request.getSession().getAttribute(SecureSharingConstants.LOGIN_BEAN) != null ?  (LoginBean) request.getSession().getAttribute(SecureSharingConstants.LOGIN_BEAN) : null);
		
		if(loginBean == null) {
			model.addAttribute("message", "Please login");
			return "login";
		}	
		
		DoumentList doclist = service.getFileList(loginBean.getEmail());
		model.addAttribute("FileList", doclist);
		model.addAttribute("view", "secureshare");
		return "main";
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
