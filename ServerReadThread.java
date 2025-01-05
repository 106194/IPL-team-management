package fahim.fahim22;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerReadThread implements Runnable{
    ObjectInputStream ois;
    ObjectOutputStream oos;
    Socket socket;
    Thread t;
    String Name;
    volatile ArrayList<Players> str;
    volatile ArrayList<Players>available;
    ServerReadThread(Socket socket,ArrayList<Players> str,ArrayList<Players>available){
        this.socket=socket;
        this.str=str;
        this.available=available;
        t=new Thread(this);
    }

    @Override
    public void run() {
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            Object o=ois.readObject();
            Name=(String)o;
            String[] splt = ((String) Name).split(",");
            System.out.println("sell");
            if(splt[0].equals("sell")){
                Players ob=SearchPlayer(splt[1]);
                str.remove(ob);
                str.add(ob);
                available.add(ob);
            }
            else{
                Players ob=SearchPlayer(splt[1]);
                str.remove(ob);
                ob.setClub(splt[2]);
                str.add(ob);
                available.add(ob);
            }
            Data d=new Data(str,available);
            oos.writeObject(d);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
    public Players SearchPlayer(String Name){
        for(int i=0;i<str.size();i++){
            if(str.get(i).getName().equalsIgnoreCase(Name)){
                return str.get(i);
            }
        }
        return null;
    }
}
