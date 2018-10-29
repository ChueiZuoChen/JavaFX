package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLiteJDBC {
    public static void main(String args[]) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:testA.db");
            System.out.println("Connected...");
            Statement statement = connection.createStatement();
            //Create Table...

//            String sql = "create table users0 " +
//                    "(username varchar(15) not null, " +
//                    "password varchar(15) not null, " +
//                    "constraint ID primary key (username))";
//            //System.out.println(000);
//            statement.executeUpdate(sql);
            //System.out.println(321);
            //Insert
//            String sql = "insert into users0 values ('chen1','111')";

            String sql = "create table patients01 " +
                    "(id integer primary key autoincrement, "+
                    "name varchar(15) not null, " +
                    "surname varchar(15) not null, " +
                    "phone varchar(15) not null, " +
                    "room varchar(15) not null, " +
                    "bed varchar(15) not null " +
                    ")";
//            statement.executeUpdate(sql);
//            String sql = "insert into patients0 values ('paul12','chen12','04040214','13212','23123')";
            statement.executeUpdate(sql);

            statement.close();
            connection.commit();
            connection.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }
}
