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
		 <#list offers as offer>
            <a href="/shoppers/offer/${offer.offerID}">
                <div style="border: 1px solid black;">
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
                </div>
            </a>
        </#list>
		
		<h3>Orders</h3>	
		<#list orders as order>
			<div stlye="border: 1px solid black">
				<p>${order.orderID}</p>
			</div>
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