package com.chabakchabak.www.lee.domain.upload;

import java.io.File;

import lombok.Data;

@Data
public class FolderDto {
	private File folder;
	private String uploadPath;
}
