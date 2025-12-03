<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${offer.getTitle()}"/>
    <link rel="stylesheet" type="text/css" href="/css/offer.css">
</head>
<body>
    <@macros.shoppernav/>
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
    </main>
</body>
</html>