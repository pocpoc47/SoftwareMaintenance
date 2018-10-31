package actuators;

import java.util.ArrayList;

import devices.Light;
import home.Room;

public class LightControl extends Actuator {
		
	private ArrayList<Room> roomList;
	
	public LightControl(ArrayList<Room> rL)
	{
		this.roomList = rL;
	}
	public void autoShutdown()
	{
		for (Room r : roomList)
		{
			if(System.currentTimeMillis() - r.getMovementSensor().getLastMovementDetectedTime().getTime() >= 2*60*1000) // No more movement for 2 min.
			{
				for( Light l : r.getLights())
				{
					l.turnOff();
				}
			}	
		}
	}
}
