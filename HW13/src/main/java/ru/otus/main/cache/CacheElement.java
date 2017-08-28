package ru.otus.main.cache;

import java.lang.ref.SoftReference;

public class CacheElement<V> {
    private final SoftReference<V> reference;
    private final long creationTime;
    private long lastAccessTime;


    public CacheElement(V value) {
        this.reference = new SoftReference<V>(value);
        this.creationTime = getCurrentTime();
        this.lastAccessTime = getCurrentTime();
    }

    protected long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public long getCreationTime() {
        return creationTime;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setAccessed() {
        lastAccessTime = getCurrentTime();
    }

    public SoftReference<V> getReference() {
        return reference;
    }
}
