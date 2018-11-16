package interpreter;

import java.util.ArrayList;

import asg.cliche.Command;
import exceptions.LockingException;
import exceptions.SearchException;
import exceptions.VariabilityException;
import home.Door;
import home.Room;
import sensors.MovementSensor;
import sensors.TemperatureSensor;
import actuators.*;

public class CommandInterpreter {
	
	private ArrayList<Room> roomList;
	private TemperatureSensor tempSens;
	private ArrayList<MovementSensor> movSensList;
	private ArrayList<Observer> actuatorList;
	private ArrayList<Door> doorList;
	
	public static final int GO_SLEEP = 1;
	
	public CommandInterpreter(ArrayList<Room> roomList, TemperatureSensor tempSens,
	ArrayList<MovementSensor> movSensList, ArrayList<Observer> actuatorList,ArrayList<Door> doorList)
	{
		this.roomList = roomList;
		this.tempSens = tempSens;
		this.movSensList = movSensList;
		this.actuatorList = actuatorList;
		this.doorList = doorList;
	}
	
	@Command
	public String changeMode(int mode)
	{
		switch(mode)
		{
			case GO_SLEEP:
				((LightControl) actuatorList.get(0)).autoShutDown();
				((LockingControl) actuatorList.get(1)).lockDoors();
				((HeatingControl) actuatorList.get(2)).setDesiredTemp(19);
				((AlarmControl) actuatorList.get(4)).setAlarm(true);
				return "Sleep Mode Enabled";
		}
		return "Incorrect Mode";
		
	}
	@Command
	public String armAlarm()
	{
		if(((AlarmControl) actuatorList.get(4)).setAlarm(true))
		return "the alarm is now armed ";
		else
			return "Please check the error above.";
	}
	@Command
	public String disarmAlarm()
	{
		if(((AlarmControl) actuatorList.get(4)).setAlarm(false))
		return "the alarm is now disarmed ";
		else
			return "Please check the error above.";
	}
	@Command
	public String setLight(boolean lightOn)
	{
		if(((LightControl) actuatorList.get(0)).turnLights(lightOn))
		return "Lights are now "+((lightOn)?"enabled":"unabled");
		else
			return "Please check the error above.";
	}
	
	@Command
	public String setLight(boolean lightOn, int room)
	{
		if(this.roomList.size()< room || room < 0)
		{
			return "Wrong room number\n";
		}
		else
		{
			if(((LightControl) actuatorList.get(0)).turnLights(lightOn, roomList.get(room)))
			return "Lights are now "+((lightOn)?"enabled":"unabled")+"in the "+room;
			else
				return "Please check the error above.";
		}
		
	}
	
	@Command
	public String setGarageDoor(boolean open, int door)
	{
		boolean behaveOk = false;
		if(open)
			behaveOk = ((LockingControl) actuatorList.get(1)).unlockDoor(doorList.get(door));
		else
			behaveOk = ((LockingControl) actuatorList.get(1)).lockDoor(doorList.get(door));
		if(behaveOk)
		return "Garage door is now"+((open)?"opened":"closed");
		else
			return "Please check the error above.";
	}
	
	@Command
	public String setHallDoor(boolean open, int door) 
	{
		boolean behaveOk = false;
		if(open)
			behaveOk = ((LockingControl) actuatorList.get(1)).unlockDoor(doorList.get(door));
		else
			behaveOk = ((LockingControl) actuatorList.get(1)).lockDoor(doorList.get(door));
		if(behaveOk)
		return "Hall door is now"+((open)?"opened":"closed");
		else
			return "Please check the error above.";
	}
	
	@Command
	public void currentState()
	{
		
	}
}