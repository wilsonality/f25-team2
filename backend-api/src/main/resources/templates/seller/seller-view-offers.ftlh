<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body class="open-sans-bold">
    <@macros.navbar/>
    <section class="offers-box">
            <!-- show all offers -->
            <h1>See Your Offers</h1>
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
                                <p>${offer.isAvailability()?string('Available Now', 'Unavailable')}</p>
                                <p>Purchased: ${offer.getNumPurchased()}</p>
                                <p class="grayed small">Description: ${offer.getDescription()}</p>
                                <#if offer.getPrice()??>
                                    <p>Price: $${offer.getPrice()}</p>
                                    <p class="grayed small">Payment Method: ${offer.getPayment()}</p>
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
</body>
</html>