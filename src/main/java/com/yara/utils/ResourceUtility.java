package com.yara.utils;

import java.util.Properties;
import java.util.ResourceBundle;
import org.apache.commons.lang3.StringUtils;

public class ResourceUtility {

  ResourceBundle resourceBundle;
  private Properties properties;

  public ResourceUtility(String fileName) {
    this.resourceBundle = ResourceBundle.getBundle(fileName);
  }

  public String getResource(String resourceKey) {
    return this.resourceBundle.getString(resourceKey);
  }

  public boolean hasProperty(String key) {
    return StringUtils.isNotBlank(properties.getProperty(key));
  }

}
