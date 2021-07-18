package opensamlems;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xml.security.exceptions.Base64DecodingException;
import org.opensaml.DefaultBootstrap;
import org.opensaml.common.SAMLException;
import org.opensaml.saml2.core.LogoutResponse;
import org.opensaml.saml2.core.Response;
import org.opensaml.xml.Configuration;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.Unmarshaller;
import org.opensaml.xml.io.UnmarshallerFactory;
import org.opensaml.xml.io.UnmarshallingException;
import org.opensaml.xml.util.Base64;
import org.opensaml.xml.util.XMLConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SAMLUtil
{
	private static boolean bootStrapped = false;

	public  Response decodeSAMLResponse(String responseMessage)
		    throws ConfigurationException,
		    SAXException, IOException, UnmarshallingException, SAMLException, ParserConfigurationException 
		{
		  doBootstrap();
		  DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		  documentBuilderFactory.setNamespaceAware(true);
		  DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
		  byte[] base64DecodedResponse = Base64.decode(responseMessage);
		  ByteArrayInputStream bytes=new ByteArrayInputStream(base64DecodedResponse);
		  Document document = docBuilder.parse(bytes);
		  Element element = document.getDocumentElement();
		  UnmarshallerFactory unmarshallerFactory = Configuration.getUnmarshallerFactory();
		  Unmarshaller unmarshaller = unmarshallerFactory.getUnmarshaller(element);
		  return (Response) unmarshaller.unmarshall(element);
		}
	
    public static void doBootstrap()
	{
		 System.out.println("In doBootstrap");
	        if (!bootStrapped) {
	            try {
	                DefaultBootstrap.bootstrap();
	                bootStrapped = true;
	            } catch (Exception e) {
	            	e.printStackTrace();
	            }
	        }
	}

	public  LogoutResponse decodeSAMLLogoutResponse(String responseMessage) throws ParserConfigurationException, SAXException, IOException, UnmarshallingException 
	{
		  doBootstrap();
		  DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		  documentBuilderFactory.setNamespaceAware(true);
		  DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
		  Document document = docBuilder.parse(new InputSource(new StringReader(responseMessage)));
		  Element element = document.getDocumentElement();
		  UnmarshallerFactory unmarshallerFactory = Configuration.getUnmarshallerFactory();
		  Unmarshaller unmarshaller = unmarshallerFactory.getUnmarshaller(element);
		  return (LogoutResponse) unmarshaller.unmarshall(element);
	}
	public  Response decodeSAMLLoginResponse(String responseMessage) throws ParserConfigurationException, SAXException, IOException, UnmarshallingException 
	{
		  doBootstrap();
		  DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		  documentBuilderFactory.setNamespaceAware(true);
		  DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
		  Document document = docBuilder.parse(new InputSource(new StringReader(responseMessage)));
		  Element element = document.getDocumentElement();
		  UnmarshallerFactory unmarshallerFactory = Configuration.getUnmarshallerFactory();
		  Unmarshaller unmarshaller = unmarshallerFactory.getUnmarshaller(element);
		  return (Response) unmarshaller.unmarshall(element);	
	}
	 
}