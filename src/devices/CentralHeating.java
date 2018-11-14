package devices;

import home.Kitchen;
import home.Room;

public class CentralHeating implements DeviceInterface{
	public final static int TYPE = 2;
	private boolean turnedOn;
	private Room room;
	
	public CentralHeating(boolean turnedOn) {
		this.turnedOn = turnedOn;
	}
	public CentralHeating(boolean turnedOn, Room room) {
		this.turnedOn = turnedOn;
		this.room = room;
	}

	public boolean isTurnedOn() {
		return turnedOn;
	}

	public void setTurnedOn(boolean turnedOn) {
		this.turnedOn = turnedOn;
	}

	@Override
	public void turnOn() {
		this.turnedOn = true;
	}

	@Override
	public void turnOff() {
		this.turnedOn = false;
	}

	@Override
	public void toggle() {
		this.turnedOn = !this.turnedOn;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public static int getType() {
		return TYPE;
	}
	
}
