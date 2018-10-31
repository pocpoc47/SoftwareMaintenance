package actuators;

import java.util.ArrayList;

import home.*;

public class HeatingControl extends Actuator {
	
	private double desiredTemp;
	private ArrayList<Room> roomList;
	
	public HeatingControl(ArrayList<Room>rL)
	{
		this.roomList = rL;
	}
	
	public void KeepHouseTemp()
	{
		if(roomList.get(2).getTemperatureSensor().getTemp() < desiredTemp)
		{
			roomList.get(2).getCentralHeating().turnOn();
			//roomList.get(2).getTemperatureSensor().setTemp(desiredTemp);
		}
		else
		{
			roomList.get(2).getCentralHeating().turnOff();
		}
	}
}
