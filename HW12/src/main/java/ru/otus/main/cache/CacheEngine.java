package ru.otus.main.cache;

/**
 * Created by tully.
 */
public interface CacheEngine<K, V> {

    public void put(K key, V value);

    V get(K key);

    int getHitCount();

    int getMissCount();

    void dispose();
}
