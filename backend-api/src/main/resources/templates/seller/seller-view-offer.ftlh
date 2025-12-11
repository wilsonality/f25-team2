<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="View ${offer.getTitle()}"/>
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
                <a href="/offers/${offer.getOfferID()}/update">
                    <button>edit offer</button>
                </a>
                <p>${offer.isAvailability()?string('Available Now', 'Currently Unavailable')}</p>
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
        <section class="offer-requests">
            <#if requests??>
                <h3>orders for this offer</h3>
                <ul class="orders-list">
                    <#list request as requests>
                        <li class="request-item">
                            <p>${request.getShopper().getUsername()}</p>
                            <p>${request.getOffer().getTitle()}</p>
                        </li>
                    </#list>
                </ul>
            <#else>
                <p>Hmm.. You don't have any orders yet. Check back later once someone requests your offer.</p>
            </#if>
        </section>
        <section class="offer-reviews">
            <h2>Reviews for this Offer</h2>
            <#if reviews??>
                <ul class="reviews-list">
                <#list reviews as review>
                    <li class="review-item">
                        <img src="/seller-pictures/default.jpg" alt="" class="review-icon">
                        <p>${review.getAuthor().getUsername()}</p>
                        <a href="/offers/${review.offer.getOfferID()}">
                            <h3>${review.getOffer().getTitle()}</h3>
                        </a>
                        <p>${review.content}</p>
                        <p>${review.rating}/5</p>
                        <#if review.reply ??>
                            <p class="grayed">You: ${review.reply}</p>
                        <#else>
                        <button class="create reply" data-review-id="${review.getReviewID()}">Reply to Review</button>
                        <div id="response-box-${review.getReviewID()}" class="hide">
								<textarea name="reply" id="response-input-${review.getReviewID()}"></textarea>
								<button class="create submit-btn" id="${review.getReviewID()}" data-review-id="${review.getReviewID()}">Submit Reply</button>
							</div>
                        <button class="delete remove" data-review-id="${review.getReviewID()}">Delete this Review</button>
                        </#if>      
                    </li>
                </#list>
                </ul>
            <#else>
                <p>Hmm.. You don't have any reviews on this offer yet. Check back later once someone reviews your offer.</p>
            </#if>
        </section>
    </main>
    <script src="/js/reviews.js" defer ></script>
</body>
</html>