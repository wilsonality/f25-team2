<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="Please Log In"/>
</head>
<body>
    <main class="log-in">
        <section class="shopper-log-in">
            <form action="/login/shoppers" method="post">
                <label for="username">Username</label><br>
                <input type="text" id="username" name="username" maxlength="16" required><br>
                <br>
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </form>
        </section>
        <section class="seller-log-in">
            <form action="/login/sellers" method="post">
                <label for="username">Username</label><br>
                <input type="text" id="username" name="username" maxlength="16" required><br>
                <br>

                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
                <br>
                <button type="submit">Register</button>
            </form>
        </section>
    </main>
    
</body>
</html>