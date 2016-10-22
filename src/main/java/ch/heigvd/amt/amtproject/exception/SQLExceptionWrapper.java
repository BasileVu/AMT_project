package ch.heigvd.amt.amtproject.exception;

import java.sql.SQLException;

/**
 * Exception wrapping an SQLException. Useful to bypass the EJBException nesting (SQLException are
 * nested by default by EJBs).
 *
 * @author Benjamin Schubert and Basile Vu
 */
public class SQLExceptionWrapper extends Exception {
    public SQLExceptionWrapper(SQLException e) {
        super(e);
    }
}
