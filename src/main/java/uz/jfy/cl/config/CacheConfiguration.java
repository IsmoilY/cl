package uz.jfy.cl.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.util.Objects;

@Slf4j
@Configuration
@EnableCaching
public class CacheConfiguration {

    private static final String HZ_INSTANCE_NAME = "cl";
    private static final int HZ_INSTANCE_PORT = 5701;

    @PreDestroy
    public void destroy() {
        log.info("Started Hazelcast shut down");
        Hazelcast.shutdownAll();
    }

    @Bean
    public CacheManager cacheManager(HazelcastInstance hzInstance) {
        log.info("Starting Hazelcast Cache Manager");
        return new HazelcastCacheManager(hzInstance);
    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
        log.info("Configuring HZ instance");
        var hzInstance = Hazelcast.getHazelcastInstanceByName(HZ_INSTANCE_NAME);
        if (Objects.nonNull(hzInstance)) {
            log.info("HZ instance already exists");
            return hzInstance;
        }

        var config = new Config();
        config.setInstanceName(HZ_INSTANCE_NAME);
        config.getNetworkConfig().setPort(HZ_INSTANCE_PORT);
        config.getNetworkConfig().setPortAutoIncrement(true);
        config.addMapConfig(defaultMapConfig());
        config.addMapConfig(domainMapConfig());

        return Hazelcast.newHazelcastInstance(config);
    }

    private MapConfig defaultMapConfig() {
        var config = new MapConfig("default");
        config.setBackupCount(0);
        config.setEvictionPolicy(EvictionPolicy.LRU);
        config.setMaxSizeConfig(new MaxSizeConfig(0, MaxSizeConfig.MaxSizePolicy.USED_HEAP_SIZE));
        return config;
    }

    private MapConfig domainMapConfig() {
        var config = new MapConfig("cl.domain");
        config.setTimeToLiveSeconds(3600);
        return config;
    }

}
