<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body>
    <@macros.navbar/>
    <main class="open-sans">
        <h2>welcome ${seller.getUsername()}</h2>

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
                                <p>Availability: ${offer.isAvailability()?string('Available Now', 'Unavailable')}</p>
                                <p>Purchased: ${offer.getNumPurchased()}</p>
                                
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
        <section class="orders-box">
            <!-- show all orders -->
            <h1>Pending Orders</h3>
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
                <p>Hmm... You don't have any pending orders on your offers.</p>
                <a href="/orders">
                <button class="view">See Your Orders </button>
            </a>
            </#if>
            </section>
    </main>
    
</body>
</html>