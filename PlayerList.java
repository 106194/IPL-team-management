package fahim.fahim22;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
class PlayerList {

    public ArrayList<Players> players;
    public ArrayList<Players>available;
    Scanner sc = new Scanner(System.in);
    PlayerList(ArrayList<Players> List,ArrayList<Players>ab) {
        this.players=List;
        this.available=ab;
    }
    public void set(ArrayList<Players> List,ArrayList<Players>ab){
        this.players=List;
        this.available=ab;
    }
    public ArrayList<Players>SearchByClub(String club){
        int i, flag = 0;
        ArrayList<Players> Clubs = new ArrayList<>();
        Players obj = new Players();
        for (i = 0; i < players.size(); i++) {
            if (players.get(i).getClub().equalsIgnoreCase(club)) {
                Clubs.add(players.get(i));
                flag = 1;
            }
        }
        if (flag == 0) {
            return null;
        }
        return Clubs;
    }
    public ArrayList<Players>SearchByClubAvailable(String club){

        return this.available;
    }
    public Players ByPlayerName(String name) {
        int i;
        Players obj = new Players();
        for (i = 0; i < players.size(); i++) {
            obj = players.get(i);
            if (obj.getName().equalsIgnoreCase(name)) {
                break;
            }
        }
        if (i == players.size()) {
            return null;
        }
        return obj;
    }

    public ArrayList<Players> ByClubCountryName(String country, String club) {
        int i, flag = 0;
        ArrayList<Players> Clubs = new ArrayList<>();
        Players obj = new Players();
        for (i = 0; i < players.size(); i++) {
            if (players.get(i).getClub().equalsIgnoreCase(club) && players.get(i).getCountry().equalsIgnoreCase(country)) {
                Clubs.add(players.get(i));
                flag = 1;
            }
        }
        if (flag == 0) {
            return null;
        }
        return Clubs;
    }

    public ArrayList<Players> searchByPosition(String position) {
        int i, flag = 0;
        ArrayList<Players> Clubs = new ArrayList<>();
        Players obj = new Players();

        for (i = 0; i < players.size(); i++) {
            obj = players.get(i);
            if (obj.getPosition().equalsIgnoreCase(position)) {
                Clubs.add(obj);
                flag = 1;
            }
        }
        if (flag == 0) {
            return null;
        }
        return Clubs;
    }

    public ArrayList<Players> SearchBySalary(int x, int y) {
        int i, flag = 0;
        System.out.println(x);
        System.out.println(y);
        ArrayList<Players> Clubs = new ArrayList<>();
        Players obj = new Players();
        for (i = 0; i < players.size(); i++) {
            obj = players.get(i);
            int z = obj.getWeeklySalary();
            if (z >= x && z <= y) {
                Clubs.add(obj);
                flag = 1;
            }
        }
        if (flag == 0) {
            return null;
        }
        return Clubs;
    }

    public HashMap<String, Integer> CountryCount() {
        int i, j;
        int size = players.size();
        HashMap<String, Integer> mp = new HashMap<>();
        String temp;
        for (i = 0; i < size; i++) {
            temp = players.get(i).getCountry();
            if (mp.containsKey(temp)) {
                mp.put(temp, mp.get(temp) + 1);
            } else {
                mp.put(temp, 1);
            }
        }
        return mp;
    }

    public ArrayList<Players> MaxSalary(String club) {
        ArrayList<Players> Clubs = new ArrayList<>();
        int max=-1;
        int i, flag = 0;
        for (i = 0; i < players.size(); i++) {
            if (players.get(i).getClub().equalsIgnoreCase(club)) {
                flag = 1;
                if (max < players.get(i).getWeeklySalary()) {
                    max = players.get(i).getWeeklySalary();
                }
            }
        }
        if (flag == 0) {
            return null;
        }
        for (i = 0; i < players.size(); i++) {
            if (players.get(i).getClub().equalsIgnoreCase(club)) {
                flag = 1;
                if (max == players.get(i).getWeeklySalary()) {
                    Clubs.add(players.get(i));
                }
            }
        }
        return Clubs;
    }

    public ArrayList<Players> MaxAge(String club) {
        ArrayList<Players> Clubs = new ArrayList<>();
        int age=-1;
        int i, flag = 0;
        for (i = 0; i < players.size(); i++) {
            if (players.get(i).getClub().equalsIgnoreCase(club)) {
                flag = 1;
                if (age< players.get(i).getAge()) {
                    age = players.get(i).getAge();
                }
            }
        }
        if (flag == 0) {
            return null;
        }
        int max = age;
        for (i = 0; i < players.size(); i++) {
            if (players.get(i).getClub().equalsIgnoreCase(club)) {
                flag = 1;
                if (max == players.get(i).getAge()) {
                    Clubs.add(players.get(i));
                }
            }
        }
        return Clubs;
    }

    public ArrayList<Players> MaxHeight(String club) {
        ArrayList<Players> Clubs = new ArrayList<>();
        double max=-1.0;
        int i, flag = 0;
        for (i = 0; i < players.size(); i++) {
            if (players.get(i).getClub().equalsIgnoreCase(club)) {
                flag = 1;
                if (max < players.get(i).getHeight()) {
                    max = players.get(i).getHeight();
                }
            }
        }
        if (flag == 0) {
            return null;
        }
        for (i = 0; i < players.size(); i++) {
            if (players.get(i).getClub().equalsIgnoreCase(club)) {
                flag = 1;
                if (max == players.get(i).getHeight()) {
                    Clubs.add(players.get(i));
                }
            }
        }
        return Clubs;
    }

    public long TotalSalary(String club) {
        long sum = 0;
        int i, flag = 0;
        for (i = 0; i < players.size(); i++) {
            Players obj = players.get(i);
            if (obj.getClub().equalsIgnoreCase(club)) {
                flag = 1;
                sum += obj.getWeeklySalary();
            }
        }
        if (flag == 0) {
            return -1;
        }
        return sum * 52;
    }

    public void addPlayer(Players obj) {
        players.add(obj);
    }
}
