package com.huatek.framework.util;

import com.huatek.base.entity.BaseEntity;
import com.huatek.framework.entity.FwAccountDuty;
import com.huatek.framework.entity.FwSrcAction;
import com.huatek.framework.exception.BusinessCheckException;
import com.huatek.framework.exception.BusinessLogicException;
import com.huatek.framework.exception.BusinessRuntimeException;
import com.huatek.framework.security.ThreadLocalClient;
import com.huatek.framework.service.ConfigFactoryFactory;
import com.huatek.framework.tag.CommonBean;
import com.huatek.framework.util.SpringContext;
import com.huatek.zip.ZipEntry;
import com.huatek.zip.ZipInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import org.apache.log4j.Logger;
import org.springframework.context.HierarchicalMessageSource;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

public class Util {
	private static final int READ_BYTE_ONCE = 1024;
	private static final Logger LOGGER = Logger.getLogger(Util.class);
	static final String SEARCH_KEY = "new Ext.grid.TableGrid(";
	static int searchKeyLen = "new Ext.grid.TableGrid(".length();

	public static String getErrorMsgInfo(BindingResult result, String formName) {
		List errors = result.getFieldErrors();
		StringBuffer buffer = new StringBuffer();
		if (result.getGlobalErrorCount() > 0) {
			for (int messageSource = 0; messageSource < result.getGlobalErrorCount(); ++messageSource) {
				if (messageSource > 0) {
					buffer.append("；");
				}

				buffer.append(((ObjectError) result.getGlobalErrors().get(messageSource)).getCodes()[1]);
			}
		}

		HierarchicalMessageSource var13 = (HierarchicalMessageSource) SpringContext.getBean("messageSource");
		if (var13 == null) {
			for (int locale = 0; locale < errors.size(); ++locale) {
				if (buffer.length() > 0) {
					buffer.append("； ");
				}

				buffer.append("\"" + ((FieldError) errors.get(locale)).getField()).append("\":\"")
						.append(((FieldError) errors.get(locale)).getDefaultMessage()).append("\"");
			}
		} else {
			Locale var14 = ThreadLocalClient.get().getLocale();

			for (int i = 0; i < errors.size(); ++i) {
				String errorInfo = null;

				String messageInfo;
				String fieldMessage;
				try {
					messageInfo = ((FieldError) errors.get(i)).getCode();
					if ("typeMismatch".equals(messageInfo)) {
						fieldMessage = ((FieldError) errors.get(i)).getDefaultMessage();
						if (fieldMessage.indexOf("[") > 0 && fieldMessage.indexOf("]") > 0) {
							messageInfo = messageInfo + "."
									+ fieldMessage.substring(fieldMessage.lastIndexOf("[") + 1, fieldMessage.lastIndexOf("]"));
						}
					}

					errorInfo = var13.getMessage(messageInfo, ((FieldError) errors.get(i)).getArguments(), var14);
				} catch (Exception var12) {
					LOGGER.warn("Can not find the message resource " + var14 + " for " + ((FieldError) errors.get(i)).getCode());
					errorInfo = ((FieldError) errors.get(i)).getDefaultMessage();
				}

				if (formName != null) {
					messageInfo = null;
					fieldMessage = formName + "." + ((FieldError) errors.get(i)).getField() + ".displayName";

					try {
						messageInfo = var13.getMessage(fieldMessage, (Object[]) null, var14);
					} catch (Exception var11) {
						LOGGER.warn("Can not find the message resource " + var14 + " for  " + fieldMessage);
						messageInfo = ((FieldError) errors.get(i)).getField();
					}

					errorInfo = messageInfo + errorInfo;
				}

				if (buffer.length() > 0) {
					buffer.append("； ");
				}

				buffer.append(errorInfo);
			}
		}

		return buffer.toString();
	}

	public static boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		return "XMLHttpRequest".equals(header);
	}

	public static List<File> unzip(String zipFileName, String outputDirectory) throws Exception {
		if (zipFileName.equalsIgnoreCase(".zip")) {
			throw new BusinessCheckException("解压文件格式不正确！");
		} else {
			ArrayList files = new ArrayList();
			File outputFile = new File(outputDirectory);
			if (!outputFile.exists() && !outputFile.mkdirs()) {
				throw new BusinessLogicException("创建解压文件目录失败！");
			} else {
				ZipInputStream zis = null;
				FileOutputStream fos = null;

				try {
					zis = new ZipInputStream(new FileInputStream(zipFileName));
					ZipEntry entry = null;

					while ((entry = zis.getNextEntry()) != null) {
						String entryName = UUID.randomUUID() + "_" + entry.getName();
						File temp;
						if (entry.isDirectory()) {
							temp = new File(outputDirectory, entryName);
							if (!temp.exists() && !temp.mkdir()) { throw new BusinessLogicException("创建解压文件目录失败！"); }
						}

						if (!entry.isDirectory()) {
							temp = new File(outputDirectory + "/" + entryName);
							fos = new FileOutputStream(temp);
							byte[] buf = new byte[1024];
							boolean length = false;

							int length1;
							while ((length1 = zis.read(buf)) > 0) {
								fos.write(buf, 0, length1);
							}

							files.add(temp);
						}
					}
				} finally {
					if (fos != null) {
						fos.close();
					}

					if (zis != null) {
						zis.close();
					}

					(new File(zipFileName)).deleteOnExit();
				}

				return files;
			}
		}
	}

	public static String getString(Object obj) {
		return obj == null ? "" : obj.toString().trim();
	}

	public static int getInteger(Object obj) {
		return obj != null && !obj.equals("") ? Integer.valueOf(obj.toString()).intValue() : 0;
	}

	public static BigDecimal getBigDecimal(Object obj) {
		return obj != null && !obj.equals("") ? new BigDecimal(obj.toString()) : new BigDecimal("0");
	}

	public static BaseEntity getObject(List<? extends BaseEntity> datas, Long id) {
		if (datas != null && id != null) {
			for (int i = 0; i < datas.size(); ++i) {
				if (((BaseEntity) datas.get(i)).getId().intValue() == id.intValue()) { return (BaseEntity) datas.get(i); }
			}

			return null;
		} else {
			return null;
		}
	}

	public static CommonBean getParamCommonBean(List<CommonBean> paramterList, String name) {
		if (paramterList != null && paramterList.size() != 0) {
			for (int i = 0; i < paramterList.size(); ++i) {
				if (((CommonBean) paramterList.get(i)).getName().equals(name)) { return (CommonBean) paramterList.get(i); }
			}

			return null;
		} else {
			return null;
		}
	}

	public static boolean isPermitAction(long actionId) {
		List actions = ThreadLocalClient.get().getPermitAction();

		for (int i = 0; i < actions.size(); ++i) {
			if (((FwSrcAction) actions.get(i)).getId().longValue() == actionId) { return true; }
		}

		return false;
	}

	public static String printString(String outputStr) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		request.setAttribute("_out_data", outputStr);
		return "frame/out_data.jsp";
	}

	public static String printErrorString(String outputStr) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		request.setAttribute("_error_info", "true");
		request.setAttribute("_out_data", outputStr);
		return "frame/out_data.jsp";
	}

	public static String getParamValue(PageContext pageContext, String name) {
		String satrribute = pageContext.findAttribute(name).toString();
		String value = pageContext.getRequest().getParameter(satrribute);
		return value != null && value.trim().length() != 0 ? value : "";
	}

	public static BigDecimal getBigDecimal(PageContext pageContext, String name) {
		String value = null;
		if (pageContext.findAttribute(name) == null) {
			value = "1";
		} else {
			value = pageContext.findAttribute(name).toString();
		}

		return new BigDecimal(value);
	}

	public static String getParamValue(HttpServletRequest request, String name) {
		String value = request.getParameter(name);
		return value != null && value.trim().length() != 0 ? value : "";
	}

	public static boolean downloadFile(HttpServletResponse response, String fileName) throws BusinessCheckException {
		String dataPath = "";
		File exportDataFloder = new File(dataPath);
		if (!exportDataFloder.exists()) {
			throw new BusinessRuntimeException("系统设置中\'指定基础数据导出目录\'地址不存在.");
		} else {
			if (!dataPath.endsWith("/") || dataPath.endsWith("\\")) {
				dataPath = dataPath + "/";
			}

			File file = new File(dataPath + fileName);
			if (!file.exists()) {
				return false;
			} else {
				try {
					FileInputStream ex = new FileInputStream(file);
					int i = ex.available();
					byte[] data = new byte[i];
					ex.read(data);
					ex.close();
					response.reset();
					response.setContentType("application/octet-stream");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
					ServletOutputStream toClient = response.getOutputStream();
					toClient.write(data);
					toClient.flush();
					toClient.close();
					return true;
				} catch (IOException var9) {
					LOGGER.error(var9.getMessage());
					throw new BusinessCheckException("下载文件出错！");
				}
			}
		}
	}

	public static boolean checkCurrentUserRoleCode(String[] roles, String[] sources, String target) {
		Set duties = ThreadLocalClient.get().getOperator().getFwAccountDuties();
		Iterator iterator = duties.iterator();
		FwAccountDuty accountDuty = null;

		while (iterator.hasNext()) {
			accountDuty = (FwAccountDuty) iterator.next();
			boolean isRole = false;

			int k;
			for (k = 0; k < roles.length; ++k) {
				if (accountDuty.getFwDuty().getId().toString().equals(roles[k])) {
					isRole = true;
				}
			}

			if (isRole) {
				for (k = 0; k < sources.length; ++k) {
					if (target.equals(sources[k])) { return false; }
				}
			}
		}

		return true;
	}

	public static String getUploadPath(MultipartFile uploadFile) throws Exception {
		String directory = ConfigFactoryFactory.getSysConfig("upload.dir");
		File copyPath = new File(directory);
		if (!copyPath.isDirectory() && !copyPath.mkdirs()) {
			throw new BusinessRuntimeException("错误: 创建目录:" + copyPath + " 失败!");
		} else {
			String uploadFileName = uploadFile.getOriginalFilename();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String dateStr = sdf.format(new Date());
			File uploadPath = new File(copyPath + "/" + dateStr);
			if (!uploadPath.isDirectory() && !uploadPath.mkdirs()) {
				throw new BusinessCheckException("上传文件目录不存在");
			} else {
				String filePath = uploadPath + "/" + uploadFileName;
				FileCopyUtils.copy(uploadFile.getBytes(), new File(filePath));
				String ext = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1).toLowerCase();
				if (!ext.equalsIgnoreCase("xls")) {
					throw new BusinessCheckException("上传文件格式不对,只支持xls格式");
				} else {
					return filePath;
				}
			}
		}
	}

	public static boolean isNumeric(String str) {
		int i = str.length();

		do {
			--i;
			if (i < 0) { return true; }
		} while (Character.isDigit(str.charAt(i)));

		return false;
	}

	public static boolean getMatchResult(String aimStr, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(aimStr);
		return matcher.matches();
	}

	public static String getStringFromArray(Long[] objs) {
		if (objs != null && objs.length != 0) {
			StringBuffer strBf = new StringBuffer("" + objs[0]);

			for (int i = 1; i < objs.length; ++i) {
				strBf.append(",").append("" + objs[i]);
			}

			return strBf.toString();
		} else {
			return "";
		}
	}

	public static String getStringFromArray(long[] objs) {
		if (objs != null && objs.length != 0) {
			StringBuffer strBf = new StringBuffer(String.valueOf(objs[0]));

			for (int i = 1; i < objs.length; ++i) {
				strBf.append(",").append(String.valueOf(objs[i]));
			}

			return strBf.toString();
		} else {
			return "";
		}
	}

	public static String substring(String sourceStr, int size) {
		if (size <= 0) {
			return "";
		} else if (sourceStr != null && sourceStr.getBytes().length > size) {
			int index = 0;
			byte[] bs = sourceStr.getBytes();
			if (bs[size] >> 7 != 0 && (bs[size] >> 6 & 3) != 3) {
				for (int i = size - 1; i >= 0; --i) {
					if ((bs[i] >> 6 & 3) == 3) {
						index = i;
						break;
					}
				}
			} else {
				index = size;
			}

			return new String(Arrays.copyOf(sourceStr.getBytes(), index));
		} else {
			return sourceStr;
		}
	}

	public static void main(String[] args) throws IOException {
		String dirs = "c:\\logs\\";
		File director = new File(dirs);
		FileWriter requestContentFile = new FileWriter(dirs + "request.txt");
		File[] var7;
		int var6 = (var7 = director.listFiles()).length;

		for (int var5 = 0; var5 < var6; ++var5) {
			File file = var7[var5];
			if (file.getName().startsWith("ops2.")) {
				BufferedReader input = new BufferedReader(new FileReader(file));
				String line = null;

				while ((line = input.readLine()) != null) {
					if (line.indexOf("请求访问:/") > 0) {
						line = line.substring(0, line.indexOf("INFO")) + "\t" + line.substring(line.indexOf(":/") + 2) + "\n";
						requestContentFile.write(line);
					}
				}

				input.close();
			}
		}

		requestContentFile.close();
	}

	public static String getAssignedLengthStr(String sourceStr, int assignedLength) {
		return substring(sourceStr, assignedLength);
	}

	public static File logFile(String data, Date recDate, String fileName) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String filePath = ConfigFactoryFactory.getSysConfig("upload.dir") + File.separator + sdf.format(recDate);
		File dir = new File(filePath);
		if (!dir.exists() && !dir.mkdirs()) {
			throw new IOException("Creation directory " + filePath + " is  failure.");
		} else {
			File file = new File(filePath + File.separator + fileName);
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(data);
			fileWriter.close();
			return file;
		}
	}

	public static String getFileContent(String fileName, Date recDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String filePath = ConfigFactoryFactory.getSysConfig("upload.dir") + File.separator + sdf.format(recDate);
		File dir = new File(filePath);
		if (!dir.exists()) {
			throw new BusinessRuntimeException("该文件目录不存在:" + dir.getPath());
		} else {
			File file = new File(filePath + File.separator + fileName);
			Object fileContentByte = null;
			FileInputStream inStream = null;

			try {
				inStream = new FileInputStream(file);
				byte[] fileContentByte1 = new byte[inStream.available()];
				inStream.read(fileContentByte1);
				String var10 = new String(fileContentByte1);
				return var10;
			} catch (FileNotFoundException var20) {
				;
			} catch (IOException var21) {
				;
			} finally {
				if (inStream != null) {
					try {
						inStream.close();
					} catch (IOException var19) {
						;
					}
				}

			}

			return null;
		}
	}

	public static void deleteFile(String fileName, Date recDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String filePath = ConfigFactoryFactory.getSysConfig("upload.dir") + File.separator + sdf.format(recDate);
		File file = new File(filePath + File.separator + fileName);
		file.deleteOnExit();
	}
}
