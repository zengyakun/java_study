package cc.eslink.dto;

/**
 *@ClassName Company
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/7/17 10:49
 *@Version 1.0
 **/
public class Company {

    private String name;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Company(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Company() {
    }
}
