package ch.hsr.sevi;

import ch.hsr.sevi.ea.Member;
import ch.hsr.sevi.ea.Recombinator;

public class CompanyRecombinator implements Recombinator {

	@Override
	public Member recombine(Member m1, Member m2) {
		return m1;
	}

}
