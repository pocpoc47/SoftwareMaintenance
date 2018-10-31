package home;

import java.util.ArrayList;

import devices.*;
import sensors.*;

public class Hall extends Room {

	private Alarm alarm;
	private EntranceDoor entranceDoor;
	
	public Hall(Alarm alarm, EntranceDoor d, ArrayList<Light> l, SmokeSensor sS, MovementSensor mS, CentralHeating cH, TemperatureSensor tS)
	{
		super(sS,tS,cH,mS,l);
		this.alarm = alarm;
		entranceDoor = d;
	}
	
	public Alarm getAlarm() {
		return alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}

	public EntranceDoor getEntranceDoor() {
		return entranceDoor;
	}

	public void setEntranceDoor(EntranceDoor entranceDoor) {
		this.entranceDoor = entranceDoor;
	}


	
}
