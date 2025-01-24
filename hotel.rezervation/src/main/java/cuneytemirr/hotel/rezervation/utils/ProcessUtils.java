package cuneytemirr.hotel.rezervation.utils;

import java.util.Scanner;

public class ProcessUtils {
	
	public Scanner sc;
	
	private static ProcessUtils processUtils;
	private ProcessUtils() {
		sc = new Scanner(System.in);
	}
	public static ProcessUtils getProcessUtilsInstance() {
		if (processUtils == null) {
			processUtils = new ProcessUtils();
		}
		return processUtils;
	}
	

}
