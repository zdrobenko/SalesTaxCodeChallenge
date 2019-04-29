import java.math.BigDecimal;
import java.math.RoundingMode;

public class Products {

	private int amount;
	private String productName;
	private BigDecimal origPrice;
	private boolean imported;
	
	private BigDecimal inflationRate;
	
	public Products(int amount, String productName, BigDecimal origPrice, boolean imported) {
		this.amount = amount;
		this.productName = productName;
		this.origPrice = origPrice;
		this.imported = imported;
		
		this.inflationRate = BigDecimal.ZERO;
	}
	
	@Override
	public String toString() {
		return amount + " " + getImportLabel() + productName + ": " + getTaxPrice();
	}
	
	public BigDecimal getTaxPrice() {
		return getProductTaxedPrice().multiply(BigDecimal.valueOf(amount));
	}
	
	public BigDecimal getProductTaxedPrice() {
		return origPrice.add(getProductTaxedPrice()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public BigDecimal getProductTaxPrice() {
		BigDecimal taxPrice = origPrice.multiply(inflationRate);
		return roundToTheNearestFiveCents(taxPrice);
	}
	
	public BigDecimal roundToTheNearestFiveCents(BigDecimal tax) {
		return tax.divide(BigDecimal.valueOf(0.05), 0, RoundingMode.UP)
				.multiply(BigDecimal.valueOf(0.05))
				.setScale(2);
	}
	
	public String getImportLabel() {
		return imported ? "imported " : "";
	}
	
}
