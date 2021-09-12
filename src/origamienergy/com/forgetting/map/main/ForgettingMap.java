package origamienergy.com.forgetting.map.main;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class ForgettingMap<K, V> extends ConcurrentHashMap<K, V> {

	private final int maxAssociations;

	//to track use of the find() calls
	private Map<K, AtomicInteger> findTracker = new LinkedHashMap<K, AtomicInteger>(); //3

	public ForgettingMap(final int maxAssociations) {
		this.maxAssociations = maxAssociations;
	}

	/**
	 * Returns the maximum x associations at any time
	 *
	 * @return value representing the maximum associations
	 * that states the limit of the {@code ForgettingMap}.
	 */
	public Integer getMaxAssociations() {
		return maxAssociations;
	}

	/**
	 * Returns the FindTracker Map
	 */
	public Map<K, AtomicInteger> getFindTracker() { //3
		return findTracker;
	}

	/**
	 * Maps the specified key to the specified value in this structure.
	 *
	 * @param key Key with which the specified value is to be associated.
	 * @param value Value to be associated with the specified key.
	 * 
	 *@return the previous value associated with key, or null if there was no mapping for key
	 * 
	 *  @throws NullPointerException - if the specified key is null
	 */

	public final V add(final K key, final V value) {//3
		if (size() >= maxAssociations) {
			remove(removeLeastUsed());
		}
		return put(key, value);
	}

	/**
	 *Returns the value to which the specified key is mapped,or null if this map contains no mapping for the key. 
	 *
	 * @param  the key whose associated value is to be returned
	 * 
	 * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
	 * 
	 *  @throws NullPointerException - if the specified key is null
	 */ 
	public final V find(final K key) {
		final V value = get(key);
		if (value != null) {
			doTrack(key);
		}
		return value;
	}

	/**
	 * Utility tracking operation of when the find
	 * operation is called.
	 *
	 * @param key Used to find the given value.
	 */
	private void doTrack(final K key) {
		if (findTracker.containsKey(key)) {
			findTracker.get(key).getAndIncrement();
		} else {
			findTracker.put(key, new AtomicInteger(1));
		}
	}

	/**
	 * Find all possible occurrences of min value in the findTracker map.
	 */
	private LinkedList<K> findAllfMinValues() {
		final LinkedList<K> leastUsedList = new LinkedList<>();
		int minValue = Collections.min(findTracker.entrySet(), Comparator.comparingInt(i -> i.getValue().get())).getValue().get();
		findTracker.forEach((key, value) -> {
			if (value.intValue() == minValue) {
				leastUsedList.add(key);
			}
		});
		return leastUsedList;
	}

	/**
	 * Remove least used value in the findTracker map.
	 * 
	 * always removes the first value in the list
	 * 
	 */
	private K removeLeastUsed() {
		final LinkedList<K> leastUsed = findAllfMinValues();
		if (leastUsed.isEmpty()) {
			throw new IllegalStateException("Least used value not found.");
		}
		K key = leastUsed.get(0);
		findTracker.remove(key);

		return key;
	}
}