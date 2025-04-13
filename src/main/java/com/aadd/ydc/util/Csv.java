package com.aadd.ydc.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Csv {

	public static List<String[]> readCsv(String filePath, String delimiter) throws IOException {
		List<String[]> rows = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] columns = line.split(delimiter);
				rows.add(columns);
			}
		}
		return rows;
	}

	public static List<String[]> readCsvSinHeader(String filePath, String delimiter) throws IOException {
		List<String[]> rows = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] columns = line.split(Pattern.quote(delimiter));
				rows.add(columns);
			}
		}
		return rows;
	}
}
