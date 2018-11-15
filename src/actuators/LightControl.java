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
		if(lightList != null)
		{
			for (Light l : lightList)
			{
				Date roomDate = movRoomMap.get(l.getRoom());
				if(roomDate != null)
				{
					if((System.currentTimeMillis() - roomDate.getTime() >= 2*60*1000)) // No more movement for 2 min.
					{
						l.turnOff();
					}	
				}
				else
				{
					System.out.println("No records of movement found for this room");
				}
					
			}
		}
		else
		{
			System.out.println("No light are referenced\n");
		}
		
	}
	
	public void turnLights(boolean lightOn)
	{
		if(lightList != null)
		{
			for(Light l : lightList)
			{
				//(lightOn)? l.turnOn() : l.turnOff();
			}
		}
	}
	public void turnLights(boolean lightOn, Room room)
	{
		boolean foundRoom = false;
		if(lightList != null)
		{
			for(Light l : lightList)
			{
				if(l.getRoom().equals(room))
				{
					//((lightOn)?l.turnOn():l.turnOff());
					foundRoom = true;
				}	
			}
			if(!foundRoom) System.out.println("No light belonging to this room\");\n");
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
