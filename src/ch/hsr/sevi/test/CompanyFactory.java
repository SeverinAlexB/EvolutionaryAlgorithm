package ch.hsr.sevi.test;

import ch.hsr.sevi.ea.MemberFactory;

public class CompanyFactory implements MemberFactory<Company> {

	@Override
	public Company makeMember(double random) {
		return new Company();
	}

}
