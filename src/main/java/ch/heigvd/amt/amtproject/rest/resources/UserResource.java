package ch.heigvd.amt.amtproject.rest.resources;

import ch.heigvd.amt.amtproject.services.UserManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

@Stateless
@Path("/users")
public class UserResource {
    @EJB
    UserManager userManager;

    @GET
    @Path("/{username}/info")
    @Produces("application/json")
    public String getUserInfo(@PathParam(value="username") String username) {
        return userManager.get(username).getUsername();
    }

}
