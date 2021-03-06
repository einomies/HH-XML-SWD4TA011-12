<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes"/>
	<xsl:template match="/">
		<html>
			<head>
				<title>Elokuvat</title>
			</head>
			<body>
				<h3>Elokuvat</h3>
				<xsl:apply-templates select="Events/Event">
					<xsl:sort select="Title" order="ascending" />
				</xsl:apply-templates>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="Event">
		<p>
		Nimi: <xsl:value-of select="Title"/> (<xsl:value-of select="OriginalTitle"/>)<br/>
		Kuvaus: <xsl:value-of select="ShortSynopsis"/><br/>
		Vuosi: <xsl:value-of select="ProductionYear"/><br/>
		Esitykseen: <xsl:value-of select="substring(dtLocalRelease, 1, 10)"/><br/>
		Ohjaajat: <xsl:apply-templates select="Directors"/>
		Näyttelijät: <xsl:apply-templates select="Cast"/>
		</p>
	</xsl:template>
	<xsl:template match="Directors">
		<xsl:for-each select="Director">
		    <xsl:choose>
		        <xsl:when test="position()!=last()">
				    <xsl:value-of select="FirstName"/>
				    <xsl:text>&#160;</xsl:text>
				    <xsl:value-of select="LastName"/>
		            <xsl:text>, </xsl:text>
		        </xsl:when>
		        <xsl:when test="position()=last()">
				    <xsl:value-of select="FirstName"/>
				    <xsl:text>&#160;</xsl:text>
				    <xsl:value-of select="LastName"/>
		        </xsl:when>
		    </xsl:choose>
		</xsl:for-each>
		<br/>
	</xsl:template>
	<xsl:template match="Cast">
		<xsl:for-each select="Actor">
		    <xsl:choose>
		        <xsl:when test="position()!=last()">
				    <xsl:value-of select="FirstName"/>
				    <xsl:text>&#160;</xsl:text>
				    <xsl:value-of select="LastName"/>
		            <xsl:text>, </xsl:text>
		        </xsl:when>
		        <xsl:when test="position()=last()">
				    <xsl:value-of select="FirstName"/>
				    <xsl:text>&#160;</xsl:text>
				    <xsl:value-of select="LastName"/>
		        </xsl:when>
		    </xsl:choose>
		</xsl:for-each>
		<br/>
	</xsl:template>
</xsl:stylesheet>
