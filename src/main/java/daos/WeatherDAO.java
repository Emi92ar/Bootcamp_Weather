package daos;

import org.springframework.stereotype.Component;

public interface WeatherDAO <T> {
	void Insert(T o);
}
