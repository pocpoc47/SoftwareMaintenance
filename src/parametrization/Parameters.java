package parametrization;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Parameters {
	public ArrayList<String> rooms = new ArrayList<String>();
	public HashMap<String, String[]> roomDevices = new HashMap<String, String[]>();
	public HashMap<String, String[]> roomSensors = new HashMap<String, String[]>();
	
	public static boolean writeToJson(Parameters param) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String json = gson.toJson(param);
		try {
			PrintWriter out = new PrintWriter("config.json");
			out.print(json);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		//write
		return true;
	}
	public static Parameters readFromJson() {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Parameters param = null;
		try {
			FileReader fr = new FileReader(new File("config.json"));
			param = gson.fromJson(fr, Parameters.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	
		return param;
	}
}
