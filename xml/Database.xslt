<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   	<HEAD>
            <TITLE >APOTHEKE DATA BASE</TITLE>
        <STYLE>
               H1 {style=background-color:teal;
               font-family: Arial,Univers,sans-serif;
               font-size: 36pt;
               font-effect-fire;
               text-align:center}
               
                 H2 {style=background-color:#1a21f7;
               font-family: Arial,Univers,sans-serif;
               font-size: 20pt;
               color:blue;
               text-align:center}
                H3 {style=background-color:#1a21f7;
               font-family: sans-serif;
               font-size: 15pt;
               color:lime}
               div {
					    width: 90px;
					    height: 20px;
					    background-color: white;
					    border: 1px solid black;
					    position: relative;
					    
					    -webkit-animation-name: example; 
					    -webkit-animation-duration: 4s; 
					    -webkit-animation-iteration-count: infinite; 
					    animation-name: example;
					    animation-duration: 4s;
					    animation-iteration-count: infinite;
					}
					
									@-webkit-keyframes example {
				    0%   {background-color:red; left:200px; top:0px;}
				    25%  {background-color:yellow; left:900px; top:0px;}
				    50%  {background-color:blue; left:900px; top:100px;}
				    75%  {background-color:lime; left:200px; top:100px;}
				    100% {background-color:red; left:200px; top:0px;}
				}
				
				
				@keyframes example {
				    0%   {background-color:red; left:200px; top:0px;}
				    25%  {background-color:yellow; left:900px; top:0px;}
				    50%  {background-color:blue; left:900px; top:100px;}
				    75%  {background-color:lime; left:200px; top:100px;}
				    100% {background-color:red; left:200px; top:0px;}
				}
               
        </STYLE>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
   		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=font-effect-fire">
   		</link>
   		</link>
   		</link>
    </HEAD>
   	<BODY STYLE="font-family:Arial, helvetica, sans-serif; font-size:12pt; background-color:#96f5ee">
   		
		<div>MegaStore</div>
   		<H1> <p><i class="fa fa-heart" style="font-size:60px;color:red;"></i><b>Apotheke Data Base: </b></p></H1>
   	 

  		 <H2><i class=" glyphicon glyphicon-user"></i><p><b>Clients: </b></p></H2>
  			 <table border="2" align="center" >	
  			 <th bgcolor="#f7a9f9">Name</th>
		 	 <th bgcolor="#f7a9f9">Address</th>	
		 	 <th bgcolor="#f7a9f9">Telephone</th>			
 		 	 <th bgcolor="#f7a9f9">Email</th>					
 		 	 <th bgcolor="#f7a9f9" ><i class="fa fa-cc-visa"></i>Payment Method</th>	
 		 	  <xsl:for-each select="Database/Clients/Client">
      <xsl:sort select="@name" />
        
            <tr bgcolor="white">
            <td><xsl:value-of select="@name" /></td>
            <td><xsl:value-of select="@address" /></td>
            <td><xsl:value-of select="@telephone" /></td> 
            <td><xsl:value-of select="@email" /></td> 
            <td><xsl:value-of select="@paymentMethod" /></td> 
            </tr>
         
      </xsl:for-each>
       </table>
  
   					<xsl:for-each select="Database/Clients/Client">
   					<xsl:sort select="//Database//Clients//Client//@name" />
   					  <H3><p><b>Client:</b><xsl:value-of select="@name" /></p></H3>
   					  <p><i class="glyphicon glyphicon-envelope"></i><b>Delivery:</b></p>
   			 			<table border="1" >
   			 		
     					<th bgcolor="#f7f408">ID</th>
      					<th bgcolor="#f7f408">Selling Price</th>
      					<th bgcolor="#f7f408">Sent</th>
     					<xsl:for-each select="Deliveries/Delivery">
     					 <xsl:sort select="//Database//Clients//Client//Deliveries//Delivery//@id" />
        
			            <tr bgcolor="white">
			            <td><i><xsl:value-of select="@id" /></i></td>
			            <td><xsl:value-of select="@sellingPrice" /></td> 
			            <td><xsl:value-of select="@sent" /></td> 
			            
			            
							            </tr>
							         
				      </xsl:for-each>
				    
				   </table>
				   
				   <p><i class="fa fa-car" style="font-size:20px;color:darkblue;"></i><b>Packages:</b></p>
				   <table border="1">
				      <th bgcolor="#2cf708">Delivery Id</th>
				      <th bgcolor="#2cf708">DrugId</th>
				      <th bgcolor="#2cf708">Amount</th>
				      <xsl:for-each select="Deliveries/Delivery/Packages/Package">
				      <xsl:sort select="@name" />
				        
				            <tr bgcolor="white">
				            <td><i><xsl:value-of select="@deliveryId" /></i></td>
				            <td><xsl:value-of select="@drugId" /></td>
				            <td><xsl:value-of select="@amount" /></td>
				            </tr>
				         
				      </xsl:for-each>
				   </table>
				 </xsl:for-each>
   
		   
		 	 <H2> <p><i class="fa fa-address-card "></i><b>Providers: </b></p></H2>
		  			 <table border="1" align="center">	
		  			 <th  bgcolor="#bb8fce ">ID</th>
		  			 <th  bgcolor="#bb8fce ">Name</th>
				 	 <th  bgcolor="#bb8fce ">Address</th>	
				 	 <th  bgcolor="#bb8fce ">Telephone</th>			
		 		 	 <th  bgcolor="#bb8fce ">Email</th>						
		 		 	  <xsl:for-each select="Database/Providers/Provider">
		      <xsl:sort select="@name" />
		        
		            <tr bgcolor="white">
		            <td><xsl:value-of select="@id" /></td> 
		            <td><xsl:value-of select="@name" /></td>
		            <td><xsl:value-of select="@address" /></td>
		            <td><xsl:value-of select="@telephone" /></td> 
		            <td><xsl:value-of select="@email" /></td> 
		            </tr>
		         
		      </xsl:for-each>
		   </table>
		   
		   				<xsl:for-each select="Database/Providers/Provider">
   						<xsl:sort select="//Database//Providers//Provider//@name" />
   					  <H3> <p><b>Provider</b><xsl:value-of select="@name" /></p></H3>	 			
						   <p><b>Arrivals:</b></p>
						   <table border="1" >
						   	  <th bgcolor="#f75808">Arrival Id</th>
						      <th bgcolor="#f75808">Buying Price</th>
						      <th bgcolor="#f75808">Received</th>
						      
						      <xsl:for-each select="Arrivals/Arrival">
						      <xsl:sort select="@name" />
						        
						            <tr bgcolor="white">
						            <td><i><xsl:value-of select="@arrivalId" /></i></td>
						            <td><xsl:value-of select="@buyingPrice" /></td>
						            <td><xsl:value-of select="@received" /></td>
						            </tr>
						         
						      </xsl:for-each>
						   </table>
						   
						     <p><b>Arrival Date:</b><xsl:value-of select="//date" /></p>
						   
						    <p><b>Arrives:</b></p>
						   <table border="1" >
						      <th bgcolor="#f7f408">DrugId</th>
							<th bgcolor="#f7f408">Arrival Id</th>
						      <th bgcolor="#f7f408">Amount</th>
						      <xsl:for-each select="Arrivals/Arrival/Arrives/Arrive">
						      <xsl:sort select="//Database//Providers//Arrivals//Arrival//Arrives//@drugId" />
						        
						            <tr bgcolor="white">
									 <td><xsl:value-of select="@drugId" /></td>
						            <td><i><xsl:value-of select="@arrivalId" /></i></td>
						            <td><xsl:value-of select="@amount" /></td>
						            </tr>
						         
						      </xsl:for-each>
						   </table>
						     </xsl:for-each>
		   
		   						
		     <H2> <i class="fa fa-building"></i><p><b>Warehouses: </b></p></H2>
		  			 <table border="1" align="center">	
		  			 <th  bgcolor="#f7a9f9">ID</th>
		  			  <th bgcolor="#f7a9f9" >pc</th>	
		  			  <th  bgcolor="#f7a9f9">country</th>	
		  			  <th  bgcolor="#f7a9f9">city</th>	
				 	 <th bgcolor="#f7a9f9">Address</th>	
		 		 	 <th bgcolor="#f7a9f9">Telephone</th>						
		 		 	  <xsl:for-each select="Database/Warehouses/Warehouse">
		      <xsl:sort select="@id" />
		        
		            <tr  bgcolor="white">
		            <td><xsl:value-of select="@id" /></td> 
		            <td><xsl:value-of select="@pc" /></td>
		            <td><xsl:value-of select="@country" /></td> 
		            <td><xsl:value-of select="@city" /></td> 
		            <td><xsl:value-of select="@address" /></td>
		            <td><xsl:value-of select="@phone" /></td> 
		         
		            </tr>
		         
		      </xsl:for-each>
		   </table>
		   <H3> <i class="fa fa-child" style="font-size:16px"></i>
		   		<i class="fa fa-child" style="font-size:24px"></i>
		   		<i class="fa fa-child" style="font-size:32px;color:red"></i>
		   		<p><b>Employees </b></p></H3>
		  			 <table border="1" >	
		  			 <th  bgcolor="#bb8fce ">ID</th>
		  			 <th  bgcolor="#bb8fce ">Name</th>
				 	 <th  bgcolor="#bb8fce ">Salary</th>	
				 	 <th  bgcolor="#bb8fce ">Telephone</th>			
		 		 	 <th  bgcolor="#bb8fce ">Position</th>						
		 		 	  <xsl:for-each select="Database/Warehouses/Warehouse">
		      <xsl:sort select="@id" />
		        
		            <tr bgcolor="white">
		            <td><xsl:value-of select="@id" /></td> 
		            <td><xsl:value-of select="@name" /></td>
		            <td><xsl:value-of select="@salary" /></td>
		            <td><xsl:value-of select="@phone" /></td> 
		            <td><xsl:value-of select="@position" /></td> 
		            </tr>
		         
		      </xsl:for-each>
		   </table>
		   					
		   					
		   					<xsl:for-each select="Database/Warehouses/Warehouse/Corridors/Corridor">
   					<xsl:sort select="//Database//Warehouses//Warehouse//Corridors//Corridor//@id" />
   					  <H3> <p><b>Corridor: </b><xsl:value-of select="@id" /></p></H3>
   					 	 <p><b>Temperature</b><xsl:value-of select="@temperature" /></p>
					   	  <p ><b>Drugs:</b></p>
					   	    <i class="fa fa-medkit" style="font-size:48px; color:red;"></i>
						  <table border="1">
					      <th bgcolor="#0816f7">Drug</th>
					      <th bgcolor="#0816f7">Name</th>
					      <th bgcolor="#0816f7">Stock</th>
					      <th bgcolor="#0816f7">Selling Price</th>
					       <th bgcolor="#0816f7">Active Principle</th>
					      <xsl:for-each select="Drugs/Drug">
					      <xsl:sort select="@name" />
					      
					            <tr bgcolor="white">
					            <td><i><xsl:value-of select="@id" /></i></td>
					            <td><xsl:value-of select="@name" /></td>
					            <td><xsl:value-of select="@stock" /></td>
					            <td><xsl:value-of select="@sellingPrice" /></td> 
					            <td><xsl:value-of select="@activePrinciple" /></td> 
					            </tr>
					         
					      </xsl:for-each>
					   </table>
					   </xsl:for-each>
		</BODY>				
		    
		</html>
		</xsl:template>
		
		</xsl:stylesheet>