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

	<title>Mailing List</title>
	<link rel="icon" type="image/x-icon" href="../images/icons/favicon.svg">
</head>

<body>
	 <@macros.shoppernav/>

	<main>
        <#list subs as sub>
            <a href="/sellers/${sub.getSeller().getSellerID()}">
                <div style="border-bottom: 1px solid black;">
                    <p>${sub.getSeller().getUsername()}</p>

                    <form id="unsubscribe" action="/mailinglist/unsubscribe/${shopperID}/${sub.getSeller().getSellerID()}" method="post">
                        <button type="submit">Unsubscribe</button>
                    </form>					
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