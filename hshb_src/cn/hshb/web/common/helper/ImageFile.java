/**
 * 
 */
package cn.hshb.web.common.helper;

import java.io.File;

/**
 * 本项目图片文件同步所用的路径类
 * @author Administrator
 *
 */
public class ImageFile{
	private String fileName;		//文件名
	private String fileType;		//文件类型
	private String categoryFolder;	//分类文件夹名
	private Boolean noThumbnail;	//不生成缩略图标志
	private String basePath;		//根路径
	private String subPath;			//分类下面的子路径

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getCategoryFolder() {
		return categoryFolder;
	}
	public void setCategoryFolder(String categoryFolder) {
		this.categoryFolder = categoryFolder;
	}
	public Boolean isNoThumbnail() {
		return (noThumbnail!=null && noThumbnail);
	}
	public void setNoThumbnail(Boolean noThumbnail) {
		this.noThumbnail = noThumbnail;
	}
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	public String getSubPath() {
		return subPath;
	}
	public void setSubPath(String subPath) {
		this.subPath = subPath;
	}
	
	/**
	 * 获取图片保存的相对路径
	 * @return
	 */
	public String getRelativePath(){
		String path = "/" + this.getCategoryFolder() + "/" + subPath;
		return path.replace("//", "/");
	}
	
	/**
	 * 获取图片保存的绝对路径
	 * @return
	 */
	public String getAbsolutePath(){
		String path = this.getBasePath() + this.getRelativePath();
		return path.replace("//", "/");
	}

	/**
	 * 获取文件完整名称
	 * @return
	 */
	public String getFullFileName(){
		String fileName = getFileName();
		if(getFileName().indexOf(".")<0)
			fileName += "." + this.getFileType();
		return fileName;
	}
	
	/**
	 * 获取文件完整路径和文件名
	 * @return
	 */
	public String getRelativePathAndName(){
		String s = this.getRelativePath() + "/" + this.getFullFileName();
		return s.replace("//", "/");
	}
	
	/**
	 * 获取文件绝对路径和文件名
	 * @return
	 */
	public String getAbsolutePathAndName(){
		String s = this.getAbsolutePath() + "/" + this.getFullFileName();
		return s.replace("//", "/");
	}
	
	/**
	 * 判断文件保存路径是否存在
	 * @return
	 */
	public Boolean isPathExists(){
		File f = new File(getAbsolutePath());
		if(f.exists() && f.isDirectory())
			return true;
		else
			return false;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ImageFile [")
				.append("getFileName()=").append(getFileName()).append(", ").append("\r\n")
				.append("getFileType()=").append(getFileType()).append(", ").append("\r\n")
				.append("getCategoryFolder()=").append(getCategoryFolder()).append(", ").append("\r\n")
				.append("isNoThumbnail()=").append(isNoThumbnail()).append(", ").append("\r\n")
				.append("getBasePath()=").append(getBasePath()).append(", ").append("\r\n")
				.append("getSubPath()=").append(getSubPath()).append(", ").append("\r\n")
				.append("getRelativePath()=").append(getRelativePath()).append(", ").append("\r\n")
				.append("getAbsolutePath()=").append(getAbsolutePath()).append(", ").append("\r\n")
				.append("getFullFileName()=").append(getFullFileName()).append(", ").append("\r\n")
				.append("getRelativePathAndName()=").append(getRelativePathAndName()).append(", ").append("\r\n")
				.append("getAbsolutePathAndName()=").append(getAbsolutePathAndName()).append(", ").append("\r\n")
				.append("isPathExists()=").append(isPathExists()).append("\r\n")
				.append("]");
		return builder.toString();
	}
}