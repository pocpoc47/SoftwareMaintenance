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
	public void turnSmokeAlarmOn(Room room, Boolean isSmokeDetected)
	{
		for (Alarm alarm : alarmList)
		{	
			if(alarm.getRoom().equals(room))
				//isSmokeDetected?alarm.turnOn():alarm.turnOff();
				if(isSmokeDetected)
					alarm.turnOn();
				else
					alarm.turnOff();
		}
	}
	
	public void turnSmokeAlarmOff(Room room)
	{
		for (Alarm alarm : alarmList)
		{
			if(alarm.getRoom().equals(room))	
				alarm.turnOff();
		}
	}

	@Override
	public void update(Dto dto) {
		switch(dto.getAction())
		{
			case Dto.SMOKE_DETECTION:
				turnSmokeAlarmOn(dto.getRoom(),(boolean)dto.getData());
				break;
		}
		
	}
}
