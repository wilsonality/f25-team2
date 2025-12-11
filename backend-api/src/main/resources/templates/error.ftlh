<#import "./macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="An Error Occurred"/>
</head>
<body>
    <main class="comic">
        <h2>Something went wrong :/</h2>
    <#if error??>
        <p>${error}</p>
    <#else>
        <p>We're not sure what happened.</p>
    </#if>
    </main>
</body>
</html>