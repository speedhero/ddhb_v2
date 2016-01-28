package com.huatek.zip;

import com.huatek.zip.ZipEntry;
import com.huatek.zip.ZipInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ZipFile {
	public static void unzipFile(String directory, File zip) throws Exception {
		try {
			ZipInputStream zis = new ZipInputStream(new FileInputStream(zip));
			ZipEntry ze = zis.getNextEntry();
			File parent = new File(directory);
			if (!parent.exists() && !parent.mkdirs()) { throw new Exception("������ѹĿ¼ \"" + parent.getAbsolutePath()
					+ "\" ʧ��"); }

			while (ze != null) {
				String name = ze.getName();
				File child = new File(parent, name);
				FileOutputStream output = new FileOutputStream(child);
				byte[] buffer = new byte[10240];
				boolean bytesRead = false;

				int bytesRead1;
				while ((bytesRead1 = zis.read(buffer)) > 0) {
					output.write(buffer, 0, bytesRead1);
				}

				output.flush();
				output.close();
				ze = zis.getNextEntry();
			}

			zis.close();
		} catch (IOException var10) {
			;
		}

	}
}
