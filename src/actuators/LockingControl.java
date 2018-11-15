package actuators;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import devices.Lock;
import dto.Dto;
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
	
	public void lockDoors()
	{
		if(lockList!=null)
		{
			for(Lock lock : lockList)
			{
				Date lastMov = movRoomMap.get(lock.getDoor().getRoom());
				if(!lock.isLocked())
				{
					if(System.currentTimeMillis() - lastMov.getTime() >= 15*60*1000) //15min inactivity
					{
						lock.turnOn();
					}
					else
					{
						if(lastMov == null) System.out.println("No records about the movement in this room\n");
					}
				}
			}
		}
		else
		{
			System.out.print("No lock detected\n");
		}
		
	}
	
	private void toggleLock(Door door)
	{
		boolean lockFound = false;
		if(lockList != null)
		{
			for(Lock lock : lockList)
			{
				if(lock.getDoor().equals(door))
				{
					lock.toggle();
					lockFound = true;
				}
			}
		}
		else
		{
			System.out.println("No lock referenced");
		}
		
	}
	public
	private void updateMovMap(Date lastMov, Room room)
	{
			movRoomMap.put(room, lastMov);
	}
	
	@Override
	public void update(Dto dto) {
		switch(dto.getAction())
		{
			case Dto.DOOR_EVENT:
				toggleLock((Door)dto.getData());
				break;
			case Dto.MOV_TIME_DETECT:
				updateMovMap((Date)dto.getData(),dto.getRoom());
				//areDoorsLocked();
				break;
		}
		
	}
}
