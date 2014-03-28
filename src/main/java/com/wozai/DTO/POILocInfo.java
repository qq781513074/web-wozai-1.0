package com.wozai.DTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wyzengzihao
 * Date: 14-1-10
 * Time: 下午2:34
 * To change this template use File | Settings | File Templates.
 */
public class POILocInfo {
    String status;
    String location;
    String formatted_address;
    String business;
    AddressComponent addressComponent;
    List<POIsInfo> point;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public AddressComponent getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(AddressComponent addressComponent) {
        this.addressComponent = addressComponent;
    }

    public List<POIsInfo> getPoint() {
        return point;
    }

    public void setPoint(List<POIsInfo> point) {
        this.point = point;
    }
}
