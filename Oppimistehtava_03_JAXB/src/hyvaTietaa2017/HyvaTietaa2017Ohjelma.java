package hyvaTietaa2017;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import hyvaTietaa2017.Events;
import hyvaTietaa2017.Events.Event;

class HyvaTietaa2017Ohjelma {

	final private File xml = new File("hyvaTietaa2017.xml");
	final private File xsd = new File("hyvaTietaa2017.xsd");

	private Events events;

	public void toObject() throws JAXBException, SAXException {
		final JAXBContext jaxbContext = JAXBContext.newInstance(Events.class);
		final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		events = (Events) jaxbUnmarshaller.unmarshal(xml);
	}

	public void toXML() throws JAXBException, SAXException {

		final JAXBContext jaxbContext = JAXBContext.newInstance(Events.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
/*
		jaxbMarshaller.setProperty(
				Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION,
				"hyvaTietaa2017.xsd");

		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(xsd);
		jaxbMarshaller.setSchema(schema);
*/
		jaxbMarshaller.marshal(events, xml);
	}

	// LAITA TÄSTÄ ALASPÄIN OLEVA KOODI Viopeen

	public static void main(final String[] args) {

		HyvaTietaa2017Ohjelma sovellus = new HyvaTietaa2017Ohjelma();

		try {
			sovellus.toObject();

			Scanner inputMain = new Scanner(System.in);
			int valinta = -1;

			do {
				System.out.println("\n1 = Listaa tapahtumat");
				System.out.println("2 = Hae tapahtumat nimellä");
				System.out.println("3 = Poista tapahtuma");
				System.out.println("4 = Muuta tapahtuman url");
				System.out.println("0 = Lopeta");
				System.out.print("Anna valintasi: ");
				valinta = inputMain.nextInt();
				inputMain.nextLine();
				
				switch (valinta) {
				case 1:
					sovellus.listaaKaikkiTapahtumat(false);
					break;
				case 2:
					sovellus.listaaTapahtumatNimenPerusteella();
					break;
				case 3:
					sovellus.poistaTapahtumaNumerolla();
					break;
				case 4:
					sovellus.muutaTapahtumanUrl();
					break;
				}

			} while (valinta != 0);
			
			inputMain.close();

			sovellus.toXML();

		} catch (final JAXBException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			System.out.print("Validointi ei onnistunut");
		}
	}

	public void listaaKaikkiTapahtumat(boolean jarjestysNro) {
		List<Events.Event> tapahtumat = events.getEvent();
		for (int i = 0; i < tapahtumat.size(); i++) {
			if (jarjestysNro == true) {
				System.out.print(i+1 +". ");
			}
			Events.Event tapahtuma = tapahtumat.get(i);
			System.out.print(tapahtuma.getDate().getDay() +"." +tapahtuma.getDate().getMonth() +"." +tapahtuma.getDate().getYear());
			System.out.print(" ");
			System.out.print(tapahtuma.getName());
			System.out.print(" (");
			System.out.print(tapahtuma.getUrl());
			System.out.print(")");
			System.out.print("\n");
		}
	}
	
	public void listaaTapahtumatNimenPerusteella() {
		Scanner input01 = new Scanner(System.in);
		String nimi = null;
		System.out.println("Anna haettavan tapahtuman nimi: ");
		nimi = input01.nextLine();
//		input01.close();
		List<Events.Event> tapahtumat = events.getEvent();
		boolean loytyiko = false;
		for (int i = 0; i < tapahtumat.size(); i++) {
			Events.Event tapahtuma = tapahtumat.get(i);
			if (nimi.equals(tapahtuma.getName())) {
				loytyiko = true;
				tulostaTapahtuma(tapahtuma);
			}
		}
		if (loytyiko == false) {
			System.out.println("Tapahtumaa ei ole annetulla nimellä");
		}
	}
	
	private void tulostaTapahtuma(Event tapahtuma) {
		System.out.print(tapahtuma.getDate().getDay() +"." +tapahtuma.getDate().getMonth() +"." +tapahtuma.getDate().getYear());
		System.out.print(" ");
		System.out.print(tapahtuma.getName());
		System.out.print(" (");
		System.out.print(tapahtuma.getUrl());
		System.out.print(") ");
		if (tapahtuma.getAlternateNames() != null) {
			System.out.print(tapahtuma.getAlternateNames());
		}
		Short liputus = 1;
		if (tapahtuma.getFlagDay() != null && tapahtuma.getFlagDay().equals(liputus)) {
			System.out.print(" Liputuspäivä");
		}
		System.out.print("\n");
	}

	private void muutaTapahtumanUrl() {
		listaaKaikkiTapahtumat(true);
		List<Events.Event> tapahtumat = events.getEvent();
		int val = 0;
		Scanner input02 = new Scanner(System.in);
		System.out.println("Anna muutettavan tapahtuman numero: ");
		val = input02.nextInt();

		boolean loytyiko = false;
		for (int i = 0; i < tapahtumat.size(); i++) {
			Events.Event tapahtuma = tapahtumat.get(i);
			if (val == i+1) {
				loytyiko = true;
				System.out.println("Anna tapahtuman uusi www: ");
				String uusiUrl = input02.next();
				input02.nextLine();
				tapahtuma.setUrl(uusiUrl);
				tulostaTapahtuma(tapahtuma);
			}
		}
		if (loytyiko == false) {
			System.out.println("Tapahtumaa ei ole numerolla " +val); 
		}
	}
	
	private void poistaTapahtumaNumerolla() {
		listaaKaikkiTapahtumat(true);
		List<Events.Event> tapahtumat = events.getEvent();
		int val = 0;
		Scanner input03 = new Scanner(System.in);
		System.out.println("Anna poistettavan tapahtuman numero: ");
		val = input03.nextInt();
		
		boolean loytyiko = false;
		for (int i = 0; i < tapahtumat.size(); i++) {
//			Events.Event tapahtuma = tapahtumat.get(i);
			if (val == i+1) {
				loytyiko = true;
				tapahtumat.remove(i);
				System.out.println("Tapahtuma poistettiin numerolla " +val);
			}
		}
		if (loytyiko == false) {
			System.out.println("Tapahtumaa ei ole numerolla " +val); 
		}
	}

}
