<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:tux="http://myGame/tux">
    <xsl:output method="html"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Player Stats</title>
            </head>
            <body>
                <h2>Player: <xsl:value-of select="tux:profil/tux:nom/text()"/></h2>
                <xsl:element name="img">
                    <xsl:attribute name="src">
                        <xsl:value-of select="tux:profil/tux:avatar/text()"/>
                    </xsl:attribute>
                    <xsl:attribute name="alt">
                        <xsl:value-of select="tux:profil/tux:avatar/text()"/>
                    </xsl:attribute>
                </xsl:element>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="tux:partie">
        <p>
            <p><h3>Partie <xsl:value-of select="position() div 2"/></h3></p>
            <p> Date: <xsl:value-of select="@date"/></p>
            <p> Temps: <xsl:value-of select="tux:temps/text()"/></p>
            <p> Mot: <xsl:value-of select="tux:mot/text()"/>
                Niveau de mot: <xsl:value-of select="tux:mot/@niveau"/></p>
        </p>
    </xsl:template>
    <xsl:template match="text()"/>
</xsl:stylesheet>