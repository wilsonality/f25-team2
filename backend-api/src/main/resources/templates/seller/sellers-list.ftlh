<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Animal List</title>
    <link rel="icon" href="/page/pawprint.jpg" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="/css/styles.css">
    <!-- fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,700;1,700&display=swap" rel="stylesheet">
</head>
<body>
<!-- Begin Nav Bar -->
    <ul class="navigation space-mono-bold">
         <li>
            <a href="/home" class="navlink">HOME</a>
         </li>
      </ul>
<!-- End Nav Bar -->

    <main class="page-container"">
        <h1 class="space-mono-bold">${title}</h1>
        <ul class="animal-gallery space-mono">
            <#list animalslist as animal>
                <li class="animal-card">
                    <!-- animal card on the page -->
                    <a href="/animals${'/id/'+(animal.animalID)!'/'}">
                    <img src="/animal-pictures/${(animal.animalPicturePath)!'default.jpg'}" alt="animal picture" class="animal-picture">
                    <h3>${animal.name}</h3> 
                    </a>
                </li>
            </#list>
        </ul>
    </main>
</body>
</html>