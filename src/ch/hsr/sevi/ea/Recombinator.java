package ch.hsr.sevi.ea;

public interface Recombinator<T extends Member> {
	public T recombine(T m1, T m2);
}
