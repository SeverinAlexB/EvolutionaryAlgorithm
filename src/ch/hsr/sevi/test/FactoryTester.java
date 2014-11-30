package ch.hsr.sevi.test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import org.junit.Test;

import ch.hsr.sevi.ea.Member;

public class FactoryTester {

	@Test
	public void test() {
		CompanyFactory cf = new CompanyFactory();
		Member c = cf.makeMember(0.2d);
		assertNotNull(c);
		assertThat(c, instanceOf(Company.class));
	}

}
