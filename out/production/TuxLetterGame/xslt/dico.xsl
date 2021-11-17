<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:tux="http://myGame/tux">
    <xsl:output method="html"/>
    <xsl:template match="/">
        <html>
            <body>
                <ul>
                    <xsl:for-each select="tux:dictionnaire/tux:mot">
                        <xsl:sort select="@niveau" order="ascending"/>
                        <p>Niveau: <xsl:value-of select="@niveau"/></p>
                        <xsl:sort select="text()" order="ascending"/>
                        <p>Text: <xsl:value-of select="text()"/></p>
                    </xsl:for-each>
                </ul>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>