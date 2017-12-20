package avps.labs;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * ����� �������� ����������� ����������� SAX
 * org.xml.sax.helpers.DefaultHandler 
 */
public class ParseBooksSAX extends DefaultHandler {
// ��������� ����� ��� ���������� �������� 
// ��������� ��������� ���������
private StringBuffer stringBuffer;

public static void main(String args[]) {
	String fileName = "books.xml";

	// ������� ��������� ����������� SAX
	DefaultHandler defaultHandler = new ParseBooksSAX();
	// ������� ��������� ������ SAXParserFactory 
	SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
	try {			
		// ������� ��������� ������ SAXParser 
		SAXParser Sax_Parser = saxParserFactory.newSAXParser();			
		// ������ ������� XML ����� 
		Sax_Parser.parse(new File(fileName), defaultHandler);
	} 
	catch (Exception e) {
		e.printStackTrace();
	}		
}

	/**
	 * ������������ ������� ������ ���������
	 */
	public void startDocument() throws SAXException {
		System.out.println("<?xml version = '1.0' encoding = 'UTF-8'?>");		
	}
	
	/**
	 *  ������������ ������� ����� ��������� 
	 */	
	public void endDocument() throws SAXException {}
	
	/**
	 *  ������������ ������� ������ �������� <...>
	 */
	public void startElement(String namespaceURI, String localName, String qName, Attributes attrs) throws SAXException {
		displayBufferText();
		if ("".equals(localName))
			localName = qName;
		System.out.print("<" + localName);
		if (attrs != null) {
			// �������� ���������� ��������� ��������
			for (int i = 0; i < attrs.getLength(); i++) {
				// �������� ��������� ��� �������� 
				String aName = attrs.getLocalName(i);
				if ("".equals(aName))
					aName = attrs.getQName(i);
				System.out.print(" ");
				// �������� �������� ��������
				System.out.print(aName + "=\"" + attrs.getValue(i) + "\"");
			}
		}
		System.out.print(">");
	}
	
	/**
	 *  ������������ ������� ����� �������� </...>
	 */
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		displayBufferText();
		if ("".equals(localName))
			localName = qName;
		System.out.print("</" + localName + ">");
	}
	
	/** ������������ ������� ���������� ������ 
	 *  ���������� �������� <...>some_text</...>
	 */ 	 
	public void characters(char buf[], int offset, int len) throws SAXException {
		String s = new String(buf, offset, len);
		// ���� ��������� ����� ����� null, ������� �����
		if (stringBuffer == null)
			stringBuffer = new StringBuffer(s);
		else
			// ��������� ������� � ��������� ����� 
			stringBuffer.append(s);
	}
	
	/** 
	 *  ������� �����, ��������� � ��������� ������
	 */
	private void displayBufferText() {
		if (stringBuffer != null) {
			System.out.print(stringBuffer.toString());
			stringBuffer = null;
		}		
	}
}
