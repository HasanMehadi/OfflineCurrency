package CurrencyUI.ADDNEWCURRENCYUI;

import CurrencyUI.AlertMaker;
import Database.DataBaseHandel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AddcurrencyController implements Initializable{
    public JFXTextField txtname;
    public JFXTextField txtagtaka;
    public JFXTextField txtintaka;
    public JFXButton btnadd;
    DataBaseHandel dataBaseHandel;
    int count=0;
    public void addcurrency(ActionEvent actionEvent) {
        String Name = txtname.getText();
        Float agtaka = Float.parseFloat(txtagtaka.getText());
        Float intaka = Float.parseFloat(txtintaka.getText());

        String query ="INSERT INTO CURRENCY VALUES('"+count+1+"','"+Name+"','"+agtaka+"','"+intaka+"')";
        if(dataBaseHandel.actionExecutioner(query)){
            AlertMaker.showConfirmationAlert("Add Currency","Currency Saved");
        }else {
            AlertMaker.showErrorAlert("Add Currency","Failed!! ");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String query = "SELECT COUNT(ID) FROM CURRENCY";
        dataBaseHandel = DataBaseHandel.getDataBaseHandelInstance();

        try{
            ResultSet resultSet = dataBaseHandel.queryExeutioner(query);
            while (resultSet.next()){
                count = resultSet.getInt("COUNT(ID)");

            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
