package myynti;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;
import java.util.Iterator;

public class MyyntiOhjelma {

	private Document doc;
	final private String xmlFile = "myynti4.xml";

	public void toJDOM() throws JDOMException, IOException, SAXException {
		SAXBuilder builder = new SAXBuilder();
		doc = builder.build(xmlFile);
	}

	public void toXML() throws IOException, SAXException {
		XMLOutputter fmt = new XMLOutputter();
		fmt.setFormat(Format.getPrettyFormat().setEncoding("UTF-8"));
		FileWriter writer = new FileWriter(xmlFile);
		fmt.output(doc, writer);
	}

	// Palauta koodi tästä lähtien Viopeen
	
	public static void main(String[] args) throws Exception {
		MyyntiOhjelma sovellus = new MyyntiOhjelma();
		try {
			sovellus.toJDOM();

			int valinta = 0;

			Scanner input = new Scanner(System.in);

			do {
				System.out.println("\n1. Listaa myyntikohteet");
				System.out.println("2. Hae myyntikohde");
				System.out.println("3. Poista myyntikohde");
				System.out.println("4. Muuta myyntikohteen kuvausta");
				System.out.println("0. Lopeta");
				System.out.print("Anna valintasi: ");
				valinta = input.nextInt();
				input.nextLine();

				switch (valinta) {
				case 1:
					sovellus.listaaMyyntikohteet();
					break;
				case 2:
					sovellus.haeMyyntiKohde();
					break;
				case 3:
					sovellus.poistaMyyntiKohde();
					break;
				case 4:
					sovellus.muutaKuvaus();
					break;
				}

			} while (valinta != 0);
			
			input.close();
			
			sovellus.toXML();

		} catch (JDOMException e) {
			e.printStackTrace();
			System.out.println("XMl-dokumentti ei ole well-formed, koska "
					+ e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void muutaKuvaus() {
		Scanner input = new Scanner(System.in);
		String haku = null;
		String kuvaus = null;
		System.out.print("Anna muutettavan myyntikohteen kohdenumero: ");
		haku = input.next();
		input.nextLine();
		
		Element asunto = etsiKohde(haku);
		if (asunto != null) {
			System.out.print("Entinen kuvaus: " +asunto.getChildText("kuvaus"));
			System.out.print("\nAnna uusi kuvaus: ");
			kuvaus = input.nextLine();
			Element kuvausElem = asunto.getChild("kuvaus");
			kuvausElem.setText(kuvaus);
		} else {
			System.out.println("Kohdetta ei ole numerolla " +haku);
		}
	}

	private void poistaMyyntiKohde() {
		Scanner input = new Scanner(System.in);
		String haku = null;
		System.out.print("Anna poistettavan myyntikohteen kohdenumero: ");
		haku = input.next();
		input.nextLine();
		
		Element asunto = etsiKohde(haku);
		if (asunto != null) {
			Element parent = asunto.getParentElement();
			parent.removeContent(asunto);
			System.out.println("Kohde numerolla " +haku +" poistettiin");
		} else {
			System.out.println("Kohdetta ei ole numerolla " +haku);
		}
	}

	public Element etsiKohde(String kohdenro) {
		Element asunto = null;

		List<Element> asuntoLista = doc.getRootElement().getChildren("asunto");
		int i = 0;
		
		while (asunto == null && i < asuntoLista.size()) {
			if (asuntoLista.get(i).getAttributeValue("kohdenumero").equals(kohdenro))
				asunto = asuntoLista.get(i);
			else
			  i++;
		}
		
		return asunto;
	}

	private void haeMyyntiKohde() {
		Scanner input = new Scanner(System.in);
		String haku = null;
		System.out.print("Anna haettavan myyntikohteen kohdenumero: ");
		haku = input.next();
		input.nextLine();
		
		Element asunto = etsiKohde(haku);
		if (asunto != null) {
			tulostaKohde(asunto);
		} else {
			System.out.println("Kohdetta ei ole numerolla " +haku);
		}
	}

	public void listaaMyyntikohteet() {
		List<Element> myyntiLista = doc.getRootElement().getChildren("asunto");
		
		for (int i = 0; i < myyntiLista.size(); i++) {
			Element asunto = myyntiLista.get(i);
			tulostaKohde(asunto);
		}
	}

	public void tulostaKohde(Element asunto) {
		System.out.println("Kohdenumero: " + asunto.getAttributeValue("kohdenumero"));
		System.out.println("Sijainti : " +  asunto.getChildText("osoite"));
		System.out.println("Rakennusvuosi: " + asunto.getAttributeValue("rakennusvuosi"));
		System.out.println("Kuvaus: " +  asunto.getChildText("kuvaus"));
		System.out.println("Pinta-ala: " + asunto.getChildText("pinta-ala"));

		List<Element> hinnat = asunto.getChildren("hinta");
		System.out.print("Hinta: ");
		for (int j= 0; j < hinnat.size(); j++) {
			Element hinta = hinnat.get(j);
			System.out.print(hinta.getText() +" (" +hinta.getAttributeValue("tyyppi") +")" +" ");
		}
		
		System.out.println("\n");
	}

}
