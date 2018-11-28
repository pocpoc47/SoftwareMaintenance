package home;

public class RoomFactory {
	public RoomFactory() {
	}
	
	public Room getRoom(String type) {
		Room room = null;
		switch (type) {
		case "HALL":
			room = new Hall();
			break;
		case "KITCHEN":
			room = new Kitchen();
			break;
		case "GARAGE":
			room = new Garage();
			break;
		case "BATHROOM":
			room = new BathRoom();
			break;
		case "GARDEN":
			room = new Garden();
			break;
		case "LIVINGROOM":
			room = new LivingRoom();
			break;
		case "DININGROOM":
			room = new DiningRoom();
			break;
		default:
			System.out.println("UNKNOWN TYPE: " + type);
			break;
		}
		
		return room;
	}
}
