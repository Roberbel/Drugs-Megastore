<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   
   <p><b>Drug: </b><xsl:value-of select="//Drug//@name" /></p>
   <p><b>ID: </b><xsl:value-of select="//Drug//@id" /></p>
   <p><b>Stock: </b><xsl:value-of select="//Drug//@stock" /></p>
   <p><b>Selling Price: </b><xsl:value-of select="//Drug//@sellingPrice" /></p>
      <p><b>Active Principle: </b><xsl:value-of select="//Drug//@activePrinciple" /></p>
  
  
   <p><b>Corridor:</b> <xsl:value-of select="//Drug//corridor//@id" /></p>
    <p><b>Temperature:</b><xsl:value-of select="//Drug//corridor//@temperature" /></p>
     <p><b>Warehouse:</b><xsl:value-of select="//Drug//corridor//warehouse//@id" /></p>
     
    
   <p><b>Packaged:</b></p>
   <table border="1">
      <th>DrugId</th>
      <th>DeliveryId</th>
      <th>Amount</th>
   	<th>Selling Price</th>
   	<th>Sent</th>
   	<th>Client</th>	
      <xsl:for-each select="Drug/Packaged/Package">
      <xsl:sort select="@name" />
        
            <tr>
            <td><i><xsl:value-of select="@drugId" /></i></td>
            <td><xsl:value-of select="@deliveryId" /></td>
            <td><xsl:value-of select="@amount" /></td>
            <td><xsl:value-of select="//Drug//Packaged//Package//delivery//@sellingPrice" /></td>
            <td><xsl:value-of select="//Drug//Packaged//Package//delivery//@sent" /></td>
            <td><xsl:value-of select="//Drug//Packaged//Package//delivery//client//@name" /></td>
             
             
            </tr>
         
      </xsl:for-each>
   </table>
   
 
   
   </html>
</xsl:template>

</xsl:stylesheet>