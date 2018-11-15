package home;
import java.util.ArrayList;
import java.util.List;

import devices.*;
import sensors.MovementSensor;
import sensors.Sensor;
import sensors.SmokeSensor;
import sensors.TemperatureSensor;

public abstract class Room  {

	private ArrayList<DeviceInterface> devices;
	private ArrayList<Sensor> sensors;
	private Door door;
	
	
	public Room(){
		
	}


	public ArrayList<DeviceInterface> getDevices() {
		return devices;
	}


	public void setDevices(ArrayList<DeviceInterface> devices) {
		this.devices = devices;
	}


	public Door getDoor() {
		return door;
	}


	public void setDoor(Door door) {
		this.door = door;
	}


	public ArrayList<Sensor> getSensors() {
		return sensors;
	}


	public void setSensors(ArrayList<Sensor> sensors) {
		this.sensors = sensors;
	}


	
}
