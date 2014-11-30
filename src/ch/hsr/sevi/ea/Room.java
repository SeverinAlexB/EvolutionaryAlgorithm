package ch.hsr.sevi.ea;

import java.util.ArrayList;

import java.util.Random;

public class Room<T extends Member> implements Comparable<Room<T>> {
	private long roundCount = 0;
	private float mutationValue = 0.1f;
	private float recombinationValue = 0.1f;
	private ArrayList<T> members;
	private Recombinator<T> recombinator;
	private MemberFactory<T>  factory;
	
	private Random random = new Random();
	public Room(Recombinator<T> recombinator, MemberFactory<T> factory, int generateMemberCount) throws EaException{
		this.recombinator = recombinator;
		this.factory = factory;
		if(generateMemberCount < 2) throw new EaException("It has be at least 2 members");
		members = new ArrayList<>(generateMemberCount);
		for(int i = 0; i < generateMemberCount; i++) {
			members.add(factory.makeMember(random.nextDouble()));
		}
		this.sortMember();
	}
	public ArrayList<T> getMembers() {
		return members;
	}
	public void runOnce() {
		//mutation
		for(Member m:members){
			if(1 == random.nextInt((int)(1/mutationValue))) {
				m.mutate(mutationValue);
			}
		}
		//recombination
		Range worsest = this.getRecombinationRange();
		for(int i = worsest.getStart(); i <= worsest.getEnd(); i++){
			T m1, m2, newM;
			m1 = this.getRandomOfBest();
			m2 = this.getRandomOfBest();
			newM = this.recombinator.recombine(m1, m2);
			members.remove(i);
			members.add(newM);
		}
		//selection
		for(Member m:members){
			m.calcFitValue();
		}

		this.sortMember();
			

		roundCount++;
	}
	public float getMutationValue() {
		return mutationValue;
	}
	
	public void setMutationValue(float mutationValue) throws EaException {
		if(mutationValue < 0 || mutationValue > 1) 
			throw new EaException("Mutation value has to be between 0 and 1.");
		this.mutationValue = mutationValue;
	}
	
	public float getRecombinationValue() {
		return recombinationValue;
	}
	
	public void setRecombinationValue(float recombinationValue) throws EaException {
		if(recombinationValue < 0 || recombinationValue > 1) 
			throw new EaException("Recombination value has to be between 0 and 1.");
		this.recombinationValue = recombinationValue;
	}
	
	public Recombinator<T> getRecombinator() {
		return recombinator;
	}
	public Range getRecombinationRange() {
		int start = (int) ((1- recombinationValue) * members.size());
		int end = members.size() -1;
		Range worsest = new Range(start,end);
		return worsest;	
	}
	public Range getNonRecombinationRange() {
		Range worsest = this.getRecombinationRange();
		Range best = new Range(0,worsest.getStart() -1);
		return best;	
	}
	private T getRandomOfBest() {
		Range best = this.getNonRecombinationRange();
		double d = this.random.nextDouble();
		int index = (int) (d * best.getEnd());
		return members.get(index);
	}
	public MemberFactory<T> getFactory() {
		return factory;
	}
	public long getRoundCount() {
		return roundCount;
	}
	public void sortMember() {
		MergeSort<T> mergeSort = new MergeSort<>(members);
		mergeSort.sort();
		this.members = (ArrayList<T>) mergeSort.getSortedItems();
	}
	public boolean isFinished() {
		return this.getMembers().get(0).isFinished();
	}
	@Override
	public int compareTo(Room<T> r) {
		return r.getMembers().get(0).getFitValue() - this.getMembers().get(0).getFitValue();
	}
	
	
}
