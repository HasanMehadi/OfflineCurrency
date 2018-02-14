package CurrencyUI.SEARCHCURRENCYUI;

import CurrencyUI.AlertMaker;
import Database.DataBaseHandel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class SearchcurrencyController implements Initializable {
    public JFXTextField txtid;
    public JFXButton btnsearch;
    public JFXTextField txtname;
    public JFXTextField txtagtaka;
    public JFXTextField txtintaka;
    public JFXButton btnupdate;
    public JFXButton btndelete;
    DataBaseHandel dataBaseHandel;
    String temp=null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataBaseHandel = DataBaseHandel.getDataBaseHandelInstance();
    }

    public void searchCurrency(ActionEvent actionEvent) {
        temp = txtid.getText();
        String query ="SELECT * FROM CURRENCY WHERE ID ='"+Integer.parseInt(temp)+"'";
        try{
            ResultSet resultSet = dataBaseHandel.queryExeutioner(query);
            while (resultSet.next()){
                txtname.setText(resultSet.getNString("NAME"));
                txtagtaka.setText(resultSet.getNString("VALUE"));
                txtintaka.setText(resultSet.getNString("INTAKA"));

            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void updateCurrency(ActionEvent actionEvent) {
        String name = txtname.getText();
        Float agtaka = Float.parseFloat(txtagtaka.getText());
        Float intaka = Float.parseFloat(txtintaka.getText());

        String query ="UPDATE CURRENCY SET NAME='"+name+"', VALUE='"+agtaka+"',INTAKA='"+intaka+"'WHERE ID='"+Integer.parseInt(temp)+"'";

        if(dataBaseHandel.actionExecutioner(query)){
            AlertMaker.showInformationAlert("Currency Update","Currency Updated");
        }else {
            AlertMaker.showErrorAlert("Currency Update","Update Failed");
        }
    }

    public void deleteCurrency(ActionEvent actionEvent) {
        String query ="DELETE FROM CURRENCY WHERE ID= '"+   Integer.parseInt(temp)+"'";

        if(dataBaseHandel.actionExecutioner(query)){
            AlertMaker.showInformationAlert("Currency Delete","Currency Updated");
        }else {
            AlertMaker.showErrorAlert("Currency Delete","Delete Failed");
        }
    }
}
