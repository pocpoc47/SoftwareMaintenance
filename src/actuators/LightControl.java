package actuators;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import devices.Alarm;
import devices.DeviceInterface;
import devices.Light;
import dto.Dto;
import home.Room;

public class LightControl implements Observer {
		
	private ArrayList<Light> lightList;
	private Map<Room,Date> movRoomMap;
	public static LightControl instance;
	
	public LightControl()
	{
		movRoomMap = new HashMap<Room,Date>();
		this.lightList = new ArrayList<Light>();
	}
	public static LightControl getInstance() {
		if(instance == null) {
			instance = new LightControl();
		}
		return instance;
	}
	public void addDevice(DeviceInterface device) {
		this.lightList.add((Light)device);
	}
	public boolean autoShutDown()
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
					System.out.println("No records of movement found for the "+l.getRoom().getClass().getSimpleName()+"\n");
				}
			}
		}
		else
		{
			System.out.println("No light are referenced\n");
			return false;
		}
		return true;
		
	}
	
	public boolean turnLights(boolean lightOn)
	{
		if(lightList != null)
		{
			for(Light l : lightList)
			{
				if(lightOn)l.turnOn();
				else l.turnOff();
			}
		}
		else {
			System.out.println("No light belonging to this room\n");
			return false;
		}
		return true;
	}
	public boolean turnLights(boolean lightOn, Room room)
	{
		boolean foundRoom = false;
		if(lightList != null)
		{
			for(Light l : lightList)
			{
				if(l.getRoom().equals(room))
				{
					l.turnOff();
					foundRoom = true;
				}	
			}
			if(!foundRoom) 
			{
				System.out.println("No light belonging to the"+room.getClass().getSimpleName()+"\n");
				return false;
			}
		}
		else
		{
			System.out.println("No light recensed for this house");
			return false;
		}
		return true;
	}
	
	private void updateMovMap(Date lastMov, Room room)
	{
			movRoomMap.put(room, lastMov);
			this.turnLights(true);
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
