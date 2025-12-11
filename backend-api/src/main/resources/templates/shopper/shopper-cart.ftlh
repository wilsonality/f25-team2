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
				   <li class="cart-item">
					   <#if item.getOffer().getOfferImagePath() ??>
						   <img src="${item.getOffer().getOfferImagePath()}" alt="" class="offer-picture">
					   <#else>
						   <img src="/offer-pictures/eraser.jpeg" alt="" class="offer-picture">
					   </#if>
					   <div class="text-box">
						   <p>${item.getOffer().getTitle()}</p>
						   <p class="grayed small">${item.getOffer().getDescription()}</p>
						   <p>$ ${item.getOffer().getPrice()}</p>
					   </div>
					   <div class="button-box">
						   <form id="deleteFromCart" action="/cart/delete/${item.getOffer().getOfferID()}" method="post">
						   <button type="submit">Delete From Cart</button>
						   </form>
   
						   <form id="order" action="/api/orders/${item.getOffer().offerID}" method="post">
							   <button type="submit">Request</button>
						   </form>
					   </div>
					</li>
			   </a>
		   </#list>
		   <#else>
		   <p>Your cart is empty.</p>
		   <p class="grayed">That's kinda weird. Go find some offers you like and request them.</p>
		   </#if>
		</ul>
		<hr>
		<section class="orders-box">
			<h1>Your Orders</h1>
			<#if orders ??>
			<ul class="orders-list">
				<#list orders as order>
				<li class="orders-item">
					<#if order.getOffer().getOfferImagePath() ??>
					<img src="${order.getOffer().getOfferImagePath()}" alt="" class="order-icon">
					<#else>
					<img src="/offer-pictures/eraser.jpeg" alt="" class="order-icon">
					</#if>
					<a href="/offers/${order.offer.getOfferID()}">
						<h3>${order.offer.getTitle()}</3>
					</a>
					<p>Status: ${order.getStatusAsString()}</p>
	
					<#if order.status == 1>
						<button class="delete cancel" data-order-id="${order.getOrderID()}">Cancel This Order</button>
					</#if>
				</li>
				</#list>
			</ul>
			<#else>
			<p>Hmm... You don't have any orders on your offers. Come back when someone has requested your offer.</p>
			<a href="/offers/myoffers">
				<button class="view">See Your Offers </button>
			</a>
        	</#if>
		</section>
	</main>
	<script src="/js/orders.js" defer></script>
</body>
</html>