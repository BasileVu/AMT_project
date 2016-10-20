package ch.heigvd.amt.amtproject.exception;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Allows handling of UnrecognizedPropertyException when it is thrown, for example when an unrecognized field is passed
 * in the Json sent to the REST API.
 *
 * @author Benjamin Schubert and Basile Vu
 */
@Provider
public class UnrecognizedPropertyExceptionMapper implements ExceptionMapper<UnrecognizedPropertyException> {
    @Override
    public Response toResponse(UnrecognizedPropertyException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("\"" + exception.getPropertyName() + "\" is an unrecognized field.")
                .build();
    }
}
