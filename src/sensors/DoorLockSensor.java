package sensors;

import java.util.ArrayList;
import actuators.Observer;
import dto.Dto;
import home.Door;
import home.Room;

public class DoorLockSensor extends Sensor {

	public final static int TYPE = 3;
	private boolean doorOpen;
	private Door door;
	
	
	public DoorLockSensor(ArrayList<Observer> obsList, boolean doorOpen, Room room, Door door)
	{
		super(obsList, room);
		this.doorOpen = doorOpen;
		this.door = door;
	}
	
	public Door getDoor() {
		return door;
	}

	public void setToggle() {
		this.doorOpen = doorOpen;
		super.notifyObservers(new Dto(3, Dto.DOOR_EVENT, door, getRoom()));
	}
	
	

}
