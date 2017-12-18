import com.pi4j.io.serial.*;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@Startup
public class ArduinoBoard {
    private final Serial serial = SerialFactory.createInstance();
    private boolean online;
    private Logger LOGGER = Logger.getLogger(this.getClass().getSimpleName());
    private String received;
    private Orientation orientation = null;
    private boolean orientationAvailable;

    @PostConstruct
    public void init(){
        LOGGER.log(Level.INFO, "init Arduino Board");
        try {
            SerialConfig config = new SerialConfig();
            // set default serial settings (device, baud rate, flow control, etc)
            //
            // by default, use the DEFAULT com port on the Raspberry Pi (exposed on GPIO header)
            // NOTE: this utility method will determine the default serial port for the
            //       detected platform and board/model.  For all Raspberry Pi models
            //       except the 3B, it will return "/dev/ttyAMA0".  For Raspberry Pi
            //       model 3B may return "/dev/ttyS0" or "/dev/ttyAMA0" depending on
            //       environment configuration.
            config.device("/dev/ttyACM0")
                    .baud(Baud._38400)
                    .dataBits(DataBits._8)
                    .parity(Parity.NONE)
                    .stopBits(StopBits._1)
                    .flowControl(FlowControl.NONE);
            serial.open(config);
            serial.addListener( e ->
            {   LOGGER.log(Level.INFO, "Message received from arduino Serial. Time=" +System.currentTimeMillis());
                try {
                    String rcv = e.getAsciiString();
                    received += rcv;
                    LOGGER.log(Level.INFO, "Received: rcv =  " + rcv);
                    parseResponse(rcv);
                } catch (IOException ex) { LOGGER.log(Level.WARNING,"Exception: " + ex.getMessage());}
            });
            online = true;
            LOGGER.log(Level.INFO, "init Arduino Board success");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to init arduino board");
        }
    }

    private void parseResponse(String received) {
        LOGGER.log(Level.INFO, "Parsing message: " + received);
        LOGGER.log(Level.INFO, "regex match: " + received.matches("\r\n[a-zA-Z0-9.\\-\t]*\r\n"));
        if(received.matches("\r\n[a-zA-Z0-9.\\-\t]*\r\n")){
            String[] cmds = received.split("\r\n");
            parseCommand(cmds[1]);
            //delete all commands but the last (not parsed yet)
            this.received.substring(this.received.lastIndexOf("\r\n"));
        }
    }

    private void parseCommand(String cmd) {
        LOGGER.log(Level.INFO, "Parsing command: " + cmd);
        String[] params = cmd.split("\\t");
        LOGGER.log(Level.INFO, "Array splitted: " + Arrays.toString(params));
        this.orientation = new Orientation();
        orientation.setYaw(Float.parseFloat(params[0]));
        orientation.setPitch(Float.parseFloat(params[1]));
        orientation.setRoll(Float.parseFloat(params[2]));
        orientationAvailable = true;
    }

    public void write(byte[] bytes) throws IOException {
        serial.write(bytes);
    }

    public byte[] read(int i) throws IOException {
        return serial.read(i);
    }

    public InputStream getInputStream() {
        return serial.getInputStream();
    }

    public OutputStream getOutputStream() {
        return serial.getOutputStream();
    }

    public Orientation requestYPRAndWait(long timeout) throws IOException, TimeoutException{
        LOGGER.log(Level.INFO, "Receveid requestYPRAndWait for " + timeout + "ms");
        serial.write('0');
        serial.write(new char[]{'\r','\n'});
        boolean timeoutCondition = true;
        long time = System.currentTimeMillis();
        while(System.currentTimeMillis() - time < timeout) {
            if (orientationAvailable()) {
                timeoutCondition = false;
                break;
            }
        }
        if (timeoutCondition) {
            LOGGER.log(Level.WARNING, "Timeout reached without response, time: " + System.currentTimeMillis());
            throw new TimeoutException();
        }
        LOGGER.log(Level.INFO, "Successfully received orientation from sensor");
        return getOrientation();
    }

    public boolean orientationAvailable(){
        return orientationAvailable;
    }

    public Orientation getOrientation(){
        Orientation copy = orientation.clone();
        orientation = null;
        orientationAvailable = false;
        return copy;
    }
}
