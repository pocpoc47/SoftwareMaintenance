import devices.*;
import home.*;
import sensors.*;

import java.util.ArrayList;
import java.util.Date;

import actuators.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//creation of rooms
		CentralHeating cH = new CentralHeating(false);
		ArrayList<Room> roomList = new ArrayList();
		
		//HALL
		Alarm alarm = new Alarm(false);
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
		
	}

}
