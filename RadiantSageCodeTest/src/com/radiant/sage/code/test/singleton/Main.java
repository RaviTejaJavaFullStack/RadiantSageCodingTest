package com.radiant.sage.code.test.singleton;

public class Main {

	public static void main(String[] args) {
		try {
			Singleton x = Singleton.getInstance();
			Singleton y = Singleton.getInstance();
			Singleton z = Singleton.getInstance();

			System.out.println("Hashcode of x is "+ x.hashCode());
			System.out.println("Hashcode of y is "+ y.hashCode());
			System.out.println("Hashcode of z is "+ z.hashCode());

			if (x == y && y == z) {
				System.out.println("Objects point to the same memory location on the heap i.e, to the same object");
			}
			else {
				System.out.println("Objects do not point to the same memory location on the heap");
			}
		} catch (Exception e) {
			System.out.println("Exception occured inside the Main: " + e.getMessage());
		}
	}
}
