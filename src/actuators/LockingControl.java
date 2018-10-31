package actuators;

import java.util.ArrayList;

import home.*;

public class LockingControl extends Actuator {
	
	private ArrayList<Room> roomList;
	
	public LockingControl(ArrayList<Room>rL)
	{
		this.roomList = rL;
	}
	public void areDoorsLocked()
	{
		Hall hall = (Hall) roomList.get(0);
		Garage garage = (Garage) roomList.get(1);
		//Next : doors closing after 15min of inactivity, doors are closed and locked.
		if(hall.getEntranceDoor().getDoorEngine().getDoorOpen() == false) 
		{
			if(System.currentTimeMillis() - hall.getMovementSensor().getLastMovementDetectedTime().getTime() >= 15*60*1000)
			{
				hall.getEntranceDoor().getDoorEngine().turnOff();
				hall.getEntranceDoor().getLock().turnOn();
			}
		}
		if(garage.getGarageDoor().getDoorEngine().getDoorOpen() == false)
		{	
			if(System.currentTimeMillis() - hall.getMovementSensor().getLastMovementDetectedTime().getTime() >= 15*60*1000)
			{
				garage.getGarageDoor().getDoorEngine().turnOff();
				garage.getGarageDoor().getLock().turnOn();
			}
		}
		
	}
}
