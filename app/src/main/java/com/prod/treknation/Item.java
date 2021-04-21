package com.prod.treknation;

import android.text.Spanned;

public class Item {
    private int id;
    private String itemName;
    private String itemNameWithoutSequencesNumber ;
    private String itemNameCompltedName;
    private String itemShortDesc;
    private boolean isExpanded;
    private Spanned itemLongDesc;
    public boolean isViewed = false;

    public Item(int id, String itemName, String compltedName,String itemNameWithoutSequencesNumber,String itemShortDesc, boolean isExpanded, Spanned itemLongDesc) {
        this.id = id;
        this.itemName = itemName;
        this.itemShortDesc = itemShortDesc;
        this.isExpanded = isExpanded;
        this.itemLongDesc = itemLongDesc;
        this.itemNameCompltedName=compltedName;
        this.itemNameWithoutSequencesNumber=itemNameWithoutSequencesNumber;
//        this.isViewed = isViewed;
    }

    public Item(int id, String itemName,String itemShortDesc, boolean isExpanded, Spanned itemLongDesc) {
        this.id = id;
        this.itemName = itemName;
        this.itemShortDesc = itemShortDesc;
        this.isExpanded = isExpanded;
        this.itemLongDesc = itemLongDesc;
//        this.isViewed = isViewed;
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

    public String getItemNameWithoutSequencesNumber() {
        return itemNameWithoutSequencesNumber;
    }

    public void setItemNameWithoutSequencesNumber(String itemNameWithoutSequencesNumber) {
        this.itemNameWithoutSequencesNumber = itemNameWithoutSequencesNumber;
    }

    public String getItemNameCompltedName() {
        return itemNameCompltedName;
    }

    public void setItemNameCompltedName(String itemNameCompltedName) {
        this.itemNameCompltedName = itemNameCompltedName;
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

    public Spanned getItemLongDesc() {
        return itemLongDesc;
    }

    public void setItemLongDesc(Spanned itemLongDesc) {
        this.itemLongDesc = itemLongDesc;
    }


    public boolean isViewed() {
        return isViewed;
    }

    public void setViewed(boolean viewed) {
        isViewed = viewed;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemShortDesc='" + itemShortDesc + '\'' +
                ", isExpanded=" + isExpanded +
//                ", itemLongDesc='" + itemLongDesc + '\'' +
                '}';
    }
}
