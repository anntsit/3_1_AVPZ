<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl= "http://www.w3.org/1999/XSL/Transform" version= "1.0">
<xsl:output method="html" indent="yes"/>
<xsl:param name="needed_date" />
<xsl:template match="/">
<html>
  <body>
  	 <xsl:apply-templates/>
  </body>
</html>
</xsl:template>
<xsl:template match="MeetingInfo">
   <table border="2" width="100%" >
  	 <tr bgcolor="#CCEEFF">
  	 	<td><font size="4">Meeting Date</font></td>  	 	
  	 	<td><font size="4">Meeting Time</font></td>
  	 	<td><font size="4">Meeting Person Name</font></td>
  	 	<td><font size="4">Meeting Place</font></td>
  	 </tr>
 	<xsl:for-each select="Meeting">
 		<xsl:if test="@date = $needed_date">
 	 	 <tr>
 	      	<td bgcolor="#FFFFE0"><xsl:value-of select="@date"/></td>
 	      	<td><xsl:value-of select="@time"/></td>
 	 		<td><xsl:value-of select="MeetingPersonName"/></td>
 	 		<td><xsl:value-of select="MeetingPlace"/></td>
 	   	</tr>
 	   	</xsl:if>
 	</xsl:for-each>
    </table>
  </xsl:template>
</xsl:stylesheet>
