/**
 * 
 */
package org.securefilesharing.contoller;

import org.securefilesharing.beans.LoginBean;
import org.securefilesharing.beans.SignupBean;
import org.securefilesharing.service.Service;
import org.securefilesharing.util.SecureKeyGenerator;
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
public class SignupContoller {

	/**
	 * @author puchakayalak
	 *
	 */
	private Service service;
	
	private SecureKeyGenerator secureKeyGenerator;

	@RequestMapping(value = "/signup.view", method = RequestMethod.GET)
	public ModelAndView student() {
		return new ModelAndView("login", "command", new LoginBean());
	}

	@RequestMapping(value = "/signup.view", method = RequestMethod.POST)
	public String addStudent(@ModelAttribute("SpringWeb") SignupBean signupBean, ModelMap model) {
		System.out.println(signupBean.getFirstName());
		model.addAttribute("message", "message");
		model.addAttribute(signupBean);
		
		return "login";
	}
	
	private String userCreationProcess(SignupBean signupBean) {
		
		if(service.findUserByEmail(signupBean.getEmail())) {
			
		} else {
			secureKeyGenerator.generateRSAKeys(signupBean);
			return service.createUser(signupBean);
		}
		
		return null;
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
