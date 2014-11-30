package ch.hsr.sevi.test;

import java.util.ArrayList;

import ch.hsr.sevi.ea.Room;
import ch.hsr.sevi.ea.RoomCombineStrategy;

public class CompanyRoomStrategy implements RoomCombineStrategy<Company> {
	int i = 0;
	@Override
	public ArrayList<Room<Company>> run(ArrayList<Room<Company>> rooms) {
		//do a swap every 100 rounds
		i++;
		if(i == 100) {
			Company best = rooms.get(rooms.size() -1).getMembers().get(0);
			for(Room<Company> r: rooms){
				r.getMembers().set(r.getMembers().size()-1,best);
				best = r.getMembers().get(0);
			}
		}
		return rooms;
	}

}
