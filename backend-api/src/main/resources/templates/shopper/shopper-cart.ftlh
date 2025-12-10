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
		<#list offers as offer>
			<p>${offer}</p>
			<p>a</p>
		</#list>


       <p>Cart</p>
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