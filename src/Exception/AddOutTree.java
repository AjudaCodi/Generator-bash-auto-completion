package Exception;

import java.lang.Exception;

@SuppressWarnings("serial")
public class AddOutTree extends Exception {
	public AddOutTree(String msg) {
		super(msg);
	}
}
//throw new Exception("Can\'t add with this profundity");
