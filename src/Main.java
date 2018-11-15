import devices.*;
import home.*;
import interpreter.CommandInterpreter;
import sensors.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.beust.jcommander.JCommander;

import actuators.*;
import asg.cliche.Command;
import asg.cliche.ShellFactory;

public class Main {

	public static void main(String[] args) {

		//creation of rooms
		ArrayList<Room> roomList = new ArrayList();
		Room kitchen = new Kitchen();
		Room hall = new Hall();
		Room garage = new Garage();
		Door entranceDoor = new EntranceDoor(hall);
		Door garageDoor = new GarageDoor(garage);
		roomList.add(kitchen);
		roomList.add(hall);
		roomList.add(garage);
		
		//Creation of devices
		ArrayList<CentralHeating> heatingList = new ArrayList<CentralHeating>();
		ArrayList<Light> lightList = new ArrayList<Light>();
		ArrayList<Lock> lockList = new ArrayList<Lock>();
		ArrayList<Alarm> alarmList = new ArrayList<Alarm>();
		heatingList.add(new CentralHeating(false, (Kitchen)kitchen));
		lightList.add(new Light(true, kitchen));
		lockList.add(new Lock(false,entranceDoor));
		lockList.add(new Lock(false,garageDoor));
		alarmList.add(new Alarm(kitchen));
		
		//Creation of actuators (array because actuator need 'observer' array
		/*ArrayList<Observer> lightArray = new ArrayList<>(Arrays.asList(new LightControl(lightList)));
		ArrayList<Observer> lockArray = new ArrayList<>(Arrays.asList(new LockingControl(lockList)));
		ArrayList<Observer> tempArray = new ArrayList<>(Arrays.asList(new HeatingControl(heatingList,15)));
		ArrayList<Observer> alarmArray = new ArrayList<>(Arrays.asList(new AlarmControl(alarmList)));*/
		ArrayList<Observer> lightArray = new ArrayList<>(Arrays.asList(new LightControl(null)));
		ArrayList<Observer> lockArray = new ArrayList<>(Arrays.asList(new LockingControl(null)));
		ArrayList<Observer> tempArray = new ArrayList<>(Arrays.asList(new HeatingControl(null,15)));
		ArrayList<Observer> alarmArray = new ArrayList<>(Arrays.asList(new AlarmControl(null)));
		
		//Creation of sensors
		ArrayList<Observer> mergingList = new ArrayList<Observer>();
		mergingList.addAll(lightArray);
		mergingList.addAll(lockArray);
		ArrayList<MovementSensor> movSensList = new ArrayList<MovementSensor>();
		TemperatureSensor tempSens = new TemperatureSensor(10,kitchen, tempArray);
		MovementSensor movSensKitchen = new MovementSensor(new Date(), kitchen, mergingList);
		MovementSensor movSensHall = new MovementSensor(new Date(), hall, mergingList);
		MovementSensor movSensGarage = new MovementSensor(new Date(), garage, mergingList);
		SmokeSensor smokeSens = new SmokeSensor(alarmArray,kitchen);
		movSensList.add(movSensGarage);
		movSensList.add(movSensHall);
		movSensList.add(movSensKitchen);
		mergingList.addAll(tempArray);
		mergingList.addAll(alarmArray);
		
		//Test
		//Il fait 10 degrés dans la maison et nous en voulont 15
		System.out.println("Is central heating turned on ? "+heatingList.get(0).isTurnedOn()+ " - Actual temp : "+((HeatingControl)tempArray.get(0)).getActualTemp());
		tempSens.setTemp(10);
		System.out.println("Is central heating turned on ? "+heatingList.get(0).isTurnedOn()+ " - Actual temp : "+((HeatingControl)tempArray.get(0)).getActualTemp());
		tempSens.setTemp(15.4);
		System.out.println("Is central heating turned on ? "+heatingList.get(0).isTurnedOn()+ " - Actual temp : "+((HeatingControl)tempArray.get(0)).getActualTemp()+"\n");
		
		//La lumière est allumée et elle doit s'éteindre automatiquement.
		System.out.println("Is light on ? : "+lightList.get(0).isTurnedOn());
		movSensHall.setLastMovementDetectedTime(new Date(System.currentTimeMillis()-1000000));
		movSensKitchen.setLastMovementDetectedTime(new Date(System.currentTimeMillis()-1000000));
		movSensGarage.setLastMovementDetectedTime(new Date(System.currentTimeMillis()-1000000)); // Un mouvement est détecté
		System.out.println("Mouvement detecté ! Is light on ? : "+lightList.get(0).isTurnedOn());
		movSensKitchen.setLastMovementDetectedTime(new Date(System.currentTimeMillis()-1000000));
		((LightControl)lightArray.get(0)).autoShutDown();// Plus de mouvement depuis un bon bout de temps (forcer une valeur antérieure pour ne pas attendre)
		System.out.println("Plus de mouvement : Is light on ? : "+lightList.get(0).isTurnedOn()+"\n");
		
		//Les portes sont ouvertes et doivent se fermer.
		System.out.println("Is entrance door locked ? : "+lockList.get(0).isLocked());
		System.out.println("Is garage door locked ? : "+lockList.get(1).isLocked());
		System.out.println("Plus de mouvement : are doors closed ?");
		((LockingControl)lockArray.get(0)).lockDoors();
		System.out.println("GarageDoor : "+lockList.get(0).isLocked());
		System.out.println("EntranceDoor : "+lockList.get(1).isLocked()+"\n");
		
		//Il y a le feu
		smokeSens.setSmokeDetected(true);
		System.out.println("Alarme déclanchée? "+alarmList.get(0).isAlarmOn());
		smokeSens.setSmokeDetected(false);
		System.out.println("Feu éteint,alarme déclanchée? "+alarmList.get(0).isAlarmOn());
		
		
		
		try {
			ShellFactory.createConsoleShell("hello", "", new CommandInterpreter(roomList, tempSens, movSensList, mergingList)).commandLoop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



