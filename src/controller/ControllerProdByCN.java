package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import Command.Command;
import Command.DeleteProdCommand;
import file_execut.file_Iterator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Product;
import model.Store;
import view.ViewDisplayProd;
import view.ViewMenu;
import view.ViewProductByCN;

public class ControllerProdByCN {

	private Store theModel;
	private ViewProductByCN theView;
	private ViewDisplayProd displayPView;
	private boolean find;
	private Command deleteProdCommand;
	
	public ControllerProdByCN(Store theModel,ViewProductByCN theView,ViewDisplayProd displayPView) {
		this.theModel=theModel;
		this.theView=theView;
		this.displayPView = displayPView;
		
		EventHandler<ActionEvent> eventToRemove= new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			Product p=theView.getP();
			Product pToCompare;
			
			try {
				find=false;
				file_Iterator newFileIterator=new file_Iterator(theModel.getFile().getTotalFileSize());
				int removePos=newFileIterator.getMyCurrentPos();
				
				while ((newFileIterator.hasNext())&&(find==false)) {
					pToCompare=newFileIterator.next();
					if(pToCompare.getMKTid().equals(p.getMKTid())) {
						newFileIterator.remove();
						find=true;
						
						deleteProdCommand = new DeleteProdCommand(theModel, p);
						deleteProdCommand.execute();
					}
					removePos=newFileIterator.getMyCurrentPos();

				}
				
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			
			Alert a = new Alert(AlertType.INFORMATION);
			a.setHeaderText("Confirmation");
			a.setContentText("Product Removed");
			a.show();
			
			theView.getStage().hide();
			displayPView.getStage().hide();
			
			}
		};

		theView.addEventHandlerToBtnToRemove(eventToRemove);
		
	}
}
