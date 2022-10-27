package hr.java.production.model;

public class Factory {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public Factory(String name, Address address, Item[] items) {
        this.name = name;
        this.address = address;
        this.items = items;
        this.len = 0;
    }

    String name;
    Address address;
    Item[] items ;
    int len;

}
