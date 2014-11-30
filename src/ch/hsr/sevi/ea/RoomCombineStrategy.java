package ch.hsr.sevi.ea;

import java.util.ArrayList;

public interface RoomCombineStrategy<T extends Member> {
	public ArrayList<Room<T>> run(ArrayList<Room<T>> rooms);
}
