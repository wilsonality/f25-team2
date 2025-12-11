<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body>
    <@macros.navbar/>
    <main class="profile">
        <section class="about seller-left center">
            <#if seller.getProfileImagePath()??>
                <img src="${seller.getProfileImagePath()}" alt="" class="profile-image pulse">
            <#else>
                <img src="/seller-pictures/default.jpg" alt="" class="profile-image pulse">
            </#if>
            <h1>${seller.getUsername()}</h1>
            <p>Spartan Seller</p>
            <p>Member since: ${seller.getJoinDate()}</p>
            <p>Description: ${seller.getProfileBio()}</p>
            <#if author ??>
            <a href="/sellers/updateprofile">
                <button class="edit">Edit Profile</button>
            </a>
            </#if>

            <div class="analytics">
                <h2>Profile Analytics</h2>
					<h3>Monthly:</h3>
					<p>Purchases This Month: <span>NULL</span></p>
					<p>Requests This Month: <span>NULL</span></p>
					<h3>All-Time</h3>
					<p>Follow Count: <span>NULL</span></p>
            </div>

            <#if user.shopperID??>
            <a href="/mailinglist/subscribe/${user.shopperID}/${seller.getSellerID()}"><button>Subscribe</button></a>
            </#if>
            
        </section>
        <section class="seller-right">
            <h2>Offers</h2>
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
            <p>Hmm... No offers to show here.</p>
                <#if author ??>
                <a href="/offers/create">
                    <button class="create">Post an Offer Here</button>
                </a>
                </#if>
            </#if>
            <h2>Top Reviews</h2>
            <#if reviews??>
            <ul class="reviews-list">
                <#list reviews as review>
                <li class="review-item">
                    <img src="/seller-pictures/default.jpg" alt="" class="review-icon">
                    <a href="/offers/${review.offer.getOfferID()}">
                        <h3>${review.getOffer().getTitle()}</h3>
                    </a>
                    <#if author ??>
                        <p class="underline">You</p>
                    <#else>
                        <p class="underline">${review.author.username}</p>
                    </#if>
                    <p class="underline">${review.author.username}</p>
                    <p class="grayed">${review.content}</p>
                    <p>${review.rating}/5</p>
                </li>
                </#list>
            </ul>
            <#else>
            <p>Hmm... No reviews to show here.</p>
                <#if author ??>
                <a href="/offers/create">
                    <button class="create">Post an Offer Here</button>
                </a>
                </#if>
            </#if>
        </section>
    </main>
    
</body>
</html>