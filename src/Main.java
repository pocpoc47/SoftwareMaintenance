import devices.*;
import home.*;
import sensors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import actuators.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
		TemperatureSensor tempSens = new TemperatureSensor(10,kitchen, tempArray);
		MovementSensor movSensKitchen = new MovementSensor(new Date(), kitchen, mergingList);
		MovementSensor movSensHall = new MovementSensor(new Date(), hall, mergingList);
		MovementSensor movSensGarage = new MovementSensor(new Date(), garage, mergingList);
		
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
		
		
		
		
		//HALL
		/*Alarm alarm = new Alarm(false);
		DoorEngine doorEngineEntrance = new DoorEngine(false);
		Lock lockEntrance = new Lock(false);
		EntranceDoor entranceDoor = new EntranceDoor(lockEntrance, doorEngineEntrance);
		Light entranceLight = new Light(true);
		ArrayList<Light> hLL = new ArrayList();
		hLL.add(entranceLight);
		SmokeSensor sS = new SmokeSensor();
		TemperatureSensor tSHall = new TemperatureSensor(15.3);
		MovementSensor mS = new MovementSensor(new Date(System.currentTimeMillis()-1000000));
		Hall hall = new Hall(alarm, entranceDoor, hLL, sS, mS,cH, tSHall);
		roomList.add(hall);
		
		//GARAGE
		DoorEngine doorEngineGarage = new DoorEngine(false);
		Lock lockGarage = new Lock(false);
		GarageDoor garageDoor = new GarageDoor(lockGarage, doorEngineGarage);
		Light garageLight = new Light(true);
		ArrayList<Light> gLL = new ArrayList();
		gLL.add(garageLight);
		SmokeSensor sSGarage = new SmokeSensor();
		MovementSensor mSGarage = new MovementSensor(new Date(System.currentTimeMillis()-1000000));
		TemperatureSensor tSGarage = new TemperatureSensor(15.3);
		Garage garage = new Garage(garageDoor,gLL,sSGarage,mSGarage,cH,tSGarage);
		roomList.add(garage);
		
		//Kitchen
		Light kitchenLight = new Light(true);
		ArrayList<Light> kLL = new ArrayList();
		kLL.add(kitchenLight);
		SmokeSensor sSKitchen = new SmokeSensor();
		sSKitchen.setSmokeDetected(true);
		MovementSensor mSKitchen = new MovementSensor(new Date(System.currentTimeMillis()-1000000));
		TemperatureSensor tempSKitchen = new TemperatureSensor(15.3);
		Kitchen kitchen = new Kitchen(kLL, sSKitchen, mSKitchen, tempSKitchen, cH);
		roomList.add(kitchen);
		

		
		//Creation of Actuators
		AlarmControl alarmControl = new AlarmControl(roomList);
		LightControl lightControl = new LightControl(roomList);
		HeatingControl heatingControl = new HeatingControl(roomList);
		LockingControl lockingControl = new LockingControl(roomList);
		
		System.out.println("Debut de simulation\n");
		System.out.println("Les portes du garage et de l'entrée sont ouvertes,");
		System.out.println("Les lampes cuisines , hall et garage sont allumées");
		System.out.println("Alarme déclanchée ? "+hall.getAlarm().isAlarmOn());
		System.out.println("La température est de "+kitchen.getTemperatureSensor().getTemp()+" dégré(s)");
		
		lightControl.autoShutdown();
		heatingControl.KeepHouseTemp();
		lockingControl.areDoorsLocked();
		alarmControl.isEverytingFine();
		
		System.out.println("Porte d'entrée 'is locked' ? : "+hall.getEntranceDoor().getLock().getLocked());
		System.out.println("Porte de garage 'is locked' ? : "+garage.getGarageDoor().getLock().getLocked());
		System.out.println("Lampe allumées ?\n ");
		for (Room r : roomList)
		{
			
			for( Light l : r.getLights())
			{
				if(l.isTurnedOn())
				{
					System.out.println("il reste une lampe allumée \n");
				}
			}
		}
		System.out.println("Alarme déclanchée ? "+hall.getAlarm().isAlarmOn());
		System.out.println("Simulation terminée\n");
		*/
	}

}
