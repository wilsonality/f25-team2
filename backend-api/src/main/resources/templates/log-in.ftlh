<#import "./macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="Please Log In"/>
</head>
<body>
    <@macros.navbar/>
    <main class="log-in open-sans-bold">
        <section class="shopper-log-in">
            <h3>Shopper Log In</h3>
            <form action="/login/shopper" method="post">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" maxlength="16" required><br>
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
                <button type="submit" class="create long">Login</button>
            </form>
        </section>
        <section class="seller-log-in">
            <h3>Seller Log In</h3>
            <form action="/login/seller" method="post">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" maxlength="16" required><br>

                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
                <button type="submit" class="create long">Login</button>
            </form>
        </section>
    </main>
    
</body>
</html>