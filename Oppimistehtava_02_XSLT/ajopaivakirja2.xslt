<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes"/>
	<xsl:template match="/">
		<html>
			<head>
				<title>Ajopaivakirja</title>
			</head>
			<body>
				<table border="1" cellpadding="5" cellspacing="0">
					<tr>
						<th>Lähtöaika</th>
						<th>Lähtöpaikka</th>
						<th>Tuloaika</th>
						<th>Tulopaikka</th>
						<th>Matka ja korvaus</th>
					</tr>
					<xsl:apply-templates />
				</table>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="ajo">
		<tr>
		<td>
			<xsl:apply-templates select="alku/aika"/>
		</td>
		<td>
			<xsl:value-of select="alku/paikka"/>
		</td>
		<td>
			<xsl:apply-templates select="loppu/aika"/>
		</td>
		<td>
			<xsl:apply-templates select="loppu/paikka"/>
		</td>
		<td>
			<xsl:variable name="kilometrit" select="number(loppu/lukema) - number(alku/lukema)"/>
			<xsl:value-of select="$kilometrit"/><xsl:text> kilometriä, </xsl:text>
			<!-- Km-korvaus on ilmeisesti 0,41 EUR/km alkuperäisen html-sivun tulosten perusteella -->
			<xsl:variable name="eurperkm" select="number(0.41)"/>
			<xsl:value-of select="number($kilometrit * $eurperkm)"/><xsl:text> euroa</xsl:text>
		</td>
		</tr>
	</xsl:template>
	<xsl:template match="aika">
		<xsl:value-of select="@paiva"/>
		<xsl:text>&#160;</xsl:text>
		<xsl:value-of select="@kello"/>
	</xsl:template>
</xsl:stylesheet>
