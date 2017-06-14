package ua.step.kostyn.retrofitfich.models;

/**
 * Created by konstantin on 31.05.17.
 */

public class DepartmentModel {
    String name;
    String state;
    String id;
    String country;
    String sity;
    String index;
    String phone;
    String email;
    String address;

    public DepartmentModel(String name, String state, String id, String country, String sity, String index, String phone, String email, String address) {
        this.name = name;
        this.state = state;
        this.id = id;
        this.country = country;
        this.sity = sity;
        this.index = index;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSity() {
        return sity;
    }

    public void setSity(String sity) {
        this.sity = sity;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "DepartmentModel{" +
                "name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", id='" + id + '\'' +
                ", country='" + country + '\'' +
                ", sity='" + sity + '\'' +
                ", index='" + index + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
