package ch.hsr.sevi.test;

import ch.hsr.sevi.ea.Recombinator;

public class CompanyRecombinator implements Recombinator<Company> {
	@Override
	public Company recombine(Company m1, Company m2) {
		int i1 = m1.getProduktKosten();
		int i2 = m2.getProduktKosten();
		int diff = i1 - i2;
		if(diff < 0) diff=-diff;
		int i = 0;
		if(i1 < i2) i = i1 + diff;
		else i = i2 + diff;

		try{
			return new Company(i);
		}catch(Exception ex){
			System.out.println("Recombination ex i1:" + i1 + " i2: " + i2 + " i: " + i);
			return new Company();
		}
		
		
	}

}
