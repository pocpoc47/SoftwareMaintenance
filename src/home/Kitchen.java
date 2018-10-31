package home;

import java.util.ArrayList;

import devices.CentralHeating;
import devices.Light;
import sensors.*;

public class Kitchen extends Room {

	
	public Kitchen(ArrayList<Light> kitchenLights, SmokeSensor sS, MovementSensor mS, TemperatureSensor tS, CentralHeating cH)
	{
		super(sS,tS,cH,mS,kitchenLights);

	}
	
}
