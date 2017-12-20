package avps.labs;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * Класс является наследником обработчика SAX
 * org.xml.sax.helpers.DefaultHandler 
 */
public class ParseBooksSAX extends DefaultHandler {
// Строковый буфер для накопления символов 
// текстовых элементов документа
private StringBuffer stringBuffer;

public static void main(String args[]) {
	String fileName = "books.xml";

	// Создаем экземпляр обработчика SAX
	DefaultHandler defaultHandler = new ParseBooksSAX();
	// Создаем экземпляр класса SAXParserFactory 
	SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
	try {			
		// Создаем экземпляр класса SAXParser 
		SAXParser Sax_Parser = saxParserFactory.newSAXParser();			
		// Запуск парсера XML файла 
		Sax_Parser.parse(new File(fileName), defaultHandler);
	} 
	catch (Exception e) {
		e.printStackTrace();
	}		
}

	/**
	 * Обрабатывает событие начала документа
	 */
	public void startDocument() throws SAXException {
		System.out.println("<?xml version = '1.0' encoding = 'UTF-8'?>");		
	}
	
	/**
	 *  Обрабатывает событие конца документа 
	 */	
	public void endDocument() throws SAXException {}
	
	/**
	 *  Обрабатывает событие начала элемента <...>
	 */
	public void startElement(String namespaceURI, String localName, String qName, Attributes attrs) throws SAXException {
		displayBufferText();
		if ("".equals(localName))
			localName = qName;
		System.out.print("<" + localName);
		if (attrs != null) {
			// Получаем количество атрибутов элемента
			for (int i = 0; i < attrs.getLength(); i++) {
				// Получаем локальное имя атрибута 
				String aName = attrs.getLocalName(i);
				if ("".equals(aName))
					aName = attrs.getQName(i);
				System.out.print(" ");
				// Получаем значение атрибута
				System.out.print(aName + "=\"" + attrs.getValue(i) + "\"");
			}
		}
		System.out.print(">");
	}
	
	/**
	 *  Обрабатывает событие конца элемента </...>
	 */
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		displayBufferText();
		if ("".equals(localName))
			localName = qName;
		System.out.print("</" + localName + ">");
	}
	
	/** Обрабатывает событие символьных данных 
	 *  текстового элемента <...>some_text</...>
	 */ 	 
	public void characters(char buf[], int offset, int len) throws SAXException {
		String s = new String(buf, offset, len);
		// если строковый буфер равен null, создаем новый
		if (stringBuffer == null)
			stringBuffer = new StringBuffer(s);
		else
			// Добавляем символы в строковый буфер 
			stringBuffer.append(s);
	}
	
	/** 
	 *  Выводит текст, собранный в строковом буфере
	 */
	private void displayBufferText() {
		if (stringBuffer != null) {
			System.out.print(stringBuffer.toString());
			stringBuffer = null;
		}		
	}
}
