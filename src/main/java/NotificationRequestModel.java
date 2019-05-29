import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class NotificationRequestModel {

    @SerializedName("data")
    private NotificationData mData;
    @SerializedName("notification")
    private NotificationNotification notificationNotification;
    @SerializedName("to")
    private String mTo;

    public NotificationNotification getNotificationNotification() {
        return notificationNotification;
    }

    public void setNotificationNotification(NotificationNotification notificationNotification) {
        this.notificationNotification = notificationNotification;
    }

    public NotificationData getData() {
        return mData;
    }

    public void setData(NotificationData data) {
        mData = data;
    }

    public String getTo() {
        return mTo;
    }

    public void setTo(String to) {
        mTo = to;
    }
}