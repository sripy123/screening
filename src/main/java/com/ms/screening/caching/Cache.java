package com.ms.screening.caching;

/**
 * 
 * @author Sriram
 *
 */
public interface Cache {
	/**
	 * Add value to the Cache
	 * @param key
	 * @param value
	 */
	 public void add(String key, Object value);
	 /**
	  * Remove the value from Cache
	  * @param key
	  */
	 public void remove(String key);
	 /**
	  * Get the Object from Cache.
	  * @param key
	  * @return
	  */
	 public Object get(String key);
	 /**
	  * Clear all the objects from Cache.
	  */
	 public void clear();
	 
}
