package com.ericpol.lab.rest.client;

import javax.ws.rs.POST;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by xdzm on 2015-08-07.
 */
public class Config {


    private static final String ACCEPT = "client.accept";

    private static final String CONTENT_TYPE = "client.content.type";

    private static final String PROTOCOL = "client.protocol";

    private static final String HOST = "client.host";

    private static final String PORT = "client.port";

    private static final String CONTEXT = "client.context";

    private static final String USER = "client.user";

    private static final String PASSWORD = "client.password";






    private Logger logger = Logger.getLogger(Config.class.getName());


    private String host = "localhost";

    private String protocol = "http";

    private int port = 8080;

    private String context = "/stellar/webapi";

    private String contentType = "application/json";

    private String accept = "application/json";

    private String user = "manager";

    private String password = "manager123";


    public Config(){
        load();
    }

    private void load(){

        InputStream is = null;

        try {
            Properties prop = new Properties();
            String propFileName = "rest-client.properties";

            is = getClass().getClassLoader().getResourceAsStream(propFileName);

            if(is != null) {
                prop.load(is);
                logger.info("Configuraton loaded.");
                setAccept(getProperty(prop, ACCEPT, accept));
                setContentType(getProperty(prop, CONTENT_TYPE, contentType));
                setHost(getProperty(prop, HOST, host));
                setPort(Integer.parseInt(getProperty(prop, PORT)));
                setProtocol(getProperty(prop, PROTOCOL, protocol));
                setContext(getProperty(prop, CONTEXT, context));
                setUser(getProperty(prop, USER, user));
                setPassword(getProperty(prop, PASSWORD, password));
            } else {
                logger.warning("no config found, working on defaults");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "problem while loading configuration", e);
        } finally {
            try {
                if(is!=null)is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getProperty(Properties prop, String key, String... defaultValue) {
        String value = prop.getProperty(key);
        return value!=null ? value : defaultValue.length>0 ? defaultValue[0] : null;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String toURL(String uri){
        return protocol + "://" + host + ":" + port + context + uri;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
