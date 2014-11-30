package ch.hsr.sevi.ea;

import java.util.ArrayList;

public class World<T extends Member> {
	private ArrayList<Room<T>> rooms = new ArrayList<>();
	private EaSettings<T> settings;
	public World(EaSettings<T> s) throws EaException{
		if(s.roomCount < 1) throw new EaException("Must be at leat 1 room.");
		this.settings = s;
		for(int i = 0; i < s.roomCount; i++){
			rooms.add(new Room<T>(s.recombinator,s.factory,s.memberPerRoomCount));
		}
	}
	public EaSettings<T> getSettings() {
		return this.settings;
	}
	public ArrayList<Room<T>> getRooms() {
		return rooms;
	}
	public void run() {
		for(Room<T> r:rooms){
			r.runOnce();
		}
		rooms = settings.roomCombineStrategy.run(rooms);
	}
	public void run(int count) {
		for(int i = 0; i < count; i++){
			for(Room<T> r:rooms){
				r.runOnce();
			}
			rooms = settings.roomCombineStrategy.run(rooms);
		}
	}
	
	public long getCurrentRound(){
		return rooms.get(0).getRoundCount();
	}
	public T getBestMember() {
		T best = rooms.get(0).getMembers().get(0);
		for(Room<T> r: rooms){
			T roomBest = r.getMembers().get(0);
			if(best.getFitValue() < roomBest.getFitValue()){
				best = roomBest;
			};
		}
		return best;
	}
	public boolean isFinished(){
		for(Room<T> r: rooms) {
			if(r.isFinished()) return true;
		}
		return false;
	}
	
}
