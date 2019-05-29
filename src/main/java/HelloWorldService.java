import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Request;
import spark.servlet.SparkApplication;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class HelloWorldService{

    private static List<Order> createOrderList(JSONArray jsonArray) {
        List<Order> orderList=new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            JSONObject jsonObject=(JSONObject)jsonArray.get(i);
            System.out.println("\n"+jsonArray.get(i));
            orderList.add(new Order(jsonObject.getString("Name"),jsonObject.getString("price"),jsonObject.getString("image")));
        }
        return orderList;
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        post("/sendBillToServer", (request, response) -> {
            // Create something
            System.out.println(request.body());
            JSONObject body=new JSONObject(request.body());
            try {
                String bill=body.get("bill").toString();
                sendPushNotification(body.getString("restaurantName"),body.getString("key"),createOrderList(new JSONArray(body.get("orderList").toString())),bill);
              //  System.out.println((String)body.get("bill"));
                return response;

            }
            catch (IOException e){
                System.out.println(e.getMessage());
                return "problem occurred";
            }
        });


        get("/check",(req,res)->{
           return "working fine";
        });

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    private static void sendPushNotification(String restaurantName, String key,List<Order> orderList,String bill) throws IOException {

        System.out.println("Welcome to Developine");


        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(
                "https://fcm.googleapis.com/fcm/send");

        // we already created this model class.
        // we will convert this model class to json object using google gson library.

        NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
        NotificationData notificationData = new NotificationData();
        notificationData.setBill(bill);
        notificationData.setmOrder(orderList);
        notificationRequestModel.setData(notificationData);

        NotificationNotification notificationNotification=new NotificationNotification();
        notificationNotification.setTitle(restaurantName);
        notificationNotification.setBody("Your order request");
        notificationRequestModel.setNotificationNotification(notificationNotification);

        notificationRequestModel.setTo(key);


        Gson gson = new Gson();
        Type type = new TypeToken<NotificationRequestModel>() {
        }.getType();

        String json = gson.toJson(notificationRequestModel, type);

        StringEntity input = new StringEntity(json);
        input.setContentType("application/json");

        // server key of your firebase project goes here in header field.
        // You can get it from firebase console.

        postRequest.addHeader("Authorization", "key=AAAAkqtcwWw:APA91bEx4T_M3Tp7rgYFOdpO1th6s0_NQ7DenSa80-DvEZ-8xw6B_yzQME1N6FmlAQeqvPvo0mqcKgDh_fP_9naQlqh997JqAicNHZX5jd149MgN50ljE90JHMHva0m5bPNCUQ1v2R15");
        postRequest.setEntity(input);

        System.out.println("request:" + json);

        HttpResponse response = httpClient.execute(postRequest);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        } else if (response.getStatusLine().getStatusCode() == 200) {

            System.out.println("response:" + EntityUtils.toString(response.getEntity()));

        }
    }



}