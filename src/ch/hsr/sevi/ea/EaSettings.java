package ch.hsr.sevi.ea;

public class EaSettings<T extends Member> {
	public int roomCount = 1;
	public int memberPerRoomCount = 100;
	public double mutationValue = 0.1;
	public double recombinationValue = 0.1;
	public Recombinator<T> recombinator;
	public MemberFactory<T> factory;
	public RoomCombineStrategy<T> roomCombineStrategy;
}
