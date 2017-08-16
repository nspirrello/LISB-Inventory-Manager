/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryManager.GUI;


import inventoryManager.InvItems.Door;
import inventoryManager.InvItems.InventoryItem;
import inventoryManager.InvItems.Lumber;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Zach Pirrello
 */
public class MainGUI {
    private static MainGUI gui = null;
    private static Stage stage;
    private BorderPane borderPane;
    
    private TableView<InventoryItem> inventoryTable;
    //Add all potential TableCol's below
    private TableColumn<InventoryItem, String> inventoryID;
    private TableColumn<InventoryItem, String> vendor;
    private TableColumn<InventoryItem, String> color;
    private TableColumn<InventoryItem, String> description;
    private TableColumn<InventoryItem, String> dimensions;
    private TableColumn<InventoryItem, Integer> quantity;
    
    private Button increQuantBttn,decreQuantBttn,applyFindBttn,editItemBttn,addEntryBttn;
    private TextField searchCriteria;
    
    private MenuBar menuBar;
    private Menu fileMenu,toolsMenu;
    private MenuItem aboutMenuItem,increQuantMenuItem,decreQuantMenuItem,addMenuItem,editMenuItem;
    
    private ObservableList typeList;
    
    private ComboBox searchType;
    
    private EditItemGUI editGui;
    
    protected MainGUI(Stage stage) {
        typeList = FXCollections.observableArrayList();
        typeList.addAll("All","Lumber","Siding","Roofing","Hardware","Paint","Windows","Trim","Doors");
        this.stage = stage;
        borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 1200,800);
        scene.getStylesheets().addAll(this.getClass().getResource("inventory_manager.css").toExternalForm());
//        buildTopMenus();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/shed.png")));
        stage.setTitle("Long Island Shed Builders Inventory Manager");
        buildBottomMenus();
        buildInvTable();
        stage.setScene(scene);
    }
    
    public static MainGUI getGui(Stage stage){
        if(gui == null){
            gui = new MainGUI(stage);
        }
        return gui;
    }
    
    protected EditItemGUI getEdit(){
        EditItemGUI editGUI = EditItemGUI.getEditItemGUI(getGui(stage));
        return editGUI;
    }
    
    protected void buildInvTable(){
        
        inventoryTable = new TableView();
        HBox tableHolder = new HBox();
        tableHolder.getChildren().addAll(inventoryTable);
        tableHolder.setPadding(new Insets(10,10,0,10));
        borderPane.setCenter(tableHolder);
        borderPane.getStyleClass().add("borderPaneMain");
        
        
        tableHolder.prefWidthProperty().bind(borderPane.widthProperty());
        inventoryTable.prefWidthProperty().bind(tableHolder.widthProperty());
        
        
        //REMOVE THIS LATER
        ObservableList<InventoryItem> bs = FXCollections.observableArrayList();
        bs.add(new Lumber("123","WEH","blue","something","12x4x8",12,"$48.00",48));
        bs.add(new Door("124","WEH2","blue","something2","12x4x8",12,"$48.00",48));
        inventoryTable.setItems(bs);
        
        PropertyValueFactory<InventoryItem, String> invIdVal = new PropertyValueFactory<InventoryItem, String>("inventoryID");
        PropertyValueFactory<InventoryItem, String> vendorVal = new PropertyValueFactory<InventoryItem, String>("vendor");
        PropertyValueFactory<InventoryItem, String> colorVal = new PropertyValueFactory<InventoryItem, String>("color");
        PropertyValueFactory<InventoryItem, String> descVal = new PropertyValueFactory<InventoryItem, String>("description");
        PropertyValueFactory<InventoryItem, String> dimensionVal = new PropertyValueFactory<InventoryItem, String>("dimensions");
        PropertyValueFactory<InventoryItem, Integer> quantVal = new PropertyValueFactory<InventoryItem, Integer>("quant");
    
        inventoryID = new TableColumn<InventoryItem, String>("Inventory ID");
        inventoryID.setCellValueFactory(invIdVal);
        inventoryID.setResizable(false);
        inventoryID.prefWidthProperty().bind(inventoryTable.widthProperty().multiply(.10));
        
        
        vendor = new TableColumn<InventoryItem, String>("Vendor");
        vendor.setCellValueFactory(vendorVal);
        vendor.setResizable(false);
        vendor.prefWidthProperty().bind(inventoryTable.widthProperty().multiply(.128));
        
        color = new TableColumn<InventoryItem, String>("Color");
        color.setCellValueFactory(colorVal);
        color.setResizable(false);
        color.prefWidthProperty().bind(inventoryTable.widthProperty().multiply(.20));
        
        description = new TableColumn<InventoryItem, String>("Description");
        description.setCellValueFactory(descVal);
        description.prefWidthProperty().bind(inventoryTable.widthProperty().multiply(.30));
        
        dimensions = new TableColumn<InventoryItem, String>("Dimensions");
        dimensions.setCellValueFactory(dimensionVal);
        dimensions.setResizable(false);
        dimensions.prefWidthProperty().bind(inventoryTable.widthProperty().multiply(.20));
        
        quantity = new TableColumn<InventoryItem, Integer>("Quantity");
        quantity.setCellValueFactory(quantVal);
        quantity.setResizable(false);
        quantity.prefWidthProperty().bind(inventoryTable.widthProperty().multiply(.07));
        
        inventoryTable.getColumns().addAll(inventoryID,vendor,color,description,dimensions,quantity);
        
        for(TableColumn tc : inventoryTable.getColumns()){
//            tc.setStyle("-fx-background-color:#64903E");
        }
    }
    
    protected void buildTopMenus(){
        menuBar = new MenuBar();
        
        toolsMenu = new Menu("Tools");
        fileMenu = new Menu("File");
        
        increQuantMenuItem = new MenuItem("Increse quantity");
        decreQuantMenuItem = new MenuItem("Decrease quantity");
        addMenuItem = new MenuItem("Add entry");
        editMenuItem = new MenuItem("Edit entry");
        aboutMenuItem = new MenuItem("About");
        
        menuBar.getMenus().addAll(fileMenu,toolsMenu);
        fileMenu.getItems().addAll(aboutMenuItem);
        toolsMenu.getItems().addAll(increQuantMenuItem,decreQuantMenuItem,addMenuItem,editMenuItem);
        borderPane.setTop(menuBar);

    }
    
    protected void buildBottomMenus(){
        HBox contentContainer = new HBox();
        HBox leftSideContainer = new HBox(10);
        HBox rightSideContainer = new HBox();
        
        //Button and TextField Instantiation
        //Property Text to be loaded from XML...
        
        String searchCriteriaText = "Enter criteria to search...  EX: Vendor,WEH";
        searchCriteria = new TextField();
        searchCriteria.setPromptText(searchCriteriaText);
        
        
        String applyFindText = "Search";
        applyFindBttn = new Button(applyFindText);
        applyFindBttn.getStyleClass().add("button");
        
        String increQuantText = "+1";
        increQuantBttn = new Button(increQuantText);
        
        
        String decreQuantText = "-1";
        decreQuantBttn = new Button(decreQuantText);
        
        
        String editBttnText = "Edit entry";
        editItemBttn = new Button(editBttnText);
        
        
        String addBttnText = "Add entry";
        addEntryBttn = new Button(addBttnText);
        
        
        searchType = new ComboBox();
        searchType.setItems(typeList);
        searchType.getSelectionModel().selectFirst();
        searchType.getStyleClass().add("button");
        
        //Left-Handed Bindings
        increQuantBttn.prefWidthProperty().bind(leftSideContainer.widthProperty().multiply(.05));
        decreQuantBttn.prefWidthProperty().bind(leftSideContainer.widthProperty().multiply(.05));
        editItemBttn.prefWidthProperty().bind(leftSideContainer.widthProperty().multiply(.15));
        addEntryBttn.prefWidthProperty().bind(leftSideContainer.widthProperty().multiply(.15));
        
        //Right-Handed Bindings
        searchCriteria.prefWidthProperty().bind(rightSideContainer.widthProperty().multiply(.7)); 
        applyFindBttn.prefWidthProperty().bind(rightSideContainer.widthProperty().multiply(.10));   
        searchType.prefWidthProperty().bind(rightSideContainer.widthProperty().multiply(.20));
        
        //Left-Handed Buttons
        leftSideContainer.getChildren().addAll(increQuantBttn,decreQuantBttn,editItemBttn,addEntryBttn);
        leftSideContainer.prefWidthProperty().bind(contentContainer.widthProperty().multiply(.55));
        
        //Right-Handed Buttons
        rightSideContainer.getChildren().addAll(searchType,searchCriteria,applyFindBttn);
        rightSideContainer.prefWidthProperty().bind(contentContainer.widthProperty().multiply(.45));
        
        //Bottom Content Area
        contentContainer.getChildren().addAll(leftSideContainer,rightSideContainer);
        contentContainer.prefWidthProperty().bind(borderPane.widthProperty());
        contentContainer.setPadding(new Insets(10,10,10,10));
        
        
       
        
        //Add Bottom to BorderPane's Bottom
        borderPane.setBottom(contentContainer);
        contentContainer.getStyleClass().add("borderPaneBottom");

        editItemBttn.setOnAction(e -> {
            EditItemGUI gui = getEdit();
            boolean active = gui.isActive();
            if(!active && getSelectedFromList() != null){
                gui.activate(borderPane);
            } else {
                gui.deactivate(borderPane);
            }
        });
        
        increQuantBttn.setOnAction(e -> {
            if(getSelectedFromList() != null){
                InventoryItem item = getSelectedFromList();
                item.setQuant(item.getQuant()+1);
                inventoryTable.refresh();
                if(getEdit().isActive()){
                    getEdit().setValues(item);
                }
            }
        });
        decreQuantBttn.setOnAction(e -> {
            if(getSelectedFromList() != null){
                InventoryItem item = getSelectedFromList();
                item.setQuant(item.getQuant()-1);
                inventoryTable.refresh();
                if(getEdit().isActive()){
                    getEdit().setValues(item);
                }
            }
        });
        
     
    }
    
    public InventoryItem getSelectedFromList(){
        return inventoryTable.getSelectionModel().getSelectedItem();
    }
    
    protected static Stage getStage(){
        return stage;
    }

    public ObservableList getTypeList() {
        return typeList;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public TableView getInventoryTable() {
        return inventoryTable;
    }
    
    
}
