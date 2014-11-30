package ch.hsr.sevi.ea;

import java.util.ArrayList;

public class World<T extends Member> {
	private ArrayList<Room<T>> rooms;
	private EaSettings<T> settings;
	public World(EaSettings<T> s) throws EaException{
		if(s.roomCount < 1) throw new EaException("Must be at leat 1 room.");
		this.settings = s;
		
		rooms = new ArrayList<>(s.roomCount);
		for(int i = 0; i < s.roomCount; i++){
			rooms.add(new Room<T>(s.recombinator,s.factory,s.memberPerRoomCount));
		}
		this.sortRooms();
	}
	public EaSettings<T> getSettings() {
		return this.settings;
	}
	public ArrayList<Room<T>> getRooms() {
		return rooms;
	}
	public void run() {
		rooms = settings.roomCombineStrategy.run(rooms);
		for(Room<T> r:rooms){
			r.runOnce();
		}
		this.sortRooms();
		
	}
	public void run(int count) {
		for(int i = 0; i < count; i++){
			this.run();
			if(this.isFinished()) return;
		}
	}
	public long getCurrentRound(){
		return rooms.get(0).getRoundCount();
	}
	public T getBestMember() {
		return rooms.get(0).getMembers().get(0);
	}
	public boolean isFinished(){
		return this.getRooms().get(0).isFinished();
	}
	private void sortRooms() {
		MergeSort<Room<T>> mergeSort = new MergeSort<>(rooms);
		mergeSort.sort();
		this.rooms = (ArrayList<Room<T>>) mergeSort.getSortedItems();
	}
	
}
