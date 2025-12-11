<#import "./macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="Access Denied"/>
</head>
<body class="open-sans">
    <@macros.navbar/>
    <section class="error-msg">
        <h2>Access Denied</h2>
        <p>Hey, you are NOT supposed to be here.</p>

        <a href="/home">
            <p>Go home?</p>
        </a>
    </section>
    
</body>
</html>