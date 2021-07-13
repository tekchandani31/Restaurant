package com.example.project;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class itemData {
    String itemName;
    String itemType;
    String itemCost;
    String documentId;

    public itemData() {

    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @ServerTimestamp
    Date timestamp;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }



    public itemData(String itemName, String itemType, String itemCost){

    }

    public itemData(String itemName, String itemType, String itemCost, String documentId,Date timestamp) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemCost = itemCost;
        this.documentId = documentId;
        this.timestamp = timestamp;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemCost() {
        return itemCost;
    }

    public void setItemCost(String itemCost) {
        this.itemCost = itemCost;
    }
}
