package com.mt.mng;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Editor {

	private static String OS = System.getProperty("os.name").toLowerCase();

	public String loadLog() throws IOException {

		String fileName = "/etc/wpa_supplicant/wpa_supplicant.conf";
		File file = new File(fileName);

		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader streamReader = new BufferedReader(isr);
		StringBuilder responseStrBuilder = new StringBuilder();
		String inputStr;
		while ((inputStr = streamReader.readLine()) != null)
			responseStrBuilder.append(inputStr);

		System.out.println(responseStrBuilder.toString());
		return responseStrBuilder.toString();

	}

	public String getWifiSettings() throws IOException {
		String fileName = "/etc/wpa_supplicant/wpa_supplicant.conf";
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader streamReader = new BufferedReader(isr);
		StringBuilder responseStrBuilder = new StringBuilder();
		String inputStr;
		while ((inputStr = streamReader.readLine()) != null) {
			responseStrBuilder.append(inputStr + "\n");
		}
		isr.close();
		fis.close();
		return responseStrBuilder.toString();

	}

	public String setWifi(String ssid, String pass) throws IOException {

		String fileName = "/etc/wpa_supplicant/wpa_supplicant.conf";
		File file = new File(fileName);

		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader streamReader = new BufferedReader(isr);
		StringBuilder responseStrBuilder = new StringBuilder();
		String inputStr;
		while ((inputStr = streamReader.readLine()) != null) {
			if (inputStr.contains("ssid=\"")) {
				inputStr = "	ssid=\"" + ssid + "\"";
			}
			if (inputStr.contains("psk=\"")) {
				inputStr = "	psk=\"" + pass + "\"";
			}
			responseStrBuilder.append(inputStr + "\n");
		}

		try (PrintWriter out = new PrintWriter(fileName)) {
			out.println(responseStrBuilder.toString());
			out.close();
		}

		isr.close();
		fis.close();
		return responseStrBuilder.toString();

	}

	public String rebootWifi() {
		if (isSolaris() || isUnix()) {
			Runtime runtime = Runtime.getRuntime();
			try {
				Process process = runtime.exec("wpa_cli -i wlan0 reconfigure");
				
				InputStream is = process.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				StringBuilder responseStrBuilder = new StringBuilder();

				String line = null;
				while ((line = reader.readLine()) != null) {
				   responseStrBuilder.append(line + "\n");
				}
				reader.close();
				is.close();
				System.out.println(responseStrBuilder.toString());
				return responseStrBuilder.toString();
			} catch (IOException e) {
				e.printStackTrace();
				return "Wifi error reboot !";
			}
		}
		return "Wifi error reboot !";

	}

	public static boolean isWindows() {
		return OS.contains("win");
	}

	public static boolean isMac() {
		return OS.contains("mac");
	}

	public static boolean isUnix() {
		return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
	}

	public static boolean isSolaris() {
		return OS.contains("sunos");
	}
}
