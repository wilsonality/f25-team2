<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body class="open-sans">
    <@macros.navbar/>
    <main class="seller-orders">
        <section class="orders-box">
        <h1>Manage Your Orders Here</h1>
        <#if orders ??>
            <ul class="orders-list">
                <#list orders as order>
                <li class="orders-item pulse">
                    <#if order.getOffer().getOfferImagePath()??>
                    <img src="${order.getOffer().getOfferImagePath()}" class="offer-icon">
                    <#else>
                    <img src="/offer-pictures/eraser.jpeg" class="offer-icon">
                    </#if>
                    <div class="order-info">
                            <a href="/offers/${order.getOffer().getOfferID()}">
                                <h3>${order.getOffer().getTitle()}</h3>
                            </a>
                            <#if order.getStatus()??>
                            <p>Status:
                                <#if (order.getStatus() == 1)>
                                Requested
                                <#elseif (order.getStatus() == 2)>
                                Accepted
                                <#elseif (order.getStatus() == 3)>
                                Rejected
                                <#elseif (order.getStatus() == 4)>
                                Completed
                                </#if>
                            </p>
                            </#if>
                            <p>Requested by User ${order.getShopper().getUsername()}</p>
                            <p class="grayed">Posted ${order.getPostDate()}</p>
                            <div class="buttons">
                                <#if (order.getStatus() == 1)>
                                    <button class="create accept" data-order-id="${order.getOrderID()}">Accept</button>
                                    <button class="delete reject" data-order-id="${order.getOrderID()}">Reject</button>
                                </#if>
                            </div>
                        </div>
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
    </main>
    <script src="/js/orders.js" defer ></script>
</body>
</html>