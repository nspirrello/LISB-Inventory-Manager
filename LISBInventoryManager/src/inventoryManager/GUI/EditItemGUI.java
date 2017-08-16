/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryManager.GUI;


import inventoryManager.InvItems.InventoryItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Zach Pirrello
 */
public class EditItemGUI {
    private BorderPane borderPane;
    private static EditItemGUI gui = null;
    private MainGUI mainGui;
    private boolean isActive = false;
    private Button applyChanges, cancel;
    private InventoryItem selected;
    
    private TextField inventoryID,vendor,color,description,dimensions,caseQuant,price,quant;
    
    private ComboBox type;
    private ObservableList typeList;
    
    private VBox contentHolder;
    
    protected EditItemGUI(MainGUI mainGUI){
        mainGui = mainGUI;
        selected = mainGui.getSelectedFromList();
        typeList = FXCollections.observableArrayList();
        typeList.addAll("Lumber","Siding","Roofing","Hardware","Paint","Windows","Trim","Doors");
        this.borderPane = mainGUI.getBorderPane();
        buildEditArea();
    }
    
    public static EditItemGUI getEditItemGUI(MainGUI mainGUI){
        if(gui == null)
            gui = new EditItemGUI(mainGUI);
        return gui;
    }
    
    public void buildEditArea(){
        type = new ComboBox();
        type.setEditable(false);
        type.setItems(typeList);
        type.getStyleClass().add("button");
        
        String inventoryIDText = "Inventory ID";
        inventoryID = new TextField();
        inventoryID.setPromptText(inventoryIDText);
        
        String vendorText = "Vendor";
        vendor = new TextField();
        vendor.setPromptText(vendorText);
        
        String colorText = "Color";
        color = new TextField();
        color.setPromptText(colorText);
        
        String descriptionText = "Description";
        description = new TextField();
        description.setPromptText(descriptionText);
        
        String dimensionsText = "Dimensions";
        dimensions = new TextField();
        dimensions.setPromptText(dimensionsText);
        
        String caseQuantText = "Case/Skid Quantity";
        caseQuant = new TextField();
        caseQuant.setPromptText(caseQuantText);
        
        String priceText = "Price";
        price = new TextField();
        price.setPromptText(priceText);
        
        String quantText = "Quantity";
        quant = new TextField();
        quant.setPromptText(quantText);
        
        HBox buttons = new HBox(20);  
        String cancelText = "Cancel";
        cancel = new Button(cancelText);
        String applyText = "Apply";
        applyChanges = new Button(applyText);
        buttons.getChildren().addAll(applyChanges,cancel);
        buttons.setAlignment(Pos.CENTER);
      
        
        contentHolder = new VBox(20);
        contentHolder.prefWidthProperty().bind(borderPane.widthProperty().multiply(.15));
        contentHolder.getChildren().addAll(type,inventoryID,vendor,color,description,dimensions,caseQuant,price,quant,buttons);
        contentHolder.setAlignment(Pos.CENTER);
        contentHolder.setPadding(new Insets(10,10,10,10));
        
        contentHolder.getStyleClass().add("editPane");

        cancel.setOnAction(e -> {
            TableView table = mainGui.getInventoryTable();
            table.getSelectionModel().clearSelection();
            deactivate(borderPane);
        });
        
        applyChanges.setOnAction(e -> {
            setNewValues();
            mainGui.getInventoryTable().refresh();
            deactivate(borderPane);
        });
        
    }
    
    public void setNewValues(){
        selected.setInventoryID(inventoryID.getText());
        selected.setColor(color.getText());
        selected.setVendor(vendor.getText());
        selected.setDescription(description.getText());
        selected.setDimensions(dimensions.getText());
        selected.setPackQuant(Integer.parseInt(caseQuant.getText()));
        selected.setPrice(price.getText());
        selected.setQuant(Integer.parseInt(quant.getText()));
    }
    
    public void setValues(InventoryItem i){
        type.getSelectionModel().select(i.getType());
        inventoryID.setText(i.getInventoryID());
        vendor.setText(i.getVendor());
        color.setText(i.getColor());
        description.setText(i.getDescription());
        dimensions.setText(i.getDimensions());
        caseQuant.setText(i.getPackQuant()+"");
        price.setText(i.getPrice());
        quant.setText(i.getQuant()+"");
    }

    public void activate(BorderPane borderPane) {
        borderPane.setRight(contentHolder);
        selected = mainGui.getSelectedFromList();
        setValues(selected);
        isActive = true;
    }
    
    public void deactivate(BorderPane borderPane){
        borderPane.setRight(null);
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }
    
}
