<?xml version="1.0" encoding="ISO-8859-15"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<!-- output kertoo, mihin muotoon muunto tehd��n -->
	<xsl:output method="html" indent="yes"/>
	<!-- Malli kertoo, kuinka XML-dokumentista tehd��n html-sivu-->
	<xsl:template match="/">
		<!-- Malli sis�lt�� kokonaisen html-sivun rakenteen -->
		<html>
			<head>
				<meta content="text/html; charset=ISO-8859-15" http-equiv="Content-Type"/>
				<title>Tuotteet</title>
			</head>
			<body>
				<h2>Tuotteet</h2>
				<!-- Suoritetaan malli. Malli suoritetaan XML-dokumentissa tuotteet/tuote -hakupolun elementeille -->
				<xsl:apply-templates select="tuotteet/tuote"/>
			</body>
		</html>
	</xsl:template>
	<!-- Malli kertoo, miten tuote-elementti muunnetaan html-sivulle -->
	<xsl:template match="tuote">
		<p>
			<!-- tuotekoodi on tuote-elementin attribuuttii. Attribuutit haetaan value-of -komennolla -->
	Tuotekoodi: <xsl:value-of select="@tuotekoodi"/>
			<br/>
			<!-- nimi on tuote-elementin alielementti. Koska nimi-elementtej� on tuote-elementill� vain yksi, k�ytet��n value-of -komentoa -->
	Nimi: <xsl:value-of select="nimi"/>
			<br/>
	Kuvaus: <xsl:value-of select="kuvaus"/>
			<br/>
			<!-- &#160; kirjoittaa v�lily�nnin html-sivulle -->
			<!-- hinta/@valuutta, koska haetaan hinnan valuutta-attribuutti. Jos olisi pelkk� @valuutta, haettaisiin tuote-elementin valuutta-attribuutia -->
	Hinta: <xsl:value-of select="hinta"/>&#160;<xsl:value-of select="hinta/@valuutta"/>
			<br/>
			<!-- Testataan, onko tuote-elementill� kuva-alielementti� -->
			<xsl:if test="kuva">
				<!-- tuote-elementill� voi olla useita kuva-alielementtej�, mink� vuoksi k�ytet��n mallia-->
	Kuvat: <xsl:apply-templates select="kuva"/>
			</xsl:if>
			<hr/>
		</p>
	</xsl:template>
	<!-- Malli kertoo, miten kuva-elementti muunnetaan html-sivulle -->
	<xsl:template match="kuva">
		<xsl:value-of select="@src"/>
		<!-- testataan, ettei elementti ole viimeinen kuva. Jos ei ole, laitetaan pilkku -->
		<xsl:if test="position() &lt; last()">,&#160;</xsl:if>
	</xsl:template>
	<!-- YHTEENVETO -->
	<!-- 1.  Mallit (xsl:template): kertovat, miten elementit muunnetaan HTML-muotoon. Malli tehd��n elementille, joita on useita. tuote-elementtej� on monta, tuote-elementill� voi olla monta kuvaa -->
	<!-- 2.  Mallikutsu (xsl:apply-templates): pyyt�� k�ytt�m��n tehty� mallia -->
	<!-- 3.  Tiedon hakeminen XML:st� (xsl:value-of): hakee elementin/attribuutin arvon XML-dokumentista -->
</xsl:stylesheet>
