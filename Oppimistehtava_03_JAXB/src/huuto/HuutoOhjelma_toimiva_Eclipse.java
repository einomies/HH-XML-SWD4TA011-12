package huuto;

import java.io.File;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Iterator;
import java.util.Scanner;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import JAXB.Entry_temp;
import JAXB.Entry_temp.Category;
import JAXB.Entry_temp.DeliveryMethods;
import JAXB.Entry_temp.DeliveryMethods.DeliveryMethod;
import JAXB.Entry_temp.Price;
import JAXB.Entry_temp.Price.StartingPrice;

class HuutoOhjelma_toimiva_Eclipse {

	final private File xml = new File("huuto3.xml");
//	final private File xsd = new File("huuto3.xsd");

	private Entry_temp entry;

	public void toXML(Entry_temp entry) throws JAXBException, SAXException {

		final JAXBContext jaxbContext = JAXBContext.newInstance(Entry_temp.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		/*
		 * jaxbMarshaller.setProperty(
		 * Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "huuto3.xsd");
		 *
		 * SchemaFactory schemaFactory = SchemaFactory
		 * .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); Schema schema =
		 * schemaFactory.newSchema(xsd); jaxbMarshaller.setSchema(schema);
		 */

		jaxbMarshaller.marshal(entry, xml);
	}

	// LAITA TÄSTÄ ALASPÄIN OLEVA KOODI Viopeen

	public void muodostaEntry(Entry_temp entry) {

		Scanner input01 = new Scanner(System.in);

		System.out.println("Anna tapahtuman nimi: ");
		String title = input01.nextLine();
//		String title = "Nappiset Adidas Predator, koko 38";
//		input01.nextLine();
		entry.setTitle(title);

		System.out.println("Anna tapahtuman kategorian numero: ");
		String scheme = input01.nextLine();
//		String scheme = "20071008";
		Category cat = new Category();
		cat.setScheme(scheme);
		entry.setCategory(cat);

		System.out.println("Anna tapahtuman yhteenveto: ");
		String summary = input01.nextLine();
//		String summary = "Hyväkuntoiset ja vähän käytetyt futiskengät keinonurmelle";
		entry.setSummary(summary);
		
		System.out.println("Anna huuhdon päättymispäivä (vvvv-kk-pp): ");
		String pvmString = input01.nextLine();
//		String pvmString = "2017-12-31";
		int pvmIntVvvv = Integer.parseInt(pvmString.substring(0, 4));
		int pvmIntKk = Integer.parseInt(pvmString.substring(5, 7));
		int pvmIntPp = Integer.parseInt(pvmString.substring(8, 10));

		GregorianCalendar tempPaivays = new GregorianCalendar();
		tempPaivays.set(Calendar.YEAR, pvmIntVvvv);
		tempPaivays.set(Calendar.MONTH, pvmIntKk);
		tempPaivays.set(Calendar.DATE, pvmIntPp);
		XMLGregorianCalendar paivays = null;
		try {
			paivays = DatatypeFactory.newInstance().newXMLGregorianCalendar(
					tempPaivays);
			paivays.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
			paivays.setTime(DatatypeConstants.FIELD_UNDEFINED,
					DatatypeConstants.FIELD_UNDEFINED,
					DatatypeConstants.FIELD_UNDEFINED,
					DatatypeConstants.FIELD_UNDEFINED);
		} catch (DatatypeConfigurationException e) {
		}
		entry.setExpirationTime(paivays);
		
		System.out.println("Anna tapahtuman aloitushinta: ");
		double price = input01.nextDouble();
//		double price = 5.42;
		String valuutta = "EUR";
		Price hintaYlataso = new Price();
		StartingPrice aloitushinta = new StartingPrice();
		aloitushinta.setCurrency(valuutta);
		aloitushinta.setValue(BigDecimal.valueOf(price));
		hintaYlataso.setStartingPrice(aloitushinta);
		entry.setPrice(hintaYlataso);
		System.out.println(entry.getPrice().getStartingPrice());
		
		DeliveryMethods toimitustavat = new DeliveryMethods();
		String valinta = null;
		int dLkm = 0;
		do {
			System.out.println("Anna toimitustapa (- lopettaa): ");
			valinta = input01.next();
//			if (dLkm == 0) {
//				valinta = "postipaketti";
//			}
//			if (dLkm == 1) {
//				valinta = "nouto";
//			}
//			if (dLkm == 2) {
//				valinta = "matkahuolto";
//			}
//			if (dLkm > 2) {
//				valinta = "-";
//			}
			input01.nextLine();
			if (!valinta.equalsIgnoreCase("-")) {
				dLkm++;
				if (dLkm < 6) {
					DeliveryMethod toimitustapa = new DeliveryMethod();
					toimitustapa.setType(valinta);
					toimitustavat.getDeliveryMethod().add(toimitustapa);
				}
			}
		} while (!valinta.equalsIgnoreCase("-") && dLkm < 6);
		
		input01.close();
		
		if (dLkm > 0) {
			entry.setDeliveryMethods(toimitustavat);
		}
	}

	public static void main(final String[] args) {

		HuutoOhjelma_toimiva_Eclipse ohjelma = new HuutoOhjelma_toimiva_Eclipse();
		
		Entry_temp huuto = new Entry_temp();

		try {
			ohjelma.muodostaEntry(huuto);
			ohjelma.toXML(huuto);
		} catch (final JAXBException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			System.out.print("Validointi ei onnistunut");
		}
	}
}
