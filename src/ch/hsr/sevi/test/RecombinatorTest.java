package ch.hsr.sevi.test;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import org.junit.Test;

import ch.hsr.sevi.ea.Member;

public class RecombinatorTest {

	@Test
	public void test() {
		Company c1 = new Company();
		Company c2 = new Company();
		CompanyRecombinator cr = new CompanyRecombinator();
		Member c3 = cr.recombine(c1, c2);
		assertNotNull(c3);
		assertThat(c3, instanceOf(Company.class));
	}

}
