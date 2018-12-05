package liwh.security.environment;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author: Liwh
 * @ClassName: AbstractEnvironmentPostProcessor
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-03 12:14 PM
 */
public class AbstractEnvironmentPostProcessor {

    protected final Logger log = LoggerFactory.getLogger(AbstractEnvironmentPostProcessor.class);
    protected final String PROPERTY_SOURCES_NAME = "defultPropertySources";

    public void addEnvironmentProperties(MutablePropertySources propertySources, Map<String, Object> map) {
        MapPropertySource target = null;
        if (propertySources.contains(PROPERTY_SOURCES_NAME)) {
            PropertySource<?> source = propertySources.get(PROPERTY_SOURCES_NAME);
            if (source instanceof MapPropertySource) {
                target = (MapPropertySource) source;
                Map<String, Object> property = (Map<String, Object>) target.getProperty(PROPERTY_SOURCES_NAME);
                Set<String> keySet = map.keySet();
                Iterator<String> iterator = keySet.iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    property.put(key, map.get(key));
                }
            }
        }

        if (target == null) {
            target = new MapPropertySource(PROPERTY_SOURCES_NAME, map);
            log.info("DefaultProperties init ,name :{}", PROPERTY_SOURCES_NAME);
        }

        if (!propertySources.contains(PROPERTY_SOURCES_NAME)) {
            propertySources.addLast(target);
        }

    }
}
