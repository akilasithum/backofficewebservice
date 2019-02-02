package com.back.office.ws.entity;

    public class OrderMainDetails {

    private int orderDetailId;
    private String orderId;
    private String tax;
    private float taxFloat;
    private String discount;
    private float discountFloat;
    private String seatNo;
    private String subTotal;
    private float subTotalFloat;
    private String serviceType;
    private String flightId;

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

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
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
    }
