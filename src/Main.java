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
		
		//Creation of actuators
		ArrayList<Observer> tempArray = new ArrayList<>(Arrays.asList(new HeatingControl(heatingList,15)));
		ArrayList<Observer> lightArray = new ArrayList<>(Arrays.asList(new LightControl(lightList)));
		ArrayList<Observer> lockArray = new ArrayList<>(Arrays.asList(new LockingControl(lockList)));
		ArrayList<Observer> mergingList = new ArrayList<Observer>();
		mergingList.addAll(lightArray);
		mergingList.addAll(lockArray);
		
		//Creation of sensors
		ArrayList<MovementSensor> movSensList = new ArrayList<MovementSensor>();
		TemperatureSensor tempSens = new TemperatureSensor(10,kitchen, tempArray);
		MovementSensor movSensKitchen = new MovementSensor(new Date(), kitchen, mergingList);
		MovementSensor movSensHall = new MovementSensor(new Date(), hall, mergingList);
		MovementSensor movSensGarage = new MovementSensor(new Date(), garage, mergingList);
		movSensList.add(movSensGarage);
		movSensList.add(movSensHall);
		movSensList.add(movSensKitchen);
		
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
		System.out.println("Plus de mouvement : Is light on ? : "+lightList.get(0).isTurnedOn());
		
		//Les portes sont ouvertes et doivent se fermer.
		System.out.println("Is entrance door open ? : "+lockList.get(0).isLocked());
		System.out.println("Is garage door open ? : "+lockList.get(1).isLocked());
		System.out.println("Plus de mouvement : are doors closed ?\n");
		((LockingControl)lockArray.get(0)).areDoorsLocked();
		System.out.println("GarageDoor : "+lockList.get(0).isLocked());
		System.out.println("EntranceDoor : "+lockList.get(1).isLocked());
		
		try {
			ShellFactory.createConsoleShell("hello", "", new CommandInterpreter(roomList, tempSens, movSensList)).commandLoop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



