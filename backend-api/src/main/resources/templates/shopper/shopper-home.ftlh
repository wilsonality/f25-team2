<#import "../macros.ftlh" as macros>
<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body>
    <@macros.navbar/>
	<main class="open-sans">
		<section class="offers-box">
			<#if offers??>
            <ul class="offers-list">
                <#list offers as offer>
                        <li class="offer-item">
                            <a href="/offers/${offer.getOfferID()}">
                                <#if offer.getOfferImagePath()??>
                                    <img src="${offer.getOfferImagePath()}" alt="" class="offer-preview">
                                <#else>
                                    <img src="/offer-pictures/eraser.jpeg" alt="" class="offer-preview">
                                </#if>
                                <h3>${offer.getTitle()}</h3>
                                <p>Availability: ${offer.isAvailability()?string('Available Now', 'Unavailable')}</p>
                                <#if offer.getPrice()??>
									<p>Price: $${offer.getPrice()}</p>
								</#if>
								
                            </a>
                        </li>
                </#list>
            </ul>
            <#else>
            <p>Hmm... You don't have any offers.</p>
            <a href="/offers/create">
                <button class="create">Post an Offer Here</button>
            </a>
            </#if>
		</section>
		<section class="cart-preview">
			<!-- cart content preview -->
		</section>
		<section class="orders-box">
			<!-- show your requests -->
			 <h1>Your Requests</h1>
			 <#if requests ??>
                <ul class="orders-list">
                    <#list requests as request>
                        <li class="request-item">
                            <p>${request.getShopper().getUsername()}</p>
                            <p>${request.getOffer().getTitle()}</p>
                        </li>
                    </#list>
                </ul>
            <#else>
                <p>Hmm... You don't have any requests. Browse offers and make a request</p>
                <a href="/offers">
                	<button class="view">Browse Offers </button>
            	</a>
            </#if>
		</section>
	</main>
	<footer>
        <hr>
		<p>About Us</p>
		<p>Contact</p>
		<p>Terms of Service</p>
	</footer>
</body>
</html>