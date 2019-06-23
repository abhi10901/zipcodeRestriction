package com.backend.solution;

public class ZipcodeRange {

	private int upperBound;
	private int lowerBound;

	public int getupperBound() {
		return upperBound;
	}

	public void setupperBound(int upperBound) {
		if(!isValidZipcode(upperBound))
			throw new IllegalArgumentException("Invalid Upper Bound " + upperBound + ". Required five digit zipcode.");
		
		this.upperBound = upperBound;
	}

	public int getlowerBound() {
		return lowerBound;
	}

	public void setlowerBound(int lowerBound) {
		if(!isValidZipcode(lowerBound))
			throw new IllegalArgumentException("Invalid Lower Bound " + lowerBound + ". Required five digit zipcode.");
		
		this.lowerBound = lowerBound;
	}

	public ZipcodeRange() {}
	
	public ZipcodeRange(int upperBound, int lowerBound) {
		super();
		this.setupperBound(upperBound);
		this.setlowerBound(lowerBound);
	}
	
	
	private boolean isValidZipcode(int zipcode) {
		if(("" + zipcode).length() != 5)
			return false;
		
		if(!(zipcode >= 10000 && zipcode <= 99999)) 
			return false;
		
		return true;
	}

	public String toString() {
		return this.upperBound + "," + this.lowerBound;
	}
	
	public boolean isRangeWithIn(ZipcodeRange providedRange) {
		return (providedRange.upperBound <= this.upperBound && providedRange.lowerBound >= this.lowerBound);
	}
	
	public boolean isUpperBoundWithIn(ZipcodeRange providedRange) {
		return (providedRange.upperBound <= this.upperBound && providedRange.lowerBound >= this.upperBound);
	}
	
	public boolean isLowerBoundWithIn(ZipcodeRange providedRange) {
		return (providedRange.upperBound <= this.lowerBound && providedRange.lowerBound >= this.lowerBound);
	}
}
