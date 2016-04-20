import static org.junit.Assert.*;

import java.io.StringWriter;

import org.junit.Test;

import junit.framework.Assert;

import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
public class Test_SOAP {

	@Test
	public void test() {
		fail("Not yet implemented");
		try {
	 
	         // change header's attribute
	          MessageFactory messageFactory = MessageFactory.newInstance();
	          SOAPMessage soapMessage = messageFactory.createMessage();
	          SOAPPart soapPart = soapMessage.getSOAPPart();

	          String serverURI = "http://services.aonaware.com/webservices/";

	          SOAPEnvelope envelope = soapPart.getEnvelope();
	          envelope.addNamespaceDeclaration("xsd", serverURI);

	          SOAPBody soapBody = envelope.getBody();
	          SOAPElement soapBodyElem = soapBody.addChildElement("LetterSolutions", "xsd");
	          SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("anagram", "xsd");
	          soapBodyElem1.addTextNode("testing");

	          MimeHeaders headers = soapMessage.getMimeHeaders();
	          headers.addHeader("SOAPAction", serverURI  + "LetterSolutions");

	          soapMessage.saveChanges();

	          System.out.print("Request SOAP Message:");
	          soapMessage.writeTo(System.out);
	          System.out.println();
	 
	         // Sending request
	         String endUrl ="http://services.aonaware.com/CountCheatService/CountCheatService.asmx?WSDL";
	         SOAPMessage soapResponseMessage = soapConnection. call(soapMessage, endUrl);
	 
	         // View the output
	         System.out.println("XML response\n");
	 
	         // Create transformer
	         TransformerFactory transformerFactory = TransformerFactory
	                                .newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	 
	         // Get reply content
	          Source source = soapResponseMessage.getSOAPPart().
	                 getContent();
	 
	         // Set output transformation
	         StringWriter writer = new StringWriter();
	         StreamResult sResult = new StreamResult(writer);
	         transformer.transform(source, sResult);
	         String result1 = writer.toString();
	         System.out.println(result1);
	         Assert.assertTrue(result1.contains("testing,setting,ingest,tinges,gents,gites,inset,singe"
	         		+ ",stein,sting,stint,tents,tines,tinge,tings,tints, gens,gent,gets,gins,gist,gite,gits,nest,"
	         		+ "nets,nett,nits,sent,sett,sign,sine,sing,site,tens,tent,test,ties,tine,ting,tins,tint,tits"));
	 
	         // Close connection
	         soapConnection.close();
	 
	     } catch (Exception e) {
	          System.out.println(e.getMessage());
	     }
	  }
	@Test
	public void test2(){
		fail("Not yet implemented");
		try {
	 
	         // change header's attribute
	          MessageFactory messageFactory = MessageFactory.newInstance();
	          SOAPMessage soapMessage = messageFactory.createMessage();
	          SOAPPart soapPart = soapMessage.getSOAPPart();

	          String serverURI = "http://services.aonaware.com/webservices/";

	          SOAPEnvelope envelope = soapPart.getEnvelope();
	          envelope.addNamespaceDeclaration("xsd", serverURI);

	          SOAPBody soapBody = envelope.getBody();
	          SOAPElement soapBodyElem = soapBody.addChildElement("LetterSolutionsMin", "xsd");
	          SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("anagram", "xsd");
	          SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("minLetters", "xsd");
	          soapBodyElem1.addTextNode("testing");
	          soapBodyElem2.addTextNode("6");

	          MimeHeaders headers = soapMessage.getMimeHeaders();
	          headers.addHeader("SOAPAction", serverURI  + "LetterSolutionsMin");

	          soapMessage.saveChanges();

	          System.out.print("Request SOAP Message:");
	          soapMessage.writeTo(System.out);
	          System.out.println();
	 
	         // Sending request
	         String endUrl ="http://services.aonaware.com/CountCheatService/CountCheatService.asmx?WSDL";
	         SOAPMessage soapResponseMessage = soapConnection. call(soapMessage, endUrl);
	 
	         // View the output
	         System.out.println("XML response\n");
	 
	         // Create transformer
	         TransformerFactory transformerFactory = TransformerFactory
	                                .newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	 
	         // Get reply content
	          Source source = soapResponseMessage.getSOAPPart().
	                 getContent();
	 
	         // Set output transformation
	         StringWriter writer = new StringWriter();
	         StreamResult sResult = new StreamResult(writer);
	         transformer.transform(source, sResult);
	         String result1 = writer.toString();
	         System.out.println(result1);
	         Assert.assertTrue(result1.contains("setting,testing,ingest,tinges"));
	 
	         // Close connection
	         soapConnection.close();
	 
	     } catch (Exception e) {
	          System.out.println(e.getMessage());
	     }
	  }
	
	          
	}