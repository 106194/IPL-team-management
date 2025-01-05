package fahim.fahim22;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Optional;

public class ShowPlayerController
{
    @FXML
    Button buy=new Button();
    @FXML
    Button Sell=new Button();
    @FXML
    Button cancel=new Button();
    @FXML
    Button back=new Button();
    @FXML
    Stage stage;
    @FXML
    Scene scene;
    @FXML
    Label Name;
    @FXML
    Label Country;
    @FXML
    Label Position;
    @FXML
    Label Club;
    @FXML
    Label Age;
    @FXML
    Label Height;
    @FXML
    Label Jersey;
    @FXML
    Label Salary;
    @FXML
    ImageView imageView1;
    @FXML
    ImageView imageView2;
    @FXML
    ImageView imageView3;
    String name;
    @FXML
    ImageView imageView4;
    public void initialize(){
        zoomButton Zoom=new zoomButton();
        Zoom.zoom(buy);
        Zoom.zoom(back);
        Zoom.zoom(Sell);
        Zoom.zoom(cancel);
    }
    public void display(String name,String club,String country,String salary,String position,String age,String height,String number){
        String nm=name;
        checkPiture ob=new checkPiture();
        if(!ob.check(name)){
            nm="default";
        }
        Image image1=new Image("C:/Users/Fahim/IdeaProjects/Fahim22/src/main/resources/images/"+nm+".jpg");
        imageView1.setImage(image1);
        Image image2=new Image("C:/Users/Fahim/IdeaProjects/Fahim22/src/main/resources/images/"+club+".jpg");
        imageView2.setImage(image2);
        Image image3=new Image("C:/Users/Fahim/IdeaProjects/Fahim22/src/main/resources/images/"+country+".jpg");
        imageView3.setImage(image3);
        Name.setText(name);
        Country.setText(country);
        Position.setText(position);
        Club.setText(club);
        Age.setText(age);
        Height.setText(height);
        Jersey.setText(number);
        Salary.setText(salary);
        this.name=name;
    }
    public void NameMenu(ActionEvent e)throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("InputName.fxml"));
        stage =(Stage)((Node)(e.getSource())).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void back(ActionEvent e)throws Exception{
        new MainMenuApplication(e);
    }
    public void cell(ActionEvent e)throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation!");
        alert.setContentText("Press OK to confirm");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL) {
            ;
        }
        if (result.get() == ButtonType.OK){
            Client obj=new Client();
            obj.cell(name,e);
        }
    }
    public void buy(ActionEvent e)throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation!");
        alert.setContentText("Press OK to confirm");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL) {
            ;
        }
        if (result.get() == ButtonType.OK){
            Client obj=new Client();
            obj.buy(name,e);
        }
    }
    public void cancel(ActionEvent e)throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation!");
        alert.setContentText("Press OK to confirm cancellation");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Client obj=new Client();
            obj.cancel(name,e);
        }
    }
}