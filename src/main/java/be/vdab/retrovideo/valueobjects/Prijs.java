package be.vdab.retrovideo.valueobjects;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

public class Prijs {
	@NumberFormat(pattern = "0.00")
	private final BigDecimal waarde;
	
	public Prijs(BigDecimal waarde) {
		this.waarde = waarde;
	}

	public BigDecimal getWaarde() {
		return waarde;
	}
}
