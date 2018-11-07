package devices;

public class DoorEngine implements DeviceInterface {

	public final static int TYPE = 3;
	private boolean doorOpen;
	
	public DoorEngine(boolean doorOpen)
	{
		this.doorOpen = doorOpen;
	}
	public boolean getDoorOpen()
	{
		return this.doorOpen;
	}
	
	@Override
	public void turnOn() {
		this.doorOpen = true;	
	}

	@Override
	public void turnOff() {
		this.doorOpen = false;	
	}
	@Override
	public void toggle() {
		this.doorOpen = !this.doorOpen;
	}

}
