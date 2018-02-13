package com.subutai.nova.web;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@LocalBean
@Startup
@Singleton
public class MyUpnpServer {
    /**private static UpnpService upnpService;

    public MyUpnpServer() {
        try {
        } catch (Exception e) {
            System.out.println("DANNNNNNNNGERRRRRRRRRR");
        }
    }



    @PostConstruct
    public void initUpnp() {
        if (upnpService == null) return;
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
    //
    return new LocalService[0];
    }**/
}
