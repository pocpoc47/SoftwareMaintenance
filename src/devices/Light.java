package devices;

public class Light implements DeviceInterface {

	public final static int TYPE = 4;
	private boolean turnedOn;
	
	public Light(boolean isTurnedOn)
	{
		this.turnedOn = isTurnedOn;
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
