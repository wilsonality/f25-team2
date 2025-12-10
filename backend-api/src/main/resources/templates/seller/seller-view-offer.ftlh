<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${offer.getTitle()}"/>
    <link rel="stylesheet" type="text/css" href="/css/offer.css">
</head>
<body class="open-sans-bold">
    <@macros.navbar/>
    <main>
        <section class="offer-summary">
            <div class="offer-left">
                <img src="" alt="" class="offer-picture">
                <h3>${offer.getTitle()}</h3>
                <!-- <p>OfferID: ${offer.getOfferID()}</p> -->
                <p>Availability: ${offer.isAvailability()?string('Available Now', 'Unavailable')}</p>
                <p>Purchased: ${offer.getNumPurchased()}</p>
            </div>
            <div class="offer-right">
                <p>Description: ${offer.getDescription()}</p>
                <#if offer.getPrice()??>
                    <p>Price: $${offer.getPrice()}</p>
                    <p>Payment Method: ${offer.getPayment()}</p>
                </#if>
            </div>
        </section>
        <section>
            <!-- <#if requests??>
                <h3>orders for this offer</h3>
                <ul class="orders-list">
                    <#list request as requests>
                        <li class="request-item">
                            <p>${request.getShopper().getUserName()}</p>
                            <p>${request.getOffer().getTitle()}</p>
                        </li>
                    </#list>
                </ul>
            </#if> -->
        </section>
        <a href="/offers/${offer.getOfferID()}/update">
            <button>edit offer</button>
        </a>
    </main>
</body>
</html>