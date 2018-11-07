package sensors;

public class TemperatureSensor implements Sensor {
	public static int TYPE = 3;
	
	private double temp;
	
	public TemperatureSensor() {
		this.temp = 15.3;
	}
	public TemperatureSensor(double temp) {
		this.temp = temp;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
	
	public double getTemp()
	{
		return temp;
	}
	public void setTemp(double t)
	{
		temp = t;
	}

}
