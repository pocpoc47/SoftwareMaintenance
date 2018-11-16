package home;

import devices.DeviceInterface;

public class Dooor {
	
	private Room room;
	private String doorName;
	
	public Door(Room room, String doorName)
	{
		this.room = room;
		this.doorName = doorName;
	}
	public Room getRoom()
	{
		return room;
	}
	public String getDoorName() {
		return doorName;
	}
	
	

	
}
