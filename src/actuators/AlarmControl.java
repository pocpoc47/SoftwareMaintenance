package actuators;

import java.util.ArrayList;

import devices.Alarm;
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
	public void setAlarmFireRing(Room room, Boolean isSmokeDetected)
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
			if(!roomFound)System.out.println("No alarm found for this room\n");
		}
		else
		{
			System.out.println("No alarm are referenced\n");
		}	
	}
	public void setAlarm(boolean armed)
	{
		for (Alarm alarm : alarmList)
		{
			alarm.setArmed(armed);
		}	
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
