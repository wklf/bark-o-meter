package server;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {

    private String ip = "";
    private String dbName = "";
    private String user = "";
    private String password = ""; //add password
    private String port = "";
    private String url = "jdbc:postgresql://" + ip + ":" + port + "/" + dbName;

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Connection to the database failed.");
        }
        return conn;
    }

    // might need to handle empty list in case of exception
    public ArrayList<SoundEvent> readDataAll(){
        String query = "SELECT * FROM barkdata";
        return  readData(query);
    }

    public ArrayList<SoundEvent> readDataWeek(){
        String query = "SELECT * FROM barkdata WHERE time > (current_date - interval '6 days')";
        return  readData(query);
    }

    public ArrayList<SoundEvent> readDataToday(){
        String query = "SELECT * FROM barkdata WHERE time > current_date";
        return  readData(query);
    }

    private ArrayList<SoundEvent> readData(String query){
        ArrayList<SoundEvent> result = new ArrayList<>();

        try{Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                SoundEvent event = new SoundEvent(rs.getInt("id"),
                        rs.getTimestamp("time"),
                        rs.getString("event"),
                        rs.getInt("level")
                );
                result.add(event);
            }

            conn.close();
        } catch (SQLException ex) {
            System.out.println("get all errors");
            System.out.println(ex.getMessage());
        }

        return result;
    }

}
