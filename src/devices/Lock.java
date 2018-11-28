package devices;

import home.Room;

public class Lock implements DeviceInterface {
	public final static int TYPE = 5;
	
	private boolean locked;
	private Room room;
	
	public Lock(boolean locked, Room room) {
		this.locked = locked;
		this.room = room;
	}
	
	public boolean isLocked() {
		return locked;
	}
	@Override
	public void turnOn() {
		this.locked = true;
		
	}

	@Override
	public void turnOff() {
		this.locked = false;
		
	}

	@Override
	public void toggle() {
		this.locked = !this.locked;
	}
	
	public Room getRoom() {
		return room;
	}

}
