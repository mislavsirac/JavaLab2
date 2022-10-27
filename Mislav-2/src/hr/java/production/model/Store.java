package hr.java.production.model;

public class Store {
    String name;
    String webAddress;
    Item[] items;
    int len;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
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

    public Store(String name, String webAddress, Item[] items) {
        this.name = name;
        this.webAddress = webAddress;
        this.items = items;
        this.len = 0;
    }
}
