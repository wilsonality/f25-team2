<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body>
    <@macros.navbar/>
    <main class="open-sans">
        <h1>Update Offer</h1>
            <form action="/offers/${offer.getOfferID()}" method="post" enctype="multipart/form-data" class="edit" >
            <label for="title">Offer Title</label>
            <input type="text" id="title" name="title" value="${offer.getTitle()}">

            <label for="type">Offer Type</label>
            <select name="type" id="type" required>
                <option value="service">Service</option>
                <option value="item">Item</option>
            </select>

            <label for="description">Description</label>
            <input type="text" id="description" name="description" value="${offer.getDescription()}">

            <label for="availability">Availability</label>
            <select name="availability" id="availability">
                <#if offer.isAvailability()>
                    <option value="true" selected>Available Now</option>
                    <option value="false">Unavailable</option>
                <#else>
                    <option value="true">Available Now</option>
                    <option value="false" selected>Unavailable</option>
                </#if>
            </select>
            
            <label for="price">Price</label>
            <input type="number" step="1" min="0" max="9999999999" id="price" name="price" placeholder="${offer.getPrice()}" value="${offer.getPrice()}">
            
            <#if offer.getPayment()??>
            <label for="payment">Payment</label>
            <input type="text" step="1" min="0" max="9999999999" id="payment" name="payment" placeholder="${offer.getPayment()}" value="${offer.getPayment()}">
            </#if>
            
            <label for="offerPicture">Change Offer Picture</label>
            <input type="file" id="offerPicture" name="offerPicture">
            <p>Max Size: 10MB</p>
            <#if offer.getOfferImagePath()??>
                <img src="${offer.getOfferImagePath()}" alt="" class="offer-picture">
            <#else>
                <p class="grayed">You're using the default picture.</p>
                <img src="/offer-pictures/eraser.jpeg" alt="" class="offer-picture">
            </#if>
            <br>

            <button type="submit" class="edit">Save Changes</button>
            <a href="/offers"><button>Cancel Edit</button></a>
        </form>
        <section class="danger center-align">
            <a href="/${offer.getOfferID()}">
                <button class="delete">Delete Offer</button>
            </a>
        </section>
	</main>
</body>
</html>