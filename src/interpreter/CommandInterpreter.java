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

//One improvement for next release : 
//We should add a string label to the door, to the room and pass the from the main to the interpreter so we can list them.
public class CommandInterpreter {
	
	private ArrayList<Room> roomList;
	private ArrayList<Observer> actuatorList;
	private ArrayList<Door> doorList;
	
	public static final int GO_SLEEP = 1;
	public static final int WAKE_UP = 2;
	
	public CommandInterpreter(ArrayList<Room> roomList,  ArrayList<Observer> actuatorList,ArrayList<Door> doorList)
	{
		this.roomList = roomList;
		this.actuatorList = actuatorList;
		this.doorList = doorList;
	}
	@Command
	public String printHelp()
	{
		StringBuilder b  = new StringBuilder();
		for(int i = 0; i < roomList.size(); i++)
		{
			b.append(i+" - Room: "+roomList.get(i).getClass().getSimpleName()+"\n");
		}
		for(int i = 0; i < doorList.size(); i++)
		{
			b.append(i+ " - "+doorList.get(i).getDoorName()+" in room: "+doorList.get(i).getRoom().getClass().getSimpleName()+"\n");
		}
		return b.toString();
		
	}
	
	//Hardcoded array since actuators won't vary, their lists could be empty.
	@Command
	public String changeMode(int mode)
	{
		switch(mode)
		{
			case GO_SLEEP:
				if(((LightControl) actuatorList.get(0)).autoShutDown() &&
				((LockingControl) actuatorList.get(1)).lockDoors() &&
				((HeatingControl) actuatorList.get(2)).setDesiredTemp(19) &&
				((AlarmControl) actuatorList.get(3)).setAlarm(true))
					return "Sleep Mode Enabled\n";
				else
					return "Check the error above.\n";
			case WAKE_UP:
				if(((HeatingControl) actuatorList.get(2)).setDesiredTemp(21)&&
				((AlarmControl) actuatorList.get(3)).setAlarm(false))
				return "Wake Up Mode Enabled\n";
			else
				return "Check the error above.\n";
				
		}
		return "Incorrect Mode";
		
	}
	@Command
	public String armAlarm()
	{
		return ((AlarmControl) actuatorList.get(4)).setAlarm(true) ? "the alarm is now armed\n" : "Please check the error above.\n";
	}
	@Command
	public String disarmAlarm()
	{
		return ((AlarmControl) actuatorList.get(4)).setAlarm(false) ? "the alarm is now disarmed\n " : "Please check the error above.\n";
	}
	@Command
	public String setLight(boolean lightOn)
	{
		if(((LightControl) actuatorList.get(0)).turnLights(lightOn))
		return "Lights are now "+((lightOn)?"enabled\n":"unabled\n");
		else
			return "Please check the error above.\n";
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
			return "Lights are now "+((lightOn)?"enabled":"unabled")+"in the "+room+"\n";
			else
				return "Please check the error above.\n";
		}
		
	}
	
	@Command
	public String setDoor(boolean open, int door)
	{
		boolean behaveOk = false;
		if(open)
			behaveOk = ((LockingControl) actuatorList.get(1)).unlockDoor(doorList.get(door));
		else
			behaveOk = ((LockingControl) actuatorList.get(1)).lockDoor(doorList.get(door));
		if(behaveOk)
		return "Door is now"+((open)?"opened\n":"closed\n");
		else
			return "Please check the error above\n";
	}
	
	@Command
	public void currentState()
	{
		
	}
}