package ch.hsr.sevi.ea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Room {
	private float mutationValue = 0.1f;
	private float recombinationValue = 0.1f;
	private ArrayList<Member> members = new ArrayList<Member>();
	private Recombinator recombinator;
	private MemberFactory factory;
	
	private Random random = new Random();
	public Room(Recombinator recombinator, MemberFactory factory, int generateMemberCount) throws EsimException{
		this.recombinator = recombinator;
		this.factory = factory;
		if(generateMemberCount < 2) throw new EsimException("It has be at least 2 members");
		for(int i = 0; i < generateMemberCount; i++) {
			members.add(factory.makeMember(random.nextDouble()));
		}
	}
	public ArrayList<Member> getMembers() {
		return members;
	}
	public void runOnce() {
		//mutation
		for(Member m:members){
			m.mutate(mutationValue);
		}
		//selection
		for(Member m:members){
			m.calcFitValue();
		}
		Collections.sort(members);
		
		//recombination
		Range worsest = this.getWorsestRange();
		for(int i = worsest.getStart(); i <= worsest.getEnd(); i++){
			Member m1, m2, newM;
			m1 = this.getRandomOfBest();
			m2 = this.getRandomOfBest();
			newM = this.recombinator.recombine(m1, m2);
			members.remove(i);
			members.add(newM);
		}
	}
	public float getMutationValue() {
		return mutationValue;
	}
	
	public void setMutationValue(float mutationValue) throws EsimException {
		if(mutationValue < 0 || mutationValue > 1) 
			throw new EsimException("Mutation value has to be between 0 and 1.");
		this.mutationValue = mutationValue;
	}
	
	public float getRecombinationValue() {
		return recombinationValue;
	}
	
	public void setRecombinationValue(float recombinationValue) throws EsimException {
		if(recombinationValue < 0 || recombinationValue > 1) 
			throw new EsimException("Recombination value has to be between 0 and 1.");
		this.recombinationValue = recombinationValue;
	}
	
	public Recombinator getRecombinator() {
		return recombinator;
	}
	private Range getWorsestRange() {
		int start = (int) ((1- recombinationValue) * members.size());
		int end = members.size() -1;
		Range worsest = new Range(start,end);
		return worsest;	
	}
	private Range getBestRange() {
		Range worsest = this.getWorsestRange();
		Range best = new Range(0,worsest.getStart() -1);
		return best;	
	}
	private Member getRandomOfBest() {
		Range best = this.getBestRange();
		double d = this.random.nextDouble();
		int index = (int) (d * (best.getEnd() - best.getStart()) + best.getStart());
		return members.get(index);
	}
	public MemberFactory getFactory() {
		return factory;
	}
	
	
	
}
