<?xml version="1.0" encoding="ISO-8859-15"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes"/>
	<xsl:template match="/">
		<html>
			<head>
				<meta content="text/html; charset=ISO-8859-15" http-equiv="Content-Type"/>
				<title>Tilaukset</title>
			</head>
			<body>
				<h2>Tilaukset</h2>
				<xsl:apply-templates select="tilaukset/tilaus"/>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="tilaus">
		<p>
			<b>Tilausnmero:</b>&#160;<xsl:value-of select="@tilausnumero"/>
			<br/>
			<b>Asiakas:</b>&#160;<xsl:value-of select="asiakas/nimi"/>
			<br/>
			<b>Tila:</b>&#160;<xsl:value-of select="@tila"/>
		</p>
		<table border="1" cellpadding="5" cellspacing="0">
			<tr>
				<th>Tuotenumero</th>
				<th>Yksikköhinta</th>
				<th>Määrä</th>
			</tr>
			<xsl:apply-templates select="tilausrivi"/>
		</table>
	</xsl:template>
	<xsl:template match="tilausrivi">
		<tr>
			<td>
				<xsl:value-of select="@tuotekoodi"/>
			</td>
			<td>
				<xsl:value-of select="hinta"/>
			</td>
			<td>
				<xsl:value-of select="maara"/>&#160; <xsl:value-of select="maara/@yksikko"/>
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>
