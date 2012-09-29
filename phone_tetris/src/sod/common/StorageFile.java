package sod.common;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;


enum SeekOrigin
{
    Begin,
    Current,
    End,
}


/**
 * 
 * @author MB
 *
 */
public  class StorageFile {
	File file;
	String filePath;
	
	FileInputStream fileInputStream;
	BufferedInputStream in;
	
	FileOutputStream fileOutputStream;
	BufferedOutputStream out;
	StringBuilder mBuf;
	
	public final static int READ = 0;
	public final static int WRITE = 1;
	public final static int WRITE_PLUS = 2;
	
	int mode = 0;
	
	int position;
	
	StorageFile() {
		
		
	}
	

	
	
	/**
	 * 해당경로의 StorageFile을 생성한다.
	 * @param mFile 
	 * @param filePath
	 * @return 해당경로의 StorageFile
	 * @throws IOException 파일 생성에 실패하면 IOException을 던진다.
	 * @throws NullPointerException 인자로 넘어온 값이 Null이면 NullPointerException을 던진다.
	 */
	 static public StorageFile createStorageFile(File mFile, String filePath)throws IOException, NullPointerException{
	 
		StorageFile storageFile = new StorageFile();
	 
		storageFile.file = mFile;	
		storageFile.filePath = filePath;
		storageFile.fileOutputStream = new FileOutputStream(storageFile.file);
		
		storageFile.out = new BufferedOutputStream(storageFile.fileOutputStream);
		storageFile.mode = WRITE;
		
		return storageFile;
	 }
	
	/**
	 * 해당경로의 StorageFile을 받아온다.
	 * @param mFile
	 * @return 해당경로의 StorageFile
	 * @throws IOException
	 * 파일 생성에 실패하면 IOException을 던진다.
	 * @throws NullPointerException
	 * 인자로 넘어온 값이 Null이면 NullPointerException을 던진다.
	 */
	static public StorageFile getStorageFile(File mFile, int mode, String filePath)throws IOException, NullPointerException{
		StorageFile storageFile = new StorageFile();
		
		storageFile.file = mFile;	
		storageFile.filePath = filePath;
		
		switch(mode){
		case READ:
			storageFile.fileInputStream = new FileInputStream(storageFile.file);
			
			storageFile.in = new BufferedInputStream(storageFile.fileInputStream);
			storageFile.mode = READ;
			break;
		case WRITE:
			storageFile.fileOutputStream = new FileOutputStream(storageFile.file);
			
			storageFile.out = new BufferedOutputStream(storageFile.fileOutputStream);
			storageFile.mode = WRITE;
			break;
		case WRITE_PLUS:
			storageFile.fileInputStream = new FileInputStream(storageFile.file);
			storageFile.in = new BufferedInputStream(storageFile.fileInputStream);
			storageFile.mode = WRITE_PLUS;
			
			byte [] buf = new byte[storageFile.getLength()];
			while(storageFile.in.read(buf) != -1) {;}
			String sBuf = new String(buf);
			storageFile.in.close();
			storageFile.fileInputStream.close();
			
			storageFile.fileOutputStream = new FileOutputStream(storageFile.file);
			storageFile.out = new BufferedOutputStream(storageFile.fileOutputStream);
			storageFile.write(sBuf.getBytes());
			storageFile.flush();
			
			break;
		}
		return storageFile;
	}

	/**
	 * byte배열에 파일에 있는 모든 데이터를 담아온다.
	 * 
	 * @param buf
	 * 파일에서 읽은 내용을 담기위한 버퍼
	 * @throws IOException
	 * 이미 close가 실행됐거나 파일에 읽기를 실패하면  IOException을 던진다.
	 * @throws EOFException
	 * 파일을 읽다가 파일의 끝에 도달하면 EOFException을 던진다
	 * @throws NullPointerException
	 * mode가 WRITE인데 read를 호출하면  NullPointerException을 던진다.
	 */
	public void read(byte[] buf) throws IOException,  EOFException{
		
		if(mode != READ)
			DebugUtils.throwException();
		
		while(in.read(buf) != -1) {;}
		
	}
	
	/**
	 * 해당 파일의 데이터를 읽는다.
	 * @param buf
	 * 파일에서 읽은 내용을 담기위한 버퍼
	 * @param index
	 * 파일에서 데이터를 읽을 때, 버퍼에서 담기 시작할 위치
	 * @param length
	 * 버퍼를 사용하여 읽어올 길이
	 * @throws  IOException
	 * 이미 close가 실행됐거나 파일에 읽기를 실패하면  IOException을 던진다.
	 * @throws EOFException
	 * 파일을 읽다가 파일의 끝에 도달하면 EOFException을 던진다.
	 * @throws NullPointerException
	 * mode가 WRITE인데 read를 호출하면  NullPointerException을 던진다.
	 */
	public void read(byte[] buf, int index, int length) throws IOException, EOFException{
		
		if(mode != READ)
			DebugUtils.throwException();
		
		for(int i = 0 ; i<index ; i++){
			if (in.read() == -1)
				break;
		}
		
		int returnInt;	
		returnInt = in.read(buf, 0, length);
		
		
		if(returnInt == -1)
			throw new EOFException();
	}
	
	/**
	 * 해당파일에 데이터를 쓴다.
	 * @param buf
	 * 파일에서 읽은 내용을 담기위한 버퍼
	 * @throws IOException
	 *  이미 close가 실행됐거나 파일에 쓰기를 실패하면  IOException을 던진다.
	 * @throws NullPointerException
	 * mode가 READ인데 write를 호출하면  NullPointerException을 던진다.
	 */
	public void write(byte [] buf) throws IOException{
		
		if(mode == READ)
			DebugUtils.throwException();
		
		out.write(buf);
	}
	
	/**
	 * 해당 파일에 데이터를 쓴다.
	 * @param buf
	 * 파일에 쓰고자하는 내용을 바이트 배열로 직렬화한 것
	 * @param index
	 * 버퍼에서의 쓰고자하는 부분의 시작위치
	 * @param length
	 * 쓰고자하는 바이트 배열의 index부터의 길이
	 * @throws  IOException
	 * 파일에 쓰기를 실패하면  IOException을 던진다.
	 * @throws NullPointerException
	 * mode가 READ인데 write를 호출하면  NullPointerException을 던진다.
	 */
	public void write(byte[] buf, int index, int length) throws IOException{
		
		if(mode != WRITE)
			DebugUtils.throwException();
		
		out.write(buf, index, length);

	}
	
	/**
	 * 저장소에 있는 파일에 이미지를 쓸때 사용한다.
	 * 지원하는 확장자는 jpg, png이다.
	 * @param img
	 * 이미지 정보가 들어있는 객체
	 * @throws IOException
	 *  이미 close가 실행됐거나 파일에 쓰기를 실패하면  IOException을 던진다.
	 *  파일 확장자가 jpg, png가 아니여도
	 * @throws NullPointerException
	 * mode가 READ인데 write를 호출하면  NullPointerException을 던진다.
	 */
	public void writeImage(Bitmap img) throws IOException{
		
		if(mode == READ)
			DebugUtils.throwException();
		
		String fileName = file.getName();
		String [] splitStr = fileName.split(".");
		
		if( splitStr[splitStr.length - 1].equals("jpg") )
			img.compress(CompressFormat.JPEG, 100, out);
		else if( splitStr[splitStr.length - 1].equals("png")){
			img.compress(CompressFormat.PNG, 100, out);
		}
		else
			DebugUtils.throwException();
	//	out.write(buf);
	}

	/**
	 * 파일내에 파일 포인터가 가르치는 곳을 offset만큼 이동한다.
	 * (아직 미구현)
	 * @param offset
	 * 파일포인터를 이동하고자 하는 위치
	 * @param seekorigin
	 * 파일을 이동하기 전에 파일포인터의 위치
	 * @throws EOFException
	 * 파일 포인터를 이동하다가 파일의 끝에 도달하면 EOFException을 던진다.
	 */
	public void seek(int offset, SeekOrigin seekorigin) throws EOFException{
		
	}
	
	/**
	 * 현재의 파일포인터의 위치를 얻는다.
	 * (아직 미구현)
	 */
	public void getPosition(){
		
	}
	
	/**
	 * 파일의 길이를 가져온다.
	 * 단위는 byte
	 */
	public int getLength(){
		return (int) file.length();
		
		
	}
	
	/**
	 * 파일의 내용을 저장소 상에 기록한다.
	 * (삭제예정)
	 * @throws IOException 
	 * 이미 닫혀있으면 IOException 을 던진다.
	 * @throws NullPointerException
	 * mode가 READ인 상태에서 flush를 호출하면 NullPointerException을 던진다.
	 */
	public void flush() throws IOException{
		switch(mode){
		case READ:
			DebugUtils.throwException();
			break;
			
		case WRITE:
		case WRITE_PLUS:
			out.flush();
			fileOutputStream.flush();
			break;
		}
	}
	
	/**
	 * StorageFile의 이름을 반환한다.
	 * @return StorageFile의 이름
	 */
	public String getName(){
		return file.getName();
	}
	
	/**
	 * 파일을 닫고 저장한다.
	 * @throws IOException 
	 * 이미 닫혀있으면 IOException 을 던진다.
	 */
	public void close() throws IOException{
		switch(mode){
		case READ:
			in.close();
			fileInputStream.close();
			break;
			
		case WRITE:
		case WRITE_PLUS:
			out.close();
			fileOutputStream.close();
			break;
		}
	}
	
	/**
	 * storageFile의 SOD루트 저장소 기준 상대경로를 반환한다.
	 * @return SOD루트 저장소 기준 상대경로
	 */
	public String getRelativeFilePath(){
		return filePath;
	}
	
	/**
	 * storageFile의 절대경로를 반환한다.
	 * @return 절대경로
	 */
	public String getAbsoluteFilePath(){
		return file.getAbsolutePath();
	}
	
	
}
