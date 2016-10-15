package ch.heigvd.amt.amtproject.rest.resources;

import ch.heigvd.amt.amtproject.dao.UserDAO;
import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.rest.dto.PasswordUserDTO;
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
        User u;
        try {
            u = userDAO.get(username);
            if (u == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(new UserDTO(u.getUsername(), u.getQuote())).build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        List<UserDTO> res = new ArrayList<>();
        try {
            userDAO.getAll().forEach(u -> res.add(new UserDTO(u.getUsername(), u.getQuote())));
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(res).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(PasswordUserDTO user) {
        try {
            userDAO.create(new User(user.getUsername(), user.getPassword()));
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("username") String username, PasswordUserDTO user) {
        try {
            User u = userDAO.get(username);
            if (u == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            if (user.getPassword() != null) {
                u.setPassword(user.getPassword());
            }

            if (user.getQuote() != null) {
                u.setQuote(user.getQuote());
            }

            userDAO.update(u);
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{username}")
    public Response delete(@PathParam(value="username") String username) {
        try {
            userDAO.delete(username);
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
