/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryManager.InvItems;

/**
 *
 * @author Zach Pirrello
 */
public abstract class InventoryItem {
    private String type;
    private String inventoryID;
    private String vendor;
    private String color;
    private String description;
    private String dimensions;
    private int packQuant;
    private String price;
    private int quant;
    
    public InventoryItem(String invId, String vendor, String color, String desc, String dimensions, int packQuant, String price, int quant){
        this.type = "Item";
        this.inventoryID = invId;
        this.vendor = vendor;
        this.color = color;
        this.description = desc;
        this.dimensions = dimensions;
        this.packQuant = packQuant;
        this.price = price;
        this.quant = quant;
    }
    
    public String getType(){
        return type;
    }
    
    public String getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(String inventoryID) {
        this.inventoryID = inventoryID;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public int getPackQuant() {
        return packQuant;
    }

    public void setPackQuant(int packQuant) {
        this.packQuant = packQuant;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }
    
}
