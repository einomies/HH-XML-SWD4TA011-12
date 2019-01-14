package sportsML;

import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.SAXException;

import java.util.Iterator;

public class SportsmlMMOhjelma {
	private Document doc;
	final private String xmlFile = "sportsmlMM.xml";

	public void toJDOM() throws JDOMException, IOException, SAXException {
		SAXBuilder builder = new SAXBuilder();
		doc = builder.build(xmlFile);
	}

	public static void main(String[] args) {
		try {
			SportsmlMMOhjelma sovellus = new SportsmlMMOhjelma();

			sovellus.toJDOM();
			 // TEE METODI haeTulokset
			sovellus.haeTulokset();

		} catch (JDOMException e) {
			e.printStackTrace();
			System.out
					.println("XMl-dokumentti ei ole oikein muodostettu, koska "
							+ e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

	}

	// Palauta tästä lähtien Viopeen

	public void haeTulokset() {
		List<Element> tournamentList = doc.getRootElement().getChildren("tournament");
		
		for (int i = 0; i < tournamentList.size(); i++) {
			Element tournament = tournamentList.get(i);
			tulostaTournamentMetadata(tournament);
			System.out.println();
			tulostaTournamentDivision(tournament);
			System.out.println();
		}
	}
	
	private void tulostaTournamentMetadata(Element tournament) {
		Element tournamentMetadata = tournament.getChild("tournament-metadata");
		String	city=null,
				tournamentName=null,
				startDateTime=null,
				endDateTime=null;
		city = tournamentMetadata.getChild("site").getChild("site-metadata").getChild("home-location").getAttributeValue("city");
		tournamentName = tournamentMetadata.getAttributeValue("tournament-name");
		startDateTime = tournamentMetadata.getAttributeValue("start-date-time");
		endDateTime = tournamentMetadata.getAttributeValue("end-date-time");
		System.out.println(city +" " +tournamentName +" " +startDateTime +" - " +endDateTime);
	}

	private void tulostaTournamentDivision(Element tournament) {

		List<Element> tournamentDivisionList = tournament.getChildren("tournament-division");
		
		for (int i = 0; i < tournamentDivisionList.size(); i++) {
			Element tournamentDivision = tournamentDivisionList.get(i);
			String sportsContentCodeKey = 
					tournamentDivision.
					getChild("tournament-division-metadata").
					getChild("sports-content-codes").
					getChild("sports-content-code").getAttributeValue("code-key");
			String eventName =
					tournamentDivision.
					getChild("tournament-round").
					getChild("sports-event").
					getChild("event-metadata").getAttributeValue("event-name");
			System.out.println(sportsContentCodeKey +" " +eventName);
//			System.out.println();
			tulostaPlayers(tournamentDivision);
		}
	}

	private void tulostaPlayers(Element tournamentDivision) {
		List<Element> playerList = tournamentDivision.getChild("tournament-round").getChild("sports-event").getChildren("player");
		
		for (int i = 0; i < playerList.size(); i++) {
			Element player = playerList.get(i);
			String rankValue = player.getChild("player-stats").getChild("rank").getAttributeValue("value");
			String playerName = player.getChild("player-metadata").getChild("name").getAttributeValue("full");
			String playerCountry = player.getChild("player-metadata").getChild("home-location").getAttributeValue("country");
			String playerScore = player.getChild("player-stats").getAttributeValue("score");
			if (player.getChild("player-stats").getChild("award") != null) {
				String playerAwardName = player.getChild("player-stats").getChild("award").getAttributeValue("name");
				System.out.println(rankValue +". " +playerName +" " +playerCountry +" " +playerScore +" " +playerAwardName);
			} else {
				System.out.println(rankValue +". " +playerName +" " +playerCountry +" " +playerScore);
			}
		}
		System.out.println();
		
	}

}

/*
Tehtävä 2

SportsML mahdollistaa eri urheilulajien tietojen (pisteet, aikataulut, tulokset, tilastot) vaihtamisen XML-muodossa.
sportsmlMM.xml-dokumentissa on yleisurheilun MM-kisojen tuloksia vuosilta 2013 ja 2015 sekä kesän 2016 olympialaiset SportsML muodossa.
Tee XML-dokumentista alla oleva listaus JDOM:lla.

Esimerkki ohjelmasta

Moscow Friidrott 2013-08-10 - 2013-08-18 

Javelin final
1. Vítezslav Veselý Czech 87,17 Gold 
2. Tero Pitkämäki Finland 87,07 Silver 
3. Dmitri Tarabin Ryssland 86,23 Bronze 
4. Julius Yego Kenyan 85,40 
5. Antti Ruuskanen Finland 81,44 
6. Andreas Thorkildsen Norway 81,06 
7. Ihab Abdelrahman El Sayed Egypt 80,94 
8. Risto Mätas Estonia 80,03 

100-metre-dash final
1. Usain Bolt Jamaica 9,77 Gold 
2. Justin Gatlin USA 9,85 Silver 
3. Nesta Carter Jamaica 9,95 Bronze 
4. Kemar Bailey-Cole Jamaica 9,98 
5. Nickel Ashmeade Jamaica 9,98 
6. Mike Rodgers USA 10,04 
7. Christophe Lemaitre France 10,06 
8. James Dasaolu United Kingdom 10,24 


Peking Friidrott 2015-08-22 - 2015-08-30 

Javelin final
1. Johannes Vetter German 83,79 Gold 
2. Jakub Vadlejch Czech  89,73 Silver 
3. Petr Frydrych Czech 88,32 Bronze 
4. Thomas Röhler German 88,26 
5. Tero Pitkämäki Finland 86,94 
6. Ioannis Kiriazis Greece 84,52 
7. Keshorn Walcott German 84,48 
8. Andreas Hofmann Trinidad ja Tobago 83,98 

100-metre-dash final
1. Usain Bolt Jamaica 9,79 Gold 
2. Justin Gatlin USA 9,80 Silver 
3. Trayvon Bromell USA 9,92 Bronze 
3. Andre De Grasse Canada 9,92 Bronze 
5. Mike Rodgers USA 9,94 
6. Tyson Gay USA 9,94 
7. Asafa Powell Jamaica 10,00 
8. Jimmy Vicaut France 10,00 
9. Bingtian Su China 10,06 


Rio Olympic 2016-08-05 - 2016-08-21 

Javelin final
1. Röhler Thomas German 90,30 Gold 
2. Julius Yego Kenyan 88,24 Silver 
3. Keshorn Walcott Trinidad & Tobago 85,28 Bronze 
4. Johannes Vetter German 85,32 
5. Dmytro Kosynskyi Ukraina 83,95 
6. Antti Ruuskanen Finland 83,05 
7. IVítezslav Veselý Czech 82,51 
8. Jakub Vadlejch Czech 82,42 

100-metre-dash final
1. Usain Bolt Jamaica 9,81 Gold 
2. Justin Gatlin USA 9,89 Silver 
3. Andre De Grasse Canada 9,91 Bronze 
4. Johan Blake Jamaica 9,93 
5. Akani Simbine South Africa 9,94 
6. Meïté Ben Youssef Ivory Coast 9,96 
7. Jimmy Vicaut France 10,04 
8. Trayvon Bromell USA 10,06 


London IAAF World Championships 2017-08-04 - 2017-08-13 

Javelin final
1. Johannes Vetter German 89,89 Gold 
2. Jakub Vadlejch Czech 89,73 Silver 
3. Petr Frydrych Czech 88,32 Bronze 
4. Thomas Röhler German 88,26 
5. Tero Pitkämäki Finland 86,94 
6. Ioannis Kiriazis Greece 84,52 
7. Keshorn Walcott Trinidad & Tobago 84,48 
8. Andreas Hofmann German 83,98 

100-metre-dash final
1. Justin Gatlin USA 9,92 Gold 
2. Christian Coleman USA 9,94 Silver 
3. Usain Bolt Jamaica 9,95 Brons 
4. Johan Blake Jamaica 9,99 
5. Akani Simbine South Africa 10,01 
6. Jimmy Vicaut France 10,08 
7. Reece Prescod Great Britain 10,17 
8. Bingtian Su China 10,27

Tehtävän pisteytys
5p listaa tulokset
0p jos tehtävää ei ole Viopessa
 */
