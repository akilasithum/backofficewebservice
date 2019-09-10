package com.back.office.ws.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderMainDetails {

    private int orderDetailId;
    private String orderId;
    private String tax;
    private float taxFloat;
    private String discount;
    private float discountFloat;
    private String subTotal;
    private float subTotalFloat;
    private String category;
    private String flightId;
    private String sellerId;
    private Date date;
    private Date flightDateVal;
    private String flightDate;

        public int getOrderDetailId() {
            return orderDetailId;
        }

        public void setOrderDetailId(int orderDetailId) {
            this.orderDetailId = orderDetailId;
        }

        public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public float getTaxFloat() {
            return taxFloat;
        }

        public void setTaxFloat(float taxFloat) {
            this.taxFloat = taxFloat;
        }

        public float getDiscountFloat() {
            return discountFloat;
        }

        public void setDiscountFloat(float discountFloat) {
            this.discountFloat = discountFloat;
        }

        public float getSubTotalFloat() {
            return subTotalFloat;
        }

        public void setSubTotalFloat(float subTotalFloat) {
            this.subTotalFloat = subTotalFloat;
        }

        public String getFlightId() {
            return flightId;
        }

        public void setFlightId(String flightId) {
            this.flightId = flightId;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getFlightDateVal() {
        return flightDateVal;
    }

    public void setFlightDateVal(Date flightDateVal) {
        this.flightDateVal = flightDateVal;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }
}
