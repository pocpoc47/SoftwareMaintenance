package devices;

import home.Room;

public class Alarm implements DeviceInterface{
	public final static int TYPE = 1;
	private boolean turnedOn;
	private boolean armed;
	private Room room;
	
	public Alarm(Room room)
	{
		this.turnedOn = false;
		this.room = room;
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
	public Room getRoom() {
		return room;
	}
	public void toggleArmed()
	{
		this.armed = !this.armed;
	}
	public boolean getArmed()
	{
		return this.armed;
	}
	public void setArmed(boolean armed)
	{
		this.armed = armed;
	}


}
