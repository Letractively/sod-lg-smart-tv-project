package sod.smarttv;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import sod.common.Constants;
import sod.common.Packet;
import sod.common.Storage;
import sod.common.StorageFile;

/**
 * 서버에서 서비스파일의 경로는 /sod/lib
 * @author MB
 *
 */
public class ServiceProvider {
	
	String serviceName; // /ana
	String serviceFilePath ; // /ana/lib
	
	static final int TEXT_TYPE = 0;
	static final int IMAGE_TYPE = 1;
	static final int FILE_NEXT = 2;
	static final int FILE_END = 3;
	
	static final int MTU = 10000;//0x3765;//0x000007530
	
	
	public ServiceProvider(String serviceName){
		this.serviceName = serviceName;
		this.serviceFilePath = "/lib";
		 
	}
	
	/**
	 * set files path where content files are stored.
	 * @param newpath
	 * 변경할 디렉토리 경로
	 * @throws IllegalArgumentException
	 * 매개변수가 null일때 발생
	 */
	void setPath(String newpath) throws IllegalArgumentException{
		serviceFilePath = newpath;
	}
	
	/**
	 * 서비스 데이터를 담고있는 패킷을 반환
	 * 최초 호출시 createServicePacket()이 내부적으로 호출된다.
	 * @return
	 * 서비스 데이터를 담은 패킷
	 */
    public ArrayList<Packet> getServicePacket(){
		Storage storage;
		StorageFile [] serviceFiles = null;
		ArrayList<Packet> serviceFilePackets = new ArrayList<Packet>();
		
		try {
			
			if (Storage.checkStorageIs(serviceFilePath))
				storage = Storage.getStorage(serviceFilePath);
			else
				storage = Storage.createStorage(serviceFilePath);
			
			String [] fileList = storage.getFileList("*");
			
			
			for(int i = 0 ; i<fileList.length ; i++ ){
				if( isDirectory(fileList[i])){//디렉토리일때는 하위 파일의 목록을 다시 가져와서 패킷 생성
					getServicePacketSubDirectory(serviceFilePath, fileList[i] ,fileList[i], serviceFilePackets);
				}
				else{//파일일때...패킷생성
					
					StorageFile openServiceFile = storage.openFile(fileList[i], Storage.READ);
					
					
					//파일의 정보를 가진 패킷을 가져온다. 30kB이상이면 나눠서 가져온다.
					ArrayList<Packet> subServiceFilePackets = createServicePacketList(openServiceFile, "/");
					//넣는다.
					for(int j = 0 ; j < subServiceFilePackets.size() ; j++)
						serviceFilePackets.add(subServiceFilePackets.get(j));
					
				}
				
			}
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			Constants.logger.log("(debug:server)IllegalArgumentException\n");
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Constants.logger.log("(debug:server)IOException .\n");
			return null;
		}
		
		Constants.logger.log("(debug:server)getServicePacket() Success");
		return serviceFilePackets;
	}

    /**
     * 하위디렉토리의 파일로부터 패킷들을 생성한다. 
     * @param serviceFilePath
     * @param subPath
     * @param addPath
     * @param serviceFilePackets
     */
	protected void getServicePacketSubDirectory(String serviceFilePath,
			String subPath, String addPath, ArrayList<Packet> serviceFilePackets) {

		Storage subStorage;
		serviceFilePath = serviceFilePath + "/" + addPath;
		try {

			subStorage = Storage.getStorage(serviceFilePath);

			String[] subFileList = subStorage.getFileList();

			for (int j = 0; j < subFileList.length; j++) {

				if (isDirectory(subFileList[j])) {
					getServicePacketSubDirectory(serviceFilePath, subPath+"/"+subFileList[j] , subFileList[j], serviceFilePackets);
				} else {
					StorageFile openServiceFile = subStorage.openFile(subFileList[j], Storage.READ);
					//파일의 정보를 가진 패킷을 가져온다. 
					ArrayList<Packet> subServiceFilePackets = createServicePacketList(openServiceFile, subPath);
					
					//넣는다 
					for(int k =0 ; k < subServiceFilePackets.size() ; k++){
						serviceFilePackets.add(subServiceFilePackets.get(k));
					}
				}
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
	 * create packet which contains every content files need to service.
	 * @return
	 * 서비스 데이터를 담은 패킷
	 * @throws IllegalStateException
	 * 내부에 지정된 경로가 없거나 잘못되었을 경우 발생
	 */
	protected ArrayList<Packet> createServicePacketList(StorageFile openServiceFile, String path) throws IllegalStateException{
	//	Packet packet = new packet
		ArrayList<Packet> subServicePackets = new ArrayList<Packet>();
		
		byte [] buf = new byte[openServiceFile.getLength()];//
		try {
			openServiceFile.read(buf);
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//
		int fileSize = openServiceFile.getLength();
		int packetCnt = fileSize / MTU + 1;////////////////////////////////////////////////
		int lastPacketDataSize = fileSize % MTU;
		
		for(int i = 0 ; i < packetCnt ; i++){
			Packet servicePacket = createServicePacket(openServiceFile, path); //1~4 과정을 거침
			
			byte [] subBuf;
			int byteSize;
			
			if (i == packetCnt - 1) { // 해당 파일의 마지막 패킷일때
				subBuf = new byte[lastPacketDataSize];
				byteSize = lastPacketDataSize;
			} else {
				subBuf = new byte[MTU];
				byteSize = MTU;
			}
			
			int k = 0;
			for(int j = i*MTU; j < (i*MTU + (byteSize)); j++){

				subBuf[k] = buf[j];
				k++;
			}//end for....
			//여기는 짤라서 넣어야하고
			servicePacket.push(subBuf);//7. data를 넣는다. (이미지는 7)
			
			
			//6. 파일의 마지막인지, 계속 이어지는지 여부를 표시한다.
			if (i == packetCnt - 1){
				servicePacket.push((Integer)ServiceProvider.FILE_END);
			}
			else{
				servicePacket.push((Integer)ServiceProvider.FILE_NEXT);
			}
			
			subServicePackets.add(servicePacket);
		}//end for....
		
		try {
			openServiceFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return subServicePackets;
	}
	
	/**
	 * 패킷을 생성해서 파일의 정보를 담는다.
	 * @param openServiceFile
	 * @param path
	 * @return
	 * @throws IllegalStateException
	 */
	protected Packet createServicePacket(StorageFile openServiceFile, String path) 
			throws IllegalStateException{
		
		Packet servicePacket = new Packet();
		
		servicePacket.signiture = Packet.RESPONSE_SERVICE_DATA; // 0. 시그니쳐 설정
		
		servicePacket.push(serviceName);//1. 서비스명 넣고 (String)
		servicePacket.push(path);//2.서비스 파일의 상대경로 (  /서비스명/service   의 상대경로 ) (String)
		servicePacket.push(openServiceFile.getName()); //3. 파일 이름을 넣는다. (String)
		Constants.logger.log("send : "+openServiceFile.getName());
		servicePacket.push((Integer)openServiceFile.getLength());//4. 파일의 총 사이즈
		if( isText(openServiceFile.getName())){ // 5.서비스파일의 타입을 넣는다 (int)
			// 텍스트라면
			servicePacket.push((Integer)ServiceProvider.TEXT_TYPE);
		}
		else{//이미지라면
			servicePacket.push((Integer)ServiceProvider.IMAGE_TYPE);
			
			String absoluteFilePath = openServiceFile.getAbsoluteFilePath();
			Bitmap img= BitmapFactory.decodeFile(absoluteFilePath);
			
			servicePacket.push((Integer)img.getWidth() );//6.1 가로길이 넣고
			servicePacket.push((Integer)img.getHeight() );//6.2 세로길이 넣고
		}
		
		return servicePacket;
	}
	
	/**
	 * 해당 파일의 디렉토리여부를 알려준다.
	 * @param fileName
	 * @return 디렉토리면 true, 아니면 false
	 */
	protected boolean isDirectory(String fileName){
		if(fileName.indexOf(".") == -1)
			return true;
		else
			return false;
	}
	
	/**
	 * 파일의 text타입 여부를 알려준다.
	 * @param fileName
	 * @return text 타입이면 true, 아니면 false
	 */
	protected boolean isText(String fileName){
		if( (fileName.indexOf(".js") != -1 ) && (fileName.indexOf(".html") != -1) && (fileName.indexOf(".css") != -1)   )
			return false;
		else
			return true;
	}
}
