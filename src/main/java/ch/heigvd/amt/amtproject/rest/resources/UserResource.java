package ch.heigvd.amt.amtproject.rest.resources;

import ch.heigvd.amt.amtproject.exceptions.CreationFailedException;
import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.rest.dto.RegisterUserDTO;
import ch.heigvd.amt.amtproject.rest.dto.UserDTO;
import ch.heigvd.amt.amtproject.services.UserManagerLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("/users")
public class UserResource {
    @EJB
    UserManagerLocal userManager;

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInfo(@PathParam(value="username") String username) {
        User u = userManager.get(username);
        if (u == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new UserDTO(u.getUsername())).build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getUsers() {
        List<UserDTO> res = new ArrayList<>();
        userManager.getAllUsers().forEach(a -> res.add(new UserDTO(a.getUsername())));
        return res;
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(RegisterUserDTO user) {
        userManager.createUser(user.getUsername(), user.getPassword());
        return Response.status(Response.Status.CREATED).build();
    }
}
