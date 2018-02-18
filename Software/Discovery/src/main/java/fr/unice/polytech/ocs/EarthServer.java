package fr.unice.polytech.ocs;

import org.fourthline.cling.UpnpService;
import org.fourthline.cling.UpnpServiceImpl;
import org.fourthline.cling.binding.LocalServiceBindingException;
import org.fourthline.cling.binding.annotations.AnnotationLocalServiceBinder;
import org.fourthline.cling.model.DefaultServiceManager;
import org.fourthline.cling.model.ValidationException;
import org.fourthline.cling.model.meta.*;
import org.fourthline.cling.model.types.DeviceType;
import org.fourthline.cling.model.types.UDADeviceType;
import org.fourthline.cling.model.types.UDN;

import java.io.IOException;

public class EarthServer implements Runnable {

    public static void main(String[] args) {
        // Start a user thread that runs the UPnP stack
        Thread serverThread = new Thread(new EarthServer());
        serverThread.setDaemon(false);
        serverThread.start();
    }

    public void run() {
        try {

            final UpnpService upnpService = new UpnpServiceImpl();

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    upnpService.shutdown();
                }
            });

            // Add the bound local device to the registry
            upnpService.getRegistry().addDevice(
                    createDevice()
            );

        } catch (Exception ex) {
            System.err.println("Exception occured: " + ex);
            ex.printStackTrace(System.err);
            System.exit(1);
        }
    }

    LocalDevice createDevice()
            throws ValidationException, LocalServiceBindingException, IOException {

        DeviceIdentity identity =
                new DeviceIdentity(
                        UDN.uniqueSystemIdentifier("ConnectedEarth1")
                );

        DeviceType type =
                new UDADeviceType("Earth", 1);

        DeviceDetails details =
                new DeviceDetails(
                        "Constellation - Earth",
                        new ManufacturerDetails("Polytech Nice Sophia-Antipolis"),
                        new ModelDetails(
                                "Earth",
                                "Master of all constellation objects",
                                "v1"
                        )
                );

        System.out.println(getClass().getResource("/icon.png"));

        Icon icon =
                new Icon(
                        "image/png", 48, 48, 8,
                        "icon", getClass().getResourceAsStream("/icon.png")
                );

        LocalService<EarthService> switchPowerService =
                new AnnotationLocalServiceBinder().read(EarthService.class);

        switchPowerService.setManager(
                new DefaultServiceManager(switchPowerService, EarthService.class)
        );

        return new LocalDevice(identity, type, details, icon, switchPowerService);

    /* Several services can be bound to the same device:
    return new LocalDevice(
            identity, type, details, icon,
            new LocalService[] {switchPowerService, myOtherService}
    );
    */

    }

}