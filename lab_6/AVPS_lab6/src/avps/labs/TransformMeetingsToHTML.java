package avps.labs;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class TransformMeetingsToHTML {
	public TransformMeetingsToHTML(String date) throws TransformerException, TransformerConfigurationException, FileNotFoundException, IOException {
		String inputFile = "meetings.xml";
		String xslFile = "meetings.xsl";
		String outputFile = "meetings.html";

		// ������� ��������� TransformerFactory 
		TransformerFactory transFactory = TransformerFactory.newInstance();
		// ������� ��������� Transformer 
		Transformer transformer = transFactory.newTransformer(new StreamSource(xslFile));	
		transformer.setParameter("needed_date", date);
		// ��������� �������������  
		transformer.transform(new StreamSource(inputFile), new StreamResult(new FileOutputStream(outputFile)));
		// ������� ��������� � ���, ��� ��������� ������� � ����
		System.out.println("��������� HTML-����� " + outputFile + " ���������");
	}
}
