package wtaspect;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import api.*;
import model.Ticket;
import tool.Logger;
import response.Response;

/**
 * Aspect for rate activity. It enriches functionality of rating by supporting logs and adding new response messages to the application. It needs connection to database.
 * 
 * @author Tomasz Bajorek
 */
public aspect Rate {
	pointcut rateTicket(Integer id, String hash, Ticket ratedTicket):
		execution(public Response TicketApi.rate(Integer, String, Ticket))
		&& args(id, hash, ratedTicket);
	
	protected EntityManagerFactory managerFactory;
	@PersistenceContext
	protected EntityManager entityManager;
	
	private void prepareDbEnv() {
		managerFactory = Persistence.createEntityManagerFactory("WebTicket");
		entityManager = managerFactory.createEntityManager();
	}
	
	before(Integer id, String hash, Ticket ratedTicket): rateTicket(id, hash, ratedTicket) {
		prepareDbEnv();
		Ticket ticket = (Ticket) entityManager.find(Ticket.class, id);
		Logger.saveLog(
			"rating.log",
			" Recipient: "+
			ticket.getRecipient().getName()+" "+ticket.getRecipient().getSurname()+
			" of the ticket: "+
			ticket.getTitle()+" (to: "+ticket.getDepartment().getName()+")"+
			" rated: "+ratedTicket.getRate()+
			" by: "+ticket.getAuthor().getName()+" "+ticket.getAuthor().getSurname()
		);
	}
	
	after(Integer id, String hash, Ticket ratedTicket) returning(Response response):
		rateTicket(id, hash, ratedTicket)
	{
		if(ratedTicket.getRate() < 3) {
			response.addInfo("Jeśli nadal nie rozwiązałeś swojego problemu, utwórz kolejne zgłoszenie");
		} else if(ratedTicket.getRate() > 4) {
			response.addInfo("Cieszymy się, że Twoje zgłoszenie zostało wykonane bardzo dobrze");
		} else {
			response.addInfo("Mamy nadzieję, że Twoje następne zgłoszenie zostanie wykonane lepiej");
		}
	}
}