package com.api.utility;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Properties;

import com.api.constants.FrameworkConstants;
import com.api.enums.ConfigProperties;

public final class ConfigPropertiesUtils {

	private ConfigPropertiesUtils() {}

	private static Properties property = new Properties();
	private static final Map<String, String> CONFIGMAP = new HashMap<>();

	static {

		try (FileInputStream file = new FileInputStream(FrameworkConstants.getConfigfilepath()))
		{
			property.load(file); // adding file in properties

			// putting values on map
			for(Entry<Object,Object> entry : property.entrySet()) {
				CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim());

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	// method for capturing configEnums
	public static String get(ConfigProperties key) throws Exception {

		if(Objects.isNull(CONFIGMAP.get(key.name().toLowerCase())) || (Objects.isNull(key.name()))) {
			throw new Exception("Property Name: " + key + " is not found. Please check config.properties once again.");
		}
		return CONFIGMAP.get(key.name().toLowerCase());

	}
}
