package com.alex.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {
    /**
     * save with expired time
     */
    void set(String key, Object value, long time);

    /**
     * save
     */
    void set(String key, Object value);

    /**
     * get
     */
    Object get(String key);

    /**
     * delete
     */
    Boolean del(String key);

    /**
     * batch delete
     */
    Long del(List<String> keys);

    /**
     * set expired time
     */
    Boolean expire(String key, long time);

    /**
     * get expired time
     */
    Long getExpire(String key);

    /**
     * check if a key exists
     */
    Boolean hasKey(String key);

    /**
     * increment by delta
     */
    Long incr(String key, long delta);

    /**
     * decrement by delta
     */
    Long decr(String key, long delta);

    /**
     * get hash type value
     */
    Object hGet(String key, String hashKey);

    /**
     * add value to hash structure
     */
    Boolean hSet(String key, String hashKey, Object value, long time);

    /**
     * add value to hash structure
     */
    void hSet(String key, String hashKey, Object value);

    /**
     * get whole hash structure
     */
    Map<Object, Object> hGetAll(String key);

    /**
     * reset the hash structure
     */
    Boolean hSetAll(String key, Map<String, Object> map, long time);

    /**
     * set hash structure
     */
    void hSetAll(String key, Map<String, ?> map);

    /**
     * delete property in
     */
    void hDel(String key, Object... hashKey);

    /**
     * check if a hashkey exists
     */
    Boolean hHasKey(String key, String hashKey);

    /**
     * increment a value in hash
     */
    Long hIncr(String key, String hashKey, Long delta);

    /**
     * decrement a value in hash
     */
    Long hDecr(String key, String hashKey, Long delta);

    /**
     * get a set object
     */
    Set<Object> sMembers(String key);

    /**
     * add values to set
     */
    Long sAdd(String key, Object... values);

    /**
     * add values to set with expired date
     */
    Long sAdd(String key, long time, Object... values);

    /**
     * is a member in set
     */
    Boolean sIsMember(String key, Object value);

    /**
     * get set's size
     */
    Long sSize(String key);

    /**
     * delete a value in set
     */
    Long sRemove(String key, Object... values);

    /**
     * get list values
     */
    List<Object> lRange(String key, long start, long end);

    /**
     * get list length
     */
    Long lSize(String key);

    /**
     * get list value by index
     */
    Object lIndex(String key, long index);

    /**
     * add value to list
     */
    Long lPush(String key, Object value);

    /**
     * add a value to list
     */
    Long lPush(String key, Object value, long time);

    /**
     * list batch add
     */
    Long lPushAll(String key, Object... values);

    /**
     * list batch add with expired date
     */
    Long lPushAll(String key, Long time, Object... values);

    /**
     * remove a value from a list
     */
    Long lRemove(String key, long count, Object value);
}
