package ch.hsr.sevi.ea;

public interface MemberFactory<T extends Member> {
	public T makeMember(double random);
}
