import java.util.Scanner;

class TemperatureSample {
	int month, day, year;
	double temperature;
}

public class TemperaturesByDate {
	public static void main(String[] args) throws Exception {
		String path = "http://academic.udayton.edu/kissock/http/Weather/gsod95-current/CISHANGH.txt";
//		String path = "/txt/avg-daily-temps-with-dates-atx.txt";
		Scanner inFile = new Scanner((new java.net.URL(path)).openStream());
		
		TemperatureSample[] tempDB = new TemperatureSample[10000];
		int numRecords, i = 0;
		
		while (inFile.hasNextInt() && i < tempDB.length) {
			TemperatureSample e = new TemperatureSample();
			e.month = inFile.nextInt();
			e.day	= inFile.nextInt();
			e.year	= inFile.nextInt();
			e.temperature = inFile.nextDouble();
			if (e.temperature == -99)
				continue;
			tempDB[i] = e;
			i++;
		}
		inFile.close();
		numRecords = i;
		
		System.out.println(numRecords + " daily temperatures loaded.");
		
		double total = 0, avg;
		int count = 0;
		for (i = 0; i < numRecords; i++) {
			if (tempDB[i].month == 04) {
				total += tempDB[i].temperature;
				count++;
			}
		}
		
		avg = total / count;
		avg = roundToOneDecimal(avg);
		System.out.print("Average daily temperature over " + count);
		System.out.println(" days in November: " + avg);
		
		System.out.println(tempDB[20].temperature);
	}
	
	public static double roundToOneDecimal(double d) {
		return Math.round(d * 10) / 10.0;
	}
}