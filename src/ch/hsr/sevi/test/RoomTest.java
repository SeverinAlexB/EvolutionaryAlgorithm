package ch.hsr.sevi.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.hsr.sevi.Company;
import ch.hsr.sevi.CompanyFactory;
import ch.hsr.sevi.CompanyRecombinator;
import ch.hsr.sevi.ea.EsimException;
import ch.hsr.sevi.ea.Room;

public class RoomTest {
	
	@Test
	public void test() {
		try {
			Room r = new Room(new CompanyRecombinator(), new CompanyFactory(), 100);
			for(int i = 0; i < 10000; i++){
				r.runOnce();
				System.out.println(r.getMembers().get(0).getFitValue());
			}
		}catch(EsimException ex) {
			System.out.println(ex.getMessage());
		}
		

	}

}
