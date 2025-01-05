package fahim.fahim22;

public class TempPlayer {
    String Name,TCountry,TClub,Position;
    int Age,Salary;
    String Number;
    double Height;
    public boolean isPending;
    public TempPlayer(String name, String country, String club, String position, int age, int salary, String number, double height){
        this.Name=name;
        this.TCountry=country;
        this.TClub=club;
        this.Position=position;
        this.Age=age;
        this.Salary=salary;
        this.Number=number;
        this.Height=height;
        isPending=false;
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

    public String getNumber() {
        return Number;
    }

    public int getSalary() {
        return Salary;
    }

    public int getAge() {
        return Age;
    }
}
