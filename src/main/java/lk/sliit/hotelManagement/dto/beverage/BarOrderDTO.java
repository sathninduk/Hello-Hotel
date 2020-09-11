package lk.sliit.hotelManagement.dto.beverage;

import java.util.Date;
import java.util.List;


public class BarOrderDTO {

    private int id;
    private String customerId;
    private String user;
    private List<BarOrderDetailDTO> orderDetails;
    private  String orderData;
    private Date date;

    public BarOrderDTO(int id, String customerId, String user, List<BarOrderDetailDTO> orderDetails) {
        this.id = id;
        this.customerId = customerId;
        this.user = user;
        this.orderDetails = orderDetails;
    }

    public BarOrderDTO(int id, String customerId, String user, List<BarOrderDetailDTO> orderDetails, String orderData) {
        this.id = id;
        this.customerId = customerId;
        this.user = user;
        this.orderDetails = orderDetails;
        this.orderData = orderData;
    }

    public BarOrderDTO(int id, String customerId, String user, List<BarOrderDetailDTO> orderDetails, String orderData, Date date) {
        this.id = id;
        this.customerId = customerId;
        this.user = user;
        this.orderDetails = orderDetails;
        this.orderData = orderData;
        this.date = date;
    }

    public BarOrderDTO(int orderId) {
        this.id = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BarOrderDTO() {
    }

    public String getOrderData() {
        return orderData;
    }

    public void setOrderData(String orderData) {
        this.orderData = orderData;
    }

    public BarOrderDTO(int orderId, String customerId, String user) {
        this.id = orderId;
        this.customerId = customerId;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<BarOrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<BarOrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "BarOrderDTO{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", user='" + user + '\'' +
                ", orderDetails=" + orderDetails +
                '}';
    }
}//End Class
