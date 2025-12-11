<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${offer.getTitle()}"/>
</head>
<body>
    <@macros.navbar/>
    <main class="offer-details">
        <section class="offer-summary">
            <div class="offer-left">
                <#if offer.getOfferImagePath()??>
                <img src="${offer.getOfferImagePath()}" alt="" class="offer-picture">
                <#else>
                <img src="/offer-pictures/eraser.jpeg" alt="" class="offer-picture">
                </#if>
                <h3>${offer.getTitle()}</h3>
                <a href="/sellers/${offer.getSeller().getSellerID()}"><p class="grayed">Seller: ${offer.getSeller().getUsername()}</p></a>
                <p>OfferID: ${offer.getOfferID()}</p>
                <p>Availability: ${offer.isAvailability()?string('Available Now', 'Unavailable')}</p>
                <#if (incart ?? && incart)>
                <form id="addToCart" action="/addToCart/${offer.offerID}" method="post">
                    <button type="submit" id="add-cart">Add To Cart</button>
                </form>
                <#elseif (!incart)>
                <form id="addToCart" action="/addToCart/${offer.offerID}" method="post">
                    <button type="submit" id="remove-cart">Add To Cart</button>
                </form>
                </#if>
            </div>

            <div class="offer-right">
                <p>Description: ${offer.getDescription()}</p>
                <#if offer.getPrice()??>
                    <p>Price: $${offer.getPrice()}</p>
                    <p>Payment Method: ${offer.getPayment()}</p>
                </#if>
            </div>
        </section>
        <hr>
        <section>
            <h2>Reviews</h2>
        <form id="newReview" action="/reviews/offer/${offer.getOfferID()}" method="post" class="create">
                <label for="content">Review Content Here</label>
                <textarea class="review_text_box" name="content" id="content" rows="5" cols="50" wrap="soft"></textarea><br>
                <label for="stars" name="review">Rating (1-5 Stars)</label>
                <input type="number" name="rating" id="rating">

                <button type="submit" class="create">Add Review</button>
            </form>
        </section> 
        <hr>
        <section>
            <h2>Reviews for this Offer</h2>
            <#if reviews ??>
            <ul class="reviews-list">
                <#list reviews as review>
                <li class="review-item">
                    <img src="/seller-pictures/default.jpg" alt="" class="review-icon">
                    <#if author ??>
                        <p class="underline">You</p>
                    <#else>
                        <p class="underline">${review.author.username}</p>
                    </#if>
                    <a href="/offers/${review.offer.getOfferID()}">
                        <h3>${review.getOffer().getTitle()}</h3>
                    </a>
                    <p>${review.content}</p>
                    <p>${review.rating}/5</p>
                    
                    <#if review.author.shopperID == user.getShopperID()>
                    <form id="deleteReview" action="/reviews/${review.reviewID}/delete" method="post">
                        <button type="submit" class="delete">Delete Review</button>
                    </form>
                    </#if>
                </li>
                </#list>
            </ul>
            </#if>
        </section>
    </main>
</body>
</html>