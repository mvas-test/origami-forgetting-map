package origamienergy.com.forgetting.map.main;

import java.util.concurrent.ConcurrentHashMap;

public final class ForgettingMap<K, V> extends ConcurrentHashMap<K, V> {

	private final int maxAssociations;
	
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

}