<!DOCTYPE html>
<html lang="en-us">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/styles.css">
	<title>${title}</title>
</head>

<!--  -->
<body>
	<main>
        <h1>${title}</h1>
		
		<form id="newOffer" action="/offers" method="post">
			<label for="title">Offer Title</label><br>
			<input type="text" id="title" name="title" required><br>
            <br>

            <label for="type">Offer Type</label>
            <select name="type" id="type">
                <option value="">You must select a type</option>
                <option value="service">Service</option>
                <option value="item">Item</option>
            </select>

            <label for="description">Description</label><br>
			<input type="text" id="description" name="description" required><br>
            <br>

            <label for="description">Availability</label><br>
			<input type="text" id="availability" name="availability" placeholder="Available/Unavailable" required><br>
            <br>

            <label for="price">Price</label><br>
			<input type="number" step="1" min="0" max="9999999999" id="price" name="price" placeholder="0"><br>
            <br>

            <label for="price">Payment</label><br>
			<input type="number" step="1" min="0" max="9999999999" id="price" name="price" placeholder="0" required><br>
            <br>

            <label for="profileImage">Offer Image</label><br>
			<input type="file" id="offerImage" name="profileImage"><br>
            <p>Max Size: 10MB</p>
            <br>

            <label for="profileBio">Bio</label><br>
			<textarea rows="5" cols="50" id="profileBio" name="profileBio" required></textarea><br>
            <br>

			<button type="submit">Post Offer</button>
			<a href="/sellers/1">Cancel</a>
		</form>
	</main>
</body>
</html>