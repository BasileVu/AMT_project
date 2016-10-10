package ch.heigvd.amt.amtproject.rest.resources;

import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.rest.dto.UserDTO;
import ch.heigvd.amt.amtproject.services.UserManagerLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("/users")
public class UserResource {
    @EJB
    UserManagerLocal userManager;

    @GET
    @Path("/{username}/info")
    @Produces("application/json")
    public Response getUserInfo(@PathParam(value="username") String username) {
        User u = userManager.get(username);
        if (u == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new UserDTO(u.getUsername())).build();
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public List<UserDTO> getUsers() {
        System.out.println("test");
        List<UserDTO> res = new ArrayList<>();
        userManager.getAllUsers().forEach(a -> res.add(new UserDTO(a.getUsername())));
        return res;
    }
}
