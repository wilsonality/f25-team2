<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body class="open-sans">
    <@macros.navbar/>
    <section class="orders-box">
        <h1>Manage Your Orders Here</h1>
        <#if orders ??>
            <ul class="orders-list">
                <#list orders as order>
                    <li class="orders-item">
                        <h3>${order.getOffer().getTitle()}</h3>
                        <p>${order.getShopper().getUsername()}</p>
                        <#if offer.getOfferImagePath()??>
                        <img src="${offer.getOfferImagePath()}" alt="" class="offer-icon">
                        <#else>
                            <img src="/offer-pictures/eraser.jpeg" alt="" class="offer-icon">
                        </#if>
                    </li>
                </#list>
            </ul>
        <#else>
            <p>Hmm... You don't have any orders on your offers. Come back when someone has requested your offer.</p>
            <a href="/offers/myoffers">
            <button class="view">See Your Offers </button>
        </a>
        </#if>

    </section>
</body>
</html>