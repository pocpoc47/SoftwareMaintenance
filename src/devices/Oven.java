package devices;

import home.Room;

public class Oven implements DeviceInterface {

	private boolean running;
	private Room room;
	
	public Room getRoom() {
		return room;
	}

	@Override
	public void turnOn() {
		running = true;
	}

	@Override
	public void turnOff() {
		running = false;
	}

	@Override
	public void toggle() {
		running = !running;
	}

	public boolean isRunning() {
		return running;
	}

}
