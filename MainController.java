package fahim.fahim22;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class MainController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    TextField First;
    @FXML
    TextField Second;
    @FXML
    TextField Name;
    @FXML
    Button myButton;
    @FXML
    TextField Country;
    @FXML
    TextField Club;
    @FXML
    TextField Position;
    @FXML
    TextField Age;
    @FXML
    TextField Height;
    @FXML
    TextField Number;
    @FXML
    TextField Salary;
    @FXML
    Label label;
    @FXML
    Button bname;
    @FXML
    Button bposition;
    @FXML
    Button bsalary;
    @FXML
    Button bcount;
    @FXML
    Button bclub;
    @FXML
    Button bback;
    @FXML
    public void initialize(){
         zoomButton Zoom=new zoomButton();
         Zoom.zoom(myButton);
         Zoom.zoom(bname);
         Zoom.zoom(bclub);
         Zoom.zoom(bsalary);
         Zoom.zoom(bposition);
         Zoom.zoom(bcount);
         Zoom.zoom(bback);
   }
    public void MainMenu(ActionEvent e)throws Exception {
        new MainMenuApplication(e);
    }
    public void PlayerName(ActionEvent e)throws IOException{
        Parent root= FXMLLoader.load(getClass().getResource("PlayerSearch.fxml"));
        stage =(Stage)((Node)(e.getSource())).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void ClubName(ActionEvent e)throws IOException{
        Parent root= FXMLLoader.load(getClass().getResource("ClubSearch.fxml"));
        EventObject event;
        stage =(Stage)((Node)(e.getSource())).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void ConfirmName(ActionEvent e)throws IOException{
        if(Name.getText().length()==0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid!");
            alert.setContentText("Inavlid Input");
            Optional<ButtonType> result=alert.showAndWait();
        }
        else {
            String username=ToUpper(Name.getText());
            Players obj;
            obj = MainMenuApplication.players.ByPlayerName(username);
            if (obj == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Player Not Found!");
                alert.setContentText("Please enter the name included");
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                String name = obj.getName(), country = obj.getCountry(), position = obj.getPosition();
                String club = obj.getClub(), age = String.valueOf(obj.getAge());
                String height = String.valueOf(obj.getHeight());
                String salary = String.valueOf(obj.getWeeklySalary()), number;
                if (obj.getNumber() == -1) {
                    number = "Not Assigned";
                } else number = String.valueOf(obj.getNumber());
                ArrayList<Players> temp = MainMenuApplication.players.available;
                int flag = 0;
                for (int i = 0; i < temp.size(); i++) {
                    if (temp.get(i).getName().equalsIgnoreCase(name)) {
                        flag = 1;
                        break;
                    }
                }
                FXMLLoader loader;
                if (flag == 1 && club.equalsIgnoreCase(Client.name)) {
                    loader = new FXMLLoader(getClass().getResource("Pending.fxml"));
                } else if (flag == 1) {
                    loader = new FXMLLoader(getClass().getResource("Buy.fxml"));
                }
                else if(club.equalsIgnoreCase(Client.name)){
                    loader = new FXMLLoader(getClass().getResource("MyDetails.fxml"));
                }
                else {
                    loader = new FXMLLoader(getClass().getResource("Details.fxml"));
                }
                root = loader.load();
                ShowPlayerController it = loader.getController();
                it.display(name, club, country, salary, position, age, height, number);
                stage = (Stage) ((Node) (e.getSource())).getScene().getWindow();
                scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
            }
        }
    }
    public void DisplayClub(String club){
        label.setText(club);
    }
    public void NameMenu(ActionEvent e)throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("InputName.fxml"));
        EventObject event;
        stage =(Stage)((Node)(e.getSource())).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void InputClub(ActionEvent e)throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("InputClub.fxml"));
        stage =(Stage)((Node)(e.getSource())).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public String ToUpper(String s){
        if(s==null)return s;
        String [] arr=s.split("[ ]+");
        String temp=new String();
        for(int i=0;i<arr.length;i++){
            char a[]=arr[i].toCharArray();
            a[0]=Character.toUpperCase(a[0]);
            String t=new String(a);
            if(i==0){
                temp=t;
            }
            else {
                temp = temp.concat(t);
            }
            if(i<arr.length-1){
                temp=temp.concat(" ");
            }
        }
        return temp;
    }
    @FXML
    public void Exit(ActionEvent e)throws Exception{

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation!");
        alert.setContentText("Are you sure to log out?");
        Optional<ButtonType> result=alert.showAndWait();
        if(result.get()==ButtonType.OK) {
            Client.oos.writeObject("close" + "," + ToUpper(Client.name) + "," + "n");
            Parent root = FXMLLoader.load(getClass().getResource("Client.fxml"));
            stage = (Stage) ((Node) (e.getSource())).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    static ArrayList<TempPlayer> obj=new ArrayList<>();
    static ArrayList<CountryCount>obj1=new ArrayList<>();
    public void ClubCountry(ActionEvent e)throws Exception{
        if(Club.getText().length()==0||Country.getText().length()==0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid!");
            alert.setContentText("Inavlid Input");
            Optional<ButtonType> result=alert.showAndWait();
        }
        else {
            String country = ToUpper(Country.getText());
            String club = ToUpper(Club.getText());
            ArrayList<Players> temp = new ArrayList<>();
            ArrayList<TempPlayer> tt = new ArrayList<>();
            temp = MainMenuApplication.players.ByClubCountryName(country, club);
            if (temp == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Club or Country Not Found!");
                alert.setContentText("Please enter the name included");
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                for (int i = 0; i < temp.size(); i++) {
                    tt.add(switchObject(temp.get(i)));
                }
                obj = tt;
                Parent root = FXMLLoader.load(getClass().getResource("LIST.fxml"));
                stage = (Stage) ((Node) (e.getSource())).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }
    public void InputSalary(ActionEvent e)throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("InputSalary.fxml"));
        stage =(Stage)((Node)(e.getSource())).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void BySalary(ActionEvent e)throws Exception{
        try {
            int first = Integer.parseInt(First.getText());
            int second = Integer.parseInt(Second.getText());
            System.out.println(first);
            System.out.println(second);
            ArrayList<Players> temp = new ArrayList<>();
            ArrayList<TempPlayer> tt = new ArrayList<>();
            temp = MainMenuApplication.players.SearchBySalary(first, second);
            if (temp == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Salary range Not Found!");
                alert.setContentText("Please enter the range included");
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                for (int i = 0; i < temp.size(); i++) {
                    tt.add(switchObject(temp.get(i)));
                }
                obj = tt;
                Parent root = FXMLLoader.load(getClass().getResource("LIST.fxml"));
                stage = (Stage) ((Node) (e.getSource())).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        catch(Exception ex){
            Alert alt = new Alert(Alert.AlertType.ERROR);
            alt.setTitle("Invalid Input!");
            alt.setContentText("Please enter valid input of all data");
            Optional<ButtonType> rt = alt.showAndWait();
        }
    }
    public void InputPosition(ActionEvent e)throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("InputPosition.fxml"));
        stage =(Stage)((Node)(e.getSource())).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SearchByPosition(ActionEvent e)throws Exception{
        if(Position.getText().length()==0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid!");
            alert.setContentText("Inavlid Input");
            Optional<ButtonType> result=alert.showAndWait();
        }
        else {
            String pos=ToUpper(Position.getText());
            ArrayList<Players> temp = new ArrayList<>();
            ArrayList<TempPlayer> tt = new ArrayList<>();
            temp = MainMenuApplication.players.searchByPosition(pos);
            if (temp == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Position Not Found!");
                alert.setContentText("Please enter the position included");
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                for (int i = 0; i < temp.size(); i++) {
                    tt.add(switchObject(temp.get(i)));
                }
                obj = tt;
                Parent root = FXMLLoader.load(getClass().getResource("LIST.fxml"));
                stage = (Stage) ((Node) (e.getSource())).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }
    public void CountCountry(ActionEvent e)throws Exception{
        HashMap<String,Integer>hash= MainMenuApplication.players.CountryCount();
        ArrayList<CountryCount>obj2=new ArrayList<>();
        for (var i : hash.entrySet()) {
            CountryCount ob=new CountryCount(i.getKey() ,i.getValue());
            obj2.add(ob);
        }
        obj1=obj2;
        Parent root= FXMLLoader.load(getClass().getResource("CountryCount.fxml"));
        stage =(Stage)((Node)(e.getSource())).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void InputSearchClub(ActionEvent e)throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("SearchClubMaxSalary.fxml"));
        stage =(Stage)((Node)(e.getSource())).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void MaxSalary(ActionEvent e)throws Exception{
        if(Club.getText().length()==0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid!");
            alert.setContentText("Inavlid Input");
            Optional<ButtonType> result=alert.showAndWait();
        }
        else {
            String club=ToUpper(Club.getText());
            ArrayList<Players> temp = new ArrayList<>();
            ArrayList<TempPlayer> tt = new ArrayList<>();
            temp = MainMenuApplication.players.MaxSalary(club);
            if (temp == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Club Not Found!");
                alert.setContentText("Please enter the Club included");
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                for (int i = 0; i < temp.size(); i++) {
                    tt.add(switchObject(temp.get(i)));
                }
                obj = tt;
                Parent root = FXMLLoader.load(getClass().getResource("LIST.fxml"));
                stage = (Stage) ((Node) (e.getSource())).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }
    public void InputSearchClubAge(ActionEvent e)throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("SearchClubMaxAge.fxml"));
        stage =(Stage)((Node)(e.getSource())).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void MaxAge(ActionEvent e)throws Exception{
        if(Club.getText().length()==0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid!");
            alert.setContentText("Inavlid Input");
            Optional<ButtonType> result=alert.showAndWait();
        }
        else {
            String club=ToUpper(Club.getText());
            ArrayList<Players> temp = new ArrayList<>();
            ArrayList<TempPlayer> tt = new ArrayList<>();
            temp = MainMenuApplication.players.MaxAge(club);
            if (temp == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Club Not Found!");
                alert.setContentText("Please enter the Club included");
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                for (int i = 0; i < temp.size(); i++) {
                    tt.add(switchObject(temp.get(i)));
                }
                obj = tt;
                Parent root = FXMLLoader.load(getClass().getResource("LIST.fxml"));
                stage = (Stage) ((Node) (e.getSource())).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }
    public void InputSearchClubHeight(ActionEvent e)throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("SearchClubMaxHeight.fxml"));
        stage =(Stage)((Node)(e.getSource())).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void MaxHeight(ActionEvent e)throws Exception{
        if(Club.getText().length()==0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid!");
            alert.setContentText("Inavlid Input");
            Optional<ButtonType> result=alert.showAndWait();
        }
        else {
            String club=ToUpper(Club.getText());
            ArrayList<Players> temp = new ArrayList<>();
            ArrayList<TempPlayer> tt = new ArrayList<>();
            temp = MainMenuApplication.players.MaxHeight(club);
            if (temp == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Club Not Found!");
                alert.setContentText("Please enter the Club included");
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                for (int i = 0; i < temp.size(); i++) {
                    tt.add(switchObject(temp.get(i)));
                }
                obj = tt;
                Parent root = FXMLLoader.load(getClass().getResource("LIST.fxml"));
                stage = (Stage) ((Node) (e.getSource())).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }
    public void InputTotal(ActionEvent e)throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("TotalYearly.fxml"));
        stage =(Stage)((Node)(e.getSource())).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void Total(ActionEvent e)throws Exception{
        if(Club.getText().length()==0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid!");
            alert.setContentText("Inavlid Input");
            Optional<ButtonType> result=alert.showAndWait();
        }
        else {
            String club=ToUpper(Club.getText());
            String salary = String.valueOf(MainMenuApplication.players.TotalSalary(club));
            if (salary.equals("-1")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Club Not Found!");
                alert.setContentText("Please enter the club included");
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TotalYearlyShow.fxml"));
                root = loader.load();
                ShowYearlySalary it = loader.getController();
                it.display(salary);
                stage = (Stage) ((Node) (e.getSource())).getScene().getWindow();
                scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
            }
        }
    }
    public TempPlayer switchObject(Players obj2){
        String num;
        if(obj2.getNumber()==-1){
            num="Not assigned";
        }
        else {
            num=String.valueOf(obj2.getNumber());
        }
        TempPlayer obj1=new TempPlayer(obj2.getName(), obj2.getCountry(), obj2.getClub(), obj2.getPosition(), obj2.getAge(), obj2.getWeeklySalary() ,num, obj2.getHeight());
        return obj1;
    }
    public void MyPlayerList(ActionEvent e)throws Exception{
        ArrayList<Players>temp=new ArrayList<>();
        ArrayList<TempPlayer>tt=new ArrayList<>();
        temp= MainMenuApplication.players.SearchByClub(Client.name);
        for(int i=0;i<temp.size();i++){
            tt.add(switchObject(temp.get(i)));
        }
        obj=tt;
        Parent root= FXMLLoader.load(getClass().getResource("MyPlayerList.fxml"));
        stage =(Stage)((Node)(e.getSource())).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void available(ActionEvent e)throws Exception{
        ArrayList<Players>temp;
        ArrayList<TempPlayer>tt=new ArrayList<>();
        temp= MainMenuApplication.players.SearchByClubAvailable(Client.name);
        if(temp.size()==0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty List!");
            alert.setContentText("There is no any available player to buy");
            Optional<ButtonType> result=alert.showAndWait();
        }
        else {
            for (int i = 0; i < temp.size(); i++) {
                tt.add(switchObject(temp.get(i)));
            }
            obj = tt;
            Parent root = FXMLLoader.load(getClass().getResource("BuyPlayerList.fxml"));
            stage = (Stage) ((Node) (e.getSource())).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void changePass(ActionEvent e)throws Exception{

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation!");
        alert.setContentText("Are you sure to change password?");
        Optional<ButtonType> result=alert.showAndWait();
        if(result.get()==ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("password.fxml"));
            stage = (Stage) ((Node) (e.getSource())).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}