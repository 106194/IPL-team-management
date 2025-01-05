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
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CountryCountController implements Initializable{
    @FXML
    TableView<CountryCount>table=new TableView<>();
    @FXML
    private TableColumn<CountryCount,String> country=new TableColumn<>();
    @FXML
    Stage stage;
    @FXML
    Scene scene;
    @FXML
    private TableColumn<CountryCount,Integer> count=new TableColumn<>();
    @FXML
    ObservableList<CountryCount>list=FXCollections.observableArrayList(MainController.obj1);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // System.out.println(MainController.obj1.get(0).getCountry());
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        count.setCellValueFactory(new PropertyValueFactory<>("count"));
        table.setItems(list);
    }
    public void Back(ActionEvent e)throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("PlayerSearch.fxml"));
        stage =(Stage)((Node)(e.getSource())).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
