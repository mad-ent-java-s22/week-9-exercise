package org.davidcalabrese.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.davidcalabrese.services.UserService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class App extends Application {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Override
    public Set<Class<?>> getClasses() {
        logger.info("in App.getClasses()");
        HashSet h = new HashSet<Class<?>>();
        h.add(UserService.class);
        return h;
    }
}