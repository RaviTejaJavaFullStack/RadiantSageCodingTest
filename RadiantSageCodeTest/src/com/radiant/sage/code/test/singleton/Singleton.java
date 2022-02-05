package com.radiant.sage.code.test.singleton;

public class Singleton {

	private static Singleton singleInstance = null;

	public String s;

	private Singleton() {
		s = "Singleton class";
	}

	public static Singleton getInstance() {
		if (singleInstance == null) {
			singleInstance = new Singleton();
		}
		return singleInstance;
	}
}
