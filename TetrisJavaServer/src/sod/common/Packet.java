package sod.common;


import java.util.LinkedList;
import java.util.Queue;


/**
 * 
 * @author MB a unit of transferring
 */

public class Packet implements Cloneable {
	
	public static final String DataType_Int = "int";
//	public static final String DataType_Long = "long";
	public static final String DataType_Float = "float";
	public static final String DataType_Double = "double";
	public static final String DataType_String = "string";
	public static final String DataType_ByteArray = "bytearray";
	
	/*
	 * 클라이언트에서 접속을 요청하는 패킷 시그니쳐
	*/
	public static final int REQUEST_ACCEPT = 0xFF000010;
	
	/*
	 * 서버에서 접속을 승인했음을 알려주는 패킷 시그니쳐
	*/
	public static final int RESPONSE_ACCEPT = 0xFF000011;
	
	/*
	 * 서버가 존재하는지 알기 위해 클라이언트에서 보내는 패킷 시그니쳐
	*/
	public static final int REQUEST_PING = 0xFF000020;
	
	/*
	 * 클라이언트에서 요청한 핑에 응답하는 패킷 시그니쳐
	*/
	public static final int RESPONSE_PING = 0xFF000021;
	

	/**
	 * 스마트TV에서 스마트폰의 접속상태가 유효한지를 체크하기위해 보내는 패킷의 시그니쳐
	 */
	public static final int REQUEST_CLIENT_ALIVE = 0xFF000001;

	/**
	 * 스마트TV로부터 접속상태 확인 패킷을 받은 후, 접속상태가 양호하다는 응답 패킷의 시그니쳐
	 */
	public static final int RESPONSE_CLIENT_ALIVE = 0xFF000002;

	/**
	 * 스마트TV로부터 서비스명칭을 받은 후, 서비스를 실행시키기위한 파일이 있는지를 확인하고 파일이 없을때 보내는 패킷의 시그니쳐
	 */
	public static final int REQUEST_SERVICE_DATA = 0xFF000003;

	/**
	 * 스마트폰으로부터 서비스가 없다는 패킷을 받은 후, 서비스 실행과 관련된 파일을 보내주는 패킷의 시그니쳐
	 */
	public static final int RESPONSE_SERVICE_DATA = 0xFF000004;
	
	/**
	 * 서비스 전송이 모두 완료됐다는 패킷의 시그니쳐
	 */
	public static final int RESPONSE_SERVICE_DATA_END = 0xFF000005;
	
	/**
	 * 스마트폰이 스마트TV에 서비스 패킷을 잘 받았다는 패킷의 시그니쳐
	 */
	public static final int RESPONSE_SERVICE_DATA_ARK = 0xFF000006;

	
	protected LinkedList<String> typeset;
	protected LinkedList<Object> dataset;
	
	
	/**
	 * 개발자가 지정한 시그니쳐로 구분하여 패킷의 용도가 결정된다. 0xff000000~0xff000004는 시스템에서 할당한
	 * 시그니쳐이므로 개발자는 사용하면 안된다.
	 */
	public int signiture = 0;
	
	public Packet(){
		typeset = new LinkedList<String>();
		dataset = new LinkedList<Object>();
	}
	
	@SuppressWarnings("unchecked")
	public Object clone() throws CloneNotSupportedException{
		Packet returnPkt = (Packet)super.clone();
		returnPkt.typeset = (LinkedList<String>)typeset.clone();
		returnPkt.dataset = (LinkedList<Object>)dataset.clone();
		return returnPkt;
	}

	/**
	 * 패킷에 들어있는 객체들의 개수를 반환한다. 패킷을 받은 후 처리하는 과정에서 사용하면 된다.
	 * 
	 * @return 패킷에 들어있는 객체의 개수
	 */
	public int getElementCount(){
		return dataset.size();
	}
	
	public String getTopElementType() {
		if (dataset.size() == 0)
			return null;
		Object o = dataset.peek();

		typeset.peek();

		if (o instanceof Integer)
			return DataType_Int;
		// if(o instanceof Long)
		// return DataType_Long;
		if (o instanceof Float)
			return DataType_Float;
		if (o instanceof Double)
			return DataType_Double;
		if (o instanceof String)
			return DataType_String;
		if (o instanceof byte[])
			return DataType_ByteArray;
		else
			return null;

	}

	/**
	 * 패킷을 만들때, 패킷에 객체를 집어넣을 때 사용하는 메소드이다. 객체는 무한대로 들어간다.
	 * 
	 * @param item
	 *            사용자가 패킷에 집어넣고자 하는 객체
	 * @throws UnsupportedDataTypeException
	 *             패킷에서 push할 때, 지원이 되지않는 객체일 때는 UnsupportedDataTypeException을
	 *             던진다. 지원되는 객체의 타입은 Int, Double, String, ByteArray 등이다.
	 */
	public boolean push(Object item) {
		if(item == null) return false;
		if(item instanceof Integer ||
//		   obj instanceof Long ||
		   item instanceof Float ||
		   item instanceof Double ||
		   item instanceof String ||
		   item instanceof byte[])
			dataset.offer(item);
		else
			return false;
		return true;
	}
	
	/**
	 * 패킷을 만들때, 패킷에 객체를 집어넣을 때 사용하는 메소드이다. 객체는 무한대로 들어간다
	 * @param type 객체의 타입
	 * @param item 사용자가 패킷에 집어넣고자 하는 객체
	 * @return
	 */
	public boolean push(Object item, String type){
		
		typeset.offer(type);
		dataset.offer(item);
		
		return true;
	}

	/**
	 * 패킷에서 객체를 꺼낼때 사용하는 메소드이다. getElementCount()를 이용하여 개수를 파악하고 pop()을 이용하면 더욱
	 * 편리하게 이용 가능하다.
	 * 
	 * @return 패킷에 담겨있던 객체 중에 제일 먼저 집어넣은 객체
	 * @throws NullPointerException
	 *             내부의 객체의 개수가 '0'이 되면 IllegalStateException을 던진다.
	 */
	public Object pop() {
		if(dataset.size() == 0)
			return null;
		
		typeset.poll();
		return dataset.poll();
	}
	
	/**
	 * 패킷에서 객체를 꺼낼때 사용하는 메소드이다.
	 * type에 대응되는 Object를 반환한다.
	 * @param type
	 * @return type에 대응되는 Object, 없으면 -1 반환
	 */
	public Object pick(String type){
		//다시 수정해야함
		int index = typeset.indexOf(type);
		if( index == -1)
			return null;
		else{
			typeset.remove(index);
			return dataset.remove(index);
		}
		
	}

	/**
	 * 패킷 객체를 재사용하기 위해 모든 내용을 제거
	 */
	public void clear(){
		signiture = 0;
		
		while(typeset.size() > 0)
			typeset.poll();
		
		while(dataset.size() > 0)
			dataset.poll();
	
	}
}


