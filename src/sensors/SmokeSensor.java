package sensors;

import java.util.ArrayList;

import actuators.Observer;
import dto.Dto;
import home.Room;

public class SmokeSensor extends Sensor {
	public static int TYPE = 1;
	private boolean smokeDetected;
	
	public SmokeSensor(ArrayList<Observer> obsList, Room room) {
		super(obsList, room);
		smokeDetected = false;
	}
	
	public void setSmokeDetected(boolean smokeDetected) {
		this.smokeDetected = smokeDetected;
		super.notifyObservers(new Dto(TYPE, Dto.SMOKE_DETECTION, smokeDetected, super.getRoom()));
	}
	
	public boolean isSmokeDetected() {
		return smokeDetected;
	}

	public void smell()
	{
		this.setSmokeDetected(true);
		//We could implement some mecanism to randomize smoke.
	}


}
