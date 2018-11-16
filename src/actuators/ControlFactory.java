package actuators;

import java.util.ArrayList;

import devices.Alarm;
import devices.CentralHeating;
import devices.DeviceInterface;
import devices.Light;
import devices.Lock;

public class ControlFactory {
	public <E> Observer getControl(String type, ArrayList<E> deviceList) {
		Observer control = null;
		switch (type) {
		case "ALARM":
			control = new AlarmControl((ArrayList<Alarm>) deviceList);
			break;
		case "LIGHT":
			control = new LightControl((ArrayList<Light>) deviceList);
			break;
		case "HEATING":
			control = new HeatingControl((ArrayList<CentralHeating>) deviceList, 0);
			break;
		case "LOCKING":
			control = new LockingControl((ArrayList<Lock>) deviceList);
			break;
			
		default:
			System.out.println("UNKNOWN TYPE: " + type);
			break;
		}
		return control;
	}
}
