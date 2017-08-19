package rest;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
public class RestApplication extends Application {
	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(api.Auth.class);
        resources.add(api.UserApi.class);
        resources.add(api.TicketApi.class);
        resources.add(api.DepartmentApi.class);
        resources.add(api.MessageApi.class);
        resources.add(api.MilestoneApi.class);
        resources.add(api.InvitationApi.class);
        return resources;
    }
}