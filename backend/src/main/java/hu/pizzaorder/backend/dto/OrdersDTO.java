package hu.pizzaorder.backend.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OrdersDTO {
    private String username;
    private List<Long> pizzaIds;
    private Date time;

    public OrdersDTO() {
    }

    public OrdersDTO(String username, List<Long> pizzaIds, Date time) {
        this.username = username;
        this.pizzaIds = pizzaIds;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Long> getPizzaIds() {
        return pizzaIds;
    }

    public void setPizzaIds(List<Long> pizzaIds) {
        this.pizzaIds = pizzaIds;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersDTO ordersDTO = (OrdersDTO) o;
        return Objects.equals(username, ordersDTO.username) && Objects.equals(pizzaIds, ordersDTO.pizzaIds) && Objects.equals(time, ordersDTO.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, pizzaIds, time);
    }

    @Override
    public String toString() {
        return "OrdersDTO{" +
                "username='" + username + '\'' +
                ", pizzaIds=" + pizzaIds +
                ", time=" + time +
                '}';
    }
}
