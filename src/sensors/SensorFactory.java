package sensors;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;

import actuators.Observer;
import devices.Alarm;
import devices.CentralHeating;
import devices.DeviceFactory;
import devices.DeviceInterface;
import devices.Light;
import home.Garage;
import home.Hall;
import home.Kitchen;
import home.Room;

public class SensorFactory {

	public Sensor getSensor(String type, Room room, ArrayList<Observer> observerList) {
		Sensor sensor = null;
		//WHAT TO PUT IN THE LIST?
		switch (type) {
		case "LOCK":
			sensor = new LockSensor(observerList, false, room);
			break;
		case "MOVEMENT":
			sensor = new MovementSensor(Date.from(Instant.now()), room, observerList);
			break;
		case "SMOKE":
			sensor = new SmokeSensor(observerList, room);
			break;
		case "TEMPERATURE":
			sensor = new TemperatureSensor(observerList, room);
			break;

		default:
			System.out.println("UNKNOWN TYPE: " + type);
			break;
		}
		return sensor;
	}
}
