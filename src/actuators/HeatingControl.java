package actuators;

import java.util.ArrayList;

import devices.CentralHeating;
import dto.Dto;
import exceptions.VariabilityException;

public class HeatingControl implements Observer {
	
	private double desiredTemp;
	private double actualTemp;
	private ArrayList <CentralHeating> heaters;
	
	public HeatingControl(ArrayList<CentralHeating> heaters, double desiredTemp)
	{
		this.heaters = heaters;
		this.desiredTemp = desiredTemp;
	}

	public boolean keepHouseTemp()
	{
		if(heaters!=null)
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
			return true;
		}
		else {
			System.out.println("No heaters\n");
			return false;
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
