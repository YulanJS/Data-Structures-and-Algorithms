import static org.junit.Assert.*;

import org.junit.Test;

public class TwoThreeTreeGivenTests {
	
	@Test
	public void singleNodeTree() {
		TwoThreeTree t = new TwoThreeTree();
		int val = 9;
		System.out.println(t.search(val));
		assertEquals("", t.search(val));		
		t.insert(val);
		String expected = "9";
		
		assertEquals(expected, t.search(val));
		
		val = 8;
		assertEquals(expected, t.search(val));
		
		val = 10;
//		System.out.println(t.search(val));
		assertEquals(expected, t.search(val));
		
		val = 15;
		t.insert(val);
		expected = "9 15";
		val = 9;
		
		//System.out.println(t.search(val));
		assertEquals(expected, t.search(val));
		
		val = 8;
		
		//System.out.println(t.search(val));
		assertEquals(expected, t.search(val));
		
		val = 10;
		
		//System.out.println(t.search(val));
		assertEquals(expected, t.search(val));
		
		val = 15;
		
		//System.out.println(t.search(val));
		assertEquals(expected, t.search(val));
		
		val = 18;
		
		//System.out.println(t.search(val));
		assertEquals(expected, t.search(val));
		
		t = new TwoThreeTree();
		val = 15;
		
		t.insert(val);
		val = 9;
		t.insert(val);
		val = 9;
		//System.out.println(t.search(val));
		assertEquals(expected, t.search(val));
		
		val = 8;
		//System.out.println(t.search(val));
		assertEquals(expected, t.search(val));
		
		val = 10;
		//System.out.println(t.search(val));
		assertEquals(expected, t.search(val));
		
		val = 15;
		//System.out.println(t.search(val));
		assertEquals(expected, t.search(val));
		
		val = 18;
		//System.out.println(t.search(val));
		assertEquals(expected, t.search(val));
		
	}
	
	@Test
	public void oneSplitLeft() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(9);
		t.insert(15);
		t.insert(1);
		
		String expected = "9";
		//System.out.println(t.search(9));
		assertEquals(expected, t.search(9));
		expected = "15";
		//System.out.println(t.search(15));
		//System.out.println(t.search(17));
		//System.out.println(t.search(11));
		assertEquals(expected, t.search(15));
		assertEquals(expected, t.search(17));
		assertEquals(expected, t.search(11));
		
		expected = "1";
//		System.out.println(t.search(1));
//		System.out.println(t.search(0));
//		System.out.println(t.search(3));
		assertEquals(expected, t.search(1));
		assertEquals(expected, t.search(0));
		assertEquals(expected, t.search(3));
	}

	@Test
	public void oneSplitRight() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(1);
		t.insert(9);
		t.insert(15);
		
		String expected = "9";
		assertEquals(expected, t.search(9));
		expected = "15";
		assertEquals(expected, t.search(15));
		assertEquals(expected, t.search(17));
		assertEquals(expected, t.search(11));
		
		expected = "1";
		assertEquals(expected, t.search(1));
		assertEquals(expected, t.search(0));
		assertEquals(expected, t.search(3));
	}
	
	@Test
	public void oneSplitMiddle() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(1);
		t.insert(15);
		t.insert(9);
		
		String expected = "9";
		assertEquals(expected, t.search(9));
		expected = "15";
		assertEquals(expected, t.search(15));
		assertEquals(expected, t.search(17));
		assertEquals(expected, t.search(11));
		
		expected = "1";
		assertEquals(expected, t.search(1));
		assertEquals(expected, t.search(0));
		assertEquals(expected, t.search(3));
	}
	
	@Test
	public void testDuplicates() {
		TwoThreeTree t = new TwoThreeTree();
		t.insert(1);
//		System.out.println(t.search(1));
		t.insert(9);
//		System.out.println(t.search(1));
//		System.out.println(t.search(9));
		t.insert(15);
//		System.out.println(t.search(1));
//		System.out.println(t.search(9));
//		System.out.println(t.search(15));
		t.insert(13);
//		System.out.println(t.search(1));
//		System.out.println(t.search(9));
//		System.out.println(t.search(15));
//		System.out.println(t.search(13));
		t.insert(20);
//		System.out.println(t.search(1));
//		System.out.println(t.search(9));
//		System.out.println(t.search(15));
//		System.out.println(t.search(13));
//		System.out.println(t.search(20));
		t.insert(7);
//		System.out.println(t.search(1));
//		System.out.println(t.search(9));
//		System.out.println(t.search(15));
//		System.out.println(t.search(13));
//		System.out.println(t.search(20));
//		System.out.println(t.search(7));
		t.insert(4);
//		System.out.println(t.search(1));
//		System.out.println(t.search(9));
//		System.out.println(t.search(15));
//		System.out.println(t.search(13));
//		System.out.println(t.search(20));
//		System.out.println(t.search(7));
		t.insert(1);
//		System.out.println(t.search(1));
//		System.out.println(t.search(9));
//		System.out.println(t.search(15));
//		System.out.println(t.search(13));
//		System.out.println(t.search(20));
//		System.out.println(t.search(7));
		t.insert(9);
		t.insert(15);
		t.insert(1);
		t.insert(9);
		t.insert(15);
		t.insert(13);
		t.insert(20);
		t.insert(7);
		t.insert(4);
		t.insert(13);
		t.insert(20);
		t.insert(7);
		t.insert(4);
		
		String expected = "9";
		assertEquals(expected, t.search(9));
		expected = "4";
//		System.out.println(t.search(4));
		assertEquals(expected, t.search(4));
		expected = "15";
//		System.out.println(t.search(15));
		assertEquals(expected, t.search(15));
		
		expected = "13";
//		System.out.println(t.search(12));
//		System.out.println(t.search(13));
//		System.out.println(t.search(14));
		assertEquals(expected, t.search(12));
		assertEquals(expected, t.search(13));
		assertEquals(expected, t.search(14));
		expected = "20";
		assertEquals(expected, t.search(19));
		assertEquals(expected, t.search(20));
		assertEquals(expected, t.search(21));
		
		expected = "1";
		assertEquals(expected, t.search(1));
		assertEquals(expected, t.search(0));
		assertEquals(expected, t.search(3));
		
		expected = "7";
		assertEquals(expected, t.search(6));
		assertEquals(expected, t.search(7));
		assertEquals(expected, t.search(8));
		
	}
	
	@Test
	public void test1()
	{
		TwoThreeTree t = new TwoThreeTree();
		assertEquals("", t.search(10));
		t.insert(100);
		t.insert(30);
		t.insert(27);
		t.insert(92);
		t.insert(56);
		assertEquals("30 92", t.search(92));
		assertEquals("30 92", t.search(30));
		assertEquals("56", t.search(56));
		assertEquals("27", t.search(27));
		assertEquals("100", t.search(100));
		assertEquals("56", t.search(31));
		assertEquals("27", t.search(29));
		assertEquals("100", t.search(93));
		t.insert(47);
		assertEquals("47 56", t.search(47));
		assertEquals("30 92", t.search(30));
		assertEquals("30 92", t.search(92));
		assertEquals("27", t.search(27));
		assertEquals("100", t.search(100));
		assertEquals("47 56", t.search(56));
		assertEquals("47 56", t.search(31));
		assertEquals("47 56", t.search(91));
		assertEquals("27", t.search(29));
		assertEquals("100", t.search(93));
		t.insert(62);
		assertEquals("56", t.search(56));
		assertEquals("30", t.search(30));
		assertEquals("92", t.search(92));
		assertEquals("27", t.search(27));
		assertEquals("47", t.search(47));
		assertEquals("62", t.search(62));
		assertEquals("100", t.search(100));
		t.insert(40);
		assertEquals("40 47", t.search(40));
		assertEquals("40 47", t.search(47));
//		System.out.println(t.search(56));
//		System.out.println(t.search(30));
//		System.out.println(t.search(92));
//		System.out.println(t.search(62));
//		System.out.println(t.search(100));
//		System.out.println(t.search(27));
		
		assertEquals("56", t.search(56));
		t.insert(33);
//		System.out.println(t.search(40));
//		System.out.println(t.search(30));
//		System.out.println(t.search(47));
//		assertEquals("30 40", t.search(40));
//		assertEquals("30 40", t.search(30));
//		assertEquals("47", t.search(47));
//		System.out.println(t.search(56));
//		System.out.println(t.search(92));
//		System.out.println(t.search(27));
//		System.out.println(t.search(33));
//		System.out.println(t.search(62));
//		System.out.println(t.search(100));
		
		t.insert(30);
//		System.out.println(t.search(40));
//		System.out.println(t.search(30));
//		System.out.println(t.search(47));
//		assertEquals("30 40", t.search(40));
//		assertEquals("30 40", t.search(30));
//		assertEquals("47", t.search(47));
//		System.out.println(t.search(56));
//		System.out.println(t.search(92));
//		System.out.println(t.search(27));
//		System.out.println(t.search(33));
//		System.out.println(t.search(62));
//		System.out.println(t.search(100));
//		
//		System.out.println(t.search(39));
		t.insert(26);
		t.insert(25);
		t.insert(25);
//		System.out.println(t.search(30));
//		System.out.println(t.search(56));
//		System.out.println(t.search(26));
//		System.out.println(t.search(40));
//		System.out.println(t.search(92));
//		System.out.println(t.search(25));
//		System.out.println(t.search(27));
//		System.out.println(t.search(33));
//		System.out.println(t.search(47));
//		System.out.println(t.search(62));
//		System.out.println(t.search(100));
		
		t.insert(93);
		t.insert(94);
		t.insert(95);
		t.insert(97);
//		System.out.println(t.search(56));
//		System.out.println(t.search(30));
//		System.out.println(t.search(94));
//		System.out.println(t.search(26));
//		System.out.println(t.search(40));
//		System.out.println(t.search(92));
//		System.out.println(t.search(97));
//		System.out.println(t.search(25));
//		System.out.println(t.search(27));
//		System.out.println(t.search(33));
//		System.out.println(t.search(47));
//		System.out.println(t.search(62));
//		System.out.println(t.search(93));
//		System.out.println(t.search(95));
//		System.out.println(t.search(100));
//		System.out.println(t.search(46));
		
		
		
	}
}