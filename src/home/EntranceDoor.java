package home;

import devices.DeviceInterface;
import devices.DoorEngine;
import devices.Lock;

public class EntranceDoor extends Door implements DeviceInterface {
	
	private Lock lock;
	private DoorEngine doorEngine;
	
	public EntranceDoor(Lock l, DoorEngine doorEngine)
	{
		lock = l;
		this.doorEngine = doorEngine;
	}
	
	public Lock getLock() {
		return lock;
	}

	public void setLock(Lock lock) {
		this.lock = lock;
	}

	public DoorEngine getDoorEngine() {
		return doorEngine;
	}

	public void setDoorEngine(DoorEngine doorEngine) {
		this.doorEngine = doorEngine;
	}

	@Override
	public void turnOn() {
		this.lock.turnOn();
		this.doorEngine.turnOn();
	}

	@Override
	public void turnOff() {
		this.lock.turnOff();
		this.doorEngine.turnOff();
	}
	@Override
	public void toggle() {
		this.lock.toggle();
		this.doorEngine.toggle();
	}
}
