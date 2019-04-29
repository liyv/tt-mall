package com.liyv.rest.service.impl;

import com.liyv.rest.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisServiceImpl implements RedisService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    JedisPool jedisPool;

    @Override
    public String set(String key, String value) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String result = jedis.set(key, value);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public String get(String key) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String result = jedis.get(key);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public byte[] get(byte[] bytes) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                return jedis.get(bytes);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public long hset(String hash, String key, String value) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                long result = jedis.hset(hash, key, value);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public long hset(byte[] hashs, byte[] keys, byte[] values) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                return jedis.hset(hashs, keys, values);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public String hget(String hash, String key) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String result = jedis.hget(hash, key);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public byte[] hget(byte[] hashs, byte[] keys) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                return jedis.hget(hashs, keys);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public long hDel(String hash, String key) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                return jedis.hdel(hash, key);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public long hDel(byte[] hashs, byte[] keys) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                return jedis.hdel(hashs, keys);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public long del(String key) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                return jedis.del(key);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public long ttl(String key) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                return jedis.ttl(key);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public long expire(String key, int seconds) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                return jedis.expire(key, seconds);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
}
