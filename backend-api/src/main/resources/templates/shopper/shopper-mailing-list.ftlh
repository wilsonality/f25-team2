<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="Mailing List"/>
</head>

<body>
	 <@macros.navbar/>
	<main>
		<ul>
        <#list subs as sub>
		<li class="cart-item">
            <a href="/sellers/${sub.getSeller().getSellerID()}">
				<#if sub.seller.getProfileImagePath()??>
					<img src="${sub.seller.getProfileImagePath()}" alt="" class="profile-image pulse">
				<#else>
					<img src="/seller-pictures/default.jpg" alt="" class="profile-image pulse">
				</#if>

				<h1>${sub.seller.getUsername()}</h1>
				<p>Spartan Seller</p>
				<p>Member since: ${sub.seller.getJoinDate()}</p>
				<p>Description: ${sub.seller.getProfileBio()}</p>


				<form id="unsubscribe" action="/mailinglist/unsubscribe/${shopperID}/${sub.getSeller().getSellerID()}" method="post">
					<button type="submit">Unsubscribe</button>
				</form>					
            </a>
		</li>	
        </#list>
		</ul>
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