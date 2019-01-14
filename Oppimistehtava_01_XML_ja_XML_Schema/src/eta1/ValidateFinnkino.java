// MUUTA ALLA OLEVAA RIVIÄ TARVITTAESSA
package eta1;
 
import static org.w3c.dom.Node.ELEMENT_NODE;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ValidateFinnkino {

	public static void main(String[] args) throws SAXException, IOException {
		final String xmlFile = "finnkino.xml";

		try {

			String schemaFile = "<?xml version='1.0'?> <xs:schema xmlns:xs='http://www.w3.org/2001/XMLSchema'>  	<xs:element name='Events'> 		<xs:complexType> 			<xs:sequence> 				<xs:element name='Event' type='EventType' maxOccurs='unbounded' /> 			</xs:sequence> 		</xs:complexType> 	</xs:element>  	<xs:complexType name='EventType'> 		<xs:sequence> 	    	<xs:element name='Title' type='xs:token' minOccurs='1' maxOccurs='1'/> 	    	<xs:element name='OriginalTitle' type='xs:token' minOccurs='1' maxOccurs='1'/> 	    	<xs:element name='ProductionYear' type='xs:token' minOccurs='1' maxOccurs='1'/> 	    	<xs:element name='LengthInMinutes' type='xs:integer' minOccurs='1' maxOccurs='1'/> 	    	<xs:element name='dtLocalRelease' type='xs:dateTime' minOccurs='1' maxOccurs='1'/> 	    	<xs:element name='Rating' type='RatingType' minOccurs='1' maxOccurs='1'/> 	    	<xs:element name='EventType' type='EventTypeType' minOccurs='1' maxOccurs='1'/> 	    	<xs:element name='Genres' type='xs:token' minOccurs='1' maxOccurs='1'/> 	    	<xs:element name='ShortSynopsis' type='xs:token' minOccurs='1' maxOccurs='1'/> 	    	<xs:element name='Synopsis' type='xs:token' minOccurs='1' maxOccurs='1'/> 	    	<xs:element name='Cast' type='CastType' minOccurs='1' maxOccurs='1'/> 	    	<xs:element name='Directors' type='DirectorsType' minOccurs='1' maxOccurs='1'/> 		</xs:sequence> 	</xs:complexType>  	<xs:complexType name='CastType'> 		<xs:sequence> 	    	<xs:element name='Actor' type='PersonType' minOccurs='1' maxOccurs='unbounded'/> 		</xs:sequence> 	</xs:complexType>  	<xs:complexType name='DirectorsType'> 		<xs:sequence> 	    	<xs:element name='Director' type='PersonType' minOccurs='1' maxOccurs='unbounded'/> 		</xs:sequence> 	</xs:complexType>  	<xs:complexType name='PersonType'> 		<xs:sequence> 	    	<xs:element name='FirstName' type='xs:string' minOccurs='1' maxOccurs='1'/> 	    	<xs:element name='LastName' type='xs:string' minOccurs='1' maxOccurs='1'/> 		</xs:sequence> 	</xs:complexType>  	<xs:simpleType name='RatingType'> 		<xs:restriction base='xs:string'> 			<xs:enumeration value='S'/> 			<xs:enumeration value='7'/> 			<xs:enumeration value='12'/> 			<xs:enumeration value='16'/> 			<xs:enumeration value='18'/> 		</xs:restriction> 	</xs:simpleType>  	<xs:simpleType name='EventTypeType'> 		<xs:restriction base='xs:string'> 			<xs:enumeration value='Movie'/> 			<xs:enumeration value='Documentary'/> 			<xs:enumeration value='Music'/> 		</xs:restriction> 	</xs:simpleType>  </xs:schema>";
//			String schemaFile = "Laita tänne finnkino.xsd dokumentti muunnettuna MuunnaViopelle-ohjelmalla ja palauta vain tämä rivi Viopeen";
			
			StringReader reader = new StringReader(schemaFile);
			SchemaFactory factory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");

			Schema schema = factory.newSchema(new StreamSource(reader));
			Validator validator = schema.newValidator();

			Source source = new StreamSource(xmlFile);

			validator.validate(source);
			System.out.println("Tekemäsi XML Schema vastaa " + xmlFile
					+ "-dokumenttia.");

			DocumentBuilderFactory factory2 = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory2.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(schemaFile));
			Document doc = builder.parse(is);

			ValidateFinnkino ohjelma = new ValidateFinnkino();
			ohjelma.listNodes(doc.getDocumentElement());
			ohjelma.count();

		} catch (SAXException ex) {
			System.out.println("Tekemäsi XML Schema ei vastaa " + xmlFile
					+ "-dokumenttia, koska");
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Tiedostonkäsittely ei onnistu, koska");
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Käsittely ei onnistu, koska");
			System.out.println(ex.getMessage());
		}

	}
	
	private int elementCount = 0;

	private void listNodes(Node node) {
		if (node.getNodeType() == ELEMENT_NODE) {
			if (node.getNodeName().equalsIgnoreCase("xs:element")) {
				elementCount++;
			}

			NodeList list = node.getChildNodes();
			if (list.getLength() > 0) {
				for (int i = 0; i < list.getLength(); i++) {
					listNodes(list.item(i));
				}
			}
		}

	}

	private void count() {
		if (elementCount > 15) {
			System.out.println("XML Schema on tehty kokonaan.");
		} else {
			System.out.println("XML Schema on tehty puutteellisesti, koska xs:element määrä on liian pieni eli " + elementCount + " kappaletta.");
		}
	}
}
