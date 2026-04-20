
package uk.ac.westminster.resource;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.LinkedHashMap;
import java.util.Map;
/*
 * Discovery endpoint for the API.
 * Provides basic information about the API and available resources.
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
/**
 *
 * @author w1994214
 */
public class DiscoveryResource {
    
    /*
     * Handles GET requests to /api/v1
     */
    @GET
    public Map<String,Object> discovery(){

        Map<String,String> links = new LinkedHashMap<>();

        links.put("rooms","/api/v1/rooms");
        links.put("sensors","/api/v1/sensors");

        Map<String,Object> response = new LinkedHashMap<>();

        response.put("version","v1");
        response.put("links",links);

        return response;
    }
}
