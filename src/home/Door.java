package home;

import devices.DeviceInterface;

public class Door {
	
	private Room room;
	
	public Door(Room room)
	{
		this.room = room;
	}
	public Room getRoom()
	{
		return room;
	}

	
}
