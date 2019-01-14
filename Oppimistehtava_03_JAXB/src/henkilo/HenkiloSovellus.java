package henkilo;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class HenkiloSovellus {
	final private File xml = new File("henkilo.xml");
	final private File xsd = new File("henkilo.xsd");
	
	// K‰ytet‰‰n XML Schemasta generoitua luokkaa
	private Henkilo henkilo;

	// Metodi validoi ja muuntaa XML-dokumentin Henkilo-luokan olioksi
	public void toObject() throws JAXBException, SAXException {
		final JAXBContext jaxbContext = JAXBContext.newInstance(Henkilo.class);
		final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		
		// Validointi 
		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(xsd);
		jaxbUnmarshaller.setSchema(schema);
		
		// Muunnos
		henkilo = (Henkilo) jaxbUnmarshaller.unmarshal(xml);
	}

	// Metodi validoi ja muuntaa Henkilo-luokan olion XML-dokumentiksi
	public void toXML() throws JAXBException, SAXException {

		final JAXBContext jaxbContext = JAXBContext.newInstance(Henkilo.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-15");
		jaxbMarshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "henkilo.xsd");


		// Validointi 
		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(xsd);
		jaxbMarshaller.setSchema(schema);

		// Muunnos
		jaxbMarshaller.marshal(henkilo, xml);
	}

	// Metodi listaa konsoliin henkilo-luokan olion
	public void naytaHenkilo() {
		
		// Tietoja n‰ytet‰‰n Henkilo-luokan get-metodeilla
		System.out.println("Tunnus: " + henkilo.getTunnus());
		System.out.println("Hetu : " + henkilo.getHetu());

		Henkilo.Nimi nimi = henkilo.getNimi();
		System.out.println("Sukunimi : " + nimi.getSukunimi());

		// Pyydet‰‰n lista etunimist‰
		List<String> etunimet = nimi.getEtunimi();
		System.out.print("Etunimet: ");
		for (int j = 0; j < etunimet.size(); j++) {
			// Listasta haetaan yksi etunimi List-luokan get-metodilla
			String etunimi = etunimet.get(j);
			System.out.print(etunimi + " ");
		}

		// Koska osoite ei ole pakollinen, testataan onko osoitetta
		if (henkilo.getOsoite() != null) {
			Henkilo.Osoite osoite = henkilo.getOsoite();
			System.out.print("\nOsoite : " + osoite.getLahiosoite());
			System.out.print(" " + osoite.getPostinumero());
			System.out.print(" " + osoite.getPostitoimipaikka());
		}

		// Koska tiimi ei ole pakollinen, testataan onko tiimia
		if (henkilo.getTiimi() != null)
			System.out.print("\nTiimi : " + henkilo.getTiimi().getNimi());

		String email = henkilo.getEmail();
		System.out.print("\nEmail : " + email);

		List<Henkilo.Puhelin> puhelimet = henkilo.getPuhelin();
		System.out.print("\nPuhelimet: ");
		for (int k = 0; k < puhelimet.size(); k++) {
			Henkilo.Puhelin puhelin = puhelimet.get(k);
			System.out.print(puhelin.getValue() + " (" + puhelin.getType()
					+ ") ");
		}

		String nimike = henkilo.getNimike();
		System.out.println("\nNimike : " + nimike);

		if (henkilo.getTyohuone() != null) {
			Henkilo.Tyohuone tyohuone = henkilo.getTyohuone();
			System.out.println("Tyohuone: " + tyohuone.getValue() + " ("
					+ tyohuone.getSijainti() + ")");
		}
		System.out.println();
	}

	public void lisaaEtunimi() {
		Scanner input = new Scanner(System.in);

		System.out.println("Lis‰‰ uusi etunimi : ");
		String enimi = input.nextLine();
		Henkilo.Nimi nimi = henkilo.getNimi();
		// Pyydet‰‰n nimi-oliolta listaa sen etunimist‰
		List<String> etunimetLista = nimi.getEtunimi();
		
		// Lis‰‰miseen k‰ytet‰‰n List-luokan add-metodia
		etunimetLista.add(enimi);
	}

	public void muutaSukunimi() {
		Scanner input = new Scanner(System.in);

		System.out.println("Anna uusi sukunimi : ");
		String snimi = input.nextLine();
		Henkilo.Nimi nimi = henkilo.getNimi();
		
		// Tietoja muutetaan Henkilo-luokan set-metodilla
		nimi.setSukunimi(snimi);
	}

	public static void main(String[] args) throws Exception {
		HenkiloSovellus sovellus = new HenkiloSovellus();
		try {

			// Muunnetaan XML-dokumentti olioksi
			sovellus.toObject();

			// K‰sitell‰‰n luotua oliota metodeilla
			sovellus.naytaHenkilo();
			sovellus.lisaaEtunimi();
			sovellus.muutaSukunimi();
			sovellus.naytaHenkilo();

			// Muunnetaan olio takaisin XML-dokumentiksi
			sovellus.toXML();

		} catch (final JAXBException e) {
			System.out.println("JAXB k‰sittely ei onnistunut. Syy ");
			e.printStackTrace();
		} catch (final SAXException e) {
			System.out.println("Validointi ei onnistunut. Syy");
			e.printStackTrace();
		}
	}

}
