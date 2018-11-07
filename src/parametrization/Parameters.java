package parametrization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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

	private ArrayList<Integer> GarageSensors;
	private ArrayList<Integer> DiningRoomSensors;
	private ArrayList<Integer> KitchenSensors;
	private ArrayList<Integer> GardenSensors;
	private ArrayList<Integer> HallSensors;
	private ArrayList<Integer> LivingRoomSensors;
	private ArrayList<Integer> BedRoomSensors;
	private ArrayList<Integer> BathRoomSensors;

	private ArrayList<Integer> GarageDevices;
	private ArrayList<Integer> DiningRoomDevices;
	private ArrayList<Integer> KitchenDevices;
	private ArrayList<Integer> GardenDevices;
	private ArrayList<Integer> HallDevices;
	private ArrayList<Integer> LivingRoomDevices;
	private ArrayList<Integer> BedRoomDevices;
	private ArrayList<Integer> BathRoomDevices;
	
	
	
	public boolean isGarage() {
		return Garage;
	}
	public void setGarage(boolean garage) {
		Garage = garage;
	}
	public boolean isDiningRoom() {
		return DiningRoom;
	}
	public void setDiningRoom(boolean diningRoom) {
		DiningRoom = diningRoom;
	}
	public boolean isKitchen() {
		return Kitchen;
	}
	public void setKitchen(boolean kitchen) {
		Kitchen = kitchen;
	}
	public boolean isGarden() {
		return Garden;
	}
	public void setGarden(boolean garden) {
		Garden = garden;
	}
	public boolean isHall() {
		return Hall;
	}
	public void setHall(boolean hall) {
		Hall = hall;
	}
	public boolean isLivingRoom() {
		return LivingRoom;
	}
	public void setLivingRoom(boolean livingRoom) {
		LivingRoom = livingRoom;
	}
	public boolean isBedRoom() {
		return BedRoom;
	}
	public void setBedRoom(boolean bedRoom) {
		BedRoom = bedRoom;
	}
	public boolean isBathRoom() {
		return BathRoom;
	}
	public void setBathRoom(boolean bathRoom) {
		BathRoom = bathRoom;
	}
	public ArrayList<Integer> getGarageSensors() {
		return GarageSensors;
	}
	public void setGarageSensors(ArrayList<Integer> garageSensors) {
		GarageSensors = garageSensors;
	}
	public ArrayList<Integer> getDiningRoomSensors() {
		return DiningRoomSensors;
	}
	public void setDiningRoomSensors(ArrayList<Integer> diningRoomSensors) {
		DiningRoomSensors = diningRoomSensors;
	}
	public ArrayList<Integer> getKitchenSensors() {
		return KitchenSensors;
	}
	public void setKitchenSensors(ArrayList<Integer> kitchenSensors) {
		KitchenSensors = kitchenSensors;
	}
	public ArrayList<Integer> getGardenSensors() {
		return GardenSensors;
	}
	public void setGardenSensors(ArrayList<Integer> gardenSensors) {
		GardenSensors = gardenSensors;
	}
	public ArrayList<Integer> getHallSensors() {
		return HallSensors;
	}
	public void setHallSensors(ArrayList<Integer> hallSensors) {
		HallSensors = hallSensors;
	}
	public ArrayList<Integer> getLivingRoomSensors() {
		return LivingRoomSensors;
	}
	public void setLivingRoomSensors(ArrayList<Integer> livingRoomSensors) {
		LivingRoomSensors = livingRoomSensors;
	}
	public ArrayList<Integer> getBedRoomSensors() {
		return BedRoomSensors;
	}
	public void setBedRoomSensors(ArrayList<Integer> bedRoomSensors) {
		BedRoomSensors = bedRoomSensors;
	}
	public ArrayList<Integer> getBathRoomSensors() {
		return BathRoomSensors;
	}
	public void setBathRoomSensors(ArrayList<Integer> bathRoomSensors) {
		BathRoomSensors = bathRoomSensors;
	}
	public ArrayList<Integer> getGarageDevices() {
		return GarageDevices;
	}
	public void setGarageDevices(ArrayList<Integer> garageDevices) {
		GarageDevices = garageDevices;
	}
	public ArrayList<Integer> getDiningRoomDevices() {
		return DiningRoomDevices;
	}
	public void setDiningRoomDevices(ArrayList<Integer> diningRoomDevices) {
		DiningRoomDevices = diningRoomDevices;
	}
	public ArrayList<Integer> getKitchenDevices() {
		return KitchenDevices;
	}
	public void setKitchenDevices(ArrayList<Integer> kitchenDevices) {
		KitchenDevices = kitchenDevices;
	}
	public ArrayList<Integer> getGardenDevices() {
		return GardenDevices;
	}
	public void setGardenDevices(ArrayList<Integer> gardenDevices) {
		GardenDevices = gardenDevices;
	}
	public ArrayList<Integer> getHallDevices() {
		return HallDevices;
	}
	public void setHallDevices(ArrayList<Integer> hallDevices) {
		HallDevices = hallDevices;
	}
	public ArrayList<Integer> getLivingRoomDevices() {
		return LivingRoomDevices;
	}
	public void setLivingRoomDevices(ArrayList<Integer> livingRoomDevices) {
		LivingRoomDevices = livingRoomDevices;
	}
	public ArrayList<Integer> getBedRoomDevices() {
		return BedRoomDevices;
	}
	public void setBedRoomDevices(ArrayList<Integer> bedRoomDevices) {
		BedRoomDevices = bedRoomDevices;
	}
	public ArrayList<Integer> getBathRoomDevices() {
		return BathRoomDevices;
	}
	public void setBathRoomDevices(ArrayList<Integer> bathRoomDevices) {
		BathRoomDevices = bathRoomDevices;
	}

	
}
