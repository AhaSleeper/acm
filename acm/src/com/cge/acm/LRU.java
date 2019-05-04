package com.cge.acm;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRU<K,V> {
    private final int CACHE_SIZE;
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    private LinkedHashMap<K, V> map;
    private LRU(int cacheSize){
        this.CACHE_SIZE = cacheSize;
        int capacity = (int)Math.ceil(CACHE_SIZE/DEFAULT_LOAD_FACTOR) + 1;
        map = new LinkedHashMap<K,V>(capacity, DEFAULT_LOAD_FACTOR, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> entry){
                return size()>CACHE_SIZE;
            }
        };
    }

    public synchronized void put(K key, V value){
        map.put(key, value);
    }

    public synchronized V get(K key){
        return map.get(key);
    }
    @Override
    public String toString(){
        return map.toString();
    }

    public static void main(String[] args){
        LRU<Integer, Integer> lru = new LRU(5);
        lru.put(1,1);
        System.out.println(lru.toString());
        lru.put(2,2);
        System.out.println(lru.toString());
        lru.put(3,3);
        System.out.println(lru.toString());
        lru.put(4,4);
        System.out.println(lru.toString());
        lru.put(5,5);
        System.out.println(lru.toString());
        lru.put(1,1);
        System.out.println(lru.toString());
    }
}
