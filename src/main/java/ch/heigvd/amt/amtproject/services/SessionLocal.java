package ch.heigvd.amt.amtproject.services;


import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

@Local
public interface SessionLocal {
    boolean isCurrentUserConnected(HttpServletRequest request);
    void connectCurrentUser(HttpServletRequest request, String username);
    void disconnectCurrentUser(HttpServletRequest request);
}
