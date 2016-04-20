import static org.junit.Assert.*;

import org.junit.Test;

import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;




public class Connect_SOAP {
    public static void main(String args[]) {

        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();


            String url = "http://services.aonaware.com/CountCheatService/CountCheatService.asmx?WSDL";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);


            printSOAPResponse(soapResponse);

            soapConnection.close();
            
            
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
        }
    }
    private static void printSOAPResponse(SOAPMessage soapResponse) throws SOAPException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);

    }
    private static SOAPMessage createSOAPRequest() throws Exception {
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

        return soapMessage;

    }
    
  
}
