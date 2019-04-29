import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductParser {
	
	private static final String PRODUCTSTRING_COMPOSITION_REGEX = "([0-9]+) (.*?) at ([0-9]+\\.[0-9]{2})";
	public static final String IMPORT_PRODUCT_LABEL = "imported: ";
	
	public static List<String> splitStrings(String products) {
		List<String> split = new ArrayList<>();
		Matcher matcher = Pattern.compile(PRODUCTSTRING_COMPOSITION_REGEX)
				.matcher(products);
		while (matcher.find()) {
			split.add(matcher.group());
		}
		
		return split;
	}
	
	public static Products productsToString(String products) {
		Matcher matcher = Pattern.compile(PRODUCTSTRING_COMPOSITION_REGEX)
				.matcher(products);
		matcher.matches();
		int amount = Integer.valueOf(matcher.group(1));
		String name = cleanUp(matcher.group(2));
		BigDecimal cost = new BigDecimal(matcher.group(3));
		boolean imported = matcher.group(2).contains(IMPORT_PRODUCT_LABEL);
		Products newProduct = new Products(amount, name, cost, imported);
		
		return newProduct;
	}
	
	public static String cleanUp(String name) {
		name.replace(IMPORT_PRODUCT_LABEL, "");
		
		return name;
	}
}
