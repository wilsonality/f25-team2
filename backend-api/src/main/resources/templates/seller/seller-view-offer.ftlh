<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="View ${offer.getTitle()}"/>
</head>
<body class="open-sans-bold">
    <@macros.navbar/>
    <main class="offer-details">
        <section class="offer-summary">
            <div class="offer-left">
                <#if offer.getOfferImagePath()??>
                <img src="${offer.getOfferImagePath()}" alt="" class="offer-picture">
                <#else>
                <img src="/offer-pictures/eraser.jpeg" alt="" class="offer-picture">
                </#if>
                <h3>${offer.getTitle()}</h3>
                <a href="/offers/${offer.getOfferID()}/update">
                    <button>edit offer</button>
                </a>
                <p>${offer.isAvailability()?string('Available Now', 'Currently Unavailable')}</p>
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
        <section class="offer-requests">
            <#if requests??>
                <h3>orders for this offer</h3>
                <ul class="orders-list">
                    <#list request as requests>
                        <li class="request-item">
                            <p>${request.getShopper().getUserName()}</p>
                            <p>${request.getOffer().getTitle()}</p>
                        </li>
                    </#list>
                </ul>
            </#if>
        </section>
        
    </main>
</body>
</html>