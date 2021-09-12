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

		assertEquals(1, FORGETTING_MAP.getFindTracker().get("key").get());		
	}

	@Test
	public void testFindReturnsNullWhenKeyValueDoesNotExist() {
		assertNull(FORGETTING_MAP.find("nullkey"));
	}

	@Test
	public void testAddingMoreAssociationsThanAllowedRemovesLeastUsed() {
		FORGETTING_MAP.add("key1", "value1");
		FORGETTING_MAP.find("key1");
		FORGETTING_MAP.find("key1");
		FORGETTING_MAP.add("key2", "value2");
		FORGETTING_MAP.find("key2");
		FORGETTING_MAP.find("key2");
		FORGETTING_MAP.add("key3", "value3");
		FORGETTING_MAP.find("key3");
		FORGETTING_MAP.add("key4", "value4");
		FORGETTING_MAP.find("key4");
		FORGETTING_MAP.find("key4");
		FORGETTING_MAP.add("key5", "value5");
		FORGETTING_MAP.find("key5");
		FORGETTING_MAP.find("key5");
		FORGETTING_MAP.add("key6", "value6");
		FORGETTING_MAP.find("key6");
		FORGETTING_MAP.find("key6");

		final String value = FORGETTING_MAP.find("key3");
		assertNull(value);

		final String nvalue = FORGETTING_MAP.find("key6");
		assertNotNull(nvalue);
		assertEquals("value6", nvalue);
	}

}
