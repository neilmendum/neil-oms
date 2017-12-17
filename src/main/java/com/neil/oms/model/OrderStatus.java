package com.neil.oms.model;

/**
 * Created by neilmendum on 16/12/2017.
 */
public class OrderStatus {
    private final long id;
    private final Stock stock;
    private final OrderStatusMessage orderStatusMessage;

    public OrderStatus(long id, Stock stock, OrderStatusMessage orderStatusMessage) {
        this.id = id;
        this.stock = stock;
        this.orderStatusMessage = orderStatusMessage;
    }

    public long getId() {
        return id;
    }

    public Stock getStock() {
        return stock;
    }

    public OrderStatusMessage getOrderStatusMessage() {
        return orderStatusMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderStatus that = (OrderStatus) o;

        if (id != that.id) return false;
        if (!stock.equals(that.stock)) return false;
        return orderStatusMessage == that.orderStatusMessage;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + stock.hashCode();
        result = 31 * result + orderStatusMessage.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "id=" + id +
                ", stock=" + stock +
                ", orderStatusMessage=" + orderStatusMessage +
                '}';
    }
}
