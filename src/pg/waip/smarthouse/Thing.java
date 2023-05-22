package pg.waip.smarthouse;

public abstract class Thing {
    protected String id;
    public String name;

    protected House house;


    public abstract void action(String command, String senderData);

    public String getId(){
        return id;
    }

    public void setHouse(House h){
        house = h;
    }
}
