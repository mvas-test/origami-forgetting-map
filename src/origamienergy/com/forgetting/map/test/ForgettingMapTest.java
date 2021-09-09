package origamienergy.com.forgetting.map.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import origamienergy.com.forgetting.map.main.ForgettingMap;

class ForgettingMapTest {

	ForgettingMap<String, String> FORGETTING_MAP;

	@BeforeEach
	void setUp() throws Exception {
		FORGETTING_MAP = new ForgettingMap<>(5);
	}

	@Test
	public void testCreatingForgettingMapWithMaximumAssociations() {
		assertEquals(5, FORGETTING_MAP.getMaxAssociations());
	}

}
