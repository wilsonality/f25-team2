<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body>
	<main>
        <h1>Register New Seller</h1>
		
		<form id="newSeller" action="/sellers" method="post">
			<label for="username">Username</label><br>
			<input type="text" id="username" name="username" maxlength="16" required><br>
            <br>

            <label for="password">Password</label><br>
			<input type="password" id="password" name="password" maxlength="32" required><br>
            <br>

            <label for="userPhone">Phone Number</label><br>
			<input type="number" step="1" min="0" max="9999999999" id="userPhone" name="userPhone" placeholder="3335551234" required><br>
            <br>

            <label for="profileImage">Profile Image Link</label><br>
			<input type="text" id="profileImage" name="profileImage" placeholder="ex: /images/img.jpg" maxlength="255" required><br>
            <br>

            <label for="profileBio">Bio</label><br>
			<textarea rows="5" cols="50" id="profileBio" name="profileBio" required></textarea><br>
            <br>

			<button type="submit">Register</button>
			<a href="/homepage_no_login">Cancel</a>
		</form>
	</main>
</body>
</html>