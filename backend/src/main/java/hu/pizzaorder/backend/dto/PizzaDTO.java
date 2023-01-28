package hu.pizzaorder.backend.dto;

import java.util.List;
import java.util.Objects;

public class PizzaDTO {
    private String name;
    private Integer price;
    private List<String> addons;

    public PizzaDTO() {
    }

    public PizzaDTO(String name, Integer price, List<String> addons) {
        this.name = name;
        this.price = price;
        this.addons = addons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<String> getAddons() {
        return addons;
    }

    public void setAddons(List<String> addons) {
        this.addons = addons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaDTO pizzaDTO = (PizzaDTO) o;
        return Objects.equals(name, pizzaDTO.name) && Objects.equals(price, pizzaDTO.price) && Objects.equals(addons, pizzaDTO.addons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, addons);
    }

    @Override
    public String toString() {
        return "PizzaDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", addons=" + addons +
                '}';
    }
}
