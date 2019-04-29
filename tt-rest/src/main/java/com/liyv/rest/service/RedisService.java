package com.liyv.rest.service;

public interface RedisService {

    public String set(String key, String value);

    public String get(String key);

    public byte[] get(byte[] bytes);

    public long hset(String hash, String key, String value);

    public String hget(String hash, String key);

    public long hset(byte[] hashs, byte[] keys, byte[] values);

    public byte[] hget(byte[] hashs, byte[] keys);

    public long hDel(String hash, String key);

    public long del(String key);

    public long ttl(String key);

    public long expire(String key, int seconds);
}
