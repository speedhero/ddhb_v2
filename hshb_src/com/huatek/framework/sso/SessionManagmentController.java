package com.huatek.framework.sso;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huatek.framework.entity.FwAccount;
import com.huatek.framework.exception.BusinessRuntimeException;
import com.huatek.framework.sso.HttpClientUtil;
import com.huatek.framework.sso.ResponseBean;
import com.huatek.framework.sso.SubSystemSessionBean;
import com.huatek.framework.util.CommonUtil;
import com.huatek.framework.util.HuatekSessionContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/sso.show" })
public class SessionManagmentController {
	private static final Logger LOGGER = Logger.getLogger(SessionManagmentController.class);
	public static final Gson GSON = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	public static final Map<String, Map<String, SubSystemSessionBean>> SSO_MAP = new HashMap();
	private Log logger = LogFactory.getFactory().getInstance(this.getClass().getName());

	@RequestMapping(params = { "actionMethod=ssoCheck" })
	public String ssoCheck(Model model, HttpServletRequest request) throws IOException {
		String mainSessionID = request.getParameter("mainSysSessionId");
		HttpSession session = null;
		if (request.getSession().getId().equals(mainSessionID)) {
			session = request.getSession();
		} else {
			session = HuatekSessionContext.getInstance().getSession(mainSessionID);
		}

		ResponseBean response = new ResponseBean();
		if (session != null) {
			response.setResultCode("1");
			response.setResultMsg("success");
			response.setSuccess(true);
			if ("true".equals(request.getParameter("doLogin"))) {
				FwAccount user = (FwAccount) session.getAttribute("LoginAccount");
				response.setRetData(HttpClientUtil.serializableEntity(user));
				this.saveSubSysSession(mainSessionID, model, request);
			}
		} else {
			response.setResultCode("-1");
			response.setResultMsg("failure");
			response.setSuccess(false);
		}

		request.setAttribute("_out_data", GSON.toJson(response));
		return "frame/out_data.jsp";
	}

	private void saveSubSysSession(String mainSysSession, Model model, HttpServletRequest request) throws IOException {
		String sessionId = request.getParameter("subSysSessionId");
		String port = request.getParameter("port");
		String path = request.getParameter("path");
		String logoutUrl = request.getParameter("logoutUrl");
		String subIp = request.getParameter("subIp");
		SubSystemSessionBean subSysSession = new SubSystemSessionBean();
		subSysSession.setSessionId(sessionId);
		subSysSession.setUrl(logoutUrl);
		subSysSession.setIp(subIp);
		subSysSession.setPort(port);
		subSysSession.setPath(path);
		if (SSO_MAP.get(mainSysSession) == null) {
			HashMap subSysSessionMap = new HashMap();
			subSysSessionMap.put(mainSysSession, subSysSession);
			SSO_MAP.put(mainSysSession, subSysSessionMap);
		} else {
			((Map) SSO_MAP.get(mainSysSession)).put(sessionId, subSysSession);
		}

	}

	@RequestMapping(params = { "actionMethod=logout" })
	public String logout(HttpServletRequest request) throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		Map subSysSessionMap = (Map) SSO_MAP.get(session.getId());
		if (subSysSessionMap != null && subSysSessionMap.size() > 0) {
			Iterator url = subSysSessionMap.values().iterator();

			while (url.hasNext()) {
				SubSystemSessionBean subSystem = (SubSystemSessionBean) url.next();
				String subSysUrl = "http://" + subSystem.getIp() + ":" + subSystem.getPort() + subSystem.getPath()
						+ subSystem.getUrl();

				try {
					ArrayList e = new ArrayList();
					e.add(new BasicNameValuePair("sessionId", subSystem.getSessionId()));
					this.logger.info(HttpClientUtil.postMethod(subSysUrl, e, subSystem.getSessionId()));
				} catch (Exception var8) {
					this.logger.error(var8.getMessage());
				}
			}
		}

		session.invalidate();
		String url1 = "/";
		if (request.getParameter("welcomeUrl") != null) {
			url1 = URLDecoder.decode(request.getParameter("welcomeUrl"), "UTF-8");
		}

		return "redirect:" + url1;
	}

	@RequestMapping(params = { "actionMethod=getToken" })
	public void getToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String oldUrl = request.getParameter("oldUrl");
		if (CommonUtil.isZeroLengthTrimString(oldUrl)) {
			throw new BusinessRuntimeException("业务子系统没有传送本地URL请求地址");
		} else {
			oldUrl = URLDecoder.decode(oldUrl, "UTF-8");
			if (oldUrl.indexOf("?") < 0) {
				oldUrl = oldUrl + "?";
			} else {
				oldUrl = oldUrl + "&";
			}

			oldUrl = oldUrl + "tokenId=" + request.getSession().getId();
			response.sendRedirect(oldUrl);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("向业务系统发放令牌tokenId=" + request.getSession().getId());
			}

		}
	}
}
