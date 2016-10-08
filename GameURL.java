package osborn.andrew.NCAAFootballLiveScores;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameURL
{
    public GameURL()
    {
        establishDatabaseConnection();
    }

    public static void establishDatabaseConnection()
    {
        Map<String, Date> hashMap = new LinkedHashMap<>();
        try
        {
            String mySQLURL = "jdbc:mysql://localhost:3306/NCAAFootball?" +
                    "autoReconnect=true&useSSL=false";
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection
                    (mySQLURL, "root", "Osbo_789");


            String query = "SELECT * FROM schedule";

            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                Date date = resultSet.getDate("date");
                String gameURL = resultSet.getString("URL");

                hashMap.put(gameURL, date);
            }
            statement.close();
            conn.close();
        }
        catch (Exception ex) { ex.printStackTrace(); }
    }
}
