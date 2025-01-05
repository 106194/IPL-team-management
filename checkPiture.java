package fahim.fahim22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class checkPiture {
    public boolean check(String name){
        ArrayList<String> str=new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("picture.txt"));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                str.add(line);
            }
            br.close();
        }
        catch (Exception ex){

        }
        for(int i=0;i<str.size();i++){
            String splt[]=str.get(i).split(",");
            if(splt[0].equals(name)){
                return true;
            }
        }
        return false;
    }
}
