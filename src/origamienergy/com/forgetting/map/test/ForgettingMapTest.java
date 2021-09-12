package origamienergy.com.forgetting.map.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
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

	@Test
	public void testAddThrowsNullPointerExceptionWhenKeyIsNull() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			FORGETTING_MAP.add(null, "value");
		});
	}

	@Test
	public void testAddThrowsNullPointerExceptionWhenValueIsNull() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			FORGETTING_MAP.add("key", null);
		});
	}

	@Test 
	public void testAddAndFind() {
		FORGETTING_MAP.add("key", "value");
		final String value = FORGETTING_MAP.find("key");
		assertNotNull(value);
		assertEquals("value", value);
	}
	
	@Test
    public void testFindReturnsNullWhenKeyValueDoesNotExist() {
        assertNull(FORGETTING_MAP.find("nullkey"));
    }

}
