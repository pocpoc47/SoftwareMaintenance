package parametrization;

import java.util.ArrayList;

import devices.DeviceInterface;
import devices.Light;
import sensors.Sensor;

public class Parameters {
	private boolean Garage;
	private boolean DiningRoom;
	private boolean Kitchen;
	private boolean Garden;
	private boolean Hall;
	private boolean LivingRoom;
	private boolean BedRoom;
	private boolean BathRoom;
	

	private ArrayList<Sensor> GarageSensors;
	private ArrayList<Sensor> DiningRoomSensors;
	private ArrayList<Sensor> KitchenSensors;
	private ArrayList<Sensor> GardenSensors;
	private ArrayList<Sensor> HallSensors;
	private ArrayList<Sensor> LivingRoomSensors;
	private ArrayList<Sensor> BedRoomSensors;
	private ArrayList<Sensor> BathRoomSensors;
	
	
	private ArrayList<DeviceInterface> GarageDevices;
	private ArrayList<DeviceInterface> DiningRoomDevices;
	private ArrayList<DeviceInterface> KitchenDevices;
	private ArrayList<DeviceInterface> GardenDevices;
	private ArrayList<DeviceInterface> HallDevices;
	private ArrayList<DeviceInterface> LivingRoomDevices;
	private ArrayList<DeviceInterface> BedRoomDevices;
	private ArrayList<DeviceInterface> BathRoomDevices;
	
	
	public void random() {
		GarageDevices.add(new Light(true));
	}
	
	
}
