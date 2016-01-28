package com.huatek.framework.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileAnalyse {
	public static void main(String[] args) throws IOException {
		String dirs = "c:\\";
		File director = new File(dirs);
		FileWriter requestContentFile = new FileWriter(dirs + "huatek_entity_memory.txt");
		File[] var7;
		int var6 = (var7 = director.listFiles()).length;

		for (int var5 = 0; var5 < var6; ++var5) {
			File file = var7[var5];
			if (file.getName().startsWith("jvminfo20130603.")) {
				BufferedReader input = new BufferedReader(new FileReader(file));
				String line = null;

				while ((line = input.readLine()) != null) {
					if (line.indexOf("com.huatek.") > 0) {
						line = line.substring(line.indexOf(":") + 1);
						requestContentFile.write(line);
					}
				}

				input.close();
			}
		}

		requestContentFile.close();
	}
}
