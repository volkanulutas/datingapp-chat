package com.datingapp.login.constants;


/**
 * Datingapp on 20.03.2020
 * <p>
 * This class includes the name and API end points of other microservices that we need to communicate.
 * NOTE: WRITE EVERYTHING IN ALPHABETICAL ORDER
 *
 * @author volkanulutas
 */
public class MicroServiceConstants {

    public static final String BASE_API = "/api";

    public interface AdminMicroServiceConstants {
        String BASE = "datingapp-auth-demo";
        String SEARCH_ADMIN = "/search";
        String UPDATE_ADMIN = "/update";
    }
}
