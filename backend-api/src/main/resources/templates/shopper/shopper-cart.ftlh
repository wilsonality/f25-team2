<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="View Cart"/>
</head>
<body>
    <@macros.navbar/>
	<main class="shopper-cart">
		<h3>Cart</h3>
		<ul class="offers-list">
			<#if items??>
			<#list items as item>
			   <a href="/offers/${item.getOffer().getOfferID()}">
				   <li class="offer-item">
						<p>${item.getOffer().getTitle()}</p>
						<p class="grayed small">${item.getOffer().getDescription()}</p>
					    <p>$ ${item.getOffer().getTitle()}</p>
						<#if item.getOffer().getOfferImagePath() ??>
							<img src="${item.getOffer().getOfferImagePath()}" alt="" class="offer-icon">
						<#else>
							<img src="/offer-pictures/eraser.jpeg" alt="" class="offer-icon">
						</#if>

						<form id="deleteFromCart" action="/cart/delete/${item.getOffer().getOfferID()}" method="post">
						<button type="submit">Delete From Cart</button>
						</form>

						<form id="order" action="/api/orders/${item.getOffer().offerID}" method="post">
							<button type="submit">Request</button>
						</form>
					</li>
			   </a>
		   </#list>
		   <#else>
		   <p>Your cart is empty.</p>
		   <p class="grayed">That's kinda weird. Go find some offers you like and request them.</p>
		   </#if>
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