package demo.saxreader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;

public class Main {
    public static void test(String xmlStr) throws DocumentException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xmlStr.getBytes());
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(byteArrayInputStream);
        System.out.println(document.getRootElement());
    }

    public static void main(String[] args) throws DocumentException {
        test("<abc name=\"123\"></abc>");
    }
}
