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
	 * ���������� ������ main
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		try {
			String filename = "books.xml";

			// ������� ��������� ������ DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// ������� ��������� ������ DocumentBuilder
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			// ��������� ������ �������� �����
			Document doc = docBuilder.parse(new File(filename));

			// ������� ������ DOM �� �����
			new ParseBooksDOM().printNode(doc, "");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ������������ ������� <some_element></some_element>
	 * 
	 * @param node
	 * @param indent ������
	 */
	private void dumpElement(Element node, String indent) {
		System.out.println(indent + "ELEMENT: " + node.getTagName());
		// ������������ �������� ��������
		NamedNodeMap nm = node.getAttributes();
		for (int i = 0; i < nm.getLength(); i++)
			printNode(nm.item(i), indent + "	");
	}

	/**
	 * ������������ ������� <... some_atribute = "some_value">
	 * 
	 * @param node
	 * @param indent ������
	 */
	private void dumpAttributeNode(Attr node, String indent) {
		System.out.println(indent + "ATTRIBUTE " + node.getName() + "=\"" + node.getValue() + "\"");
	}

	/**
	 * ������������ ��������� ���������� 
	 * �������� <...>some_text</...>
	 * 
	 * @param node
	 * @param indent ������
	 */
	private void dumpTextNode(Text node, String indent) {
		System.out.println(indent + "TEXT: " + node.getData());
	}

	/**
	 * ����������� ��������� ��������� ����� 
	 * XML-���������
	 * 
	 * @param node �������������� ����
	 * @param indent ������
	 */
	private void printNode(Node node, String indent) {
		// �������� ��� ����
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

		// ���������� ������������ �������� ����
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++)
			printNode(list.item(i), indent + "	");
	}
}

