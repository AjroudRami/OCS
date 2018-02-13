package com.subutai.nova.web;

import org.fourthline.cling.UpnpService;
import org.fourthline.cling.UpnpServiceImpl;
import org.fourthline.cling.binding.LocalServiceBindingException;
import org.fourthline.cling.model.ValidationException;
import org.fourthline.cling.model.meta.*;
import org.fourthline.cling.model.types.DeviceType;
import org.fourthline.cling.model.types.UDADeviceType;
import org.fourthline.cling.model.types.UDN;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;

@Startup
@Singleton
public class UpnpServer {

    private final UpnpService upnpService = new UpnpServiceImpl();


    @PostConstruct
    public void initUpnp() {
        // Add the bound local device to the registry
        try {
            upnpService.getRegistry().addDevice(createDevice());
        } catch (ValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    LocalDevice createDevice()
            throws ValidationException, LocalServiceBindingException, IOException {

        DeviceIdentity identity = new DeviceIdentity(
                UDN.uniqueSystemIdentifier("Constellation-SPH-9"));

        DeviceType type = new UDADeviceType("Constellation Sphere", 1);

        DeviceDetails details =
                new DeviceDetails(
                        "Constellation Sphere",
                        new ManufacturerDetails("ACME"),
                        new ModelDetails(
                                "SPH-9",
                                "A beautifull sphere with gyro and leds and stuffs",
                                "v1")
                );

        Icon icon = new Icon(
                "image/png", 48, 48, 8,
                getClass().getResource("icon.png")
        );

        LocalService[] services = getServices();

        return new LocalDevice(identity, type, details, icon, services);
    }


    private LocalService[] getServices() {
        //TODO
        /**
         *         LocalService<SwitchPower> switchPowerService =
         new AnnotationLocalServiceBinder().read(SwitchPower.class);

         switchPowerService.setManager(
         new DefaultServiceManager(switchPowerService, SwitchPower.class)
         );
         */
        return new LocalService[0];
    }
}
