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
	public static final int GO_SLEEP = 1;
	public static final int WAKE_UP = 2;
	
	public CommandInterpreter(ArrayList<Room> roomList)
	{
		this.roomList = roomList;
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
				if(AlarmControl.getInstance().setAlarm(true) &&
				HeatingControl.getInstance().setDesiredTemp(19) &&
				LightControl.getInstance().autoShutDown() &&
				LockingControl.getInstance().lockDoors())
					return "Sleep Mode Enabled\n";
				else
					return "Check the error above.\n";
			case WAKE_UP:
				if(HeatingControl.getInstance().setDesiredTemp(21)&&
				AlarmControl.getInstance().setAlarm(false))
				return "Wake Up Mode Enabled\n";
			else
				return "Check the error above.\n";
				
		}
		return "Incorrect Mode";
		
	}
	@Command
	public String armAlarm()
	{
		return AlarmControl.getInstance().setAlarm(true) ? "the alarm is now armed\n" : "Please check the error above.\n";
	}
	@Command
	public String disarmAlarm()
	{
		return AlarmControl.getInstance().setAlarm(false) ? "the alarm is now disarmed\n " : "Please check the error above.\n";
	}
	@Command
	public String setLight(boolean lightOn)
	{
		if(LightControl.getInstance().turnLights(lightOn))
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
			if(LightControl.getInstance().turnLights(lightOn, roomList.get(room)))
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
			behaveOk = LockingControl.getInstance().unlock(roomList.get(room));
		else
			behaveOk = LockingControl.getInstance().lock(roomList.get(room));
		
		if(behaveOk)
			return "Door is now"+((open)?"opened\n":"closed\n");
		else
			return "Check the error above.\n";
		
	}
	
	@Command
	public void currentState()
	{
		
	}
}