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
