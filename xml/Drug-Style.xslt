<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <p><b><xsl:value-of select="//name" /></b></p>
   <p><b>Price: </b><xsl:value-of select="//sellingPrice" /></p>
   <p><b>Stock : </b><xsl:value-of select="//stock" /></p>
   <p><b>Active Principle: </b><xsl:value-of select="//activePrinciple" /></p>
   
   <p><b>Corridor:</b></p>
   <table border="1">
      <th>Corridor</th>
      <th>temperature</th>
      <th>warehouse</th>
      <xsl:for-each select="Drug/Corridor">
      <xsl:sort select="@name" />
        
            <tr>
            <td><i><xsl:value-of select="@name" /></i></td>
            <td><xsl:value-of select="temperature" /></td>
            <td><xsl:value-of select="warehouse" /></td>
            </tr>
         
      </xsl:for-each>
   </table>
    
   <p><b>Packaged:</b></p>
   <table border="1">
      <th>drugId</th>
      <th>deliveryId</th>
      <th>amount</th>
      <xsl:for-each select="Drug/packaged/Packaged">
      <xsl:sort select="@name" />
        
            <tr>
            <td><i><xsl:value-of select="drugId" /></i></td>
            <td><xsl:value-of select="deliveryId" /></td>
            <td><xsl:value-of select="amount" /></td>
            </tr>
         
      </xsl:for-each>
   </table>
   
   <p><b>Arrives:</b></p>
   <table border="1">
      <th>drugId</th>
      <th>ArrivalId</th>
      <th>amount</th>
      <xsl:for-each select="Drug/arrives/Arrives">
      <xsl:sort select="@name" />
        
            <tr>
            <td><i><xsl:value-of select="drugId" /></i></td>
            <td><xsl:value-of select="arrivalId" /></td>
            <td><xsl:value-of select="amount" /></td>
            </tr>
         
      </xsl:for-each>
   </table>
        <p><b>Delivery:</b></p>
   <table border="1">
      <th>ID</th>
      <th>Selling Price</th>
      <th> Client</th>
      <th> Transaction Date</th>
      
      <xsl:for-each select="Drug/packaged/delivery">
      <xsl:sort select="@name" />
        
            <tr>
            <td><i><xsl:value-of select="id" /></i></td>
            <td><xsl:value-of select="sellingPrice" /></td>
            <td><xsl:value-of select="client" /></td>
            <td><xsl:value-of select="transactionDate" /></td>
       
            </tr>
         
      </xsl:for-each>
   </table>
   
   <p><b>Warehouse:</b></p>
   <table border="1">
      <th>ID</th>
      <th>PC</th>
      <th> Country</th>
      <th> City</th>
      <th>Address</th>
      <th>Phone</th>
      <xsl:for-each select="Drug/Warehouse">
      <xsl:sort select="@name" />
        
            <tr>
            <td><i><xsl:value-of select="id" /></i></td>
            <td><xsl:value-of select="pc" /></td>
            <td><xsl:value-of select="country" /></td>
            <td><xsl:value-of select="city" /></td>
            <td><xsl:value-of select="address" /></td>
            <td><xsl:value-of select="phone" /></td>
            </tr>
         
      </xsl:for-each>
   </table>
 
   
   </html>
</xsl:template>

</xsl:stylesheet>