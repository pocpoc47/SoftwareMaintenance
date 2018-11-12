package sensors;

import java.util.ArrayList;
import java.util.Date;

import actuators.Observer;
import dto.Dto;
import home.Room;

public class MovementSensor extends Sensor {
	public static int TYPE = 2;
	
	public MovementSensor(Date date, Room room, ArrayList<Observer> obsList) {
		super(obsList,room);
	}

	public void setLastMovementDetectedTime(Date lastMovementDetectedTime) {
		super.notifyObservers(new Dto(TYPE,Dto.MOV_TIME_DETECT,((Object)lastMovementDetectedTime), super.getRoom())); //1 = LastDetectedTime
	}
	

}
