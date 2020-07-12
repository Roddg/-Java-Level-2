package ru.specialist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Program {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Starting process...");
		// bash
		// ls
		Process p = Runtime.getRuntime().exec("cmd.exe /C dir");
		//p.getOutputStream().write(arg0);
		
		
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(p.getInputStream(), Charset.forName("cp866")));
		String s;
		while ( (s = reader.readLine()) != null) {
			System.out.println(s);
		} 
		
		int retCode = p.waitFor();
		//int retCode = p.exitValue();
		
		System.out.printf("Return code: %d\n", retCode);
		
		System.exit(retCode);

	}

}
