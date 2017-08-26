package wtaspect;

import api.*;
import model.User;
import tool.Logger;
import exception.ForbiddenRequest;

/**
 * Aspect for logging forbidden actions
 * 
 * @author Tomasz Bajorek
 */
public aspect Forbidden {
	pointcut forbiddenAuth(ForbiddenRequest e): (
			within(Auth)
			|| within(DepartmentApi)
			|| within(InvitationApi)
			|| within(MessageApi)
			|| within(MilestoneApi)
			|| within(TicketApi)
			|| within(UserApi)) 
		&& handler(ForbiddenRequest)
		&& args(e);
	
	before(ForbiddenRequest e): forbiddenAuth(e) {
		User user = e.getUser();
		String log = " Executed: "+thisEnclosingJoinPointStaticPart.getSignature();
		if(user != null) {
			log += " | User: "+user.getName()+" "+user.getSurname()+" <"+user.getEmail()+"> from "+user.getDepartment().getName();
		}
		Logger.saveLog(
			"forbidden.log",
			log
		);
	}
}