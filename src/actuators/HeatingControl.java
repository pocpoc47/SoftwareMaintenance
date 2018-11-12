package actuators;

import java.util.ArrayList;

import devices.CentralHeating;
import dto.Dto;

public class HeatingControl implements Observer {
	
	private double desiredTemp;
	private double actualTemp;
	private ArrayList <CentralHeating> heaters;
	
	public HeatingControl(ArrayList<CentralHeating> heaters, double desiredTemp)
	{
		this.heaters = heaters;
		this.desiredTemp = desiredTemp;
	}

	public void keepHouseTemp()
	{
		if(actualTemp<desiredTemp)
		{
			for(CentralHeating h : heaters)
			{
				h.turnOn();
			}
		}
		else
		{
			for(CentralHeating h : heaters)
			{
				h.turnOff();
			}
		}
	}

	@Override
	public void update(Dto dto) {
		switch(dto.getAction()) {
			case Dto.TEMP_CHANGE:
				actualTemp = (double)dto.getData();
				keepHouseTemp();
		}
		
	}
	
	public double getDesiredTemp() {
		return desiredTemp;
	}

	public void setDesiredTemp(double desiredTemp) {
		this.desiredTemp = desiredTemp;
	}

	public double getActualTemp()
	{
		return actualTemp;
	}

	
}
