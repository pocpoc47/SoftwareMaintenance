package devices;

import home.Room;

public class Alarm implements DeviceInterface{
	public final static int TYPE = 1;
	private boolean turnedOn;
	private Room room;
	
	public Alarm(boolean turnedOn, Room room)
	{
		this.turnedOn = turnedOn;
		this.room = room;
	}
	
	public boolean isAlarmOn() {
		return turnedOn;
	}

	@Override
	public void turnOn() {
		this.turnedOn = true;
	}

	@Override
	public void turnOff() {
		this.turnedOn = false;
	}
	public void toggle(){
		this.turnedOn = !this.turnedOn;
	}
	public Room getRoom() {
		return room;
	}

}
