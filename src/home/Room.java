package home;
import java.util.ArrayList;
import java.util.List;

import devices.*;
import sensors.MovementSensor;
import sensors.Sensor;
import sensors.SmokeSensor;
import sensors.TemperatureSensor;

public abstract class Room  {

	private SmokeSensor sS;
	private TemperatureSensor tS;
	private CentralHeating cH;
	private MovementSensor mS; 
	private ArrayList<Sensor> sensorList;
	private ArrayList<Light> lightsList;
	public Room(SmokeSensor sS, TemperatureSensor tS, CentralHeating cH, MovementSensor mS, ArrayList<Light> lightsList){
		
		this.sS = sS;
		this.tS = tS;
		this.cH = cH;
		this.mS = mS;
		this.lightsList =lightsList;
	}
	public SmokeSensor getSmokeSensor() {
		return sS;
	}
	public TemperatureSensor getTemperatureSensor()
	{
		return tS;
	}
	public CentralHeating getCentralHeating()
	{
		return cH;
	}
	
	public MovementSensor getMovementSensor()
	{
		return mS;
	}
	public ArrayList<Light> getLights()
	{
		return lightsList;
	}
	
}
