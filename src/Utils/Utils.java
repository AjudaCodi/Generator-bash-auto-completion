package Utils;

public class Utils {
	private static final int EXIT_ERROR = -1;
	private static final int EXIT_SUCCESS = 0;
	private static final int EXIT_FAILURE = 1;


	public static void MessageError(String msgE) {
		System.out.println(msgE);
		System.exit(EXIT_ERROR);
	}
}
