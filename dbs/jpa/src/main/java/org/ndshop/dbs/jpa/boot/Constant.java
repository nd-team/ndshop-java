package org.ndshop.dbs.jpa.boot;


import org.ndshop.dbs.jpa.boot.initializer.AppConfig;

public class Constant extends AppConfig {
	public static final String SCAN_APP_PACKAGES = "org.ndshop.dbs.jpa.entity";
	public static final String CACHE_NAME[] = {"queryCache","daoCache","serviceCache"};

}