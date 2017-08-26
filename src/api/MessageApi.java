package api;
 
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import exception.ForbiddenRequest;
import model.AccountType;
import model.Ticket;
import model.Message;
import model.User;
import response.Response;

/**
 * End point of ticket messages
 * 
 * @author Tomasz Bajorek
 */
@Path("/message")
public class MessageApi extends AbstractEndpoint {
	/**
	 * Return message data in response
	 * @param id Message id
	 * @param token Session token
	 * @return
	 */
	@GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response view(@PathParam("id") Integer id, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
			model.Message message = (model.Message) entityManager.find(model.Message.class, id);
			if(message != null) {
				User user = getSessionByToken(token).getUser();
				if(!message.getTicket().hasUserPermissions(user)) {
					throw new ForbiddenRequest(user);
				}
				response.Message outputMessage = new response.Message(message);
	            response.addData("id", outputMessage.getId());
	            response.addData("author", outputMessage.getAuthor());
	            response.addData("content", outputMessage.getContent());
	            response.addData("created", outputMessage.getCreated());
			} else {
				response.addWarning("Nie znaleziono wiadomości");
			}
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	/**
	 * Add new message to the database
	 * @param newMessage Message object containing content of sending message
	 * @param token Session token
	 * @return
	 */
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(Message newMessage, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	Ticket dbTicket = (model.Ticket) entityManager.find(Ticket.class, newMessage.getTicket().getId());
        	User user = getSessionByToken(token).getUser();
        	if(!dbTicket.hasUserPermissions(user)) {
        		throw new ForbiddenRequest(user);
        	}
        	newMessage.setAuthor(user);
        	newMessage.setCreated(new Date());
        	dbTicket.setSomethingNew();
            entityTransaction.begin();
            entityManager.persist(newMessage);
            entityManager.persist(dbTicket);
            entityManager.flush();
            entityTransaction.commit();
            response.addData("id", newMessage.getId());
            response.addSuccess("Wiadomość została zapisana");
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	/**
	 * Change content of the concrete message
	 * @param id Message id
	 * @param token Session token
	 * @param newMessage Message object containing new content
	 * @return
	 */
	@PUT
	@Path("{id}")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response change(@PathParam("id") Integer id, @HeaderParam("X-Token") String token, Message newMessage) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.WORKER, token);
        	User user = getSessionByToken(token).getUser();
        	Message dbMessage = (Message) entityManager.find(Message.class, id);
        	if(dbMessage != null) {
        		if(!dbMessage.getAuthor().getId().equals(user.getId())) {
	        		throw new ForbiddenRequest(user);
	        	}
	        	if(newMessage.getContent() != null) {
	        		dbMessage.setContent(newMessage.getContent());
	        	}
	        	entityTransaction.begin();
	            entityManager.merge(dbMessage);
	            entityTransaction.commit();
	        	response.addSuccess("Wiadomość została zmieniona");
        	} else {
        		response.setResponse(0);
        		response.addError("Nie znaleziono wiadomości o id "+id);
        	}
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
	
	/**
	 * Delete the concrete message
	 * @param id Message id
	 * @param token Session token
	 * @return
	 */
	@DELETE
	@Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") Integer id, @HeaderParam("X-Token") String token) {
        Response response = new Response(1);
        try {
        	authorize(AccountType.Types.MANAGER, token);
        	User user = getSessionByToken(token).getUser();
        	Message message = (Message) entityManager.find(Message.class, id);
        	if(message != null) {
        		if(!message.getAuthor().getId().equals(user.getId())) {
	        		throw new ForbiddenRequest(user);
	        	}
        		entityTransaction.begin();
            	entityManager.remove(message);
            	entityTransaction.commit();
            	response.addSuccess("Wiadomość została usunięta");
        	} else {
        		response.addError("Nie znaleziono wiadomości o id "+id);
        	}
        } catch (ForbiddenRequest e) {
        	response.setResponse(0);
        	response.addError("Nie masz dostępu do tego zasobu");
        } catch (Exception e) {
            System.out.println("Failed !!! " + e.getMessage());
        }
        return response;
    }
}
