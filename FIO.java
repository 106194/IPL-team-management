package fahim.fahim22;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

class FIO
{
    ArrayList<String> str=new ArrayList<>();
    ArrayList<String>available=new ArrayList<>();
    public ArrayList<String> FI(String s)throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(s));
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            if(s.equalsIgnoreCase("players.txt")) {
                str.add(line);
            }
            else {
                available.add(line);
            }
        }
        br.close();
        if(s.equalsIgnoreCase("players.txt")) {
            return str;
        }
        return available;
    }
    public void FO(ArrayList<Players>pp,ArrayList<Players>available)throws Exception{
        BufferedWriter bw = new BufferedWriter(new FileWriter("players.txt"));
        for (Players i : pp) {
            String s=convert(i);
            bw.write(s);
            bw.write(System.lineSeparator());
        }
        bw.close();
        bw = new BufferedWriter(new FileWriter("Available.txt"));
        for (Players i : available) {
            String s=convert(i);
            bw.write(s);
            bw.write(System.lineSeparator());
        }
        bw.close();
    }
    public String convert(Players obj){
        String s;
        s=obj.getName();
        s=s.concat(",");
        s=s.concat(obj.getCountry());
        s=s.concat(",");
        s=s.concat(String.valueOf(obj.getAge()));
        s=s.concat(",");
        s=s.concat(String.valueOf(obj.getHeight()));
        s=s.concat(",");
        s=s.concat(obj.getClub());
        s=s.concat(",");
        s=s.concat(obj.getPosition());
        s=s.concat(",");
        if(obj.getNumber()!=-1){
            s=s.concat(String.valueOf(obj.getNumber()));
        }
        s=s.concat(",");
        s=s.concat(String.valueOf(obj.getWeeklySalary()));
        return s;
    }
}
