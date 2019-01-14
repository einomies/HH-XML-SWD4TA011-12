package henkilosto;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.List;
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

public class HenkilostoSovellus {

	final private File xml = new File("henkilosto.xml");
	final private File xsd = new File("henkilosto.xsd");
	private Henkilosto henkilosto;

	public void toObject() throws JAXBException, SAXException {
		final JAXBContext jaxbContext = JAXBContext
				.newInstance(Henkilosto.class);
		final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(xsd);
		jaxbUnmarshaller.setSchema(schema);

		henkilosto = (Henkilosto) jaxbUnmarshaller.unmarshal(xml);
	}

	public void toXML() throws JAXBException, SAXException {

		final JAXBContext jaxbContext = JAXBContext
				.newInstance(Henkilosto.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-15");
		jaxbMarshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "henkilosto.xsd");


		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(xsd);
		jaxbMarshaller.setSchema(schema);

		jaxbMarshaller.marshal(henkilosto, xml);
	}

	public void listaaHenkilot() {
		List<Henkilosto.Henkilo> henkiloLista = henkilosto.getHenkilo();

		for (int i = 0; i < henkiloLista.size(); i++) {
			Henkilosto.Henkilo henkilo = henkiloLista.get(i);

			System.out.println("Tunnus: " + henkilo.getTunnus());
			System.out.println("Hetu : " + henkilo.getHetu());

			Henkilosto.Henkilo.Nimi nimi = henkilo.getNimi();
			System.out.println("Sukunimi : " + nimi.getSukunimi());

			List<String> etunimet = nimi.getEtunimi();
			System.out.print("Etunimet: ");
			for (int j = 0; j < etunimet.size(); j++) {
				String etunimi = etunimet.get(j);
				System.out.print(etunimi + " ");
			}

			if (henkilo.getOsoite() != null) {
				Henkilosto.Henkilo.Osoite osoite = henkilo.getOsoite();
				System.out.print("\nOsoite : " + osoite.getLahiosoite());
				System.out.print(" " + osoite.getPostinumero());
				System.out.print(" " + osoite.getPostitoimipaikka());
			}

			if (henkilo.getTiimi() != null)
				System.out.print("\nTiimi : " + henkilo.getTiimi().getNimi());

			String email = henkilo.getEmail();
			System.out.print("\nEmail : " + email);

			List<Henkilosto.Henkilo.Puhelin> puhelimet = henkilo.getPuhelin();
			System.out.print("\nPuhelimet: ");
			for (int k = 0; k < puhelimet.size(); k++) {
				Henkilosto.Henkilo.Puhelin puhelin = puhelimet.get(k);
				System.out.print(puhelin.getValue() + " (" + puhelin.getType()
						+ ") ");
			}

			String nimike = henkilo.getNimike();
			System.out.println("\nNimike : " + nimike);

			if (henkilo.getTyohuone() != null) {
				Henkilosto.Henkilo.Tyohuone tyohuone = henkilo.getTyohuone();
				System.out.println("Tyohuone: " + tyohuone.getValue() + " ("
						+ tyohuone.getSijainti() + ")");
			}
			System.out.println();
		}
	}

	public void lisaaHenkilo() {
		Henkilosto.Henkilo henkilo = new Henkilosto.Henkilo();

		Scanner input = new Scanner(System.in);

		System.out.print("Anna tunnus: ");
		String tunnus = input.nextLine();
		henkilo.setTunnus(tunnus);

		System.out.print("Anna hetu: ");
		String hetu = input.nextLine();
		henkilo.setHetu(hetu);

		Henkilosto.Henkilo.Nimi nimi = new Henkilosto.Henkilo.Nimi();
		System.out.print("Anna sukunimi: ");
		String sukunimi = input.nextLine();
		nimi.setSukunimi(sukunimi);

		String etunimi;
		do {
			System.out.print("Anna etunimi (enter lopettaa): ");
			etunimi = input.nextLine();
			if (!etunimi.equals(""))
				nimi.getEtunimi().add(etunimi);
		} while (!etunimi.equals(""));

		henkilo.setNimi(nimi);

		Henkilosto.Henkilo.Osoite osoite = new Henkilosto.Henkilo.Osoite();

		System.out.print("Anna lähiosoite: ");
		String lahiosoite = input.nextLine();
		osoite.setLahiosoite(lahiosoite);

		System.out.print("Anna postinumero: ");
		String postinumero = input.nextLine();
		osoite.setPostinumero(postinumero);

		System.out.print("Anna postitoimipaikka: ");
		String postitoimipaikka = input.nextLine();
		osoite.setPostitoimipaikka(postitoimipaikka);

		henkilo.setOsoite(osoite);

		Henkilosto.Henkilo.Tiimi tiimi = new Henkilosto.Henkilo.Tiimi();

		System.out.print("Anna tiimin nimi: ");
		String tiiminimi = input.nextLine();
		tiimi.setNimi(tiiminimi);

		System.out.print("Anna sema tiimissä: ");
		String tiimiasema = input.nextLine();

		tiimi.setAsema(tiimiasema);

		henkilo.setTiimi(tiimi);

		System.out.print("Anna email: ");
		String email = input.nextLine();
		henkilo.setEmail(email);

		String puhelinStr;
		do {
			System.out.print("Anna puhelin (- = ei muita puhelimia): ");
			puhelinStr = input.nextLine();
			if (!puhelinStr.equals("-")) {
				Henkilosto.Henkilo.Puhelin puhelin = new Henkilosto.Henkilo.Puhelin();
				puhelin.setValue(new String(puhelinStr));

				System.out.print("Anna tyyppi: ");
				String tyyppi = input.nextLine();
				puhelin.setType(tyyppi);

				henkilo.getPuhelin().add(puhelin);
			}
		} while (!puhelinStr.equals("-"));

		System.out.print("Anna nimike: ");
		String nimike = input.nextLine();
		henkilo.setNimike(nimike);

		Henkilosto.Henkilo.Tyohuone tyohuone = new Henkilosto.Henkilo.Tyohuone();
		System.out.print("Anna työhuone: ");
		String tyohuoneStr = input.nextLine();
		tyohuone.setValue(tyohuoneStr);
		System.out.print("Anna sijainti: ");
		String sijainti = input.nextLine();
		tyohuone.setSijainti(sijainti);
		henkilo.setTyohuone(tyohuone);

		henkilosto.getHenkilo().add(henkilo);
	}

	public int haeHenkilo(String tunnus) {
		List<Henkilosto.Henkilo> henkiloLista = henkilosto.getHenkilo();
		int i = 0;
		int index = -1;

		while (index == -1 && i < henkiloLista.size()) {
			Henkilosto.Henkilo henkilo = henkiloLista.get(i);
			if (henkilo.getTunnus().equals(tunnus))
				index = i;
			else
				i++;
		}

		return index;
	}

	public void muutaSukunimi() {
		Scanner input = new Scanner(System.in);
		System.out.println("Anna muutettavan henkilön tunnus: ");
		String tunnus = input.nextLine();
		int i = haeHenkilo(tunnus);
		if (i > -1) {
			Henkilosto.Henkilo henkilo = henkilosto.getHenkilo().get(i);
			Henkilosto.Henkilo.Nimi nimi = henkilo.getNimi();
			System.out.print("Anna uusi sukunimi: ");
			String sukunimiStr = input.nextLine();
			nimi.setSukunimi(sukunimiStr);
			System.out
					.println("Tunnuksella " + tunnus + " sukunimi päivitetty");
		} else
			System.out.println("Tunnuksella " + tunnus + " ei ole henkilöä");
	}

	public void poistaHenkilo() {
		Scanner input = new Scanner(System.in);
		System.out.println("Anna poistettava henkilön tunnus: ");
		String tunnus = input.nextLine();
		int i = haeHenkilo(tunnus);
		if (i > -1) {
			henkilosto.getHenkilo().remove(i);
			System.out.println("Tunnuksella " + tunnus + " poistettu");
		} else
			System.out.println("Tunnuksella " + tunnus + " ei ole henkilöä");
	}

	public void paivitaPaivitetty() {
		GregorianCalendar tanaan = new GregorianCalendar();

		XMLGregorianCalendar paiva = null;
		try {
			paiva = DatatypeFactory.newInstance().newXMLGregorianCalendar(
					tanaan);
			paiva.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
		} catch (DatatypeConfigurationException e) {

		}

		henkilosto.setPaivitetty(paiva);
	}

	public static void main(String[] args) throws Exception {
		HenkilostoSovellus sovellus = new HenkilostoSovellus();
		try {

			sovellus.toObject();

			sovellus.listaaHenkilot();
			sovellus.paivitaPaivitetty();

			sovellus.toXML();

		} catch (final JAXBException e) {
			System.out.println("JAXB käsittely ei onnistunut. Syy ");
			e.printStackTrace();
		} catch (final SAXException e) {
			System.out.println("Validointi ei onnistunut. Syy ");
			e.printStackTrace();
		}
	}

}
