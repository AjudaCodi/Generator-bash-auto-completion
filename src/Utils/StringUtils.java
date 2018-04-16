package Utils;

public class StringUtils {
	public static String badJoin(String separator, Iterable<String> values) {
		StringBuilder out = new StringBuilder();

		for (String i : values)
			out.append(separator).append(i);
		return out.toString();
	}
}
