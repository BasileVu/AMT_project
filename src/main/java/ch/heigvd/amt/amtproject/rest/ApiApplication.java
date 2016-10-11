package ch.heigvd.amt.amtproject.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

@ApplicationPath("/api")
public class ApiApplication extends Application {
    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.disable.MoxyJson", true);
        return properties;
    }
}
