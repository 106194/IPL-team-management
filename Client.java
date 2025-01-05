package fahim.fahim22;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
public class Client extends Application {
    @FXML
    Button login=new Button();
    @FXML
    Button exit=new Button();
    @FXML
    Button submit=new Button();
    @FXML
    Button reset=new Button();
    @FXML
    PasswordField password=new PasswordField();
    @FXML
    TextField clubName=new TextField();
    @FXML
    PasswordField newPassword;
    @FXML
    PasswordField confirmPassword;
    public static Socket socket;
    public static String name;
    public static String pass;
    static ObjectOutputStream oos;
    static ObjectInputStream ois;
    @FXML
    public void initialize(){
          zoomButton Zoom=new zoomButton();
          Zoom.zoom(login);
          Zoom.zoom(exit);
          Zoom.zoom(submit);
          Zoom.zoom(reset);

    }
    public void start(Stage stage) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("Client.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("My Term Project");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args)throws Exception {
        launch();
    }
    public void Input(ActionEvent e)throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("InputClubManager.fxml"));
        Stage stage =(Stage)((Node)(e.getSource())).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void submit(ActionEvent e)throws Exception{
        MainController cntObj=new MainController();
        Client.pass=password.getText();
        Client.name=cntObj.ToUpper(clubName.getText());
        if(name.length()==0||pass.length()==0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid log In!");
            alert.setContentText("Please enter correct Name and Password");
            Optional<ButtonType> result=alert.showAndWait();
        }
        else {
            System.out.println(Client.name);
            Client.socket = new Socket("127.0.0.1", 44444);
            System.out.println("Client Connected....");
            Client.oos = new ObjectOutputStream(Client.socket.getOutputStream());
            Client.ois = new ObjectInputStream(Client.socket.getInputStream());
            Client.oos.writeObject(Client.name + "," + Client.pass);
            System.out.println(Client.name);
            Object ob = Client.ois.readObject();
            if (ob == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid log In!");
                alert.setContentText("Please enter correct Name and Password");
                Optional<ButtonType> result = alert.showAndWait();
            }
            else if(ob instanceof Data)
            {
                Data d = (Data) ob;
                MainMenuApplication.players = new PlayerList(d.str, d.available);
                new ClientThread(Client.socket);
                new MainMenuApplication(e);
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid log In!");
                alert.setContentText("This club is already logged in");
                Optional<ButtonType> result = alert.showAndWait();
            }
        }
    }
    public void Back(ActionEvent e)throws Exception{
        new MainMenuApplication(e);
    }
    public void cell(String Name,ActionEvent e)throws Exception{
        try {
            System.out.println("Write");
            Client.oos.writeObject("sell"+","+Name+","+"n");
            new MainMenuApplication(e);
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public void buy(String Name,ActionEvent e)throws Exception{
        try {
            Client.oos.writeObject(Name+","+Client.name+","+"n");
            new MainMenuApplication(e);
        }
        catch(Exception ex){
            System.out.println("Error");
        }
    }
    public void cancel(String Name,ActionEvent e)throws Exception{
        try {
            Client.oos.writeObject("cancel"+","+Name+","+"n");
            new MainMenuApplication(e);
        }
        catch(Exception ex){
            System.out.println("Error");
        }
    }
    public void changePass(ActionEvent e)throws Exception{
        String newpass,conpass;
        newpass=newPassword.getText();
        conpass=confirmPassword.getText();
        if(newpass.length()==0||!newpass.equals(conpass)){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid!");
            alert.setContentText("New Passwords didn't match");
            Optional<ButtonType> result=alert.showAndWait();
        }
        else{
            Client.oos.writeObject("pass"+","+newpass+","+Client.name);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid!");
            alert.setContentText("Password changed succesfully");
            Optional<ButtonType> result=alert.showAndWait();
            new MainMenuApplication(e);
        }
    }
    public void Exit()throws Exception{
        Platform.exit();
    }
}
