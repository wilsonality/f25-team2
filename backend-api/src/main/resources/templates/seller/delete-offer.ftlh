<#import "./macros.ftlh" as macros>
<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="An Error Occurred"/>
</head>
<body>
    <@macros.navbar/>
    <main class="delete-page">
        <section class="confirm-msg center">
            <h2>Are You Sure?</h2>
            <p>This action cannot be undone.</p>
        </section>
        <section class="danger">
            <button id="delete-btn">Delete Offer</button>
        </section>
    </main>
</body>
</html>