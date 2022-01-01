package controller;

import java.awt.Window.Type;
import java.util.ArrayList;

import Command.Command;
import Command.NewProductCommand;
import Command.RedoCommand;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Customer;
import model.Product;
import model.Store;
import view.ViewDisplayProd;
import view.ViewNewProduct;
import view.ViewProductByCN;

public class ControllerNewProd {
	private ViewNewProduct theView;
	//private ArrayList<Product> allProducts;
	private Store theModel;
	private Command redoCommand;
	private Command newProductCommand;
	
	public ControllerNewProd(ViewNewProduct newProdView, Store theModel) {
		this.theView = newProdView;
		this.theModel=theModel;

		EventHandler<ActionEvent> eventToSaveBtn = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				newProductCommand = new NewProductCommand(theModel,theView);
				newProductCommand.execute();
			}
		};
	    
		theView.addEventHandlerToSaveBtn(eventToSaveBtn);
		
		
		EventHandler<ActionEvent> eventToRedoBtn = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(theModel.MomentoIsExis()) {
					
					redoCommand = new RedoCommand(theModel);
					redoCommand.execute();
					
					Alert a = new Alert(AlertType.INFORMATION);
					a.setHeaderText("Confirmation");
					a.setContentText("Product Removed");
					a.show();
					
					theView.getRedo().setDisable(true);
				}
			}
		} ;
		theView.addEventHandlerRedoBtn(eventToRedoBtn);
		
	}

}
