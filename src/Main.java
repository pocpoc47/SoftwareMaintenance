import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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
		
		ArrayList<MovementSensor> movSensorList = new ArrayList<MovementSensor>();
		ArrayList<TemperatureSensor> tempSensorList = new ArrayList<TemperatureSensor>();
		ArrayList<SmokeSensor> smokeSensorList = new ArrayList<SmokeSensor>();
		ArrayList<LockSensor> lockSensorList = new ArrayList<LockSensor>();
		
		ArrayList<CentralHeating> heatingList = new ArrayList<CentralHeating>();
		ArrayList<Alarm> alarmList = new ArrayList<Alarm>();
		ArrayList<Light> lightList = new ArrayList<Light>();
		ArrayList<Lock> lockList = new ArrayList<Lock>();

		
		for(String room: param.rooms) {
			Room r = roomFact.getRoom(room);
			roomList.add(r);
			String[] roomDevices = param.roomDevices.get(room);
			if(roomDevices == null)break;
			for(String device: roomDevices) {
				DeviceInterface d = deviceFact.getDevice(device, r);
				deviceList.add(d);
				switch (device) {
				case "ALARM":
					alarmControl.addDevice(d);
					alarmList.add((Alarm)d);
					break;
				case "CENTRALHEATING":
					heatingControl.addDevice(d);
					heatingList.add((CentralHeating)d);
					break;
				case "LIGHT":
					lightControl.addDevice(d);
					lightList.add((Light)d);
					break;
				case "LOCK":
					lockingControl.addDevice(d);
					lockList.add((Lock)d);
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
					movSensorList.add((MovementSensor)s);
					s.registerObserver(lockingControl);
					s.registerObserver(lightControl);
					break;
				case "TEMPERATURE":
					tempSensorList.add((TemperatureSensor)s);
					s.registerObserver(heatingControl);
					break;
				case "SMOKE":
					smokeSensorList.add((SmokeSensor)s);
					s.registerObserver(alarmControl);
					break;
				case "LOCK":
					lockSensorList.add((LockSensor)s);
					s.registerObserver(lockingControl);
					break;
				default:
					break;
				}
			}
		}
		
		//Test
				//Il fait 10 degrés dans la maison et nous en voulont 15
				System.out.println("Is central heating turned on ? "+heatingList.get(0).isTurnedOn()+ " - Actual temp : "+(heatingControl.getActualTemp()));
				tempSensorList.get(0).setTemp(10);
				System.out.println("Is central heating turned on ? "+heatingList.get(0).isTurnedOn()+ " - Actual temp : "+(heatingControl.getActualTemp()));
				tempSensorList.get(0).setTemp(15.4);
				System.out.println("Is central heating turned on ? "+heatingList.get(0).isTurnedOn()+ " - Actual temp : "+(heatingControl.getActualTemp()+"\n"));
				
				//La lumière est allumée et elle doit s'éteindre automatiquement.
				System.out.println("Is light on ? : "+lightList.get(0).isTurnedOn());
				movSensorList.get(0).setLastMovementDetectedTime(new Date(System.currentTimeMillis()-1000000));
				movSensorList.get(1).setLastMovementDetectedTime(new Date(System.currentTimeMillis()-1000000));
				System.out.println("Mouvement detecté ! Is light on ? : "+lightList.get(0).isTurnedOn());
				movSensorList.get(1).setLastMovementDetectedTime(new Date(System.currentTimeMillis()-1000000));
				lightControl.autoShutDown();// Plus de mouvement depuis un bon bout de temps (forcer une valeur antérieure pour ne pas attendre)
				System.out.println("Plus de mouvement : Is light on ? : "+lightList.get(0).isTurnedOn()+"\n");
				
				//Les portes sont ouvertes et doivent se fermer.
				System.out.println("Is entrance door locked ? : "+lockList.get(0).isLocked());
				System.out.println("Is garage door locked ? : "+lockList.get(1).isLocked());
				System.out.println("Plus de mouvement : are doors closed ?");
				lockingControl.lockDoors();
				System.out.println("GarageDoor : "+lockList.get(0).isLocked());
				System.out.println("EntranceDoor : "+lockList.get(1).isLocked()+"\n");
				
				//Il y a le feu
				smokeSensorList.get(0).setSmokeDetected(true);
				System.out.println("Alarme déclanchée? "+alarmList.get(0).isAlarmOn());
				smokeSensorList.get(0).setSmokeDetected(false);
				System.out.println("Feu éteint,alarme déclanchée? "+alarmList.get(0).isAlarmOn());
				try {
					ShellFactory.createConsoleShell("HouseInterpreter", "Use \'?list\' to look at commands.", new CommandInterpreter(roomList, controls)).commandLoop();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
	}
}

