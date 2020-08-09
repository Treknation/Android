package ca.treknation.myapplicationclone;

public class Item {
    private int id;
    private String itemName;
    private String itemShortDesc;
    private boolean isExpanded;
    private String itemLongDesc;

    public Item(int id, String itemName, String itemShortDesc, boolean isExpanded, String itemLongDesc) {
        this.id = id;
        this.itemName = itemName;
        this.itemShortDesc = itemShortDesc;
        this.isExpanded = isExpanded;
        this.itemLongDesc = itemLongDesc;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemShortDesc() {
        return itemShortDesc;
    }

    public void setItemShortDesc(String itemShortDesc) {
        this.itemShortDesc = itemShortDesc;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public String getItemLongDesc() {
        return itemLongDesc;
    }

    public void setItemLongDesc(String itemLongDesc) {
        this.itemLongDesc = itemLongDesc;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemShortDesc='" + itemShortDesc + '\'' +
                ", isExpanded=" + isExpanded +
                ", itemLongDesc='" + itemLongDesc + '\'' +
                '}';
    }
}
