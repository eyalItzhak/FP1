package controller;

import java.util.ArrayList;

import Command.Command;
import Command.ExitCommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Customer;
import model.Product;
import model.Store;
import view.ViewDisplayProd;
import view.ViewMenu;
import view.ViewMessage;
import view.ViewNewProduct;
import view.ViewProductByCN;

public class ControllerMenu {
	private Store theModel;
	private ViewMenu theView;
	private ViewDisplayProd displayProdView;
	private ViewNewProduct newProdView;
	private ViewMessage newMessageView;
	private Command exitCommand;

	
	public ControllerMenu(Store m, ViewMenu v) {
		theModel = m;
		theView = v;
		theModel.loadFile();
		
		
		
		EventHandler<ActionEvent> eventToBtnDisplayAllProd = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				theModel.OrderBy(theView.GetOrderedBy());
				displayProdView = new ViewDisplayProd(new Stage(), theModel.getProductArry());
				ControllerDisplayProd controller2 = new ControllerDisplayProd(displayProdView, theModel);
			}
		};

		theView.addEventHandlerToBtnDisplayAllProd(eventToBtnDisplayAllProd);
		
		EventHandler<ActionEvent> eventToBtnNewProduct = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(theModel.getOrder().equals("")) {
					theModel.OrderBy(theView.GetOrderedBy());
				}
				newProdView = new ViewNewProduct(new Stage(),theModel.getProductArry());
				ControllerNewProd controller3 = new ControllerNewProd(newProdView,theModel);
			}
		};

		theView.addEventHandlerToBtnNewProd(eventToBtnNewProduct);
		
		
		
		
		EventHandler<ActionEvent> eventToExit = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				exitCommand = new ExitCommand(theModel, theView);
				exitCommand.execute();
			}
		};
		theView.addEventHandlerToExit(eventToExit);
		
		
		
		EventHandler<ActionEvent> eventOpenMessage = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				newMessageView=new  ViewMessage(new Stage());
				ControllerSendMessage controller4 = new ControllerSendMessage(newMessageView,theModel);
			}
		};
		theView.addEventHandlerToBtnDisplayMessage(eventOpenMessage);
		
	}
	
	
}


