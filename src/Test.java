import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import devices.Alarm;
import devices.CentralHeating;
import devices.DeviceInterface;
import devices.Light;
import devices.Lock;
import home.Garage;
import home.GarageDoor;
import home.Room;
import parametrization.Parameters;
import sensors.LockSensor;
import sensors.MovementSensor;
import sensors.SmokeSensor;
import sensors.TemperatureSensor;

public class Test {
	public static void main(String[] args) {
		Parameters param = new Parameters();
		param.setGarage(true);
		param.setHall(true);
		param.setKitchen(true);

		ArrayList<Integer> garageSensors = new ArrayList<>();
		ArrayList<Integer> garageDevices = new ArrayList<>();
		
		ArrayList<Integer> hallDevices = new ArrayList<>();
		ArrayList<Integer> hallSensors = new ArrayList<>();
		
		ArrayList<Integer> kitchenDevices = new ArrayList<>();
		ArrayList<Integer> kitchenSensors = new ArrayList<>();

		garageSensors.add(SmokeSensor.TYPE);
		garageSensors.add(MovementSensor.TYPE);
		garageSensors.add(TemperatureSensor.TYPE);
		garageDevices.add(LockSensor.TYPE);
		garageDevices.add(Lock.TYPE);
		

		hallSensors.add(SmokeSensor.TYPE);
		hallSensors.add(TemperatureSensor.TYPE);
		hallSensors.add(MovementSensor.TYPE);
		hallDevices.add(Light.TYPE);
		hallDevices.add(Alarm.TYPE);

		kitchenSensors.add(SmokeSensor.TYPE);
		kitchenSensors.add(TemperatureSensor.TYPE);
		kitchenSensors.add(MovementSensor.TYPE);
		kitchenDevices.add(Light.TYPE);
		
		param.setGarageDevices(garageDevices);
		param.setGarageSensors(garageSensors);

		param.setHallDevices(hallDevices);
		param.setHallSensors(hallSensors);
		
		param.setKitchenDevices(kitchenDevices);
		param.setKitchenSensors(kitchenSensors);
		
		writeJson(param);
		Parameters param2 = readJson();
		
		
	}
	public static void writeJson(Parameters param) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter fw = new FileWriter("config.json");
			gson.toJson(param, fw);
			fw.close();
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
	}
	public static Parameters readJson() {
		JsonReader reader = null;
		Gson gson = new Gson();
		try {
			reader = new JsonReader(new FileReader("config.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return gson.fromJson(reader, Parameters.class);
	}
}
