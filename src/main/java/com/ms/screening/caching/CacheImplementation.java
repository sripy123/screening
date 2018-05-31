package com.ms.screening.caching;

import java.lang.ref.SoftReference;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class CacheImplementation implements Cache {

    private static final int CACHE_CLEANUP_IN_SECS = 5;
    private static final int TIME_TO_LIVE_IN_SECS = 10;

    private final ConcurrentHashMap<String, SoftReference<CacheObject>> cache = new ConcurrentHashMap<>();

    public CacheImplementation() {
        Thread demon = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(CACHE_CLEANUP_IN_SECS * 1000);
                    cache.entrySet().removeIf(entry -> 
                    							Optional.ofNullable(entry.getValue()).
                    								map(SoftReference::get).
                    								map(CacheObject::isExpired).
                    								orElse(false));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        demon.setDaemon(true);
        demon.start();
    }

    @Override
    public void add(String key, Object value) {
        if (Objects.isNull(key)) {
            return;
        }
        
        if (Objects.isNull(value)) {
            cache.remove(key);
        } else {
            long expiryTime = System.currentTimeMillis() + (TIME_TO_LIVE_IN_SECS*1000);
            cache.put(key, new SoftReference<>(new CacheObject(value, expiryTime)));
        }
    }

    @Override
    public void remove(String key) {
        cache.remove(key);
    }

    @Override
    public Object get(String key) {
        return Optional.ofNullable(cache.get(key))
        		.map(SoftReference::get)
        		.filter(cacheObject -> !cacheObject.isExpired())
        		.map(CacheObject::getValue)
        		.orElse(null);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    /**
     * Inner static CacheObject class
     * @author Sriram
     *
     */
     private static class CacheObject {
    	private Object value;
    	private long time;

    	CacheObject(Object value, long time) {
    		this.value = value;
    		this.time = time;
    	}

    	boolean isExpired() {
    		return System.currentTimeMillis() > time;
    	}

    	public Object getValue() {
    		return value;
    	}

    }
    
}

