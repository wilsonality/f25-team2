<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <!-- <link rel="stylesheet" type="text/css" href="backend-api/src/main/resources/static/css/styles.css"> -->
    <!-- fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,700;1,700&display=swap" rel="stylesheet">
</head>
<body>
    <!-- begin nav Bar -->
    <ul class="navigation open-sans-bold">
         <li>
            <a href="/sellers/home" class="navlink">HOME</a>
         </li>
      </ul>
<!-- end nav bar -->

<!-- begin seller profile -->
    <main class="seller-profile">
        <!-- about the seller -->
        <section class="seller-about">
            <p> Spartan Seller</p>
            <h1>${seller.getUsername()}</h1>
            <p>Member since: XX-XX-XX</p>
            <p>Description: ${seller.getProfileBio()}</p>
            <a href="/sellers/updateForm/{seller.getSellerID()}">
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
            <ul class="offers-list">
                <#list offers as offer>
                <li class="offer-item">
                    <a href="offers/${offer.getOfferID()}">
                        <img src="backend-api/src/main/resources/static/offer-pictures/eraser.jpeg" alt="" class="offer-picture">
                        <h3>${offer.getTitle()}</h3>
                        <p>Availability: ${offer.isAvailability()}</p>
                        <p>Purchased: ${offer.getNumPurchased()}</p>
                    </a>
                </li>
                </#list>
            </ul>
        </section>
    </main>
    
</body>
</html>