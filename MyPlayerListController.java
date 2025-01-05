package fahim.fahim22;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyPlayerListController implements Initializable{
    @FXML
    Stage stage;
    @FXML
    Scene scene;
    @FXML
    private TableView<TempPlayer>table=new TableView<>();
    @FXML
    private TableColumn<TempPlayer,Integer> Age=new TableColumn<>();

    @FXML
    private TableColumn<TempPlayer, String> TClub=new TableColumn<>();

    @FXML
    private TableColumn<TempPlayer, String> TCountry=new TableColumn<>();

    @FXML
    private TableColumn<TempPlayer, Double> Height=new TableColumn<>();

    @FXML
    private TableColumn<TempPlayer, String > Name=new TableColumn<>();

    @FXML
    private TableColumn<TempPlayer, Integer> Number=new TableColumn<>();

    @FXML
    private TableColumn<TempPlayer, String> Position=new TableColumn<>();
    @FXML
    TextField Country;
    @FXML
    TextField Club;
    @FXML
    private TableColumn<TempPlayer, Integer> Salary=new TableColumn<>();
    ObservableList<TempPlayer>list=FXCollections.observableArrayList(MainController.obj);
    @FXML
    TempPlayer pp=null;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        TClub.setCellValueFactory(new PropertyValueFactory<>("club"));
        Age.setCellValueFactory(new PropertyValueFactory<>("age"));
        Height.setCellValueFactory(new PropertyValueFactory<>("height"));
        Salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        Number.setCellValueFactory(new PropertyValueFactory<>("number"));
        Position.setCellValueFactory(new PropertyValueFactory<>("position"));
        System.out.println(table);
        table.setItems(list);
        table.setOnMouseClicked(event->{myDetails(event);});
    }
    @FXML
    public void PlayerSearchMenu(ActionEvent e)throws Exception{
        new MainMenuApplication(e);
    }
    @FXML
    public void myDetails(MouseEvent e){
        TempPlayer player=table.getSelectionModel().getSelectedItem();
        if(player!=null&&player!=pp) {
            pp=player;
            try {
                ArrayList<Players>temp= MainMenuApplication.players.available;
                int flag=0;
                for(int i=0;i<temp.size();i++){
                    if(temp.get(i).getName().equalsIgnoreCase(player.getName())){
                        flag=1;
                        break;
                    }
                }
                FXMLLoader loader;
                if(flag==0) {
                    loader = new FXMLLoader(getClass().getResource("MyDetails.fxml"));
                }
                else{
                    loader = new FXMLLoader(getClass().getResource("Pending.fxml"));
                }
                Parent root=loader.load();
                ShowPlayerController it=loader.getController();
                it.display(player.getName(),player.getClub(),player.getCountry(),String.valueOf(player.getSalary()), player.getPosition(), String.valueOf(player.getAge()),String.valueOf(player.getHeight()), player.getNumber());
                stage =(Stage)((Node)(e.getSource())).getScene().getWindow();
                scene=new Scene(root);

                stage.setScene(scene);
                stage.show();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
