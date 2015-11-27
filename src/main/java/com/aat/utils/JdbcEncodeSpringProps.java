package com.aat.utils;

import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @time 2014-01-05
 * @author xingle
 * @version v1.0
 * @function 对properties的数据进行解密读取
 * @info java spring 技术，继承PropertyPlaceholderConfigurer，在配置加载文件的时候调用此类即可
 *
 */
public class JdbcEncodeSpringProps extends PropertyPlaceholderConfigurer {

	protected void processProperties(
			ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		Set<Entry<Object, Object>> entrys = props.entrySet();
		for (Entry<Object, Object> e : entrys) {
			String key = (String) e.getKey();
			String value = (String) e.getValue();
			if (key.contains("username") || key.contains("password")) {
				try {
					props.setProperty(key, Atools.jiemi(value));
				} catch (Exception e1) {
				}
			}
		}
		super.processProperties(beanFactory, props);// 调用父方法
	}

}