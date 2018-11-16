package home;

import devices.DeviceInterface;

public class Door {
	
	private Room room;
	private String doorName;
	
	public Door(Room room, String doorName)
	{
		this.room = room;
		this.doorName = doorName;
		this.room.setDoor(this);
	}
	public Room getRoom()
	{
		return room;
	}
	public String getDoorName() {
		return doorName;
	}
	
	

	
}
