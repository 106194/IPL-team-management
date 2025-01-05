package fahim.fahim22;

import java.net.Socket;

public class ClientThread implements Runnable{

    Socket socket;
    Thread t;
    public ClientThread(Socket socket){
        this.socket=socket;
        t=new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        try {
            while(true){
                Object obj=Client.ois.readObject();
                if(obj instanceof Data) {
                    Data d=(Data)obj;
                    System.out.println("size: " + d.available.size());
                    MainMenuApplication.players.set(d.str, d.available);
                }
                else if(obj instanceof String){
                    break;
                }
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
}
