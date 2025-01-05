package fahim.fahim22;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import javafx.event.ActionEvent;

import java.util.ArrayList;

public class MainMenuApplication {
    static PlayerList players;
    public MainMenuApplication(ActionEvent e)throws Exception{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root=loader.load();
        MainController it=loader.getController();
        ArrayList<Players>obj=players.SearchByClub(Client.name);
        it.DisplayClub(obj.get(0).getClub());
        Stage stage =(Stage)((Node)(e.getSource())).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}