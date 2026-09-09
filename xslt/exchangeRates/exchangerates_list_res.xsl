<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:tns="sales"
                exclude-result-prefixes="tns">

    <xsl:output method="xml" indent="yes"/>

    <xsl:template match="/">
	
	
	<tns:DT_SAP_EXCHANGERATES_LIST_RES>
        <CurrencyList>

            <xsl:for-each select="/tns:DT_3RD_EXCHANGERATES_LIST_RES/currencies/*">

                <Row>

                    <name>
                        <xsl:value-of select="name()"/>
                    </name>
					
					<longName>
                        <xsl:value-of select="."/>
                    </longName>

                </Row>

            </xsl:for-each>

        </CurrencyList>
		
	</tns:DT_SAP_EXCHANGERATES_LIST_RES>

    </xsl:template>

</xsl:stylesheet>