package ra.bussiness.entity;

import java.io.Serializable;

public class OderDetail implements Serializable {
    private int oderDetailId;
    private Product product;
    private int oderId;
    private int quantity;
    private float price;
    private float totalMoney;

    public OderDetail() {
    }

    public OderDetail(int oderDetailId, Product product, int oderId, int quantity, float price, float totalMoney) {
        this.oderDetailId = oderDetailId;
        this.product = product;
        this.oderId = oderId;
        this.quantity = quantity;
        this.price = price;
        this.totalMoney = totalMoney;
    }

    public int getOderDetailId() {
        return oderDetailId;
    }

    public void setOderDetailId(int oderDetailId) {
        this.oderDetailId = oderDetailId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getOderId() {
        return oderId;
    }

    public void setOderId(int oderId) {
        this.oderId = oderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }
}
