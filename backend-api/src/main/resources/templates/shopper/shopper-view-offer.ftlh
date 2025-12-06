<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${offer.getTitle()}"/>
    <link rel="stylesheet" type="text/css" href="/css/offer.css">
</head>
<body>
    <@macros.shoppernav/>
    <main>
        <section class="offer-summary">
            <div class="offer-left">
                <img src="" alt="" class="offer-picture">
                <h3>${offer.getTitle()}</h3>
                <p>OfferID: ${offer.getOfferID()}</p>
                <p>Availability: ${offer.isAvailability()?string('Available Now', 'Unavailable')}</p>
                <p>Purchased: ${offer.getNumPurchased()}</p>
            </div>
            <div class="offer-right">
                <p>Description: ${offer.getDescription()}</p>
                <#if offer.getPrice()??>
                    <p>Price: $${offer.getPrice()}</p>
                    <p>Payment Method: ${offer.getPayment()}</p>
                </#if>
            </div>
        </section>
        <section>
            <h2>Reviews</h2>
            <form id="newReview" action="/api/reviews" method="post">
                <div class="make_review_container">
                <textarea class="review_text_box" name="content" id="content" rows="14" cols="10" wrap="soft"></textarea><br>

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
        </div>   
            <#list reviews as review>
                <p>${review.author.username}</p>
                <p>${review.content}</p>
                <p>${review.rating}</p> 
            </#list>
        </section>
    </main>
</body>
</html>