package devices;

import home.Room;

public class DeviceFactory {
	public DeviceInterface getDevice(String type, Room room) {
		DeviceInterface device = null;
		switch (type) {
		case "LIGHT":
			device = new Light(false, room);
			break;
		case "ALARM":
			device = new Alarm(room);
			break;
		case "CENTRALHEATING":
			device = new CentralHeating(false, room);
			break;
		case "LOCK":
			device = new Lock(false, room);
			break;

		default:
			System.out.println("UNKNOWN TYPE: " + type);
			break;
		}
		return device;
	}

	
	
}
