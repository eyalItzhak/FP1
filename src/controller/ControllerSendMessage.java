package controller;

import java.util.ArrayList;

import Command.Command;
import Command.SendMessageCommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import observer.MessegRespond;
import model.Store;
import view.ViewMessage;
import view.ViewNewProduct;

public class ControllerSendMessage {
	private Store theModel;
	private ViewMessage theView;
	private Command sendMessageCommand;
	
	public ControllerSendMessage(ViewMessage newProdView, Store theModel) {
		
		this.theModel=theModel;
		this.theView=newProdView;
		
		EventHandler<ActionEvent> eventToShowMess = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				sendMessageCommand = new SendMessageCommand(theModel, theView);
				sendMessageCommand.execute();
			}
		};
		theView.addEventHandlerToMess(eventToShowMess);
	}
	
	
	public Store getTheModel() {
		return theModel;
	}
	public void setTheModel(Store theModel) {
		this.theModel = theModel;
	}
	public ViewMessage getView() {
		return theView;
	}
	public void setView(ViewMessage view) {
		this.theView = view;
	}
	
}
