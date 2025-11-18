<#import "../macros.ftlh" as macros>
<!DOCTYPE html>
<html lang="en-us">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/styles.css">
	<title>Shopper Registration</title>
</head>

 <@macros.shoppernav/>

<body>
	<main>
        <h1>Edit ${shopper.username}</h1>
		
		<form id="newShopper" action="/shoppers/update" method="post">
			<label for="username">Username</label><br>
			<input type="text" id="username" name="username" maxlength="16" value="${shopper.username}" required><br>
            <br>

            <label for="userPhone">Phone Number</label><br>
			<input type="number" step="1" min="0" max="9999999999" id="userPhone" name="userPhone" value="${shopper.userPhone}" required><br>
            <br>

            <label for="profileImage">Profile Image Link</label><br>
			<input type="text" id="profileImage" name="profileImage" value="${shopper.profileImage}" maxlength="255" required><br>
            <br>

            <label for="profileBio">Bio</label><br>
			<textarea rows="5" cols="50" id="profileBio" name="profileBio" required>${shopper.profileBio}</textarea><br>
            <br>

			<button type="submit">Submit</button>
			<a href="/">Cancel</a>
		</form>
	</main>
</body>
</html>