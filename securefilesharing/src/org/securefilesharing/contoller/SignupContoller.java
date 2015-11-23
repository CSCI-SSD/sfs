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
	public String addUser(@ModelAttribute("SpringWeb") SignupBean signupBean, ModelMap model) {
		System.out.println(signupBean.getFirstName());
		model.addAttribute("signupBean", signupBean);
		model.addAttribute("register", "yes");
		if (!validatePassword(signupBean)) {
			model.addAttribute("message", "Please Enter valied password in both password feilds");
			return "login"; 
		}
		String message = userCreationProcess(signupBean);
		model.addAttribute("message",message);
		return "login";
	}
	
	private boolean validatePassword(SignupBean signupBean) {
		if (signupBean.getPassword() == null || signupBean.getReenterPassword() == null ) 
			return false;
		else if (signupBean.getPassword() != null && signupBean.getReenterPassword() != null && (!(signupBean.getPassword().length() >= 8) || !signupBean.getPassword().equals(signupBean.getReenterPassword()))) 
			return false;
		else 
			return true;
	}

	private String userCreationProcess(SignupBean signupBean) {
		
		if(service.findUserByEmail(signupBean.getEmail())) {
			return "User Alredy Registerd";
		} else {
			SignupBean signupBean1 = new SignupBean();
			secureKeyGenerator.generateRSAKeys(signupBean1);
			if (signupBean1 == null) {
				return "Unable to create User";
			} else {
				signupBean.setPrivateKey(signupBean1.getPrivateKey());
				signupBean.setPublicKey(signupBean1.getPublicKey());
				return service.createUser(signupBean);
			}
			
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
