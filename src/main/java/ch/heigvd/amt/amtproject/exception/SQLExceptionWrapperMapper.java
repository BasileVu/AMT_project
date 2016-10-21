package ch.heigvd.amt.amtproject.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Allows handling of SQLExceptionWrapper when it is thrown in the REST API.
 *
 * @author Benjamin Schubert and Basile Vu
 */
@Provider
public class SQLExceptionWrapperMapper implements ExceptionMapper<SQLExceptionWrapper> {
    @Override
    public Response toResponse(SQLExceptionWrapper exception) {
        return Response
                .serverError()
                .build();
    }
}
