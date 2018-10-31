package devices;

public class Lock implements DeviceInterface {

	private boolean locked;
	public Lock(boolean locked)
	{
		this.locked = locked;
	}
	
	public boolean getLocked()
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

}
