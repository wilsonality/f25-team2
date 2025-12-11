<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body>
    <@macros.navbar/>
    <main class="open-sans">
        <h1>Post a New Offer</h1>
		<section class="offer-create">
            <form id="newOffer" action="/offers" method="post" class="create" enctype="multipart/form-data">
            <label for="title">Offer Title</label><br>
            <input type="text" id="title" name="title" required><br>
            <br>

            <label for="type">Offer Type</label>
            <select name="type" id="type" required>
                <option value="service">Service</option>
                <option value="item">Item</option>
            </select><br>

            <label for="description">Description</label><br>
            <input type="text" id="description" name="description" required><br>
            <br>

            <label for="availability">Availability</label>
            <select name="availability" id="availability" required>
                <option value="true">Available Now</option>
                <option value="false">Unavailable</option>
            </select>
            <br><br>

            <label for="price">Price</label><br>
            <input type="number" step="1" min="0" max="9999999999" id="price" name="price" placeholder="0"><br>
            <br>

            <label for="payment">Payment</label><br>
            <input type="text" step="1" min="0" max="9999999999" id="payment" name="payment" placeholder="Cash"><br>
            <br>

            <label for="offerPicture">Offer Picture</label><br>
            <input type="file" id="offerPicture" name="offerPicture"><br>
            <p>Max Size: 10MB</p>
            <br>

            <input type="hidden" name="sellerID" value=1>
            <input type="hidden" name="numPurchased" value="0">

            <button type="submit">Post Offer</button>
            <a href="/sellers/1">Cancel</a>
        </form>
        </section>
	</main>
</body>
</html>