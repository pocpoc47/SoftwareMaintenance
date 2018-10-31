package actuators;

import java.util.ArrayList;

import devices.Light;
import home.Hall;
import home.Room;

public class AlarmControl extends Actuator {
	
	private ArrayList<Room> roomList;
	
	public AlarmControl(ArrayList<Room>rL)
	{
		this.roomList = rL;
	}
	public void isEverytingFine()
	{
		for (Room r : roomList)
		{
				if( r.getSmokeSensor().isSmokeDetected())
				{
					if(((Hall)roomList.get(0)).getAlarm() != null) //Hall is always the first element and the only room to have the alarm.
						((Hall)roomList.get(0)).getAlarm().turnOn();
				}
		}
	}
}
