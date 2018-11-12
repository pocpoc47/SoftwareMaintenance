package devices;

import home.Door;

public class Lock implements DeviceInterface {
	public final static int TYPE = 5;
	
	private boolean locked;
	private Door door;
	
	public Lock(boolean locked, Door door)
	{
		this.locked = locked;
		this.door = door;
	}
	
	public boolean isLocked()
	{
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
	
	public Door getDoor()
	{
		return door;
	}

}
