package fahim.fahim22;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {
    ArrayList<Players> str;
    ArrayList<Players>available;
    public Data(ArrayList<Players>str,ArrayList<Players>available){
        this.str=str;
        this.available=available;
    }
}
