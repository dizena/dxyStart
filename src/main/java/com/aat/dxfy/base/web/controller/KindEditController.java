package com.aat.dxfy.base.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aat.dxfy.base.bean.User;
import com.alibaba.fastjson.JSONObject;

/**
 * @time 2014-01-05
 * @author xingle
 * @email 1066031245@qq.com
 * @version v1.0
 * @function kindEdit插件的后端实现，用于上传的Controller
 * @info java springMVC
 * @update 无
 */
@Controller
@Scope("prototype")
@RequestMapping("upload")
public class KindEditController extends BaseController {

	/**
	 * @info 文件上传,个人用户上传到自己的用户目录结构
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "user_self_upload")
	public void user_self_upload(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		User user = getSessionUser();
		if (null == user) {
			out.println(getError("用户未登录。"));
			return;
		}

		List<MultipartFile> files = request.getFiles("imgFile");
		// 将文件存入硬盘
		String uploadpath = request.getSession().getServletContext().getRealPath("/") + "WEB-INF" + File.separator
				+ "upload/userFiles/";
		// 文件保存目录URL
		String saveUrl = request.getContextPath() + "/upload/userFiles/";

		File userDirFile = new File(uploadpath);
		if (!userDirFile.exists()) {
			userDirFile.mkdirs();
		}

		// 创建用户的目录
		uploadpath += user.getId() + "/";
		saveUrl += user.getId() + "/";
		File sa = new File(uploadpath);
		if (!sa.exists()) {
			sa.mkdirs();
		}

		// 类型文件夹
		String dirName = request.getParameter("dir");
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp,svg");
		extMap.put("flash", "swf,flv,mp4,ogg");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4,mkv");
		extMap.put("file", "pdf,doc,docx,xls,xlsx,ppt,pptx,htm,html,txt,zip,rar,gz,bz2");

		if (!ServletFileUpload.isMultipartContent(request)) {
			out.println(getError("请选择文件。"));
			return;
		}
		// 检查目录
		File uploadDir = new File(uploadpath);
		if (!uploadDir.isDirectory()) {
			out.println(getError("上传目录不存在。"));
			return;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			out.println(getError("上传目录没有写权限。"));
			return;
		}

		//
		if (dirName == null) {
			dirName = "image";
		}
		// 创建文件夹
		uploadpath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(uploadpath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		uploadpath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(uploadpath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		for (MultipartFile file : files) {
			if (file.isEmpty())
				continue;
			// 检查扩展名
			String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1)
					.toLowerCase();
			if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
				out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
				return;
			}

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
			FileOutputStream fileOS = new FileOutputStream(uploadpath + File.separator + newFileName);
			fileOS.write(file.getBytes());
			fileOS.flush();
			fileOS.close();
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url", saveUrl + newFileName);
			out.println(obj.toJSONString());
		}

	}

	/**
	 * @info 文件管理,个人账户的文件管理
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("user_self_manger")
	public void user_self_manger(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		User user = getSessionUser();
		if (null == user) {
			out.println(getError("用户未登录。"));
			return;
		}

		// 将文件存入硬盘
		String rootPath = request.getSession().getServletContext().getRealPath("/") + "WEB-INF" + File.separator
				+ "upload/userFiles/";
		// 文件保存目录URL
		String rootUrl = request.getContextPath() + "/upload/userFiles/";

		File userDirFile = new File(rootPath);
		if (!userDirFile.exists()) {
			userDirFile.mkdirs();
		}

		// 创建用户的目录
		rootPath += user.getId() + "/";
		rootUrl += user.getId() + "/";
		File sa = new File(rootPath);
		if (!sa.exists()) {
			sa.mkdirs();
		}
		

		// 图片扩展名
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };

		String dirName = request.getParameter("dir");
		if (dirName != null) {
			if (!Arrays.<String> asList(new String[] { "image", "flash", "media", "file" }).contains(dirName)) {
				out.println("不合法的文件夹名称！");
				return;
			}
			rootPath += dirName + "/";
			rootUrl += dirName + "/";
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		// 根据path参数，设置各路径和URL
		String path = request.getParameter("path") != null ? request.getParameter("path") : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
		}

		// 排序形式，name or size or type
		String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

		// 不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			out.println("不允许使用！");
			return;
		}
		// 最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			out.println("最后一个字符不是/");
			return;
		}
		// 目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if (!currentPathFile.isDirectory()) {
			out.println("目录不存在或不是目录！");
			return;
		}

		// 遍历目录取的文件信息
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if (currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if (file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if (file.isFile()) {
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String> asList(fileTypes).contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(hash);
			}
		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}

		JSONObject result = new JSONObject();
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);

		response.setContentType("application/json; charset=UTF-8");
		out.println(result.toJSONString());
	}

	/**
	 * @info 文件上传
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "upload_json.do")
	public void upload_json(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		if (null == getSessionUser()) {
			return;
		}
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		MultipartFile files = request.getFile("imgFile");// getFiles("imgFile");
		// 将文件存入硬盘
		String uploadpath = request.getSession().getServletContext().getRealPath("/") + "WEB-INF" + File.separator
				+ "upload/";
		// 文件保存目录URL
		String saveUrl = request.getContextPath() + "/upload/";

		File userDirFile = new File(uploadpath);
		if (!userDirFile.exists()) {
			userDirFile.mkdirs();
		}

		// 类型文件夹
		String dirName = request.getParameter("dir");
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp,svg");
		extMap.put("flash", "swf,flv,mp4,ogg");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4,mkv");
		extMap.put("file", "pdf,doc,docx,xls,xlsx,ppt,pptx,htm,html,txt,zip,rar,gz,bz2");

		response.setContentType("text/html; charset=UTF-8");
		if (!ServletFileUpload.isMultipartContent(request)) {
			out.println(getError("请选择文件。"));
			return;
		}
		// 检查目录
		File uploadDir = new File(uploadpath);
		if (!uploadDir.isDirectory()) {
			out.println(getError("上传目录不存在。"));
			return;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			out.println(getError("上传目录没有写权限。"));
			return;
		}

		//
		if (dirName == null) {
			dirName = "image";
		}
		// 创建文件夹
		uploadpath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(uploadpath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		uploadpath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(uploadpath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		if (files.isEmpty()) {
			out.println(getError("文件是空的。"));
			return;
		}
		// 检查扩展名
		String fileExt = files.getOriginalFilename().substring(files.getOriginalFilename().lastIndexOf(".") + 1)
				.toLowerCase();
		if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
			out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
			return;
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
		FileOutputStream fileOS = new FileOutputStream(uploadpath + File.separator + newFileName);
		fileOS.write(files.getBytes());
		fileOS.flush();
		fileOS.close();
		JSONObject obj = new JSONObject();
		obj.put("error", 0);
		obj.put("url", saveUrl + newFileName);
		out.println(obj.toJSONString());

	}

	/**
	 * @info 文件管理
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("file_manager_json.do")
	public void file_manager_json(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (null == getSessionUser()) {
			return;
		}
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// 根目录路径，可以指定绝对路径，比如 /var/www/attached/
		String rootPath = request.getSession().getServletContext().getRealPath("/") + "WEB-INF/upload/";
		// 根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
		String rootUrl = request.getContextPath() + "/upload/";

		File userDirFile = new File(rootPath);
		if (!userDirFile.exists()) {
			userDirFile.mkdirs();
		}

		// 图片扩展名
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };

		String dirName = request.getParameter("dir");
		if (dirName != null) {
			if (!Arrays.<String> asList(new String[] { "image", "flash", "media", "file" }).contains(dirName)) {
				out.println("不合法的文件夹名称！");
				return;
			}
			rootPath += dirName + "/";
			rootUrl += dirName + "/";
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		// 根据path参数，设置各路径和URL
		String path = request.getParameter("path") != null ? request.getParameter("path") : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
		}

		// 排序形式，name or size or type
		String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

		// 不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			out.println("不允许使用！");
			return;
		}
		// 最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			out.println("最后一个字符不是/");
			return;
		}
		// 目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if (!currentPathFile.isDirectory()) {
			out.println("目录不存在或不是目录！");
			return;
		}

		// 遍历目录取的文件信息
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if (currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if (file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if (file.isFile()) {
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String> asList(fileTypes).contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(hash);
			}
		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}

		JSONObject result = new JSONObject();
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);

		response.setContentType("application/json; charset=UTF-8");
		out.println(result.toJSONString());
	}

	public String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}

	@SuppressWarnings("rawtypes")
	public class NameComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public class SizeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
					return 1;
				} else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public class TypeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));
			}
		}
	}

}
