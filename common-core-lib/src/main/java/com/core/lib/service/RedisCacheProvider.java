package com.core.lib.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@Log4j2
public class RedisCacheProvider {

    private final RedisTemplate<String, String> redisTemplate;
    private final HashOperations<String, String, String> hashOperations;

    @Autowired
    public RedisCacheProvider(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    /**
     * Add data into Redis hash.
     */
    public void addData(String hashName, String key, String data) {
        try {
            log.info("Adding Data in hashName [{}] for key [{}] into Cache", hashName, key);
            hashOperations.put(hashName, key, data);
        } catch (RedisConnectionFailureException ex) {
            log.error("Redis connection failed while adding data: {}", ex.getMessage(), ex);
            throw ex;
        } catch (DataAccessException ex) {
            log.error("Redis DataAccessException while adding data: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * Fetch single entry from Redis hash.
     */
    public Optional<String> getData(String hashName, String key) {
        try {
            log.debug("Fetching Data using hashName [{}] for key [{}] from Cache", hashName, key);
            return Optional.ofNullable(hashOperations.get(hashName, key));
        } catch (Exception ex) {
            log.error("Error fetching data from Redis: {}", ex.getMessage(), ex);
            return Optional.empty();
        }
    }

    /**
     * Fetch all entries from Redis hash.
     */
    public Map<String, String> getAllData(String hashName) {
        try {
            log.debug("Fetching All Data using hashName [{}] from Cache", hashName);
            return hashOperations.entries(hashName);
        } catch (Exception ex) {
            log.error("Error fetching all data from Redis: {}", ex.getMessage(), ex);
            return Collections.emptyMap();
        }
    }

    /**
     * Update (same as put) data in Redis hash.
     */
    public void updateData(String hashName, String key, String data) {
        log.info("Updating Data in hashName [{}] for key [{}] in Cache", hashName, key);
        addData(hashName, key, data); // reuse addData for consistency
    }

    /**
     * Delete a key from Redis hash.
     */
    public void deleteData(String hashName, String key) {
        try {
            log.info("Deleting Data in hashName [{}] for key [{}] in Cache", hashName, key);
            hashOperations.delete(hashName, key);
        } catch (Exception ex) {
            log.error("Error deleting data from Redis: {}", ex.getMessage(), ex);
        }
    }

    /**
     * Check if hash contains a given key.
     */
    public boolean ifHasKey(String hashName, String key) {
        try {
            log.debug("Checking if hashName [{}] has key [{}] in Cache", hashName, key);
            return hashOperations.hasKey(hashName, key);
        } catch (Exception ex) {
            log.error("Error checking if key exists in Redis: {}", ex.getMessage(), ex);
            return false;
        }
    }

    /**
     * Set expiry for a Redis hash.
     */
    public void setExpiry(String hashName, Duration ttl) {
        try {
            log.info("Setting expiry of [{}] seconds on hashName [{}]", ttl.getSeconds(), hashName);
            redisTemplate.expire(hashName, ttl);
        } catch (Exception ex) {
            log.error("Error setting expiry on Redis key: {}", ex.getMessage(), ex);
        }
    }

    /**
     * Clear entire hash.
     */
    public void clearHash(String hashName) {
        try {
            log.warn("Clearing all data for hashName [{}]", hashName);
            redisTemplate.delete(hashName);
        } catch (Exception ex) {
            log.error("Error clearing Redis hash [{}]: {}", hashName, ex.getMessage(), ex);
        }
    }
}