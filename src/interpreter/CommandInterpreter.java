package interpreter;

import java.util.ArrayList;

import actuators.AlarmControl;
import actuators.HeatingControl;
import actuators.LightControl;
import actuators.LockingControl;
import actuators.Observer;
import asg.cliche.Command;
import home.Room;

//One improvement for next release : 
//We should add a string label to the door, to the room and pass the from the main to the interpreter so we can list them.
public class CommandInterpreter {
	
	private ArrayList<Room> roomList;
	private ArrayList<Observer> actuatorList;
	
	public static final int GO_SLEEP = 1;
	public static final int WAKE_UP = 2;
	
	public CommandInterpreter(ArrayList<Room> roomList,  ArrayList<Observer> actuatorList)
	{
		this.roomList = roomList;
		this.actuatorList = actuatorList;
	}
	@Command
	public String printHelp()
	{
		StringBuilder b  = new StringBuilder();
		for(int i = 0; i < roomList.size(); i++)
		{
			b.append(i+" - Room: "+roomList.get(i).getClass().getSimpleName()+"\n");
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
				if(((AlarmControl) actuatorList.get(0)).setAlarm(true) &&
				((HeatingControl) actuatorList.get(1)).setDesiredTemp(19) &&
				((LightControl) actuatorList.get(2)).autoShutDown() &&
				((LockingControl) actuatorList.get(3)).lockDoors())
					return "Sleep Mode Enabled\n";
				else
					return "Check the error above.\n";
			case WAKE_UP:
				if(((HeatingControl) actuatorList.get(1)).setDesiredTemp(21)&&
				((AlarmControl) actuatorList.get(0)).setAlarm(false))
				return "Wake Up Mode Enabled\n";
			else
				return "Check the error above.\n";
				
		}
		return "Incorrect Mode";
		
	}
	@Command
	public String armAlarm()
	{
		return ((AlarmControl) actuatorList.get(0)).setAlarm(true) ? "the alarm is now armed\n" : "Please check the error above.\n";
	}
	@Command
	public String disarmAlarm()
	{
		return ((AlarmControl) actuatorList.get(0)).setAlarm(false) ? "the alarm is now disarmed\n " : "Please check the error above.\n";
	}
	@Command
	public String setLight(boolean lightOn)
	{
		if(((LightControl) actuatorList.get(2)).turnLights(lightOn))
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
			if(((LightControl) actuatorList.get(2)).turnLights(lightOn, roomList.get(room)))
			return "Lights are now "+((lightOn)?"enabled":"unabled")+"in the "+room+"\n";
			else
				return "Please check the error above.\n";
		}
		
	}
	
	@Command
	public String setLock(boolean open, int room)
	{
		boolean behaveOk = false;
		if(open)
			behaveOk = ((LockingControl) actuatorList.get(3)).unlock(roomList.get(room));
		else
			behaveOk = ((LockingControl) actuatorList.get(3)).lock(roomList.get(room));
		return "Door is now"+((open)?"opened\n":"closed\n");
		
	}
	
	@Command
	public void currentState()
	{
		
	}
}