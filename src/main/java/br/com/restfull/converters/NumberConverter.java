package br.com.restfull.converters;

public final class NumberConverter {

	public static Double convertToDouble(String strDouble) {
		if (strDouble == null)
			return 0D;
		strDouble = strDouble.replaceAll(",", ".");
		if (!isNaN(strDouble))
			return Double.parseDouble(strDouble);
		return 0D;
	}

	public static boolean isNaN(String strDouble) {
		if (strDouble == null)
			return true;
		String number = strDouble.replaceAll(",", ".");
		return !number.matches("[+-]?[0-9]*\\.?[0-9]+");
	}

	public static Long convertToLong(String id) {
		if (!isNaN(id))
			return Long.parseLong(id);
		return 1L;
	}
}
