// MUUTA ALLA OLEVAA RIVIA TARVITTAESSA
package eta1;
 
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ValidateCv {
	public static void main(String[] args) throws SAXException, IOException {
		final String schemaFile = "cv.xsd";

		try {
			
	        String xmlFile = "<?xml version='1.0' encoding='UTF-8'?> <Cv xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' 	xsi:noNamespaceSchemaLocation='cv.xsd'>   <Identification>     <PersonName>       <FirstName>Eino</FirstName>       <Surname>Kämäräinen</Surname>     </PersonName>     <ContactInfo>       <AddressLine>Nauristie 12</AddressLine>       <PostalCode>04410</PostalCode>       <Municipality>Järvenpää</Municipality>       <Country>Finland</Country>       <Email>einokamarainen@hotmail.com</Email>     </ContactInfo>     <Demographics>       <Birthdate day='26' month='1' year='1975'/>       <Gender>Male</Gender>       <Nationality>Finnish</Nationality>     </Demographics>   </Identification>   <WorkExperienceList>     <WorkExperience>       <From day='2' month='5' year='2011'/>       <To day='31' month='12' year='2017'/>       <Position>Systems Specialist</Position>       <Activities>Software development</Activities>       <Employer>Oy Samlink Ab</Employer>     </WorkExperience>   </WorkExperienceList> </Cv>";
//	        String xmlFile = "Laita tänne cv.xml dokumentti muunnettuna MuunnaViopelle-ohjelmalla ja palauta vain tämä rivi Viopeen";
	        
	        StringReader reader = new StringReader(xmlFile);
			SchemaFactory factory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");

			File schemaLocation = new File(schemaFile);
			Schema schema = factory.newSchema(schemaLocation);

			Validator validator = schema.newValidator();

			Source source = new StreamSource(reader);

			validator.validate(source);
			System.out.println("Tekemäsi XML-dokumentti noudattaa "
					+ schemaFile + " XML Schemaa.");

		} catch (SAXException ex) {
			System.out.println("Tekemäsi XML-dokumentti ei noudata "
					+ schemaFile + " XML Schemaa, koska");
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Tiedostonkäsittely ei onnistu, koska");
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Käsittely ei onnistu, koska");
			System.out.println(ex.getMessage());
		}

	}
}
