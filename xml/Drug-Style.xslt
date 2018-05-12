<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   
   <p><b>Drug: </b><xsl:value-of select="//Drug//@name" /></p>
   <p><b>ID: </b><xsl:value-of select="//Drug//@id" /></p>
   <p><b>Stock: </b><xsl:value-of select="//Drug//@stock" /></p>
   <p><b>Selling Price: </b><xsl:value-of select="//Drug//@sellingPrice" /></p>
      <p><b>Active Principle: </b><xsl:value-of select="//Drug//@activePrinciple" /></p>
   <table border="1">
      <th>Drug</th>
      <th>Name</th>
      <th>Stock</th>
      <th>Selling Price</th>
       <th>Active Principle</th>
      <xsl:for-each select="Drug">
      <xsl:sort select="@name" />
        
            <tr>
            <td><i><xsl:value-of select="@id" /></i></td>
            <td><xsl:value-of select="@name" /></td>
            <td><xsl:value-of select="@stock" /></td>
            <td><xsl:value-of select="@sellingPrice" /></td> 
            <td><xsl:value-of select="@activePrinciple" /></td> 
            </tr>
         
      </xsl:for-each>
   </table>
  
   <p><b>Corridor:</b></p>
   <table border="1">
      <th>Corridor</th>
      <th>Temperature</th>
      <th>Warehouse</th>
      <xsl:for-each select="Drug/corridor">
      <xsl:sort select="@name" />
        
            <tr>
            <td><i><xsl:value-of select="@id" /></i></td>
            <td><xsl:value-of select="@temperature" /></td>
            <td><xsl:value-of select="//Drug//corridor//warehouse//@id" /></td>
            </tr>
         
      </xsl:for-each>
   </table>
    
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
   
   <p><b>Arrives:</b></p>
   <table border="1">
      <th>drugId</th>
      <th>ArrivalId</th>
      <th>amount</th>
      <th>buying Price</th>
       <th>received</th>
        <th>Date</th>
       
      <xsl:for-each select="Drug/Arrives/Arrive">
      <xsl:sort select="@name" />
        
            <tr>
            <td><i><xsl:value-of select="@drugId" /></i></td>
            <td><xsl:value-of select="@arrivalId" /></td>
            <td><xsl:value-of select="@amount" /></td>
            <td><xsl:value-of select="//Drug//Arrives//Arrive//arrival//@buyingPrice" /></td>
            <td><xsl:value-of select="//Drug//Arrives//Arrive//arrival//@received" /></td>
            <td><xsl:value-of select="//Drug//Arrives//Arrive//arrival//date" /></td>
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
            <td><i><xsl:value-of select="@id" /></i></td>
            <td><xsl:value-of select="@pc" /></td>
            <td><xsl:value-of select="@country" /></td>
            <td><xsl:value-of select="@city" /></td>
            <td><xsl:value-of select="@address" /></td>
            <td><xsl:value-of select="@phone" /></td>
            </tr>
         
      </xsl:for-each>
   </table>
 
   
   </html>
</xsl:template>

</xsl:stylesheet>