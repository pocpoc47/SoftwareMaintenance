package devices;

import home.Room;

public class Light implements DeviceInterface {

	public final static int TYPE = 4;
	private boolean turnedOn;
	private Room room;
	
	public Light(boolean isTurnedOn, Room room)
	{
		this.turnedOn = isTurnedOn;
		this.room = room;
	}
	
	public Room getRoom() {
		return room;
	}

	public boolean isTurnedOn() {
		return turnedOn;
	}

	public void toggle() {
		this.turnedOn = !this.turnedOn;
	}

	@Override
	public void turnOn() {
		this.turnedOn = true;
	}

	@Override
	public void turnOff() {
		this.turnedOn = false;
	}

}
