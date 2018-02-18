package fr.unice.polytech.ocs;

import fr.unice.polytech.ocs.entity.Orientation;
import org.fourthline.cling.binding.annotations.*;

@UpnpService(
        serviceId = @UpnpServiceId("EarthService"),
        serviceType = @UpnpServiceType(value = "EarthService", version = 1))
public class EarthService {

    @UpnpStateVariable(sendEvents = false)
    private int batteryState;

    @UpnpStateVariable(name = "Orientation", sendEvents = false)
    private String orientationString;

    @UpnpStateVariable(name = "red")
    private int red;

    @UpnpStateVariable(name = "green")
    private int green;

    @UpnpStateVariable(name = "blue")
    private int blue;

    @UpnpStateVariable(name = "text")
    private String text;

    @UpnpStateVariable(name = "lang")
    private String lang;


    private Orientation orientation;


    @UpnpAction(out = @UpnpOutputArgument(name = "BatteryState"))
    public int getBatteryState() {
        //TODO
        return batteryState;
    }

    @UpnpAction
    public void ledOn(@UpnpInputArgument(name = "red") int red,
                      @UpnpInputArgument(name = "green") int green,
                      @UpnpInputArgument(name = "blue") int bleu) {
        //TODO
    }

    @UpnpAction(out = @UpnpOutputArgument(name = "Orientation"))
    public String queryOrientation() {
        //TODO
        return orientation.toString();
    }

    @UpnpAction
    public void readText(@UpnpInputArgument(name = "text") String text,
                         @UpnpInputArgument(name = "lang") String lang) {
        //TODO
    }
}
