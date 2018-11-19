import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import actuators.AlarmControl;
import actuators.HeatingControl;
import actuators.LightControl;
import actuators.LockingControl;
import actuators.Observer;
import asg.cliche.ShellFactory;
import devices.Alarm;
import devices.CentralHeating;
import devices.DeviceFactory;
import devices.DeviceInterface;
import devices.Light;
import devices.Lock;
import home.Room;
import home.RoomFactory;
import interpreter.CommandInterpreter;
import parametrization.Parameters;
import sensors.LockSensor;
import sensors.MovementSensor;
import sensors.Sensor;
import sensors.SensorFactory;
import sensors.SmokeSensor;
import sensors.TemperatureSensor;

public class Main {

	public static void main(String[] args) {
//		Parameters param2 = new Parameters();
//
//		param2.rooms.add("HALL");
//		param2.rooms.add("KITCHEN");
//		param2.rooms.add("GARAGE");
//
//		String[] kitchenDevices = {"LIGHT", "CENTRALHEATING", "LOCK"};
//		String[] kitchenSensors = {"MOVEMENT", "SMOKE", "TEMPERATURE", "LOCK"};
//		
//		String[] hallDevices = {"LIGHT", "LIGHT", "ALARM", "LOCK"};
//		String[] hallSensors = {"MOVEMENT", "SMOKE", "TEMPERATURE", "LOCK"};
//
//		param2.roomDevices.put("HALL", hallDevices);
//		param2.roomSensors.put("HALL", hallSensors);
//		
//		param2.roomDevices.put("KITCHEN", kitchenDevices);
//		param2.roomSensors.put("KITCHEN", kitchenSensors);
//
//		Parameters.writeToJson(param2);
		
		Parameters param = Parameters.readFromJson();
		
		RoomFactory roomFact = new RoomFactory();
		DeviceFactory deviceFact = new DeviceFactory();
		SensorFactory sensorFact = new SensorFactory();
		
		ArrayList<Room> roomList = new ArrayList<Room>();
		ArrayList<DeviceInterface> deviceList = new ArrayList<DeviceInterface>();
		ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
		
		AlarmControl alarmControl = new AlarmControl(null);
		HeatingControl heatingControl = new HeatingControl(null, 0);
		LightControl lightControl = new LightControl(null);
		LockingControl lockingControl = new LockingControl(null);

		ArrayList<Observer> controls = new ArrayList<Observer>();
		controls.add(alarmControl);
		controls.add(heatingControl);
		controls.add(lightControl);
		controls.add(lockingControl);
		
		HashMap<Room, MovementSensor> movSensorList = new HashMap<Room, MovementSensor>();
		HashMap<Room, TemperatureSensor> tempSensorList = new HashMap<Room, TemperatureSensor>();
		HashMap<Room, SmokeSensor> smokeSensorList = new HashMap<Room, SmokeSensor>();
		HashMap<Room, LockSensor> lockSensorList = new HashMap<Room, LockSensor>();
		
		HashMap<Room,CentralHeating> heatingMap = new HashMap<Room,CentralHeating>();
		HashMap<Room,Alarm> alarmMap = new HashMap<Room,Alarm>();
		HashMap<Room,ArrayList<Light>> lightMap = new HashMap<Room,ArrayList<Light>>();
		HashMap<Room,ArrayList<Lock>> lockMap = new HashMap<Room,ArrayList<Lock>>();
		
		Room kitchen = null;
		Room hall = null;
		Room garage = null;
		
		for(String room: param.rooms) {
			Room r = roomFact.getRoom(room);
			if(room.equals("KITCHEN"))kitchen=r;
			if(room.equals("HALL"))hall=r;
			if(room.equals("GARAGE"))garage=r;
			roomList.add(r);
			String[] roomDevices = param.roomDevices.get(room);
			if(roomDevices == null)break;
			for(String device: roomDevices) {
				DeviceInterface d = deviceFact.getDevice(device, r);
				deviceList.add(d);
				switch (device) {
				case "ALARM":
					alarmControl.addDevice(d);
					alarmMap.put(r,(Alarm)d);
					break;
				case "CENTRALHEATING":
					heatingControl.addDevice(d);
					heatingMap.put(r,(CentralHeating)d);
					break;
				case "LIGHT":
					lightControl.addDevice(d);
					if(lightMap.containsKey(r)) {
						lightMap.get(r).add((Light)d);
					}else {
						lightMap.put(r, new ArrayList<Light>(Arrays.asList((Light)d)));
					}
					break;
				case "LOCK":
					lockingControl.addDevice(d);
					if(lockMap.containsKey(r)) {
						lockMap.get(r).add((Lock)d);
					}else {
						lockMap.put(r, new ArrayList<Lock>(Arrays.asList((Lock)d)));
					}
					break;
				default:
					break;
				}
			}
			
			for(String sensor: param.roomSensors.get(room)) {
				Sensor s = sensorFact.getSensor(sensor, r, new ArrayList<Observer>());
				sensorList.add(s);
				switch (sensor) {
				case "MOVEMENT":
					movSensorList.put(r,(MovementSensor)s);
					s.registerObserver(lockingControl);
					s.registerObserver(lightControl);
					break;
				case "TEMPERATURE":
					tempSensorList.put(r,(TemperatureSensor)s);
					s.registerObserver(heatingControl);
					break;
				case "SMOKE":
					smokeSensorList.put(r,(SmokeSensor)s);
					s.registerObserver(alarmControl);
					break;
				case "LOCK":
					lockSensorList.put(r,(LockSensor)s);
					s.registerObserver(lockingControl);
					break;
				default:
					break;
				}
			}
		}
		
				//Il fait 10 degrés dans la maison et nous en voulont 15
				heatingControl.setDesiredTemp(15);
				System.out.println("DesiredTemp = 15");
				tempSensorList.get(kitchen).setTemp(10);
				System.out.println("Is central heating turned on in kitchen? "+heatingMap.get(kitchen).isTurnedOn()+ " - Actual temp : "+(heatingControl.getActualTemp()));
				tempSensorList.get(kitchen).setTemp(15.4);
				System.out.println("Is central heating turned on in the kitchen? "+heatingMap.get(kitchen).isTurnedOn()+ " - Actual temp : "+(heatingControl.getActualTemp()+"\n"));
				
				//La lumière est allumée et elle doit s'éteindre automatiquement.
				lightControl.turnLights(false, kitchen);
				System.out.println("Is light on in the kitchen? : "+lightMap.get(kitchen).get(0).isTurnedOn());
				movSensorList.get(kitchen).setLastMovementDetectedTime(new Date(System.currentTimeMillis()));
				System.out.println("Mouvement detecté ! Is light on ? : "+lightMap.get(kitchen).get(0).isTurnedOn());
				movSensorList.get(kitchen).setLastMovementDetectedTime(new Date(System.currentTimeMillis()-1000000));
				lightControl.autoShutDown();// Plus de mouvement depuis un bon bout de temps (forcer une valeur antérieure pour ne pas attendre)
				System.out.println("Plus de mouvement : Is light on ? : "+lightMap.get(kitchen).get(0).isTurnedOn()+"\n");
				
				//Les portes sont ouvertes et doivent se fermer.
				System.out.println("Is entrance door locked ? : "+lockMap.get(hall).get(0).isLocked());
				System.out.println("Is garage door locked ? : "+lockMap.get(garage).get(0).isLocked());
				System.out.println("Plus de mouvement : are doors closed ?");
				movSensorList.get(garage).setLastMovementDetectedTime(new Date(System.currentTimeMillis()-1000000));
				
				lockingControl.lockDoors();
				System.out.println("GarageDoor locked?: "+lockMap.get(garage).get(0).isLocked());
				System.out.println("EntranceDoor locked?: "+lockMap.get(garage).get(0).isLocked()+"\n");
				
				//Il y a le feu
				System.out.println("Feu a la cuisine");
				smokeSensorList.get(kitchen).setSmokeDetected(true);
				System.out.println("Alarme déclanchée? "+alarmMap.get(kitchen).isAlarmOn());
				smokeSensorList.get(kitchen).setSmokeDetected(false);
				System.out.println("Feu éteint,alarme déclanchée? "+alarmMap.get(kitchen).isAlarmOn());
				try {
					ShellFactory.createConsoleShell("HouseInterpreter", "Use \'?list\' to look at commands.", new CommandInterpreter(roomList, controls)).commandLoop();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
	}
}

