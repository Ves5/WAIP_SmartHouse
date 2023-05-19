package pg.waip.smarthouse;

import com.ericsson.hosasdk.api.hui.IpAppHosaUIManager;
import com.ericsson.hosasdk.api.hui.IpAppHosaUIManagerAdapter;
import com.ericsson.hosasdk.utility.framework.FWproxy;

/**
 * High possibility that messaging and user location will need to be separate classes :<
 * mostly cause idk how this all works
 * this whole project from this point on is "Here be dragons"
 */
public class House {

    private Feature feature;
    private FWproxy framework;

    public House(Feature f, FWproxy fw){
        feature = f;
        framework = fw;
    }

    public void start(){

    }
    public void stop(){

    }
}
