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
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import huuto.Entry.Category;
import huuto.Entry.DeliveryMethods;
import huuto.Entry.DeliveryMethods.DeliveryMethod;
import huuto.Entry.Price;
import huuto.Entry.Price.StartingPrice;

class HuutoOhjelma {

	final private File xml = new File("huuto3.xml");
	final private File xsd = new File("huuto3.xsd");

	private Entry entry;

	public void toXML() throws JAXBException, SAXException {

		final JAXBContext jaxbContext = JAXBContext.newInstance(Entry.class);
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

	public void lisaaEntry() {
		
		entry = new Entry();
		
		Scanner input01 = new Scanner(System.in);
		
		System.out.println("Anna tapahtuman nimi: ");
		String title = input01.nextLine();
//		String title = "Fender Super Sonic 22W 6V6";
		entry.setTitle(title);

		System.out.println("Anna tapahtuman kategorian numero: ");
		String scheme = input01.nextLine();
		String schemeAlku = "http://api.huuto.net/somt/0.9/categories/";
//		String scheme = "20071008";
		Category cat = new Category();
		cat.setScheme(schemeAlku+scheme);
		entry.setCategory(cat);

		System.out.println("Anna tapahtuman yhteenveto: ");
		String summary = input01.nextLine();
//		String summary = "Hyväsoundinen ja monipuolinen putkikombo";
		entry.setSummary(summary);

		System.out.println("Anna huuhdon päättymispäivä (vvvv-kk-pp): ");
		String pvmString = input01.nextLine();
//		String pvmString = "2017-12-31";
		int pvmIntVvvv = Integer.parseInt(pvmString.substring(0, 4));
		int pvmIntKk = Integer.parseInt(pvmString.substring(5, 7));
		int pvmIntPp = Integer.parseInt(pvmString.substring(8, 10));
		GregorianCalendar tempPaivays = new GregorianCalendar();
		tempPaivays.set(pvmIntVvvv, pvmIntKk-1, pvmIntPp);
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
		BigDecimal price = input01.nextBigDecimal();
//		BigDecimal price = BigDecimal.valueOf(655,42);
		String valuutta = "EUR";
		Price hintaYlataso = new Price();
		StartingPrice aloitushinta = new StartingPrice();
		aloitushinta.setCurrency(valuutta);
		aloitushinta.setValue(price);
		hintaYlataso.setStartingPrice(aloitushinta);
		entry.setPrice(hintaYlataso);
		
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
//			if (dLkm == 3) {
//				valinta = "kirjekyyhky";
//			}
//			if (dLkm > 3) {
//				valinta = "-";
//			}
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

		HuutoOhjelma ohjelma = new HuutoOhjelma();

		try {
			ohjelma.lisaaEntry();
			ohjelma.toXML();
		} catch (final JAXBException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			System.out.print("Validointi ei onnistunut");
		}
	}

}
