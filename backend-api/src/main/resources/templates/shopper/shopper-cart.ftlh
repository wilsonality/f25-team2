<#import "../macros.ftlh" as macros>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="../styles.css">
    <link rel="stylesheet" href="../provider-prototype/provider_styles.css">
	<link rel="stylesheet" href="profile.css">
	<link rel="stylesheet" href="shopper_profile.css">

	<title>Cart</title>
	<link rel="icon" type="image/x-icon" href="../images/icons/favicon.svg">
</head>

<body>
	 <@macros.shoppernav/>

	<main>
		<h3>Cart</h3>
		<ul class="offers-list">
			<#list offers as offer>
			   <a href="/shoppers/offer/${offer.offerID}">
				   <li class="offer-item">
					   <p>${offer.title}</p>
					   <p>${offer.description}</p>
					   <p>$ ${offer.price}</p>
					   <img href="${offer.offerImagePath}" alt="Image of ${offer.title}">
   
					   <form id="deleteFromCart" action="/cart/delete/${offer.offerID}" method="post">
					   <button type="submit">Delete From Cart</button>
					   </form>
   
					   <form id="order" action="/api/orders/${offer.offerID}" method="post">
					   <button type="submit">Request</button>
					   </form>
					</li>
			   </a>
		   </#list>
		</ul>
		<hr>
		<h3>Orders</h3>	
		<#list orders as order>
		<a href="/shoppers/offer/${order.offer.getOfferID()}">
			<div style="border-bottom: 1px solid black;">
				<p>${order.offer.getTitle()}</p>
				<p>${order.getStatusAsString()}</p>

				<!-- Pending -->
				<#if order.status == 1>
					<form id="cancelOrder" action="/api/orders/delete/${order.orderID}" method="get">
					<button type="submit">Cancel</button>
					</form>
				</#if>
			</div>
		</a>	
		</#list>
	</main>

	<footer>
        <hr>
		<!-- Footer -->
		<p>About Us</p>
		<p>Contact</p>
		<p>Terms of Service</p>
	</footer>
</body>
</html>