package ca.treknation.myapplicationclone;

public class Item {
    private int id;
    private String itemName;
    private String itemShortDesc;
    private boolean isExpanded;
    private String learnMore;
    private String itemLongDesc;

    public Item(int id, String itemName, String itemShortDesc) {
        this.id = id;
        this.itemName = itemName;
        this.itemShortDesc = itemShortDesc;
        isExpanded = false;
        this.itemLongDesc = itemLongDesc;
        this.learnMore = learnMore;
    }

    public String getLearnMore() {
        return learnMore;
    }

    public void setLearnMore(String learnMore) {
        this.learnMore = learnMore;
    }

    public String getItemLongDesc() {
        return itemLongDesc;
    }

    public void setItemLongDesc(String itemLongDesc) {
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

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", itemShortDesc='" + itemShortDesc + '\'' +
                '}';
    }
}
