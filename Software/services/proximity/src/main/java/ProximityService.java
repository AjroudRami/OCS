import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/proximity/")
@Consumes(MediaType.APPLICATION_JSON)
public class ProximityService {
    private Logger LOGGER = Logger.getLogger(this.getClass().getSimpleName());
    @EJB
    private ArduinoBoard board;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Orientation getOrientation() {
        Orientation orientation = new Orientation();
        try {
            LOGGER.log(Level.INFO, "Received Orientation request");
            orientation = board.requestYPRAndWait(500);
            LOGGER.log(Level.INFO, "Successfully received orientation from sensor: " + orientation.toString());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Exception sent " + e.getMessage());
            e.printStackTrace();
        }
        return orientation;
    }
}
