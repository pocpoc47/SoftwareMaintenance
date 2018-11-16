package sensors;

import java.util.ArrayList;

import actuators.Observer;
import dto.Dto;
import exceptions.SearchException;
import exceptions.VariabilityException;
import home.Room;

public abstract class Sensor {
	
	private ArrayList<Observer> observerList;
	private Room room;
	
	public Sensor(ArrayList<Observer> obsList, Room room)
	{
		this.observerList = obsList;
		this.room = room;
	}
	public void registerObserver(Observer obs)
	{
		observerList.add(obs);
	}
	public void unregisterObserver(Observer obs)
	{
		observerList.remove(obs);
	}
	public void notifyObservers(Dto dto) {
		for(Observer obs : observerList)
		{
			obs.update(dto);
		}
		
	}
	public Room getRoom()
	{
		return room;
	}
}
