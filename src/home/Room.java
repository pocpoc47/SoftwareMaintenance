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
	
	
	public Room(){
		
	}


	public ArrayList<DeviceInterface> getDevices() {
		return devices;
	}


	public void setDevices(ArrayList<DeviceInterface> devices) {
		this.devices = devices;
	}


	
}
