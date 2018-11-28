package devices;

import home.Room;

public class Shutter implements DeviceInterface {

	private boolean open;
	private Room room;
	
	public Room getRoom() {
		return room;
	}
	
	public boolean isOpen() {
		return open;
	}

	@Override
	public void turnOn() {
		this.open = true;
	}

	@Override
	public void turnOff() {
		this.open = false;

	}

	@Override
	public void toggle() {
		this.open = !this.open;

	}

}
