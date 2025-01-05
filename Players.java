package fahim.fahim22;

import java.io.Serializable;

public class Players implements Serializable {
    String Name,TCountry,TClub,Position;
    int Age,Salary;
    int Number;
    boolean hasPicture;
    double Height;
    public Players(String name, String country, String club, String position, int age, int salary, int number, double height){
        this.Name=name;
        this.TCountry=country;
        this.TClub=club;
        this.Position=position;
        this.Age=age;
        this.Salary=salary;
        this.Number=number;
        this.Height=height;
        hasPicture=false;
    }
    public Players(){

    }
    public void setPictureTrue(){
        hasPicture=true;
    }
    public boolean getPicture(){
        return hasPicture;
    }
    public void setClub(String s){
        TClub=s;
    }
    public String getClub() {
        return TClub;
    }

    public String getName() {
        return Name;
    }
    public String getCountry() {
        return TCountry;
    }

    public String getPosition() {
        return Position;
    }

    public double getHeight() {
        return Height;
    }

    public int getNumber() {
        return Number;
    }

    public int getWeeklySalary() {
        return Salary;
    }

    public int getAge() {
        return Age;
    }
}
