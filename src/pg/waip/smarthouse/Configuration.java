package pg.waip.smarthouse;

import com.ericsson.nrgsdk.examples.tools.configuration.NestedProperties;

public class Configuration extends NestedProperties{
    public static final Configuration INSTANCE = new Configuration();

    private Configuration(){}

    /* Used for testing purposes, better to use the .ini file in /resources */
    private static String name = "test_name";
    public String getName(){
        return name;
    }
}
