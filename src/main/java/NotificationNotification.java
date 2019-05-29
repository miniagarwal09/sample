import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class NotificationNotification {

    @SerializedName("body")
    private String mBody;

    @SerializedName("title")
    private String mTitle;

    public String getBody() {
        return mBody;
    }

    public void setBody(String detail) {
        mBody = detail;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}