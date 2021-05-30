package com.kdyun.common.utils.property;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
*	spring中 静态类获取 配置参数
*	
*/
@Configuration
public class PropertiesBean implements EnvironmentAware, BeanPostProcessor {
    private static Log logger = LogFactory.getLog(PropertiesBean.class);
    private Environment environment;
    private static PropertiesBean propertiesBean;

    public PropertiesBean() {
        logger.info("Construct EnvironmentProperties");
        propertiesBean = this;
    }

    public static PropertiesBean getInstance() {
        return propertiesBean;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Environment getEnvironment() {
        return this.environment;
    }

    public String[] getActiveProfiles() {
        return this.environment.getActiveProfiles();
    }

    public String[] getDefaultProfiles() {
        return this.environment.getDefaultProfiles();
    }

    public boolean acceptsProfiles(String... profiles) {
        return this.environment.acceptsProfiles(profiles);
    }

    public boolean containsProperty(String key) {
        return this.environment.containsProperty(key);
    }

    public String getProperty(String key) {
        return this.environment.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return this.environment.getProperty(key, defaultValue);
    }

    public <T> T getProperty(String key, Class<T> targetType) {
        return this.environment.getProperty(key, targetType);
    }

    public <T> T getProperty(String key, Class<T> targetType, T defaultValue) {
        return this.environment.getProperty(key, targetType, defaultValue);
    }

    public String getRequiredProperty(String key) {
        return this.environment.getRequiredProperty(key);
    }

    public <T> T getRequiredProperty(String key, Class<T> targetType) throws IllegalStateException {
        return this.environment.getRequiredProperty(key, targetType);
    }

    public String resolvePlaceholders(String text) {
        return this.environment.resolvePlaceholders(text);
    }

    public String resolveRequiredPlaceholders(String text) {
        return this.environment.resolveRequiredPlaceholders(text);
    }
}