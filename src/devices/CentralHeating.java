package devices;

public class CentralHeating implements DeviceInterface{
	public final static int TYPE = 2;
	private boolean turnedOn;
	
	public CentralHeating(boolean turnedOn) {
		this.turnedOn = turnedOn;
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
	
}
