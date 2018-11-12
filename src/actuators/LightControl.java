package actuators;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import devices.Light;
import dto.Dto;
import home.Room;

public class LightControl implements Observer {
		
	private ArrayList<Light> lightList;
	private Map<Room,Date> movRoomMap;
	
	public LightControl(ArrayList<Light> lightList)
	{
		this.lightList = lightList;
		movRoomMap = new HashMap<Room,Date>();
	}
	public void autoShutDown()
	{
		for (Light l : lightList)
		{
			if((System.currentTimeMillis() - movRoomMap.get(l.getRoom()).getTime() >= 2*60*1000)) // No more movement for 2 min.
			{
				l.turnOff();
			}	
		}
	}
	
	private void updateMovMap(Date lastMov, Room room)
	{
			movRoomMap.put(room, lastMov);
	}
	
	@Override
	public void update(Dto dto) {
		switch(dto.getAction())
		{
			case Dto.MOV_TIME_DETECT:
				updateMovMap((Date)dto.getData(),dto.getRoom());
		}
		
	}
}
