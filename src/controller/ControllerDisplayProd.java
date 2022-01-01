package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import Command.Command;
import Command.DeleteAllProdCommand;
import file_execut.file_Iterator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Product;
import model.Store;
import view.ViewDisplayProd;
import view.ViewProductByCN;

public class ControllerDisplayProd {
	private ViewDisplayProd theView;
	private ViewProductByCN prodByCNView;
	private ArrayList<Product> allProducts;
	private Store theModel;
	private ControllerProdByCN controllerProdByCN;
	private Command deleteAllProdCommand;
	
	public ControllerDisplayProd(ViewDisplayProd displayProdView,Store theModel) {
		this.theView = displayProdView;
		this.theModel=theModel;
		this.allProducts = theModel.getProductArry();
		
		
		EventHandler<KeyEvent> eventToTxtFCatalogNum = new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	            	String cn = displayProdView.getTxtF1().getText();
	            	prodByCNView = new ViewProductByCN(new Stage(),theModel,cn);
	            	controllerProdByCN=new ControllerProdByCN(theModel,prodByCNView,theView);
	            }
	        }
	    };
	    
	    displayProdView.addEventHandlerToTxtF(eventToTxtFCatalogNum);
	    
	    
	    
	    EventHandler<ActionEvent> eventToBtnRemoveAllProd = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				try {
					file_Iterator newIt=new file_Iterator(theModel.getFile().getTotalFileSize());
					newIt.removeAll();
					
					if(theModel.getProductArry().size() == 0) {
						Alert a = new Alert(AlertType.INFORMATION);
						a.setHeaderText("Error");
						a.setContentText("No Products to Remove");
						a.show();
					}else {
						Alert a = new Alert(AlertType.INFORMATION);
						a.setHeaderText("Confirmation");
						a.setContentText("All Products Removed");
						a.show();
						
						theView.getStage().hide();
					}
					
					deleteAllProdCommand = new DeleteAllProdCommand(theModel);
					deleteAllProdCommand.execute();
						
					
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		};

		theView.addEventHandlerToRemoveAllProduct(eventToBtnRemoveAllProd);
	    
	}
	
}
