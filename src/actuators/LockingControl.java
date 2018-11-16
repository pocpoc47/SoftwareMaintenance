package actuators;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import devices.Alarm;
import devices.DeviceInterface;
import devices.Light;
import devices.Lock;
import dto.Dto;
import exceptions.LockingException;
import exceptions.SearchException;
import exceptions.VariabilityException;
import home.*;
import sensors.MovementSensor;

public class LockingControl implements Observer {
	
	private ArrayList<Lock> lockList;
	private Map<Room,Date> movRoomMap;
	
	public LockingControl(ArrayList<Lock> lockList)
	{
		this.lockList = lockList;
		movRoomMap = new HashMap<Room,Date>();
	}
	public void addDevice(DeviceInterface device) {
		if(this.lockList == null) this.lockList = new ArrayList<Lock>();
		this.lockList.add((Lock)device);
	}
	public boolean lockDoors()
	{
		if(lockList!=null)
		{
			for(Lock lock : lockList)
			{
				Date lastMov = movRoomMap.get(lock.getRoom());
				if(!lock.isLocked())
				{
					if(lastMov == null) {
						System.out.println("No records about the movement in this room\n");
						return false;
					}
					else
					{
						if(System.currentTimeMillis() - lastMov.getTime() >= 15*60*1000) //15min inactivity
						{
							lock.turnOn();
						}
					}
				}
			}
			return true;
		}
		else
		{
			System.out.println("No lock detected\n");
			return false;
		}
		
	}
	
	private boolean toggleLock(Room room)
	{
		boolean foundRoom = false;
		if(lockList != null)
		{
			for(Lock l : lockList)
			{
				if(l.getRoom().equals(room))
				{
					l.toggle();
					foundRoom = true;
				}	
			}
			if(!foundRoom) 
			{
				System.out.println("No lock belonging to this room\");\n");
				return false;
			}
		}
		else
		{
			System.out.println("No lock recensed for this house");
			return false;
		}
		return true;
	}
	
	public boolean lock(Room room) 
	{
		boolean foundRoom = false;
		if(lockList != null)
		{
			for(Lock l : lockList)
			{
				if(l.getRoom().equals(room))
				{
					l.turnOn();
					foundRoom = true;
				}	
			}
			if(!foundRoom) 
			{
				System.out.println("No lock belonging to this room\");\n");
				return false;
			}
		}
		else
		{
			System.out.println("No lock recensed for this house");
			return false;
		}
		return true;
	}
	
	public boolean unlock(Room room) 
	{
		boolean foundRoom = false;
		if(lockList != null)
		{
			for(Lock l : lockList)
			{
				if(l.getRoom().equals(room))
				{
					l.turnOff();
					foundRoom = true;
				}	
			}
			if(!foundRoom) 
			{
				System.out.println("No lock belonging to this room\");\n");
				return false;
			}
		}
		else
		{
			System.out.println("No lock recensed for this house");
			return false;
		}
		return true;
	}
	
	private void updateMovMap(Date lastMov, Room room)
	{
			movRoomMap.put(room, lastMov);
	}
	
	@Override
	public void update(Dto dto)  {
		switch(dto.getAction())
		{
			case Dto.LOCK_EVENT:
				toggleLock((Room)dto.getRoom());
				break;
			case Dto.MOV_TIME_DETECT:
				updateMovMap((Date)dto.getData(),dto.getRoom());
				break;
		}
		
	}
}
