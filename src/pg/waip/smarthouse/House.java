package pg.waip.smarthouse;

import com.ericsson.hosasdk.api.*;
import com.ericsson.hosasdk.api.fw.P_UNKNOWN_SERVICE_TYPE;
import com.ericsson.hosasdk.api.hui.IpAppHosaUIManager;
import com.ericsson.hosasdk.api.hui.IpAppHosaUIManagerAdapter;
import com.ericsson.hosasdk.api.hui.IpHosaUIManager;
import com.ericsson.hosasdk.api.mm.ul.IpAppUserLocation;
import com.ericsson.hosasdk.api.mm.ul.IpAppUserLocationAdapter;
import com.ericsson.hosasdk.api.mm.ul.IpUserLocation;
import com.ericsson.hosasdk.api.ui.IpAppUI;
import com.ericsson.hosasdk.api.ui.TpUIEventCriteria;
import com.ericsson.hosasdk.api.ui.TpUIEventInfo;
import com.ericsson.hosasdk.api.ui.TpUIIdentifier;
import com.ericsson.hosasdk.utility.framework.FWproxy;
import com.ericsson.nrgsdk.examples.tools.SDKToolkit;

/**
 * this whole project from this point on is "Here be dragons"
 */
public class House extends IpAppHosaUIManagerAdapter implements IpAppHosaUIManager{

    private IpHosaUIManager msgService;
    private int msgServiceAssignmentId;

    private Feature feature;
    private FWproxy framework;
    private LocationTracker locService;

    private String serviceNumber;
    private String ownerNumber;

    public House(Feature f, FWproxy fw){
        feature = f;
        framework = fw;

        serviceNumber = Configuration.INSTANCE.getProperty("service.number");
        ownerNumber = Configuration.INSTANCE.getProperty("house.owner");
        /**
         * spawn all controllers
         */
    }

    public void start(){
        try {
            System.out.println("Trying to obtain User Interaction SCF");
            msgService = (IpHosaUIManager) framework.obtainSCF(IpHosaUIManager.class, "SP_HOSA_USER_INTERACTION");
        } catch (P_UNKNOWN_SERVICE_TYPE e){
            System.err.println("Service not found: " + e);
        }
        TpAddressRange origin = SDKToolkit.createTpAddressRange("*");
        TpAddressRange destination = SDKToolkit.createTpAddressRange(serviceNumber);

        String serviceCode = "00";
        TpUIEventCriteria criteria = new TpUIEventCriteria(origin, destination, serviceCode);

        msgServiceAssignmentId = msgService.createNotification(this, criteria);

        /**
         * init location service
         */
        locService = new LocationTracker(feature, framework);

        System.out.println("\nFinished starting SmartHouse application.\n");
    }
    public void stop(){
        try {
            msgService.destroyNotification(msgServiceAssignmentId);
        } finally {
            this.dispose();
        }
    }

    public IpAppUI reportNotification(TpUIIdentifier uiIdentifier, TpUIEventInfo uiInfo, int i){
        TpAddress originNumber = uiInfo.OriginatingAddress;
        TpAddress destinationNumber = uiInfo.DestinationAddress;
        System.out.println("Received SMS from " + originNumber.AddrString + ": " + uiInfo.DataString);
        /**
        * parse command and act accordingly
        * */
        // debug/test call for sendSMS
//        sendSMS(destinationNumber, originNumber, "Witty test reply.");
//        notifySMS("Witty notification");
//        replySMS(originNumber.AddrString, "Witty reply");
        return null;
    }

    private void parseCommand(){}

    private void sendSMS(TpAddress originAddress, TpAddress destinationAddress, String content){
        System.out.println("Sending SMS to " + destinationAddress.AddrString + ": " + content);
        // it needs to be done this way according to examples
        TpHosaTerminatingAddressList recipients = new TpHosaTerminatingAddressList();
        recipients.ToAddressList = new TpAddress[1];
        recipients.ToAddressList[0] = destinationAddress;
        String subject = "";
        // creating message
        TpHosaMessage message = new TpHosaMessage();
        message.Text(content);
        // sending as SMS
        TpHosaUIMessageDeliveryType deliveryType = TpHosaUIMessageDeliveryType.P_HUI_SMS;
        String billing = "for free";
        int response = 1;
        boolean delivNotif = false;
        // creating dummy delivery time (immediate)
        TpHosaDeliveryTime delivTime = new TpHosaDeliveryTime();
        delivTime.Dummy((short) 0);
        String validTime = "2010-01-01 00:00:00.000";
        // send SMS
        msgService.hosaSendMessageReq(this, originAddress, recipients,
                subject, message, deliveryType, billing,
                response, delivNotif, delivTime, validTime);
    }

    public void replySMS(String destAddr, String content){
        TpAddress destinationAddress = SDKToolkit.createTpAddress(destAddr);
        TpAddress originAddress = SDKToolkit.createTpAddress(serviceNumber);

        sendSMS(originAddress, destinationAddress, content);
    }
    public void notifySMS(String content){
        TpAddress destinationAddress = SDKToolkit.createTpAddress(ownerNumber);
        TpAddress originAddress = SDKToolkit.createTpAddress(serviceNumber);

        sendSMS(originAddress, destinationAddress, content);
    }

    /**
     * Location service class separate cause it's all so confusing
     */
    private class LocationTracker extends IpAppUserLocationAdapter implements IpAppUserLocation {
        private Feature feature;
        private FWproxy framework;
        private IpUserLocation locService;

        public LocationTracker(Feature f, FWproxy fw){
            feature = f;
            framework = fw;

            System.out.println("Trying to obtain User Location service");
            try {
                locService = (IpUserLocation) framework.obtainSCF(IpUserLocation.class, "P_USER_LOCATION");
            } catch (P_UNKNOWN_SERVICE_TYPE e){
                System.err.println("Service not found: " + e);
            }
        }

    }

}