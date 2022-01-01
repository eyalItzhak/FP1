package Command;

import model.Store;

public class RedoCommand implements Command {
	
	private Store theModel;
	
	public RedoCommand(Store theModel) {
		this.theModel = theModel;
	}
	
	@Override
	public void execute() {
		theModel.SetStateFromLastSave();
		theModel.SaveFile();
	}

}
