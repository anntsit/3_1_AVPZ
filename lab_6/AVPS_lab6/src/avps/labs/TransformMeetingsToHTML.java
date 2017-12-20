package avps.labs;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class TransformMeetingsToHTML {
	public TransformMeetingsToHTML(String date) throws TransformerException, TransformerConfigurationException, FileNotFoundException, IOException {
		String inputFile = "meetings.xml";
		String xslFile = "meetings.xsl";
		String outputFile = "meetings.html";

		// Создаем экземпляр TransformerFactory 
		TransformerFactory transFactory = TransformerFactory.newInstance();
		// Создаем экземпляр Transformer 
		Transformer transformer = transFactory.newTransformer(new StreamSource(xslFile));	
		transformer.setParameter("needed_date", date);
		// Выполняем трансформацию  
		transformer.transform(new StreamSource(inputFile), new StreamResult(new FileOutputStream(outputFile)));
		// Выводим сообщение о том, что результат записан в файл
		System.out.println("Генерация HTML-файла " + outputFile + " завершена");
	}
}
