package actuators;

import java.util.ArrayList;

import devices.Alarm;
import devices.DeviceInterface;
import devices.Light;
import dto.Dto;
import home.Hall;
import home.Room;

public class AlarmControl implements Observer {
	
	private ArrayList<Alarm> alarmList;
	
	public AlarmControl(ArrayList<Alarm> alarmList)
	{
		this.alarmList = alarmList;
	}
	public boolean setAlarmFireRing(Room room, Boolean isSmokeDetected)
	{
		Boolean roomFound = false;
		if(alarmList != null)
		{
			for (Alarm alarm : alarmList)
			{
				if(alarm.getRoom().equals(room))
					
					if(isSmokeDetected)
						alarm.turnOn();
					else
						alarm.turnOff();
			}
			if(!roomFound)
			{
				System.out.println("No alarm found for the "+room.getClass().getSimpleName()+"\n");
			}
			return true;
		}
		else
		{
			System.out.println("No alarm are referenced\n");
			return false;
		}	
	}
	public boolean setAlarm(boolean armed)
	{
		if(alarmList != null)
		{
			for (Alarm alarm : alarmList)
			{
				alarm.setArmed(armed);
			}	
			return true;
		}
		else
		{
			System.out.println("No alarm referenced\n");
			return false;
		}
		
	}
	
	public void addDevice(DeviceInterface device) {
		if(this.alarmList == null) this.alarmList = new ArrayList<Alarm>();
		this.alarmList.add((Alarm)device);
	}
	
	@Override
	public void update(Dto dto) {
		switch(dto.getAction())
		{
			case Dto.SMOKE_DETECTION:
				
				setAlarmFireRing(dto.getRoom(),(boolean)dto.getData());
				break;
		}
	}
}
