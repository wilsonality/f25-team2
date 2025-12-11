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
                <p>OfferID: ${offer.getOfferID()}</p>
                <p>Availability: ${offer.isAvailability()?string('Available Now', 'Unavailable')}</p>
<<<<<<< HEAD
                <#if (incart ?? && incart)>
=======
                <p>Purchased: ${offer.getNumPurchased()}</p>
                <a href="/sellers/${offer.getSeller().getSellerID()}"><p>Seller: ${offer.getSeller().getUsername()}</p></a>
>>>>>>> 81f3c32c25eb3d8627b17d4103d0f944db6fab06
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
            <form id="newReview" action="/api/reviews" method="post">
                <div class="make_review_container">
                <textarea class="review_text_box" name="content" id="content" rows="5" cols="50" wrap="soft"></textarea><br>

                <label for="stars" name="review">⭐️</label>
                <input type="radio" name="rating" id="rating" value="1">
                <label for="stars">1</label>
                <input type="radio" name="rating" id="rating" value="2">
                <label for="stars">2</label>
                <input type="radio" name="rating" id="rating" value="3">
                <label for="stars">3</label>
                <input type="radio" name="rating" id="rating" value="4">
                <label for="stars">4</label>
                <input type="radio" name="rating" id="rating" value="5">
                <label for="stars">5</label><br>

                <input type="number" name="offerID" id="offerID" value="${offer.offerID}" hidden>

                <button type="submit">Add Review</button>
            </form>
        </section> 
        <hr>
        <section>
            <#list reviews as review>
                <div style="border-bottom: 0.5px solid black">
                    <h4>${review.author.username}</h4>
                    <p>${review.content}</p>
                    <p>${review.rating}/5</p>

                    <#if review.author.shopperID == shopperID>
                    <form id="deleteReview" action="/api/reviews/${review.reviewID}" method="post">
                        <button type="submit">Delete Review</button>
                    </form>
                    </#if>
                </div>
            </#list>
        </section>
    </main>
</body>
</html>