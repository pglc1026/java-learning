package org.java.redis.redistest.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author Shengjun Liu
 * @since V1.0.0
 */
@Component
public final class CacheCheckUtils {
    private static final String PROJECT_NAME = "PLA:OTP:";
    private static final String HEALTH_PREFIX = PROJECT_NAME + "HEALTH";
    private static final String SMS_PREFIX = PROJECT_NAME + "SMS-VC";
    private static final String IMAGE_PREFIX = PROJECT_NAME + "IMAGE-VC";
    private static final String UI_PREFIX = PROJECT_NAME + "USER-INFO";
    private static final String UI_TP_PREFIX = PROJECT_NAME + "USER-TP-INFO";
    private static final String REQUEST_PREFIX = PROJECT_NAME + "REQUEST-CACHE";

    @Autowired
    @Qualifier("redisStringTemplate")
    private RedisTemplate<String, String> redis;

    /**
     * REDIS 健康检测.
     * 
     * @return
     */
    public final boolean healthCheck() {
        final String value = "HEALTH"; // 当前值
        set(HEALTH_PREFIX, value, Duration.ofSeconds(5)); // 添加
        final String get = get(HEALTH_PREFIX); // 获取
        delete(HEALTH_PREFIX); // 删除
        return value.equals(get);
    }

    /**
     * 短信验证码前缀.
     * 
     * @param phone
     * @return
     */
    public final static String smsVCodeKey(final String phonePrefix, final String phone) {
        return String.format("%s:%s-%s", SMS_PREFIX, phonePrefix, phone);
    }

    /**
     * 图片验证码前缀.
     * 
     * @param vc
     * @return
     */
    public final static String imageVCodeKey(final String vc) {
        return String.format("%s:%s", IMAGE_PREFIX, vc);
    }

    /**
     * 用户信息前缀.
     * 
     * @param username
     * @return
     */
    public final static String userInfoKey(final String username) {
        return String.format("%s:%s", UI_PREFIX, username);
    }

    /**
     * 第三方用户信息前缀.
     * 
     * @param username
     * @return
     */
    public final static String thirdpartyUserInfoKey(final String username) {
        return String.format("%s:%s", UI_TP_PREFIX, username);
    }

    /**
     * 请求数据缓存信息前缀.
     * 
     * @param key
     * @return
     */
    public final static String defaultRequestCacheKey(final String key) {
        return String.format("%s:%s", REQUEST_PREFIX, key);
    }

    /**
     * redis 获取记录.
     * 
     * @param key
     */
    public String get(String key) {
        try {
            return redis.opsForValue().get(key);
        } catch (RedisConnectionFailureException e) {
            synchronized (this) {
                return redis.opsForValue().get(key);
            }
        }
    }

    /**
     * redis 添加记录.
     * 
     * @param key
     * @param value
     * @param timeout 毫秒
     */
    public void set(String key, String value, long timeout) {
        this.set(key, value, Duration.ofMillis(timeout));
    }

    /**
     * redis 添加记录.
     * 
     * @param key
     * @param value
     * @param timeout
     */
    public void set(String key, String value, Duration timeout) {
        try {
            redis.opsForValue().set(key, value, timeout);
        } catch (RedisConnectionFailureException e) {
            synchronized (this) {
                redis.opsForValue().set(key, value, timeout);
            }
        }
    }

    /**
     * redis 删除记录.
     * 
     * @param key
     * @return
     */
    public boolean delete(String key) {
        return redis.delete(key);
    }

}
