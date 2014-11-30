package ch.hsr.sevi.ea;

public interface Member extends Comparable<Member> {
	public int getFitValue();
	public void calcFitValue();
	public void mutate(float mutationValue);
}
