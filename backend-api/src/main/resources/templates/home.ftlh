<#import "./macros.ftlh" as macros>
<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body class="open-sans">
    <@macros.navbar/>
    <main>
        <section class="center">
            <h1>Welcome to Spartan's List</h1>
            <p>Do you have old furniture that you don't need in your new dorm? Want to get the perfect accessory for your campus fits? Spartan's List is perfect for the job!<br>
                Spartan's List is the solution for Spartans who need something from their community and Spartans who have something to offer. We want to bring Spartans together to support each other.</p>
        </section>
        <section class="guest-browse-offers">
            <h2>Check Out Some Offers</h2>
            <#if offers?? && (offers?size > 0)>
            <ul class="offers-list">
                <#list offers as offer>
                <a href="/login">
                    <li class="offer-item">
                        <#if offer.getOfferImagePath()??>
                            <img src="${offer.getOfferImagePath()}" alt="" class="offer-preview">
                        <#else>
                            <img src="/offer-pictures/eraser.jpeg" alt="" class="offer-preview">
                        </#if>
                        <h3 class="open-sans-bold">${offer.getTitle()}</h3>
                        <p>Availability: ${offer.isAvailability()?string('Available Now', 'Unavailable')}</p>
                        <p class="grayed">${offer.getDescription()}</p>
                    </li>
                </a>
                </#list>
            </ul>
            <#else>
            <p>No offers available at this time.</p>
            </#if>
        </section>
    </main>
</body>
</html>