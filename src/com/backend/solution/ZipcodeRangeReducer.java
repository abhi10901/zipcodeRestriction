package com.backend.solution;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ZipcodeRangeReducer {
	
	public List<ZipcodeRange> getMinimumRangesRequired(List<ZipcodeRange> ranges) {
		List<ZipcodeRange> requiredRanges = new LinkedList<>();
		
		if(ranges == null)
			return requiredRanges;
		
		for(ZipcodeRange currentRange : ranges) {
			
			if(requiredRanges.isEmpty()) {
				requiredRanges.add(currentRange);
			} else {
				ListIterator<ZipcodeRange> rangeIterator = requiredRanges.listIterator();
				boolean isRangeCovered = false;
				
				while(rangeIterator.hasNext()) {
					ZipcodeRange itrRange = rangeIterator.next();
					
					if(currentRange.isRangeWithIn(itrRange)) {
						isRangeCovered = true;
						break;
					}
					
					if(currentRange.isUpperBoundWithIn(itrRange)) {
						itrRange.setlowerBound(currentRange.getlowerBound());
						isRangeCovered = true;
						break;
					}
					
					if(currentRange.isLowerBoundWithIn(itrRange)) {
						itrRange.setupperBound(currentRange.getupperBound());
						isRangeCovered = true;
						break;
					}
					
					if(itrRange.isRangeWithIn(currentRange)) {
						rangeIterator.remove();
						rangeIterator.add(currentRange);
						isRangeCovered = true;
						break;
					}
				}
				
				if(!isRangeCovered) {
					requiredRanges.add(currentRange);
				}
			}
		}
		
		return requiredRanges;
	}
}
