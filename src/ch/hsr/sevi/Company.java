package ch.hsr.sevi;

import java.util.Random;

import ch.hsr.sevi.ea.EsimException;
import ch.hsr.sevi.ea.Member;

public class Company implements Member{
	private int fitValue = 0;
	private int produktKosten = 0;
	private Random random = new Random();
	public Company(){
		try{
			this.setProduktKosten(Integer.MAX_VALUE -10000);
		}catch(Exception ex) {
			//Never happens
		}
	}
	public Company(int initProduktKosten) throws Exception{
		this.setProduktKosten(initProduktKosten);
	}
	@Override
	public void calcFitValue() {
		this.fitValue = Integer.MAX_VALUE - produktKosten;
		
	}
	@Override
	public int getFitValue() {
		return fitValue;
	}

	private int temp1 = 0;
	@Override
	public void mutate(float mutationValue) {
		temp1 = random.nextInt(100);
		temp1 -= 50;
		temp1 = this.produktKosten + temp1;
		if(temp1 < 0)temp1 = this.produktKosten -100;	
		this.produktKosten = temp1;		
	}
	
	public int getProduktKosten() {
		return produktKosten;
	}
	public void setProduktKosten(int produktKosten) throws EsimException {
		if(produktKosten < 0) throw new EsimException("Produktkosten können nicht kleiner als 0 sein.");
		this.produktKosten = produktKosten;
	}
	@Override
	public int compareTo(Member e) {
		return this.getFitValue() - e.getFitValue();
	}




}
