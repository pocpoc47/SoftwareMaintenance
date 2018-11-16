package sensors;

import java.util.ArrayList;

import actuators.Observer;
import dto.Dto;
import exceptions.SearchException;
import exceptions.VariabilityException;
import home.Room;

public class TemperatureSensor extends Sensor {
	public static int TYPE = 3;
	private double temp;
	
	public TemperatureSensor(ArrayList<Observer> obsList, Room room) {
		super(obsList,room);
		this.temp = 15.3;
	}
	public TemperatureSensor(double temp, Room room, ArrayList<Observer> obsList) {
		super(obsList, room);
		this.temp = temp;
	}
	
	public double getTemp()
	{
		return temp;
	}
	public void setTemp(double t)
	{
		temp = t;
		super.notifyObservers(new Dto(TYPE,Dto.TEMP_CHANGE,((Object)t), super.getRoom()));
	}

}
