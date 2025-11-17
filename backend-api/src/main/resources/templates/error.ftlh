<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="An Error Occurred"/>
</head>
<body>
    <h2>Something went wrong :/</h2>
    <#if error??>
        <h3>Error Message:</h3>
        <p>${error}</p>
    <#else>
        <p>We're not sure what happened.</p>
    </#if>
</body>
</html>