package uk.ac.westminster.config;

/*
 * Import the HTTP server used by Jersey.
 * Grizzly is a lightweight HTTP server that allows us
 * to run our REST API locally without installing Tomcat or another server.
 */
import org.glassfish.grizzly.http.server.HttpServer;

/*
 * Factory class used to create and start the Grizzly HTTP server.
 */
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

/*   
 * ResourceConfig is used to register the REST resources (endpoints)
 * that our API will expose.
 */
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import static jdk.jfr.FlightRecorder.register;

/*
 * Main class responsible for starting the REST API server.
 */
public class Main {

    /*
     * Base URI where the API will run.
     * The API will be accessible at:
     * http://localhost:8081/api/v1
     */
    public static final String BASE_URI = "http://localhost:8081/";

    /*
     * Method responsible for creating and starting the HTTP server.
     */
    public static HttpServer startServer() {

        /*
         * Configure Jersey to scan the package
         * where the REST resources are located.
         */
       ResourceConfig rc = new ResourceConfig()
        .packages("uk.ac.westminster.resource");
        
        /*
         * Create and start the Grizzly HTTP server.
         */
        return GrizzlyHttpServerFactory.createHttpServer(
                URI.create(BASE_URI + "api/v1/"),
                rc
        );
    }

    /*
     * Application entry point.
     * This method runs when we start the program.
     */
    public static void main(String[] args) throws IOException {

        /*
         * Start the REST server.
         */
        HttpServer server = startServer();

        System.out.println("Server started at: " + BASE_URI);
        System.out.println("Open in browser: http://localhost:8081/api/v1");

        /*
         * Keep the server running until the user presses ENTER.
         */
        System.in.read();

        /*
         * Shut down the server when the program stops.
         */
        server.shutdownNow();
    }
}
