package ch.heigvd.amt.amtproject.rest.resources;

import ch.heigvd.amt.amtproject.exception.SQLExceptionWrapper;
import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.rest.dto.IdUserDTO;
import ch.heigvd.amt.amtproject.rest.dto.PasswordUserDTO;
import ch.heigvd.amt.amtproject.rest.dto.UserDTO;
import ch.heigvd.amt.amtproject.services.UserDAOLocal;
import ch.heigvd.amt.amtproject.util.Errors;
import ch.heigvd.amt.amtproject.util.FieldLength;
import ch.heigvd.amt.amtproject.util.PATCH;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines the REST API for the /users uri.
 *
 * @author Benjamin Schubert and Basile Vu
 */
@Stateless
@Path("/users")
public class UserResource {
    @EJB
    UserDAOLocal userDAO;

    @Context
    UriInfo uriInfo;

    /**
     * Get the info of a user, using his username.
     *
     * @param username The user's username.
     * @return A response containing the user and a "success" status if user exists, otherwise a "not found" status.
     * @throws SQLExceptionWrapper if there is an error related to the db accessed by the UserDAO.
     */
    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam(value="username") String username) throws SQLExceptionWrapper {
        User u;
        u = userDAO.get(username);
        if (u == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response
                .ok(new IdUserDTO(u.getId(), u.getUsername(), u.getQuote()))
                .build();
    }

    /**
     * Get the info of every user registered.
     *
     * @return A response containing a "success" status and the users.
     * @throws SQLExceptionWrapper if there is an error related to the db accessed by the UserDAO.
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() throws SQLExceptionWrapper {
        List<UserDTO> res = new ArrayList<>();
        userDAO.getAll()
                .forEach(u -> res.add(
                        new IdUserDTO(u.getId(), u.getUsername(), u.getQuote()))
                );
        return Response
                .ok(res)
                .build();
    }

    /**
     * Create a new user with the fields passed in the json.
     *
     * At least these values must be passed in the json:
     * - "username" : the user's username,
     * - "password" : the user's password.
     *
     * The user's quote can optionally be specified with the "quote" key.
     *
     * @param user The user info that will be stored in the PasswordUserDTO.
     * @return A response containing the uri of the user and a "created" status if successful,
     *         a "conflict" status if the user already exists or
     *         an error and a "bad request" status if one of the field is incorrect.
     * @throws SQLExceptionWrapper if there is an error related to the db accessed by the UserDAO.
     */
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(PasswordUserDTO user) throws SQLExceptionWrapper {
        String username = user.getUsername();
        String password = user.getPassword();
        String quote = user.getQuote();

        if (username == null || username.isEmpty()) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Errors.USERNAME_MISSING)
                    .build();
        }

        if ( password == null || password.isEmpty()) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Errors.PASSWORD_MISSING)
                    .build();
        }

        if (username.length() > FieldLength.USERNAME_MAX_LENGTH ) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Errors.USERNAME_TOO_LONG)
                    .build();
        }

        if (password.length() > FieldLength.PASSWORD_MAX_LENGTH) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Errors.PASSWORD_TOO_LONG)
                    .build();
        }

        if (quote != null && quote.length() > FieldLength.QUOTE_MAX_LENGTH) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(Errors.QUOTE_TOO_LONG)
                    .build();
        }

        if (userDAO.get(username) != null) {
            return Response
                    .status(Response.Status.CONFLICT)
                    .build();
        }
        userDAO.create(username, password, (quote != null) ? quote : "");
        URI href = uriInfo
                .getBaseUriBuilder()
                .path(UserResource.class)
                .path(UserResource.class, "getUser")
                .build(username);

        return Response.created(href).build();
    }

    /**
     * Partially updates the info of an user, given his username.
     *
     * The values passed in the json should correspond to the fields in the PasswordUserDTO, namely:
     * - "username" : the user's username,
     * - "password" : the user's password,
     * - "quote" : the user's quote.
     *
     * Only the values specified will be updated.
     *
     * @param username The user's username.
     * @param user The values that will be put in a PasswordUserDTO.
     * @return A response containing
     *         a "no content" status if successful,
     *         an error with a "bad request" status if parameters are incorrect or
     *         a "not found" status if user doesn't exist.
     * @throws SQLExceptionWrapper if there is an error related to the db accessed by the UserDAO.
     */
    @PATCH
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("username") String username, PasswordUserDTO user) throws SQLExceptionWrapper {
        User u = userDAO.get(username);
        if (u == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        if (user.getPassword() != null) {
            if (user.getPassword().length() > FieldLength.PASSWORD_MAX_LENGTH) {
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(Errors.PASSWORD_TOO_LONG)
                        .build();
            }

            u.setPassword(user.getPassword());
        }

        if (user.getQuote() != null) {
            if (user.getQuote().length() > FieldLength.QUOTE_MAX_LENGTH) {
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(Errors.QUOTE_TOO_LONG)
                        .build();
            }

            u.setQuote(user.getQuote());
        }

        userDAO.update(u);

        return Response
                .noContent()
                .build();
    }

    /**
     * Deletes an user based on the given username.
     *
     * @param username The username of the user to delete.
     * @return A response containing a "no content" status or a "not found" status if user doesn't exist.
     * @throws SQLExceptionWrapper if there is an error related to the db accessed by the UserDAO.
     */
    @DELETE
    @Path("/{username}")
    public Response delete(@PathParam(value="username") String username) throws SQLExceptionWrapper {
        if (userDAO.get(username) == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        userDAO.delete(username);
        return Response
                .noContent()
                .build();
    }
}
