package JavaClass.lab2;

import java.util.Scanner;

@SuppressWarnings("resource")
public class Efficiency {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter_vehicle_make:_");
		String make = reader.nextLine();
		System.out.print("Enter_vehicle_model:_");
		String model = reader.nextLine();
		System.out.print("Enter_kilometres_travelled:_");
		double travelled = Double.valueOf(reader.nextLine());
		System.out.print("Enter_litres_of_fuel_used:_");
		double fuel = Double.valueOf(reader.nextLine());
		System.out.print("Fuel_efficiency_for_a_" + make + "_" + model + ":_");
		System.out.printf("%.2f", fuel * 100 / travelled);
		System.out.print("_litres/100_km");
	}
}
