package dto;

import home.Room;

public class Dto {
	
	private int action;
	private Object data;
	private Room room;
	private int sensor;
	
	//Temperature
	public static final int TEMP_CHANGE = 10;
	
	//Light
	public static final int MOV_TIME_DETECT = 20;
	
	//Smoke
	public static final int SMOKE_DETECTION = 30;
	public static final int SMOKE_RESET = 31;
	
	//DoorEngine
	public static final int LOCK_EVENT = 40;
	
	public Dto(int sensor, int action, Object obj, Room room)
	{
		this.sensor = sensor;
		this.action = action;
		this.data = obj;
		this.room = room;
	}
	
	public Object getData()
	{
		return data;
	}
	public int getAction()
	{
		return action;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	
}
