package sensors;

import java.util.ArrayList;
import actuators.Observer;
import devices.Lock;
import dto.Dto;
import home.Room;

public class LockSensor extends Sensor {

	public final static int TYPE = 3;
	private Lock lock;
	
	
	public LockSensor(ArrayList<Observer> obsList, boolean doorOpen, Room room, Lock lock)
	{
		super(obsList, room);
		this.lock = lock;
	}
	
	public Lock getLock() {
		return lock;
	}

	public void setToggle() {
		super.notifyObservers(new Dto(3, Dto.LOCK_EVENT, lock, getRoom()));
	}
	
	

}
