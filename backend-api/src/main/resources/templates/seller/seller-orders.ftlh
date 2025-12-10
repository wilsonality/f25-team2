<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body>
    <@macros.navbar/>
    <main class="open-sans">
        <h2>Manage Orders for ${seller.getUsername()}</h2>

        <section class="orders-box">
            <!-- show all orders -->
            <h3>Pending Orders</h3>
            <ul class="orders-list">
                <#if requests??>
                    <#list requests as request>
                        <li class="request-item">
                            <p>${request.getShopper().getUserName()}</p>
                            <p>${request.getOffer().getTitle()}</p>
                        </li>
                    </#list>
                </#if>
            </ul>
        </section>
    </main>
    
</body>
</html>