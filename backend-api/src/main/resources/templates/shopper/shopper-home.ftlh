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

	<title>Home</title>
	<link rel="icon" type="image/x-icon" href="../images/icons/favicon.svg">
</head>

<body>
	 <@macros.shoppernav/>

	<main>
        <#list offers as offer>
            <a href="/shoppers/offer/${offer.offerID}">
                <div style="border: 1px solid black;">
                    <p>${offer.title}</p>
                    <p>${offer.description}</p>
                    <p>$ ${offer.price}</p>
                    <img href="${offer.offerImagePath}" alt="Image of ${offer.title}">
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