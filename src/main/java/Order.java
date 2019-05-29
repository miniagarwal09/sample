import com.google.gson.annotations.SerializedName;

public class Order {

    String name;
    String price;
    String image;

    public Order(String name, String price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
