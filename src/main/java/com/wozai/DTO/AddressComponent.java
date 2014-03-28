package com.wozai.DTO;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 14-1-10
 * Time: 下午2:37
 * To change this template use File | Settings | File Templates.
 */
public class AddressComponent {
    String city;
    String district;
    String province;
    String street;
    String street_number;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }
}
