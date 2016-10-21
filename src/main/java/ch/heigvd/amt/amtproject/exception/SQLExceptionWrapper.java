package ch.heigvd.amt.amtproject.exception;

import java.sql.SQLException;

/**
 * Created by Flagoul on 21.10.2016.
 */
public class SQLExceptionWrapper extends Exception {
    public SQLExceptionWrapper(SQLException e) {
        super(e);
    }
}
