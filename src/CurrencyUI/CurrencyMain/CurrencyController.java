package CurrencyUI.CurrencyMain;

import Database.DataBaseHandel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class CurrencyController implements Initializable{


    public JFXButton btnadd;
    public JFXButton btnsearch;
    public JFXButton btnconvert;
    public JFXTextField txtconvert;
    public JFXComboBox combosource;
    public JFXComboBox combodestination;
    DataBaseHandel dataBaseHandel;
    ObservableList<String> comboDemos;

    public void addNewCurrency(ActionEvent actionEvent) {
        loadwindow("CurrencyUI/ADDNEWCURRENCYUI/addcurrency.fxml","ADD CURRENCY");
    }

    public void searchCurrency(ActionEvent actionEvent) {
        loadwindow("CurrencyUI/SEARCHCURRENCYUI/searchcurrency.fxml","SEARCH CURRENCY");
    }

    public void convertCurrency(ActionEvent actionEvent) {
        float value = Float.parseFloat(txtconvert.getText());
        float sourceintaka=0f;
        float desintaka=0f;
        String comsource = (String) combosource.getValue();
        String comdesti = (String)combodestination.getValue();
        String query1 = "SELECT VALUE,INTAKA WHERE NAME='"+comsource+"'";
        String query2 = "SELECT VALUE,INTAKA WHERE NAME='"+comdesti+"'";

        try{

            ResultSet resultSet = dataBaseHandel.queryExeutioner(query1);
            while (resultSet.next()){
                sourceintaka = resultSet.getFloat("INTAKA");
            }
            ResultSet resultSet1 = dataBaseHandel.queryExeutioner(query2);
            while (resultSet1.next()){

                desintaka = resultSet1.getFloat("INTAKA");
            }

            float convertedvalue=(float) (desintaka/sourceintaka)*value;
            txtconvert.setText(Float.toString(convertedvalue));

        }catch (Exception ex){

        }
    }

    public void loadwindow(String location,String title){
        try{
            Parent root= FXMLLoader.load(getClass().getClassLoader().getResource(location));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataBaseHandel= DataBaseHandel.getDataBaseHandelInstance();
        comboDemos = FXCollections.observableArrayList();
        String query ="SELECT ID, NAME FROM CURRENCY";

        try{
            ResultSet resultSet = dataBaseHandel.queryExeutioner(query);
            while (resultSet.next()){

                String name =  resultSet.getNString("NAME");
                comboDemos.add(name);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        combosource.setItems(comboDemos);
        combosource.setItems(comboDemos);

    }
}
