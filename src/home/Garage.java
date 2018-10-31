package home;

import java.util.ArrayList;

import devices.*;
import sensors.*;

public class Garage extends Room {
	
	private GarageDoor garageDoor;
	
	public Garage(GarageDoor d, ArrayList<Light> l, SmokeSensor sS, MovementSensor mS, CentralHeating cH, TemperatureSensor tS)
	{
		super(sS,tS,cH,mS,l);
		garageDoor = d;
	}
	public GarageDoor getGarageDoor()
	{
		return garageDoor;
	}
	
}
