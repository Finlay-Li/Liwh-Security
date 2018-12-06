package com.liwh.environment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;

/**
 * @author: Liwh
 * @ClassName: DefultMybatisEnvironmentPostProcessor
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-03 12:15 PM
 */
public class DefultMybatisEnvironmentPostProcessor extends AbstractEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment configurableEnvironment, SpringApplication springApplication) {
        MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
        HashMap<String, Object> map = new HashMap<>();
        map.put("mybatis-plus.mapperLocations","classpath:/mybatis/**/*Mapper.xml");
        map.put("mybatis-plus.typeAliasesPackage","com.liwh.dao.model");
        this.addEnvironmentProperties(propertySources,map);
    }
}
