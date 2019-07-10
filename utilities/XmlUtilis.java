package utilities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XmlUtilis {

    private static File xmlFile;
    private static NodeList customerNodes;

    public static Map getCustomer(String xml_file, int index) throws ParserConfigurationException, IOException, SAXException {

        Log.info("Reading data from XML file");
        xmlFile = new File(xml_file);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);
        customerNodes = doc.getElementsByTagName("customer");

        Node customerNode = customerNodes.item(index);
        Map<String, String> customer = new HashMap<String, String>();

        Element customerElement = (Element) customerNode;
        String customer_value = customerElement.getElementsByTagName("name").item(0).getTextContent();
        customer.put("name", customer_value);
        customer_value = customerElement.getElementsByTagName("gender").item(0).getTextContent();
        customer.put("gender", customer_value);
        customer_value = customerElement.getElementsByTagName("date").item(0).getTextContent();
        customer.put("date", customer_value);
        customer_value = customerElement.getElementsByTagName("address").item(0).getTextContent();
        customer.put("address", customer_value);
        customer_value = customerElement.getElementsByTagName("city").item(0).getTextContent();
        customer.put("city", customer_value);
        customer_value = customerElement.getElementsByTagName("state").item(0).getTextContent();
        customer.put("state", customer_value);
        customer_value = customerElement.getElementsByTagName("pin").item(0).getTextContent();
        customer.put("pin", customer_value);
        customer_value = customerElement.getElementsByTagName("number").item(0).getTextContent();
        customer.put("number", customer_value);
        customer_value = customerElement.getElementsByTagName("email").item(0).getTextContent();
        customer.put("email", customer_value);
        customer_value = customerElement.getElementsByTagName("password").item(0).getTextContent();
        customer.put("password", customer_value);
        return customer;
    }
}
