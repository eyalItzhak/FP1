package Command;

import java.util.ArrayList;

import model.Store;
import view.ViewMessage;

public class SendMessageCommand implements Command {
	private Store theModel;
	private ViewMessage theView;
	
	public SendMessageCommand(Store theModel,ViewMessage theView) {
		this.theModel = theModel;
		this.theView = theView;
	}
	@Override
	public void execute() {
		String Msg=theView.getMess().getText();
		ArrayList<String> names=theModel.SentMesgToSubscribCustomer(Msg);
		theView.RunListThreath(names);
		
	}
	
}
