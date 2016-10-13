package ch.heigvd.amt.amtproject.rest.resources;

import ch.heigvd.amt.amtproject.dao.UserDAO;
import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.rest.dto.RegisterUserDTO;
import ch.heigvd.amt.amtproject.rest.dto.UserDTO;

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
    UserDAO userDAO;

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam(value="username") String username) {
        User u = userDAO.get(username);
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
        userDAO.getAll().forEach(a -> res.add(new UserDTO(a.getUsername())));
        return res;
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(RegisterUserDTO user) {
        userDAO.create(new User(user.getUsername(), user.getPassword()));
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{username}")
    public Response delete(@PathParam(value="username") String username) {
        if (!userDAO.delete(username)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
