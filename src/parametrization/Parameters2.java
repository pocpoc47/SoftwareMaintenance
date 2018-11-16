package parametrization;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import actuators.AlarmControl;
import actuators.ControlFactory;
import actuators.HeatingControl;
import actuators.LightControl;
import actuators.LockingControl;
import actuators.Observer;
import devices.DeviceFactory;
import devices.DeviceInterface;
import home.Room;
import home.RoomFactory;
import sensors.Sensor;
import sensors.SensorFactory;

public class Parameters2 {
	public ArrayList<String> rooms = new ArrayList<String>();
	public HashMap<String, String[]> roomDevices = new HashMap<String, String[]>();
	public HashMap<String, String[]> roomSensors = new HashMap<String, String[]>();
	public HashMap<String, String[]> doorSensors = new HashMap<String, String[]>();
	
	public ArrayList<Room> test() {
		RoomFactory roomFact = new RoomFactory();
		DeviceFactory deviceFact = new DeviceFactory();
		SensorFactory sensorFact = new SensorFactory();
		ControlFactory controlFact = new ControlFactory();
		
		
		
		

		ArrayList<Room> roomList = new ArrayList<Room>();
		ArrayList<DeviceInterface> deviceList = new ArrayList<DeviceInterface>();
		ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
		
		AlarmControl alarmControl = new AlarmControl(null);
		HeatingControl heatingControl = new HeatingControl(null, 0);
		LightControl lightControl = new LightControl(null);
		LockingControl lockingControl = new LockingControl(null);
		
		for(String room: rooms) {
			Room r = roomFact.getRoom(room);
			roomList.add(r);
			for(String device: roomDevices.get(room)) {
				DeviceInterface d = deviceFact.getDevice(device, r);
				deviceList.add(d);
				switch (device) {
				case "ALARM":
					alarmControl.AddDevice(d);
					break;
				case "HEATING":
					heatingControl.AddDevice(d);
					break;
				case "LIGHT":
					lightControl.AddDevice(d);
					break;
				case "LOCK":
					lockingControl.AddDevice(d);
					break;
				default:
					break;
				}
			}
			for(String sensor: roomSensors.get(room)) {
				Sensor s = sensorFact.getSensor(sensor, r, new ArrayList<Observer>());
				sensorList.add(s);
				switch (sensor) {
				case "MOVEMENT":
					s.registerObserver(lockingControl);
					s.registerObserver(lightControl);
					break;
				case "TEMPERATURE":
					s.registerObserver(heatingControl);
					break;
				case "SMOKE":
					s.registerObserver(alarmControl);
					break;
				case "DOORLOCK":
					s.registerObserver(lockingControl);
					break;
				default:
					break;
				}
			}
		}
		return roomList;
	}

	public boolean writeToJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		//String json = gson.toJson();
		return true;
	}
}
