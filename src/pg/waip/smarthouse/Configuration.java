package pg.waip.smarthouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ericsson.nrgsdk.examples.tools.configuration.NestedProperties;

public class Configuration {
    public static final Configuration INSTANCE = new Configuration();

    private Configuration(){}

    private static String name = "test_name";





    public String getName(){
        return name;
    }
}
