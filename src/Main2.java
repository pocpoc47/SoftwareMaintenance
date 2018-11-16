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
		ArrayList<Room> rooms = param.test();
		int a = 0;
	}

}
