package hu.pizzaorder.backend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accountUsername", nullable = false)
    private String username;

    @Column(name = "pizzaIds", nullable = false)
    @ElementCollection(targetClass=Long.class)
    private List<Long> pizzaIds;

    @Column(name = "time", nullable = false)
    private Date time;

    public Long getId() {
        return id;
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
}
