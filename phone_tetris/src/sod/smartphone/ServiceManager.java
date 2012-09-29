package sod.smartphone;

import java.io.IOException;
import java.net.SocketException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;

import sod.common.Constants;
import sod.common.Packet;
import sod.common.Storage;
import sod.common.StorageFile;


/**
 * 클라이언트에서 서비스파일의 경로는 /sod/SERVICE_NAME/service
 * @author MB
 *
 */
public class ServiceManager {
	
	ServiceReceiveHandler mHandler;
	
	static final int TEXT_TYPE = 0;
	static final int IMAGE_TYPE = 1;
	static final int FILE_NEXT = 2;
	static final int FILE_END = 3;
	
	
	byte serviceBuf[];
	boolean isFirst;
	int sBufIndex;
	

	
	
	ServiceManager(){
		serviceBuf = new byte[10000];
		isFirst = true;
		sBufIndex = 0;
		

	}

	/**
	 * 서비스가 설치되어 있는지 확인
	 * @param serviceName
	 * 서비스 이름
	 * @return
	 * 서비스가 설치되어 있으면 true 아니면 false
	 * @throws IllegalArgumentException
	 * 매게변수가 null일 때 IllegalArgumentException이 발생한다.
	 */
	public boolean findServiceByName(String serviceName) throws IllegalArgumentException{
		
		if(Storage.checkStorageIs(serviceName)){
			if(Storage.checkStorageIs(serviceName + "/service"))
				return true;
		}
		
		return false;	
	}

	/**
	 * 해당 이름의 서비스 시작
	 * @param serviceName
	 * 서비스 이름
	 * @throws IllegalArgumentException
	 * 매개변수가 null이거나 해당 이름의 서비스가 설치되지 않은 경우 발생
	 * @throws IllegalStateException
	 * 두번 이상 호출될 때 발생.
	 */
	public void startService(String serviceName) throws IllegalArgumentException, IllegalStateException{
		
	}

	/**
	 * 설치되어 있는 서비스 목록 반환
	 * @return
	 * 서비스 목록
	 */
	public String[] getServiceList() {

		Storage storage = null;

		try {
			if (Storage.checkStorageIs(""))
				storage = Storage.getStorage("");
			else
				storage = Storage.createStorage("");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return storage.getFileList("*");
	}

	/**
	 * 특정 서비스를 제거
	 * 
	 * @param serviceName
	 *            서비스 이름
	 * @throws IllegalArgumentException
	 *             매개변수가 null이거나 해당 serviceName에 일치하는 서비스 데이터가 없을 경우 발생.
	 */
	public void removeService(String serviceName) throws IllegalArgumentException{
		Storage storage = null;

		try {
			if (Storage.checkStorageIs(serviceName)){
				Storage.destroy(serviceName);
			}
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 서비스 다운로드시 호출할 콜백 함수 등록
	 * @param handler
	 * 콜백함수를 구현한 객체
	 * @throws IllegalArgumentException
	 * 매개변수가 null인 경우 발생.
	 */
	public void setSeriveReceiveHandler(ServiceReceiveHandler handler) throws IllegalArgumentException{
		mHandler = handler;
	}
	
	
	/**
	 * 서비스 파일 정보가 있는 패킷으로부터 서비스 파일을 생성하는 함수 
	 * @param pkt 서비스 파일 정보가 담긴 패킷
	 */
	public void installService(Packet pkt){
		String serviceName = (String)pkt.pop(); // 1. 패킷에서 첫번째로 나오는 것은 serviceName (String)
		String serviceFilePath = serviceName + "/service";
		
		String relativePath = (String) pkt.pop();// 2. 두번째로 나오는 것은 상대 path
													// (String)


		try {

			Storage serviceStorage = null;
			if (relativePath.equals("/")) {

				if (!Storage.checkStorageIs(serviceFilePath))
					serviceStorage = Storage.createStorage(serviceFilePath);
				else
					serviceStorage = Storage.getStorage(serviceFilePath);
			} 
			else {// 상대패스가 있으면 serviceFilePath를 업데이트 한다.

				serviceFilePath = serviceFilePath + "/" + relativePath;

				if (!Storage.checkStorageIs(serviceFilePath))
					serviceStorage = Storage.createStorage(serviceFilePath);
				else
					serviceStorage = Storage.getStorage(serviceFilePath);

			}

			String fileName = (String)pkt.pop();//3. 세번째로 나오는 것은 fileName
			Constants.logger.log(fileName);////////////////////////////////////////////////////////debug
			Integer fileSize = (Integer)pkt.pop();// 4. 네번째로 나온는 것은 총 fileSize
			
			if(isFirst){
				serviceBuf = new byte[fileSize];
				isFirst = false;
			}
			
			Integer fileType = (Integer)pkt.pop();//5. 다섯번째로 나오는 것은 파일타입
			
			//text type이냐, image타입에 따라서 다르게 실행된다.
			if(fileType == ServiceManager.TEXT_TYPE){
				
				byte [] buf = (byte [])pkt.pop(); //6. 여섯번째로 나오는 것은 데이터
				//////////
				Integer nextAend = (Integer)pkt.pop(); //7. 끝이인지 다음에도 있는지
				//파일의 끝이면 작성을 시작한다.
				if(nextAend == ServiceManager.FILE_END){ //FileEnd
					isFirst = true;
					
					for(int i = 0 ;  i < buf.length; i++ ){
						serviceBuf[sBufIndex] = buf[i];
						sBufIndex++;
					}
					sBufIndex = 0; //sBufIndex init...
					///////////
					StorageFile serviceFile = serviceStorage.createFile(fileName); // 파일 생성
					serviceFile.write(serviceBuf);
					serviceFile.close();
				}
				else{//Next
					for(int i = 0 ;  i < buf.length; i++ ){
						
						if(fileSize < sBufIndex){ //debug code...
							Constants.logger.log("ffsdffsdfdsfsdfsdsfsfdsfsrgferferferferf");
						}
						else{
						serviceBuf[sBufIndex] = buf[i];
						sBufIndex++;
						}
					}
				}// end Next else...
				
			}
			else{//5.1 이미지 일 때
				//to Do
				Integer width = (Integer)pkt.pop(); //6.1 가로 길이 가져오고
				Integer height = (Integer)pkt.pop(); //6.2 세로 길이 가져오고
				
				byte [] buf = (byte [])pkt.pop(); //7. 일곱번째로 나오는 것은 데이터
				//////////
				Integer nextAend = (Integer)pkt.pop(); //7. 끝이인지 다음에도 있는지
				///////////
				if(nextAend == ServiceManager.FILE_END){ //FileEnd
					isFirst = true;
					
					for(int i = 0 ;  i < buf.length; i++ ){
						serviceBuf[sBufIndex] = buf[i];
						sBufIndex++;
					}
					sBufIndex = 0; //sBufIndex init...
					///////////
					
					Bitmap img = BitmapFactory.decodeByteArray(serviceBuf, 0, serviceBuf.length);
					Bitmap serviceImg = Bitmap.createBitmap(img, 0, 0, width, height);
				
					StorageFile serviceFile = serviceStorage.createFile(fileName);
					serviceFile.writeImage(serviceImg);
					serviceFile.close();
				}
				else{//Next
					for(int i = 0 ;  i < buf.length; i++ ){
						serviceBuf[sBufIndex] = buf[i];
						sBufIndex++;
					}//end for
				}//end else...

			}//end else 5.1 이미지일때
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
//			Constants.logger.log("IllegalArgumentException - installService");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
//			Constants.logger.log("IOException - installService");
		}

		
	}

	
	/**
	 * 해당하는 서비스 파일이 스마트폰에 설치되있는지 확인한다.
	 * @param serviceName 서비스 이름
	 * @return 설치되있으면 true, 아니면 false
	 */
	public boolean checkServiceInstalled(String serviceName){
		
		return Storage.checkStorageIs(serviceName);
	}
	
	/**
	 * 파일이 text타입인지 확인한다.
	 * @param fileName
	 * @return text타입이면 true, 아니면 false
	 */
	protected boolean isText(String fileName){
		if( (fileName.indexOf(".js") != -1 ) && (fileName.indexOf(".html") != -1) && (fileName.indexOf(".css") != -1)   )
			return false;
		else
			return true;
	}
	
	
	
}
