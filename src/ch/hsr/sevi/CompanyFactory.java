package ch.hsr.sevi;

import ch.hsr.sevi.ea.Member;
import ch.hsr.sevi.ea.MemberFactory;

public class CompanyFactory implements MemberFactory {

	@Override
	public Member makeMember(double random) {
		return new Company();
	}

}
