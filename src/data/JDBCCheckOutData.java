package data;

import java.sql.*;
import java.util.ArrayList;

public class JDBCCheckOutData {

    Statement statement;
    private ArrayList<UsersData> UsersAllData;
    private UsersData usersData;
    private ArrayList<PatientData> patientAllData;
    private PatientData patientData;
    private Connection connection;

//    public JDBCCheckOutData() throws SQLException, ClassNotFoundException {
//        ConnectDB();
//    }
//
//    public static Connection ConnectDB() throws ClassNotFoundException, SQLException {
//        Class.forName("org.sqlite.JDBC");
//        Connection connection = DriverManager.getConnection("jdbc:sqlite:testA.db");
//        System.out.println("Connected...");
//        return connection;
//    }

    public JDBCCheckOutData() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:testA.db");
        System.out.println("Connected...");
        statement = connection.createStatement();
    }
    public ArrayList<UsersData> UsersAllData() throws SQLException {
        ResultSet resultSet = statement.executeQuery("select * from users0");
        UsersAllData = new ArrayList<>();
        while (resultSet.next()) {
            usersData = new UsersData(
                    resultSet.getString("username"),
                    resultSet.getString("password"));
            System.out.println(usersData.getUserName()+ " " + usersData.getUserPassword());
            UsersAllData.add(usersData);

        }
        resultSet.close();
        return UsersAllData;
    }

    public ArrayList<PatientData> PatientAllData() throws SQLException {
        ResultSet resultSet = statement.executeQuery("select * from patients01");
        patientAllData = new ArrayList<>();
        while(resultSet.next()) {
            patientData = new PatientData(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("phone"),
                    resultSet.getString("room"),
                    resultSet.getString("bed")
            );
            System.out.println(patientData.toString());
            patientAllData.add(patientData);
        }
        resultSet.close();
        return patientAllData;
    }

    public void  InsertNewPatientData(PatientData patientData) throws SQLException {
        connection.setAutoCommit(false);
        String sql = "insert into patients01 values (null,"+
                "'"+patientData.getName()+"', "+
                "'"+patientData.getSurname()+"', "+
                "'"+patientData.getPhone()+"', "+
                "'"+patientData.getRoom()+"', "+
                "'"+patientData.getBed()+"')";
        statement.executeUpdate(sql);
        connection.commit();
    }

    public void DeletePatientData(int id) throws SQLException {
        connection.setAutoCommit(false);
        String sql = "delete from patients01 where id="+id+"";
        statement.executeUpdate(sql);
        connection.commit();
    }


}
