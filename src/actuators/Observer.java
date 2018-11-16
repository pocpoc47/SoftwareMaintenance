package actuators;

import dto.Dto;
import exceptions.SearchException;
import exceptions.VariabilityException;

public interface Observer {
	public void update(Dto dto);
}
