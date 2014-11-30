package ch.hsr.sevi.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import ch.hsr.sevi.ea.EaException;
import ch.hsr.sevi.ea.Range;
import ch.hsr.sevi.ea.Room;

public class RoomTest {
	static Room<Company> r;
	@BeforeClass
	public static void setUpClass() {
		try {
			r =  new Room<Company>(new CompanyRecombinator(), new CompanyFactory(), 100);
		}catch(EaException ex) {
			System.out.println(ex.getMessage());
		}
	}
	@Test
	public void memberCreationTest() {
		assertEquals(r.getMembers().size(),100);
	}
	@Test(expected=EaException.class)
	public void setRecombinationValueTest() throws EaException {
		r.setRecombinationValue(1.5f);
	}
	@Test(expected=EaException.class)
	public void setRecombinationValueTest2() throws EaException {
		r.setRecombinationValue(-0.5f);
	}
	@Test(expected=EaException.class)
	public void setMutationValueTest() throws EaException {
		r.setMutationValue(1.5f);
	}
	@Test(expected=EaException.class)
	public void setMutationValueTest2() throws EaException {
		r.setMutationValue(-0.5f);
	}
	@Test
	public void recombinationRangeTest() throws EaException {
		r.setRecombinationValue(0.1f);
		Range range = r.getRecombinationRange();
		assertEquals(90, range.getStart());
		assertEquals(99, range.getEnd());
	}
	@Test
	public void nonRecombinationRangeTest() throws EaException {
		r.setRecombinationValue(0.1f);
		Range range = r.getNonRecombinationRange();
		assertEquals(0, range.getStart());
		assertEquals(89, range.getEnd());
	}
	@Test
	public void runOnceTest() {
		r.runOnce();
	}
	@Test
	public void sortedTest() {
		Company befor = r.getMembers().get(0);
		for(Company c: r.getMembers()){
			
			assertTrue(befor.getFitValue() >= c.getFitValue());
			befor = c;
		}
		r.runOnce();
		befor = r.getMembers().get(0);
		for(Company c: r.getMembers()){
			assertTrue(befor.getFitValue() >= c.getFitValue());
			befor = c;
		}
	}

}
