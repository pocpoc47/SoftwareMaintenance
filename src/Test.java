import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import devices.CentralHeating;
import devices.DoorEngine;
import devices.Light;
import devices.Lock;
import home.Garage;
import home.GarageDoor;
import home.Room;
import sensors.MovementSensor;
import sensors.SmokeSensor;
import sensors.TemperatureSensor;

public class Test {
	public static void main(String[] args) {
		Room r = new Garage(new GarageDoor(new Lock(true), new DoorEngine(false)), new ArrayList<Light>(), new SmokeSensor(), new MovementSensor(Date.from(Instant.now())), new CentralHeating(true), new TemperatureSensor());
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(r);
		System.out.println(json);
		Room r2 = gson.fromJson(json, Garage.class);
		System.out.println(r2);
	}
}
