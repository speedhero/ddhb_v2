package com.huatek.framework.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.texen.util.FileUtil;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtil {
	private static final Log log = LogFactory.getLog(UploadFileUtil.class);
	
	public static String uploadFile(HttpServletRequest request, HttpServletResponse response, String fileUploadPath,
			String maxSize, String fileType) {
		String tempPath = "";
		String filePath = fileUploadPath;
		FileUtil.mkdir(tempPath);
		FileUtil.mkdir(fileUploadPath);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(5120);
		factory.setRepository(new File(tempPath));
		ServletFileUpload upload = new ServletFileUpload(factory);
		if (maxSize != null && !"".equals(maxSize.trim())) {
			upload.setSizeMax((Integer.valueOf(maxSize).longValue() * 1024 * 1024));
		}

		try {
			List<FileItem> fileList = upload.parseRequest(request);
			Iterator<FileItem> itFile = fileList.iterator();

			FileItem item;
			do {
				if (!itFile.hasNext()) { return "fileError"; }

				item = (FileItem) itFile.next();
			} while (item.isFormField());

			String fileName = item.getName();
			String fileEnd = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if (fileType != null && !"".equals(fileType.trim())) {
				boolean uuid = false;
				String[] sbRealPath = fileType.split(",");
				String[] var19 = sbRealPath;
				int var18 = sbRealPath.length;

				for (int ii = 0; ii < var18; ++ii) {
					String file = var19[ii];
					if (fileEnd.equals(file.toLowerCase())) {
						uuid = true;
						break;
					}
				}

				if (!uuid) { return "fileTypeError"; }
			}

			String var21 = UUID.randomUUID().toString();
			StringBuffer var22 = new StringBuffer();
			var22.append(filePath).append(var21).append(".").append(fileEnd);
			File var23 = new File(var22.toString());
			item.write(var23);
			return var22.toString();
		} catch (Exception var20) {
			return "fileSizeError";
		}
	}

	public static String uploadOneMoreFile(List<MultipartFile> fileList, String fileUploadPath, String maxSize) {
		String tempPath = "";
		String tempFilePath = fileUploadPath;
		String filePath = Parameter.getInstance().getProperty("picture.upload.path") + fileUploadPath;
		FileUtil.mkdir(tempPath);
		FileUtil.mkdir(filePath);
		String resultString = "";
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(5120);
		factory.setRepository(new File(tempPath));
		ServletFileUpload upload = new ServletFileUpload(factory);
		if (maxSize != null && !"".equals(maxSize.trim())) {
			upload.setSizeMax(Long.valueOf(maxSize) * 1024L * 1024L);
		}

		upload.setHeaderEncoding("UTF-8");

		try {
			Iterator<MultipartFile> itFile = fileList.iterator();

			while (itFile.hasNext()) {
				MultipartFile e = itFile.next();
				String fileName = e.getOriginalFilename();
				if ("".equals(fileName) || fileName == null) {
					break;
				}

				if (e.getSize() > (Long.valueOf(maxSize) * 1024L * 1024L)) { return "fileSizeError"; }

				String fileEnd = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				String uuid = UUID.randomUUID().toString();
				StringBuffer sbRealPath = new StringBuffer();
				sbRealPath.append(filePath).append(uuid).append(".").append(fileEnd);
				e.transferTo(new File(sbRealPath.toString()));
				StringBuffer fileDbPath = new StringBuffer();
				fileDbPath.append(tempFilePath).append(uuid).append(".").append(fileEnd);
				if (resultString.length() == 0) {
					resultString = fileDbPath.toString();
				} else {
					resultString = resultString + "," + fileDbPath.toString();
				}
			}

			return resultString.length() > 0 ? resultString.toString() : "fileError";
		} catch (Exception ex) {
			log.warn(ex);
			return "fileSizeError";
		}
	}
}
