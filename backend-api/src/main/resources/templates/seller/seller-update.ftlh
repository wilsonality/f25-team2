<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body class="open-sans-bold">
    <form action="/sellers/${seller.getSellerID()}" method="post" enctype="multipart/form-data">
            <img src="../images/jane_doe.jpg" alt="" class="user_photo">
            <h3>Spartan Seller</h3>
            <label for="user_name">Edit Username:</label>
            <input type="text" id="username" name="username" value="${seller.getUsername()}"><br>
            <p>Member Since: <span>09/25/2025</span></p>
            <input type="text" id="userPhone" name="UserPhone" value="${seller.getUserPhone()}" readonly>
            <label for="user_bio">
                <p>Edit Bio</p>
            </label>
            <textarea name="profileBio" id="profileBio">${seller.getProfileBio()}</textarea>
            <br>
            <label for="offerImage">Upload New Profile Picture</label><br>
            <!-- TODO : add old seller image -->
            <input type="file" id="offerImage" name="offerImagePath"><br>
            <p>Max Size: 10MB</p>
            <br>
            <button type="submit">Save Profile</button>
            <a href="/sellers/${seller.getSellerID()}">Cancel</a>
    </form>
</body>
</html>