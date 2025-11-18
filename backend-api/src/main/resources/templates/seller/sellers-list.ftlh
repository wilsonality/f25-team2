<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <!-- <link rel="icon" href="/page/pawprint.jpg" type="image/x-icon"> -->
    <!-- <link rel="stylesheet" type="text/css" href="backend-api/src/main/resources/static/css/styles.css"> -->
    <!-- fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,700;1,700&display=swap" rel="stylesheet">
</head>
<body>
<!-- Begin Nav Bar -->
    <ul class="navigation open-sans-bold">
         <li>
            <!-- <a href="/home" class="navlink">HOME</a> -->
         </li>
      </ul>
<!-- End Nav Bar -->

    <main class="page-container"">
        <h1 class="space-mono-bold">${title}</h1>
        <ul class="seller-list space-mono">
            <#list sellers as seller>
                <li class="seller-item">
                    <!-- seller listed on the page -->
                    <a href="/api/sellers/${seller.getSellerID()}">
                        <img src="backend-api/src/main/resources/static/seller-pictures/defaultavatar_blue.jpg" alt="">
                        <h3>${seller.getUsername()}</h3> 
                        <h3>${seller.getUserPhone()}</h3> 
                    </a>
                </li>
            </#list>
        </ul>
    </main>
</body>
</html>