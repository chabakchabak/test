package com.chabakchabak.www.lee.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

import com.chabakchabak.www.lee.domain.upload.FolderDto;

import lombok.extern.log4j.Log4j;

@Log4j
public class MyFileUtil {
	// 폴더이름 생성
	private static String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date today = new Date();
		String folders = sdf.format(today);
		return folders;
	}
	
	// 폴더파일 생성
	public static FolderDto getFolder(String folderName) {
		String uploadPath = "D:/upload/" + folderName +"/" + getFolder();
		File folder = new File(uploadPath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		FolderDto folderDto = new FolderDto(); 
		folderDto.setFolder(folder);
		folderDto.setUploadPath(uploadPath);
		return folderDto;
	}
	
	// 이미지 인지 체크
	public static boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			if(contentType == null) {
				contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			}
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 이미지 없는것 삭제
	public static String cleanImgTask(List<String> yesterdayPaths) {
		log.info("************************ cleanImgTask ***********************");
//		log.info("yesterdayPaths : " + yesterdayPaths);
		
		//어제 날짜 폴더 파일들 가져오기
		//어제 날짜 생성
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesdterday = sdf.format(cal.getTime());
		
		log.info("yesdterday : " + yesdterday);
		//어제 날짜 path로 파일배열 생성
		String serverPath = "D:/upload/board-img/" + yesdterday;
		log.info("serverPath : " + serverPath);
		File file = new File(serverPath);
		File[] savedFiles = file.listFiles();
		log.info("savedFiles : " + savedFiles);
		
		for(File saveFile : savedFiles) {
			String serverFilePath = saveFile.getAbsolutePath();
			boolean check = false;
			for(String dbFilePath : yesterdayPaths) {
				if(serverFilePath.equals(dbFilePath)) {
					check = true;
					break;
				}
			}
			if(!check) {
				log.info("삭제처리");
				saveFile.delete();
			}
		}
		return "success";
	}
	

	//이미지삭제
	public static boolean deleImg(List<String> imgPaths) {
		boolean result = false;
		for(String imgPath : imgPaths) {
			File imgFile = new File(imgPath);
			result = imgFile.delete();
			if(!result) {
				log.info("이미지 삭제 성공.");
			}else {
				log.info("이미지 삭제 실패.");
				break;
			}
		}
		return result;
	}
	
	// 컨텐트로 이미지 경로 가져오기
//	public static List<String> getImgPaths(String content) {
//		List<String> imgPaths = new ArrayList<String>();
//		final String[] formats = {".jpeg", ".png", ".gif", ".bmp", ".webp"};
//		
//		while(true) {
//			int startPoint = content.indexOf("/D");
//			if(startPoint == -1) {
//				return imgPaths;
//			}
//			content = content.substring(startPoint);
//			int endPoint = Integer.MAX_VALUE;
//			for(String format : formats) {
//				int index = content.indexOf(format);
//				if(index != -1 && index < endPoint) {
//					endPoint = index + (format.equals(".jpeg") || format.equals(".webp") ? 5 : 4);
//				}
//			}
//			if(endPoint == Integer.MAX_VALUE) {
//				return imgPaths;
//			}
//			String path = content.substring(0, endPoint);
//			content = content.substring(endPoint);
//			imgPaths.add(path);
//		}
//	
//	}
	
	//조회수 관리
	public static boolean checkViews(HttpServletRequest request, HttpServletResponse response, int boardno) {
		String strBoardno = String.valueOf(boardno);
		boolean check = true;
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
	 		for(Cookie getCookie : cookies){
				if(getCookie.getName().equals(strBoardno)) {
					//조회 중복
					check = false;
					break;
				}
			}
		}
		
		log.info("pluseViews 전");
		if(check) {
			Cookie cookie = new Cookie(strBoardno, strBoardno);
			//해당 boardno에 1분간 조회수 증가 안됨
			cookie.setMaxAge(60);
			cookie.setPath("/");
			response.addCookie(cookie);

		}
		
		return check;
	}

	//로그인 아이디체크 쿠키생성
	
	public static void CheckIdCookie(String userid, boolean checked, 
								     HttpServletRequest request, HttpServletResponse response) {
		if(checked) {
			// 쿠키 생성 
			log.info("checked.... true");
			Cookie cookie = new Cookie("userid", userid);
			cookie.setMaxAge(60*60*24*7*4*6); // 6개월
			cookie.setPath("/");
			response.addCookie(cookie);
		}else {
			log.info("checked.... false");
			//안 한경우
			//쿠키 삭제
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					if(cookie.getName().equals("userid")) {
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
						break;
					}
				}
			}else {
	            log.info("No cookies found in request");
	        }
		}
	}
	

}
