<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>An error occured.</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,700;1,700&display=swap" rel="stylesheet">
</head>
<body>
    <h1>Something went wrong. :/</h1>
    <#if error??>
        <p>${error}</p>
    <#else>
        <p>We're not sure what happened.</p>
    </#if>
</body>
</html>