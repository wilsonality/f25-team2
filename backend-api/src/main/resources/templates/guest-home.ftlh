<#import "./macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body>
    <@macros.guestnav/>
    <section class="welcome-msg">
        <h1>Welcome to Spartan's List</h1>
        <p>Do you have old furniture that you don't need in your new dorm? Want to get the perfect accessory for your campus fits? Spartan's List is perfect for the job!<br>
            Spartan's List is the solution for Spartans who need something from their community and Spartans who have something to offer. We want to bring Spartans together to support each other.</p>
    </section>
    <section class="guest-browse-offers">
        <h3>Check Out Some Offers</h3>
        <#if offers??>
        <ul class="offers-list">
            <#list offers as offer>
            <a href="/offers/${offer.getOfferID()}">
                <li class="offer-item">
                    <p class="open-sans-bold">${offer.getTitle()}</p>
                    <p>Availability: ${offer.isAvailability()?string('Available Now', 'Unavailable')}</p>
                    <p class="grayed">${offer.getDescription()}</p>
                </li>
            </#list>
            </a>
        </ul>
        </#if>
    </section>
    
</body>
</html>