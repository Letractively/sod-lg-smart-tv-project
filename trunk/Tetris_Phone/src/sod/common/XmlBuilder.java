package sod.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Semaphore;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XmlBuilder {
	protected static DocumentBuilderFactory dbf;
	protected static DocumentBuilder db;
	protected static Document doc;
	
	protected static TransformerFactory tff;
	protected static Transformer tf;	
	
	static {
		try {
			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
			doc = db.newDocument();
			
            tff = TransformerFactory.newInstance();
            tf = tff.newTransformer();
            
            tf.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static Document parse(InputStream in){
		synchronized(db){
			try {
				return db.parse(in);
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
	}
	
	public static Document createDoc(){
		Document doc;
		synchronized(db){
			doc = db.newDocument();
		}
		return doc;
	}
	
	public static Transformer getTransformer(){
		return tf;
	}
	
}
