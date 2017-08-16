/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryManager;

import inventoryManager.GUI.MainGUI;
import static inventoryManager.GUI.MainGUI.getGui;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Nicholas Pirrello
 */
public class InventoryManagerApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        getGui(stage);
        stage.show();
    }
    
    public static void main(String [] args){
        launch(args);
    }
}
