<#import "./macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="Please Log In"/>
</head>
<body>
    <@macros.guestnav/>
    <main class="log-in open-sans-bold">
        <section class="shopper-log-in">
            <h3>Shopper Log In</h3>
            <form action="/login/shopper" method="post">
                <label for="username">Username</label><br>
                <input type="text" id="username" name="username" maxlength="16" required><br>
                <br>
                <label for="password">Password</label><br>
                <input type="password" id="password" name="password" required>
                <br>
                <button type="submit">Login</button>
            </form>
                <a href="/shoppers/register"><button>Register</button></a>
        </section>
        <section class="seller-log-in">
            <h3>Seller Log In</h3>
            <form action="/login/seller" method="post">
                <label for="username">Username</label><br>
                <input type="text" id="username" name="username" maxlength="16" required><br>
                <br>

                <label for="password">Password</label><br>
                <input type="password" id="password" name="password" required>
                <br>
                <button type="submit">Login</button>
            </form>
                <a href="/sellers/register"><button>Register</button></a>
        </section>
    </main>
    
</body>
</html>