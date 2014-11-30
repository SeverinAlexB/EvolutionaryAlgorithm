package ch.hsr.sevi.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import ch.hsr.sevi.ea.EaException;
import ch.hsr.sevi.ea.EaSettings;
import ch.hsr.sevi.ea.Room;
import ch.hsr.sevi.ea.World;

public class WorldTest {
	static World<Company> w;
	@BeforeClass
	public static void setUpClass() {
		EaSettings<Company> s = new EaSettings<>();
		s.roomCount = 2;
		s.mutationValue = 0.1f;
		s.recombinationValue = 0.1f;
		s.memberPerRoomCount = 50;
		s.factory = new CompanyFactory();
		s.recombinator = new CompanyRecombinator();
		s.roomCombineStrategy = new CompanyRoomStrategy();
		try {
			w = new World<Company>(s);
		}catch(EaException ex) {
			System.out.println(ex.getMessage());
		}
	}
	@Test
	public void roomSizetest() {
		assertEquals(w.getRooms().size(),w.getSettings().roomCount);
	}
	@Test
	public void memberSizetest() {
		int i = 0;
		for(Room<Company> r: w.getRooms()){
			i += r.getMembers().size();
		}
		assertEquals(i,w.getSettings().roomCount* w.getSettings().memberPerRoomCount);
	}
	@Test
	public void roundCountTest() {
		long start = w.getCurrentRound();
		w.run(3);
		assertEquals(w.getCurrentRound() - start,3);
	}
	@Test
	public void roomsSortedTest() {
			Room<Company> befor = w.getRooms().get(0);
			for(Room<Company> r: w.getRooms()){
				assertTrue(befor.getMembers().get(0).getFitValue() >= r.getMembers().get(0).getFitValue());
				befor = r;
			}
	}
	@Test
	public void getBestTest(){
		w.run(3);
		Company best = w.getBestMember();
		Company temp = w.getRooms().get(0).getMembers().get(0);
		for(Room<Company> r: w.getRooms()){
			for(Company c:r.getMembers()){
				if(temp.getFitValue() < c.getFitValue()) {
					temp = c;
				}
			}
		}

		assertEquals(best.getFitValue(), temp.getFitValue());
		
	}
	@Test
	public void resultIsBetterAfterRunTest(){
		int start = w.getBestMember().getFitValue();
		w.run(2000);
		int end = w.getBestMember().getFitValue();
		System.out.println("Best start: " + start);
		System.out.println("Best end: " + end);
		assertTrue(start <= end);
	}

}
