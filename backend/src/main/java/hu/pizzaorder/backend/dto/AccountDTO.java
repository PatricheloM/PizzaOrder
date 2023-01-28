package hu.pizzaorder.backend.dto;

import hu.pizzaorder.backend.model.Orders;

import java.util.List;
import java.util.Objects;

public class AccountDTO {
    private String username;
    private String password;
    private String email;
    private Integer postcode;
    private String city;
    private String addr1;
    private String addr2;

    public AccountDTO() {
    }

    public AccountDTO(String username, String password, String email, Integer postcode, String city, String addr1, String addr2, List<Orders> orders) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.postcode = postcode;
        this.city = city;
        this.addr1 = addr1;
        this.addr2 = addr2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDTO that = (AccountDTO) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(email, that.email) && Objects.equals(postcode, that.postcode) && Objects.equals(city, that.city) && Objects.equals(addr1, that.addr1) && Objects.equals(addr2, that.addr2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, postcode, city, addr1, addr2);
    }


    @Override
    public String toString() {
        return "AccountDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", postcode=" + postcode +
                ", city='" + city + '\'' +
                ", addr1='" + addr1 + '\'' +
                ", addr2='" + addr2 +
                '}';
    }
}
