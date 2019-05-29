import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class NotificationData {

    @SerializedName("bill")
    private String mBill;

    @SerializedName("orderList")
    private List<Order> mOrder;

    public List<Order> getmOrder() {
        return mOrder;
    }

    public void setmOrder(List<Order> mOrderList) {
        this.mOrder = mOrderList;
    }

    public String getBill() {
        return mBill;
    }

    public void setBill(String detail) {
        mBill = detail;
    }

}