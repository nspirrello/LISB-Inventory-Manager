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
public class Siding extends InventoryItem{
    private String type;
    public Siding(String invId, String vendor, String color, String desc, String dimensions, int packQuant, String price, int quant){
        super(invId,vendor,color,desc,dimensions,packQuant,price,quant);
        type = "Siding";
    }

    public String getType() {
        return type;
    }
}
