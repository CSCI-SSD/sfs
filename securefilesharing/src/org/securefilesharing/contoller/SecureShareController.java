/**
 * 
 */
package org.securefilesharing.contoller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.securefilesharing.beans.LoginBean;
import org.securefilesharing.service.Service;
import org.securefilesharing.util.SecureKeyGenerator;
import org.securefilesharing.util.SecureSharingConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping(value = "/login.view", method = RequestMethod.GET)
	public ModelAndView student() {
		return new ModelAndView("login", "command", new LoginBean());
	}

	@RequestMapping(value = "/login.view", method = RequestMethod.POST)
	public String login(@ModelAttribute("SpringWeb") LoginBean loginBean, ModelMap model,  HttpServletRequest request, 
	        HttpServletResponse response) {
		model.addAttribute("login", "yes");
		loginBean = service.validateUserLogin(loginBean);
		if(loginBean == null) {
			model.addAttribute("message", "Unable to Login User");
			return "login";
		} else if(SecureSharingConstants.USER_STATUS_VALIDATE.equals(loginBean.getStatus())) {
			model.addAttribute("message", "User Need To Validate");
			return "validate";
		} else if(SecureSharingConstants.USER_STATUS_COMPLETE.equals(loginBean.getStatus())) {
			request.getSession().setAttribute(SecureSharingConstants.LOGIN_BEAN, loginBean);
			model.addAttribute(SecureSharingConstants.LOGIN_BEAN, loginBean);
		}
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
