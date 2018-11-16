import devices.*;
import home.*;
import parametrization.Parameters;
import parametrization.Parameters2;
import sensors.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import actuators.*;

public class Main2 {

	public static void main(String[] args) {
		Parameters2 param = new Parameters2();

		param.rooms.add("HALL");
		param.rooms.add("KITCHEN");
		param.rooms.add("GARAGE");

		String[] kitchenDevices = {"LIGHT", "CENTRALHEATING"};
		String[] kitchenSensors = {"MOVEMENT", "SMOKE", "TEMPERATURE"};
	
		
		String[] hallDevices = {"LIGHT", "LIGHT", "ALARM"};
		String[] hallSensors = {"MOVEMENT", "SMOKE", "TEMPERATURE"};
		param.roomDevices.put("HALL", hallDevices);
		param.roomSensors.put("HALL", hallSensors);
		
		param.test();
		
		param.writeToJson();
		int a = 0;
	}

}
