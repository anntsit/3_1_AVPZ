package avps.labs;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class ParseBooksDOM {
	/**
	 * Объявление метода main
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		try {
			String filename = "books.xml";

			// Создаем экземпляр класса DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// Создаем экземпляр класса DocumentBuilder
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			// Запускаем анализ входного файла
			Document doc = docBuilder.parse(new File(filename));

			// Выводим дерево DOM на экран
			new ParseBooksDOM().printNode(doc, "");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Обрабатывает элемент <some_element></some_element>
	 * 
	 * @param node
	 * @param indent отступ
	 */
	private void dumpElement(Element node, String indent) {
		System.out.println(indent + "ELEMENT: " + node.getTagName());
		// Обрабатываем атрибуты элемента
		NamedNodeMap nm = node.getAttributes();
		for (int i = 0; i < nm.getLength(); i++)
			printNode(nm.item(i), indent + "	");
	}

	/**
	 * Обрабатывает атрибут <... some_atribute = "some_value">
	 * 
	 * @param node
	 * @param indent отступ
	 */
	private void dumpAttributeNode(Attr node, String indent) {
		System.out.println(indent + "ATTRIBUTE " + node.getName() + "=\"" + node.getValue() + "\"");
	}

	/**
	 * Обрабатывает текстовое содержимое 
	 * элемента <...>some_text</...>
	 * 
	 * @param node
	 * @param indent отступ
	 */
	private void dumpTextNode(Text node, String indent) {
		System.out.println(indent + "TEXT: " + node.getData());
	}

	/**
	 * Рекурсивная процедура обработки узлов 
	 * XML-документа
	 * 
	 * @param node обрабатываемый узел
	 * @param indent отступ
	 */
	private void printNode(Node node, String indent) {
		// Получаем тип узла
		int type = node.getNodeType();
		switch (type) {
		case Node.ATTRIBUTE_NODE:
			dumpAttributeNode((Attr) node, indent);
			break;
		case Node.ELEMENT_NODE:
			dumpElement((Element) node, indent);
			break;
		case Node.TEXT_NODE:
			dumpTextNode((Text) node, indent);
			break;
		}

		// Рекурсивно обрабатываем дочерние узлы
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++)
			printNode(list.item(i), indent + "	");
	}
}

