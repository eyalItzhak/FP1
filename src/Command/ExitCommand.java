package Command;

import model.Store;
import view.ViewMenu;

public class ExitCommand implements Command {
	private Store theModel;
	private ViewMenu theView;
	
	public ExitCommand(Store theModel,ViewMenu theView) {
		this.theModel = theModel;
		this.theView = theView;
	}
	@Override
	public void execute() {
		theModel.SaveFile();
		theView.closeStage();
	}
}
