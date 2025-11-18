<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body>
    <@macros.sellernav/>
<!-- begin seller profile -->
    <main class="seller-profile">
        <!-- about the seller -->
        <section class="seller-about">
            <p> Spartan Seller</p>
            <h1>${seller.getUsername()}</h1>
            <p>Member since: Jan 01 1970</p>
            <p>Description: ${seller.getProfileBio()}</p>
            <a href="/sellers/updateForm/${seller.getSellerID()}">
                <button>Edit Profile</button>
            </a>

            <div class="analytics">
                <h2>Profile Analytics</h2>
					<h3>Monthly:</h3>
					<p>Purchases This Month: <span>NULL</span></p>
					<p>Requests This Month: <span>NULL</span></p>
					<h3>All-Time</h3>
					<p>Follow Count: <span>NULL</span></p>
            </div>
        </section>
        <section class="seller-offers-preview">
            <h2>Offers</h2>
            <ul class="offers-list">
                <#list offers as offer>
                <li class="offer-item">
                    <a href="offers/${offer.getOfferID()}/seller">
                        <img src="backend-api/src/main/resources/static/offer-pictures/eraser.jpeg" alt="" class="offer-picture">
                        <h3>${offer.getTitle()}</h3>
                        <p>Availability: ${offer.isAvailability()?string('Available Now', 'Unavailable')}</p>
                        <p>Purchased: ${offer.getNumPurchased()}</p>
                    </a>
                </li>
                </#list>
            </ul>
        </section>
    </main>
    
</body>
</html>