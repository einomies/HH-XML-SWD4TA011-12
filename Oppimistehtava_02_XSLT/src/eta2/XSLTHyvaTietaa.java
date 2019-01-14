// MUUTA ALLA OLEVAA RIVIÄ TARVITTAESSA
package eta2;

import java.io.File;
import java.io.IOException;
import java.io.StringReader; 

public class XSLTHyvaTietaa {

	public static void main(String[] args)
			throws javax.xml.transform.TransformerException, IOException {

		try {
			
			String xslt = "<?xml version='1.0' encoding='UTF-8'?> <xsl:stylesheet version='1.0' xmlns:xsl='http://www.w3.org/1999/XSL/Transform'> 	<xsl:output method='html' indent='yes'/> 	<xsl:template match='/'> 		<html> 			<head> 				<title>Vuosi 2017</title> 			</head> 			<body> 				<xsl:apply-templates select='events/event'/> 			</body> 		</html> 	</xsl:template>  	<xsl:template match='event'> 		<p> 		Päivä: <xsl:value-of select='date'/><br/> 		Tapahtuma: <xsl:value-of select='name'/><br/> 		Kuvaus: <xsl:value-of select='description'/><br/> 		url: <xsl:value-of select='url'/> 		</p> 		<!--  <event> 	<date>2017-02-05</date> 	<name>J. L. Runebergin päivä</name> 	<flag_day>1</flag_day> 	<age>213</age> 	<url>https://fi.wikipedia.org/wiki/Johan_Ludvig_Runeberg</url> 	<description>Johan Ludvig Runeberg (5. helmikuuta 1804 Pietarsaari - 6. toukokuuta 1877 Porvoo) oli Suomen kansallisrunoilija. Hän oli suomalainen ruotsinkielinen runoilija, kirjailija ja toimittaja, jonka tuotanto on hyvin isänmaallista. Hän on ollut arvostettu myös Ruotsissa ja hänen tuotantonsa vaikutti suuresti koko ruotsinkieliseen kirjallisuuteen.</description> </event> 		 --> 	</xsl:template>  </xsl:stylesheet>";
//			String xslt = "Laita tänne muunnaViopella ohjelmalla muunnettu hyvaTietaa2017.xslt";
			
			StringReader readerXSLT = new StringReader(xslt);
			final File xmlFile = new File("hyvaTietaa2017.xml");
			final File result = new File("hyvaTietaa2017.html");

			javax.xml.transform.Source xmlSource = new javax.xml.transform.stream.StreamSource(
					xmlFile);
			javax.xml.transform.Source xsltSource = new javax.xml.transform.stream.StreamSource(
					readerXSLT);
			javax.xml.transform.Result htmlResult = new javax.xml.transform.stream.StreamResult(
					result);

			javax.xml.transform.TransformerFactory transFact = javax.xml.transform.TransformerFactory
					.newInstance();

			javax.xml.transform.Transformer trans = transFact
					.newTransformer(xsltSource);

			trans.transform(xmlSource, htmlResult);
			System.out
					.println("Muunnoksen tulos on tiedostossa hyvaTietaa2017.html");

		} catch (javax.xml.transform.TransformerException ex) {
			System.out.println("Muunnos ei onnistu, koska ");
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Ongelmia, koska ");
			System.out.println(ex.getMessage());
		}
	}

}
