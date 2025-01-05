package fahim.fahim22;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class threadClass implements Runnable{
    Thread t;
    volatile ArrayList<Players> str;
    volatile ArrayList<Players>available;
    ArrayList<String>password;
    ObjectOutputStream oos ;
    ObjectInputStream ois;
    HashMap<String,ObjectOutputStream>socketMap;
    public threadClass(ArrayList<Players> str,ArrayList<Players>available,ObjectOutputStream oos,ObjectInputStream ois,HashMap<String,ObjectOutputStream>socketMap,ArrayList<String>password)throws Exception{
        this.str=str;
        this.available=available;
        this.oos=oos;
        this.ois=ois;
        this.socketMap=socketMap;
        this.password=password;
        this.t=new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        try {
            while(true){
                System.out.println("Hello");
                Object name = ois.readObject();
                if(name==null)break;
                System.out.println("Hello1");
                String [] splt=((String)name).split(",");
                if(splt[0].equals("close")){
                    socketMap.remove(splt[1]);
                    oos.writeObject("close");
                    break;
                }
                if(splt[0].equals("pass")){
                    int i;
                    String nm=new String();
                    for(i=0;i<password.size();i++){
                        String sp[]=password.get(i).split(",");
                        if(sp[0].equalsIgnoreCase(splt[2])){
                            nm=sp[0];
                            break;
                        }
                    }
                    if(i< password.size()){
                        password.remove(i);
                        String newstr=new String(nm+","+splt[1]);
                        password.add(newstr);
                    }
                    BufferedWriter bw = new BufferedWriter(new FileWriter("check.txt"));
                    int sz=1;
                    for (String ob : password) {
                        bw.write(ob);
                        if(sz<password.size()) {
                            bw.write(System.lineSeparator());
                        }
                        sz++;
                    }
                    bw.close();
                }
                else if(splt[0].equals("cancel")){
                    Players ob=SearchPlayer(splt[1]);
                    if(ob!=null) {
                        available.remove(find(ob));
                    }
                }
                else if(splt[0].equals("sell")){
                    Players ob=SearchPlayer(splt[1]);
                    if(ob!=null) {
                        Players obj=NEW(ob);
                        available.add(obj);
                    }
                }
                else{
                    Players ob=SearchPlayer(splt[0]);
                    if(ob!=null) {
                        str.remove(ob);
                        available.remove(find(ob));
                        Players obj=NEW(ob);
                        obj.setClub(SearchByClub(splt[1]));
                        str.add(obj);
                    }
                }
                ArrayList<Players>obj1=new ArrayList<>(str);
                ArrayList<Players>obj2=new ArrayList<>(available);
                Data d=new Data(obj1,obj2);
                FIO rw=new FIO();
                rw.FO(obj1,obj2);
                for(Map.Entry<String,ObjectOutputStream>i:socketMap.entrySet()){
                    i.getValue().writeObject(d);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public boolean SearchClub(ArrayList<Players>str,String name){
        for(int i=0;i<str.size();i++){
            if(str.get(i).getClub().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public Players SearchPlayer(String Name){
        for(int i=0;i<str.size();i++){
            if(str.get(i).getName().equalsIgnoreCase(Name)){
                return str.get(i);
            }
        }
        return null;
    }
    public Players NEW(Players ob){
        Players obj=new Players(ob.getName(), ob.getCountry(), ob.getClub(), ob.getPosition(),ob.getAge(), ob.getWeeklySalary(), ob.getNumber(), ob.getHeight());
        return obj;
    }
    public int find(Players ob){
        for(int i=0;i<available.size();i++){
            if(available.get(i).getName().equalsIgnoreCase(ob.getName())){
                return i;
            }
        }
        return 0;
    }
    public String SearchByClub(String club){
         for(int i=0;i<str.size();i++){
             if(str.get(i).getClub().equalsIgnoreCase(club)){
                 return str.get(i).getClub();
             }
         }
         return null;
    }
}
