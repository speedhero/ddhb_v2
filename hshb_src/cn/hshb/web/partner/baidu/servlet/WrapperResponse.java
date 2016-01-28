package cn.hshb.web.partner.baidu.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class WrapperResponse extends HttpServletResponseWrapper {
	private MyPrintWriter tmpWriter;
	private ByteArrayOutputStream output;

	public WrapperResponse(ServletResponse httpServletResponse) {
		super((HttpServletResponse) httpServletResponse);
		output = new ByteArrayOutputStream();
		tmpWriter = new MyPrintWriter(output);
	}

	public void finalize() throws Throwable {
		super.finalize();
		output.close();
		tmpWriter.close();
	}

	public String getContent() {
//		try {
			tmpWriter.flush(); // 刷新该流的缓冲，详看java.io.Writer.flush()
			//String s = tmpWriter.getByteArrayOutputStream().toString("UTF-8");
			String s = tmpWriter.getByteArrayOutputStream().toString();
			// 此处可根据需要进行对输出流以及Writer的重置操作
			// 比如tmpWriter.getByteArrayOutputStream().reset()
			return s;
//		} catch (UnsupportedEncodingException e) {
//			return "UnsupportedEncoding";
//		}
	}

	// 覆盖getWriter()方法，使用我们自己定义的Writer
	public PrintWriter getWriter() throws IOException {
		return tmpWriter;
	}

	public void close() throws IOException {
		tmpWriter.close();
	}
	public void superWrite(String str) throws IOException {
		super.getResponse().getWriter().write(str);
	}
	public void superWrite(char[] buf) throws IOException {
		super.getResponse().getWriter().write(buf);
	}
	public void superWrite(int c) throws IOException {
		super.getResponse().getWriter().write(c);
	}
	public void superWrite(char[] buf, int off, int len) throws IOException {
		super.getResponse().getWriter().write(buf, off, len);
	}
	public void superWrite(String str, int off, int len) throws IOException {
		super.getResponse().getWriter().write(str, off, len);
	}
	public void superFlush() throws IOException {
		super.getResponse().getWriter().flush();
	}
	
	// 自定义PrintWriter，为的是把response流写到自己指定的输入流当中
	// 而非默认的ServletOutputStream
	private static class MyPrintWriter extends PrintWriter {
		ByteArrayOutputStream myOutput; // 此即为存放response输入流的对象

		public MyPrintWriter(ByteArrayOutputStream output) {
			super(output);
			myOutput = output;
		}

		public ByteArrayOutputStream getByteArrayOutputStream() {
			return myOutput;
		}
	}
}
