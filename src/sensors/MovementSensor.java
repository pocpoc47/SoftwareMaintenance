package sensors;

import java.util.Date;

public class MovementSensor implements Sensor {
	
	private Date lastMovementDetectedTime; //min
	
	public MovementSensor(Date date) {
		lastMovementDetectedTime = date;
	}

	public Date getLastMovementDetectedTime() {
		return lastMovementDetectedTime;
	}
	public void setLastMovementDetectedTime(Date lastMovementDetectedTime) {
		this.lastMovementDetectedTime = lastMovementDetectedTime;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
