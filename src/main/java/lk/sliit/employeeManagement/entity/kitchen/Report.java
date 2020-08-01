package lk.sliit.employeeManagement.entity.kitchen;

import lk.sliit.employeeManagement.entity.kitchen.FoodItem;
import javax.persistence.*;
import java.sql.Date;

@Entity
public class Report {
    @Id
    private String reportId;
    private double quantity;
    private Date date;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name="foodItem",referencedColumnName = "itemId", insertable = false, updatable = false)
    private FoodItem foodItem;


    public Report(String reportId, double quantity, Date date, FoodItem foodItem) {
        this.reportId = reportId;
        this.quantity = quantity;
        this.date = date;
        this.foodItem = foodItem;
    }

    public Report() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
