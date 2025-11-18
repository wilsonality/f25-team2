<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body>
    <@macros.sellernav/>
    <main class="open-sans">
        <h2>welcome ${seller.getUsername()}</h2>

        <section class="offers-box">
            <!-- show all offers -->
            <h1>See Your Offers</h1>
            <ul class="offers-list">
                <#list offers as offer>
                        <li class="offer-item">
                            <a href="/offers/${offer.getOfferID()}/seller">
                                <img src="backend-api/src/main/resources/static/offer-pictures/eraser.jpeg" alt="" class="offer-picture">
                                <h3>${offer.getTitle()}</h3>
                                <p>Availability: ${offer.isAvailability()?string('Available Now', 'Unavailable')}</p>
                                <p>Purchased: ${offer.getNumPurchased()}</p>
                            </a>
                        </li>
                </#list>
            </ul>
        </section>
        <section class="orders-box">
            <!-- show all orders -->
            <h3>Pending Orders</h3>
            <ul class="orders-list">
                <#if requests??>
                    <#list requests as request>
                        <li class="request-item">
                            <p>${request.getShopper().getUserName()}</p>
                            <p>${request.getOffer().getTitle()}</p>
                        </li>
                    </#list>
                </#if>
            </ul>
        </section>
    </main>
    
</body>
</html>