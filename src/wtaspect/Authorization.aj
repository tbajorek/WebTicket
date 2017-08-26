package wtaspect;

import response.Response;
import javax.servlet.http.HttpServletRequest;

import api.Auth;
import model.User;
import tool.Logger;

/**
 * Aspect for authorization. It adds logging when someone logs into application
 * 
 * @author Tomasz Bajorek
 */
public aspect Authorization {
	after(Auth endpoint, HttpServletRequest request, User user) returning(Response response):
		execution(Response Auth.login(HttpServletRequest, model.User))
		&&  target(endpoint)
		&& args(request, user)
	{
		Logger.saveLog(
			"auth_details.log",
			"[LOGIN "+
			(response.getResponse().equals(0)?"FAILED":"SUCCESS")+
			"] Email: "+
			user.getEmail()+
			" from location: "+
			request.getRemoteAddr()
		);
	}
}