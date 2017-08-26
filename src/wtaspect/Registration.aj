package wtaspect;

import response.Response;

import api.UserApi;
import response.User;
import model.NewUser;
import tool.Logger;

/**
 * Aspect for observing registration task. When anyone registers in the application, this aspect logs the activity.
 * 
 * @author Tomasz Bajorek
 */
public aspect Registration {
	after(UserApi endpoint, NewUser user, String hash) returning(Response response):
		execution(public Response UserApi.registerUser(String, NewUser))
		&& args(hash, user)
		&&  target(endpoint)
	{
		User ruser = (User)response.getData().get("user");
		Logger.saveLog(
			"registration.log",
			"[REGISTER "+
			(response.getResponse().equals(0)?"FAILED":"SUCCESS")+
			"] User: "+
			user.getName()+" "+user.getSurname()+
			"<"+ruser.getEmail()+"> "+
			"("+ruser.getPosition()+")"
		);
	}
}