package com.radiant.sage.code.test.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

public class CopyFilesService {

	public static void main(String[] args) {
		try {
		    Path src = Paths.get("D:\\project-workspace\\crosspay\\RadiantSageCodeTest\\src\\source");
		    Path dest = Paths.get("D:\\project-workspace\\crosspay\\RadiantSageCodeTest\\src\\destination");
		    Stream<Path> files = Files.walk(src);
		    files.filter(file->"TXT".equalsIgnoreCase(getExtension(file.getFileName().toString()))).forEach(file -> {copyFiles(src, dest, file);});
		    files.close();
		} catch (IOException ex) {
			System.out.println("Exception occured inside the CopyFilesService main: " + ex.getMessage());
		}
	}

	private static void copyFiles(Path src, Path dest, Path file) {
		try {
			Files.copy(file, dest.resolve(src.relativize(file)),StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println("Exception occured inside the CopyFilesService copyFiles: " + e.getMessage());
		}
	}
	
	private static String getExtension(String fileName) {
		String extension = "";
		try {
			int i = fileName.lastIndexOf('.');
			int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
			if (i > p) {
			    extension = fileName.substring(i+1);
			}
		} catch (Exception e) {
			System.out.println("Exception occured inside the CopyFilesService getExtension: " + e.getMessage());
		}
		return extension;
	}
}
