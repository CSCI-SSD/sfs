/**
 * 
 */
package org.securefilesharing.contoller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.securefilesharing.beans.LoginBean;
import org.securefilesharing.beans.SignupBean;
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
public class LogoutContoller {

	/**
	 * @author puchakayalak
	 *
	 */
	
	@RequestMapping(value = "/logout.view")
	public String logout(@ModelAttribute("SpringWeb") LoginBean loginBean, ModelMap model,  HttpServletRequest request, 
	        HttpServletResponse response) {
		System.out.println(loginBean.getEmail());
		System.out.println(loginBean.getPassword());
		model.addAttribute("login", loginBean);
		model.addAttribute(loginBean);
		
		request.getSession().setAttribute(SecureSharingConstants.LOGIN_BEAN, null);
		request.getSession().invalidate();
		
		return "login";
	}
}
