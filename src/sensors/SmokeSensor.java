package sensors;

public class SmokeSensor implements Sensor {

	private boolean smokeDetected = false;
	
	public void setSmokeDetected(boolean smokeDetected) {
		this.smokeDetected = smokeDetected;
	}
	
	public boolean isSmokeDetected() {
		return smokeDetected;
	}

	public void smell()
	{
		this.setSmokeDetected(true);
		//We could implement some mecanism to randomize smoke.
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
