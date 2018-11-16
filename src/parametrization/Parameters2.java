package parametrization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import home.Room;
import home.RoomFactory;

public class Parameters2 {
	public ArrayList<String> rooms = new ArrayList<String>();
	public HashMap<String, String[]> roomDevices = new HashMap<String, String[]>();
	public HashMap<String, String[]> roomSensors = new HashMap<String, String[]>();
	
	public ArrayList<Room> test() {
		RoomFactory roomFact = new RoomFactory();
		String[] hallDevices = {"LIGHT", "LIGHT", "ALARM"};
		String[] hallSensors = {"MOVEMENT", "SMOKE", "TEMPERATURE"};
		rooms.add("HALL");
		roomDevices.put("HALL", hallDevices);
		roomSensors.put("HALL", hallSensors);
		
		
		ArrayList<Room> roomList = new ArrayList<Room>();
		for(String room: rooms) {
			String[] devices = {};
			String[] sensors = {};
			if(roomDevices.containsKey(room)) {
				devices = roomDevices.get(room);
			}
			if(roomSensors.containsKey(room)) {
				sensors = roomSensors.get(room);
			}
			Room r = roomFact.getRoom(room);
			roomList.add(r);
		}
		return roomList;
	}
}
