<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body>
    <@macros.navbar/>
    <main class="profile">
        <section class="about shopper-left center">
            <img src="/seller-pictures/default.jpg" alt="" class="profile-image pulse">
            <h1>${shopper.getUsername()}</h1>
            <p>Spartan Seller</p>
            <p>Member since: ${shopper.getJoinDate()}</p>
            <p>Description: ${shopper.getProfileBio()}</p>
            <#if author ??>
            <a href="/shoppers/updateprofile">
                <button class="edit">Edit Profile</button>
            </a>
            </#if>

        </section>
        <section class="shopper-right">
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
                    <p class="grayed">${review.content}</p>
                    <p>${review.rating}/5 Stars</p>
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