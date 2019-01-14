<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes"/>
	<xsl:template match="/">
		<html>
			<head>
				<title>Vuosi 2017</title>
			</head>
			<body>
				<xsl:apply-templates select="events/event"/>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="event">
		<p>
		Päivä: <xsl:value-of select="date"/><br/>
		Tapahtuma: <xsl:value-of select="name"/><br/>
		Kuvaus: <xsl:value-of select="description"/><br/>
		url: <xsl:value-of select="url"/>
		</p>
		<!-- 
<event>
	<date>2017-02-05</date>
	<name>J. L. Runebergin päivä</name>
	<flag_day>1</flag_day>
	<age>213</age>
	<url>https://fi.wikipedia.org/wiki/Johan_Ludvig_Runeberg</url>
	<description>Johan Ludvig Runeberg (5. helmikuuta 1804 Pietarsaari - 6. toukokuuta 1877 Porvoo) oli Suomen kansallisrunoilija. Hän oli suomalainen ruotsinkielinen runoilija, kirjailija ja toimittaja, jonka tuotanto on hyvin isänmaallista. Hän on ollut arvostettu myös Ruotsissa ja hänen tuotantonsa vaikutti suuresti koko ruotsinkieliseen kirjallisuuteen.</description>
</event>
		 -->
	</xsl:template>

</xsl:stylesheet>
