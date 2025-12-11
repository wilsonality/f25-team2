<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body class="open-sans">
    <@macros.navbar/>
    <main class="edit">
        <form action="/shoppers/${shopper.getShopperID()}" method="post" enctype="multipart/form-data" class="edit">
            <h2>Edit Your Profile</h2>
            <label for="username">Change your username below. You may not enter spaces or special characters. Dashes and underscores are allowed.</label>
            <input type="text" id="username" name="username" value="${shopper.getUsername()}"><br>
            <img src="/seller-pictures/default.jpg" alt="" class="profile-image pulse">

            <p>You have been a member since ${shopper.getJoinDate()}.</p>

            <p>Your Registered Phone</p>
            <input type="text" id="userPhone" name="userPhone" value="${shopper.getUserPhone()}" readonly>
            <label for="profileBio">Edit Bio
            </label>
            <textarea name="profileBio" id="profileBio">${shopper.getProfileBio()}</textarea>
            
            <button type="submit" class="edit">Save Profile</button>
            <a href="/shoppers/myprofile"><button class="delete">Cancel</button></a>
        </form>
    </main>

</body>
</html>