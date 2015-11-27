package com.aat.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import weibo4j.Timeline;
import weibo4j.http.ImageItem;
import weibo4j.model.Status;

/**
 * @time 2014-01-05
 * @author xingle
 * @version v1.0
 * @function 新浪微博发送消息和图文
 * @info java weiboSDK 需要用户的token
 *
 */
public class WeiboUtils {

	// 发送微博文本
	public static int sendWeibo(String access_token, String msg) {
		Timeline tm = new Timeline(access_token);
		try {
			Status status = tm.updateStatus(msg);
			if (null == status.getUser()) {
				return 0;
			} else {
				return 1;
			}
		} catch (Exception e) {
		}
		return 0;
	}

	// 发送图片和文本
	public static int sendWeibo(String access_token, String msg, String img) {
		try {
			byte[] content = readFileImage(img);
			ImageItem pic = new ImageItem("pic", content);
			String s = java.net.URLEncoder.encode(msg, "utf-8");
			Timeline tm = new Timeline(access_token);
			Status status = tm.uploadStatus(s, pic);
			if (null == status.getUser()) {
				return 0;
			} else {
				return 1;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	//图片转二进制
	private static byte[] readFileImage(String filename) throws IOException {
		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				new FileInputStream(filename));
		int len = bufferedInputStream.available();
		byte[] bytes = new byte[len];
		int r = bufferedInputStream.read(bytes);
		if (len != r) {
			bytes = null;
		}
		bufferedInputStream.close();
		return bytes;
	}

}
