import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OffLineCurrencyConverter extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{

            Parent root= FXMLLoader.load(getClass().getClassLoader().getResource("CurrencyUI/CurrencyMain/currency.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("OFFLINE CURRENCY CONVERTER");
            primaryStage.show();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();

        }

    }

    public static void main(String[] args) {

    }
}
