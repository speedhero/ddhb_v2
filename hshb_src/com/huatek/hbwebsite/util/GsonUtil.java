package com.huatek.hbwebsite.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
	private static Gson commonGson;
	private static Gson gsonWithExpose;

	public static Gson getCommonGsonInstance() {
		if (commonGson == null) {
			commonGson = new Gson();
		}

		return commonGson;
	}

	public static Gson getGsonInstanceWithExpose() {
		if (gsonWithExpose == null) {
			gsonWithExpose = (new GsonBuilder()).excludeFieldsWithoutExposeAnnotation().create();
		}

		return gsonWithExpose;
	}
}
