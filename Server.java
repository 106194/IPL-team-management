package fahim.fahim22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    volatile static ArrayList<Players>str=new ArrayList<>();
    volatile static ArrayList<Players>available=new ArrayList<>();
    volatile static ArrayList<String>password=new ArrayList<>();
    static ArrayList<String>s;
    static ArrayList<String>ab;

    public static void main(String [] arges)throws Exception{
        HashMap<String,ObjectOutputStream>socketMap=new HashMap<>();
        FIO rw=new FIO();
        s=rw.FI("players.txt");
        ab=rw.FI("Available.txt");
        ServerSocket serverSocket;
        Convert();
        try {
            serverSocket = new ServerSocket(44444);
            while(true) {
                ObjectOutputStream oos ;
                ObjectInputStream ois;
                Socket socket = serverSocket.accept();
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());
                Object name = ois.readObject();
                System.out.println((String)name);
                String splt[]=((String)name).split(",");
                MainController obj=new MainController();
                String s=obj.ToUpper(splt[0]);
                System.out.println(s);
                if(socketMap.containsKey(s)){
                    oos.writeObject("invalid");
                    continue;
                }
                passWrite();
                String check=SearchByClub(splt[0],splt[1]);
                System.out.println(check);
                if(check==null){
                    oos.writeObject(null);
                }
                else {
                    socketMap.put(check,oos);
                    Data d = new Data(str, available);
                    oos.writeObject(d);
                    new threadClass(str, available, oos, ois, socketMap,password);
                }
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
    public static String SearchByClub(String club,String pass){
        int flag=0;
        String s=new String();
        for(int i=0;i<password.size();i++){
            String splt[]=password.get(i).split(",");
            if(splt[0].equalsIgnoreCase(club)&&splt[1].equals(pass)){
                flag=1;
                s=splt[0];
                break;
            }
        }
        if(flag==0)return null;
        else return s;
    }
    public static void passWrite()throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("check.txt"));
        int flag=0;
        String club=new String();
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            password.add(line);
        }
        br.close();
    }
    public static void Convert(){
        for (int i = 0; i < s.size(); i++) {
            String splt[] = s.get(i).split(",");
            String name=splt[0],country=splt[1],club=splt[4],position=splt[5];
            int age=Integer.parseInt(splt[2]),weeklySalary=Integer.parseInt(splt[7]),number=-1;
            double height=Double.parseDouble(splt[3]);
            if(splt[6].length()!=0){
                number=Integer.parseInt(splt[6]);
            }
            Players obj=new Players(name,country,club,position,age,weeklySalary,number,height);
            str.add(obj);
        }
        for (int i = 0; i < ab.size(); i++) {
            String splt[] = ab.get(i).split(",");
            String name=splt[0],country=splt[1],club=splt[4],position=splt[5];
            int age=Integer.parseInt(splt[2]),weeklySalary=Integer.parseInt(splt[7]),number=-1;
            double height=Double.parseDouble(splt[3]);
            if(splt[6].length()!=0){
                number=Integer.parseInt(splt[6]);
            }
            Players obj=new Players(name,country,club,position,age,weeklySalary,number,height);
            if(splt[8].equals("true")){
                obj.setPictureTrue();
            }
            available.add(obj);
        }
    }
}
