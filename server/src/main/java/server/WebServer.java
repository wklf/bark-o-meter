package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import static spark.Spark.*;

public class WebServer {

    private DBManager dbm = new DBManager(); // add db info

    private WebServer(){
        spark.Spark.staticFiles.location("/public");
        spark.Spark.staticFiles.header("Access-Control-Allow-Origin", "*");
        spark.Spark.port(5000);
        setupRoutes();
    }

    // Test and debugging method
    private void test(){
        ArrayList<SoundEvent> arr = dbm.readDataAll();
        System.out.println(stringifyEvents(arr));
    }

    private void setupRoutes(){

        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
        });

        get("/test", (request, response) -> {
            System.out.println("Test");
            return "Test executed - server available";
        });

        get("/data/all", (request, response) -> {
            System.out.println("Log - Request - Data all");
            response.type("application/json");
            ArrayList<SoundEvent> arr = dbm.readDataAll();
            String json = stringifyEvents(arr);
            return json;
        });

        get("/data/week", (request, response) -> {
            System.out.println("Log - Request - Data week");
            response.type("application/json");
            ArrayList<SoundEvent> arr = dbm.readDataWeek();
            String json = stringifyEvents(arr);
            return json;
        });

        get("/data/today", (request, response) -> {
            System.out.println("Log - Request - Data today");
            response.type("application/json");
            ArrayList<SoundEvent> arr = dbm.readDataToday();
            String json = stringifyEvents(arr);
            return json;
        });

    }

    private String stringifyEvents(ArrayList<SoundEvent> pojo){
        String json = "";
        ObjectMapper objMapper = new ObjectMapper();

        try {
            json = objMapper.writeValueAsString(pojo);
        } catch (JsonProcessingException e) {
            System.out.println("JSON exception");
            e.printStackTrace();
        }

        return json;
    }

    public static void main(String args[]){
        System.out.println("Server started!");
        TempGUI gui = new TempGUI();
        WebServer server = new WebServer();
    }
}
