<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body class="open-sans">
    <@macros.navbar/>
    <main class="edit">
        <form action="/sellers/${seller.getSellerID()}" method="post" enctype="multipart/form-data" class="edit">
            <h2>Edit Your Profile</h2>
            <label for="user_name">Change your username below. You may not enter spaces or special characters. Dashes and underscores are allowed.</label>
            <input type="text" id="username" name="username" value="${seller.getUsername()}"><br>
            
            <label for="sellerPicture"><h3>Change Your Profile Picture</h3></label>
            <#if seller.getProfileImagePath()??>
                <img src="${seller.getProfileImagePath()}" alt="" class="profile-image pulse">
            <#else>
                <p class="grayed small">You are using the default</p>
                <img src="/seller-pictures/default.jpg" alt="" class="profile-image pulse">
            </#if>
            <input type="file" id="sellerPicture" name="sellerPicture"><br>
            <p>Max Size: 10MB</p>
            <br>

            <p>You have been a member since ${seller.getJoinDate()}.</p>
            <input type="text" id="userPhone" name="UserPhone" value="${seller.getUserPhone()}" readonly>
            <label for="profileBio">Edit Bio
            </label>
            <textarea name="profileBio" id="profileBio">${seller.getProfileBio()}</textarea>
            
            <button type="submit" class="edit">Save Profile</button>
            <a href="/sellers/myprofile"><button class="delete">Cancel</button></a>
        </form>
    </main>

</body>
</html>