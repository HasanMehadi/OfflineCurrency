package Database;

import javax.swing.*;
import java.sql.*;

public class DataBaseHandel {

    public static DataBaseHandel dataBaseHandel=null;
    public Statement statement;
    public Connection connection;
    String tableName ="currency";

    String CurrencyTableStatement="CREATE TABLE "+tableName+"( id INT PRIMARY KEY ,NAME VARCHAR(40) Not NULL,VALUE FLOAT NOT NULL ,INTAKA FLOAT NOT NULL )";

    private DataBaseHandel(){
        connection = ConnectionManager.getConnection();
        setupTable(tableName,CurrencyTableStatement);
    }

    public static DataBaseHandel getDataBaseHandelInstance(){
        if(dataBaseHandel==null){
            return new DataBaseHandel();
        }
        return dataBaseHandel;
    }
    public void setupTable(String tableName,String stm){

        try{
            statement = connection.createStatement();
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null,null,tableName.toUpperCase(),null);
            if(tables.next()){
                System.out.println("Table "+tableName+" Already Exits. Good To go to next level");
            }else {
                statement.execute(stm);
            }
        }catch (SQLException ex){
            System.out.println(ex.getLocalizedMessage());
        }

    }

    public ResultSet queryExeutioner(String query){

        ResultSet resultSet;
        try{

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

        }catch (SQLException ex){
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
        return resultSet;

    }
    public boolean actionExecutioner(String query){

        try{
            statement = connection.createStatement();
            statement.execute(query);

        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Error"+ex.getMessage(),"Error Occurred",JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
        return true;
    }
}
