package devices;

public class Alarm implements DeviceInterface{
	public final static int TYPE = 1;
	private boolean turnedOn;
	
	public Alarm(boolean turnedOn)
	{
		this.turnedOn = turnedOn;
	}
	
	public boolean isAlarmOn() {
		return turnedOn;
	}

	@Override
	public void turnOn() {
		this.turnedOn = true;
	}

	@Override
	public void turnOff() {
		this.turnedOn = false;
	}
	public void toggle(){
		this.turnedOn = !this.turnedOn;
	}

}
