package pg.waip.smarthouse;

import com.ericsson.hosasdk.utility.framework.FWproxy;

public class Feature {

    private House house;

    public Feature(FWproxy fw){
        house = new House(this, fw);
    }

    public void start(){
        house.start();
    }

    public void stop(){
        house.stop();
    }
}
