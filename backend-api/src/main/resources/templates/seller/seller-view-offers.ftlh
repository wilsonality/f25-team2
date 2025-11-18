<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body>
    <h2>View All Offers By Seller ${seller.getUsername()}</h2>
    <#if offers??>
    <ul class="offers-list">
        <#list offers as offer>
        <a href="/offers/${offer.getOfferID()}/seller">
            <li class="offer-item">
                <p class="open-sans-bold">${offer.getTitle()}</p>
                <p>Availability: ${offer.isAvailability()?string('Available Now', 'Unavailable')}</p>
                <p class="grayed">${offer.getDescription()}</p>
            </li>
        </#list>
        </a>
    </ul>
    </#if>
</body>
</html>