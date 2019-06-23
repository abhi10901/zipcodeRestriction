package com.backend.solution;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ZipcodeRangeReducerTester {
	
	ZipcodeRangeReducer rangeReducer;
	List<ZipcodeRange> ranges;
	List<ZipcodeRange> resultRanges;
	
	@Before
	public void setup() {
		rangeReducer = new ZipcodeRangeReducer();
		ranges = new ArrayList<>();
	}

	@Test
	public void testWhenInputRangeIsNull() {
		assertThat(rangeReducer.getMinimumRangesRequired(null).size(), is(0));
	}
	
	@Test
	public void testWhenInputRangeIsEmpty() {
		assertThat(rangeReducer.getMinimumRangesRequired(new ArrayList<>()).size(), is(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWhenZipcodeUpperBoundSmallerThanFiveDigit() {
		ranges.add(new ZipcodeRange(1, 2));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWhenZipcodeUpperBoundBiggerThanFiveDigit() {
		ranges.add(new ZipcodeRange(123456, 2));
	}
	
	@Test
	public void testWhenSingleRangeProvided() {
		ranges.add(new ZipcodeRange(12345, 23456));
		
		assertThat(rangeReducer.getMinimumRangesRequired(ranges).size(), is(1));
		assertThat(rangeReducer.getMinimumRangesRequired(ranges).get(0).getupperBound(), is(12345));
		assertThat(rangeReducer.getMinimumRangesRequired(ranges).get(0).getlowerBound(), is(23456));
	}
	
	@Test
	public void testWhenSecondRangeWithinFirstrange() {
		ranges.add(new ZipcodeRange(94200, 94299));
		ranges.add(new ZipcodeRange(94250, 94260));
		
		assertThat(rangeReducer.getMinimumRangesRequired(ranges).size(), is(1));
		assertThat(rangeReducer.getMinimumRangesRequired(ranges).get(0).getupperBound(), is(94200));
		assertThat(rangeReducer.getMinimumRangesRequired(ranges).get(0).getlowerBound(), is(94299));
	}
	
	
	@Test
	public void testWhenSecondRangeCoversFirstrange() {
		ranges.add(new ZipcodeRange(94200, 94299));
		ranges.add(new ZipcodeRange(94100, 94300));
		
		assertThat(rangeReducer.getMinimumRangesRequired(ranges).size(), is(1));
		assertThat(rangeReducer.getMinimumRangesRequired(ranges).get(0).getupperBound(), is(94100));
		assertThat(rangeReducer.getMinimumRangesRequired(ranges).get(0).getlowerBound(), is(94300));
	}
	
	
	@Test
	public void testWhenFirstRangeUpperBoundWithinSecondRange() {
		ranges.add(new ZipcodeRange(94200, 94299));
		ranges.add(new ZipcodeRange(94100, 94250));
		
		assertThat(rangeReducer.getMinimumRangesRequired(ranges).size(), is(1));
		assertThat(rangeReducer.getMinimumRangesRequired(ranges).get(0).getupperBound(), is(94100));
		assertThat(rangeReducer.getMinimumRangesRequired(ranges).get(0).getlowerBound(), is(94299));
	}
	
	@Test
	public void testWhenFirstRangeLowerBoundWithinSecondRange() {
		ranges.add(new ZipcodeRange(94200, 94299));
		ranges.add(new ZipcodeRange(94220, 94400));
		
		assertThat(rangeReducer.getMinimumRangesRequired(ranges).size(), is(1));
		assertThat(rangeReducer.getMinimumRangesRequired(ranges).get(0).getupperBound(), is(94200));
		assertThat(rangeReducer.getMinimumRangesRequired(ranges).get(0).getlowerBound(), is(94400));
	}
	
	@Test
	public void testWhenAllRangesSame() {
		ranges.add(new ZipcodeRange(94133, 94133));
		ranges.add(new ZipcodeRange(94133, 94133));
		ranges.add(new ZipcodeRange(94133, 94133));
		
		resultRanges = rangeReducer.getMinimumRangesRequired(ranges);
		assertThat(resultRanges.size(), is(1));
		
		System.out.println("Required Minimum Ranges for testWhenAllRangesSame:");
		resultRanges.stream().forEach(System.out::println);
	}
	
	@Test
	public void testWhenAllRangesUnique() {
		ranges.add(new ZipcodeRange(94133, 94133));
		ranges.add(new ZipcodeRange(94200, 94299));
		ranges.add(new ZipcodeRange(94600, 94699));
		
		resultRanges = rangeReducer.getMinimumRangesRequired(ranges);
		assertThat(resultRanges.size(), is(3));
		
		System.out.println("Required Minimum Ranges for testWhenAllRangesUnique:");
		resultRanges.stream().forEach(System.out::println);
	}
	
	@Test
	public void testWhenLastRangeUpperBoudnWithinOneRange() {
		ranges.add(new ZipcodeRange(94133, 94133));
		ranges.add(new ZipcodeRange(94200, 94299));
		ranges.add(new ZipcodeRange(94226, 94399));
		
		resultRanges = rangeReducer.getMinimumRangesRequired(ranges);
		assertThat(resultRanges.size(), is(2));
		
		System.out.println("Required Minimum Ranges for testWhenLastRangeUpperBoudnWithinOneRange:");
		resultRanges.stream().forEach(System.out::println);
	}
	
	@After
	public void teardown() {
		rangeReducer = null;
		ranges = null;
		resultRanges = null;
	}
}
