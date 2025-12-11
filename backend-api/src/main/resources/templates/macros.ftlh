<#macro header title>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link rel="stylesheet" type="text/css" href="/css/styles.css">
   <link rel="stylesheet" type="text/css" href="/css/offer.css">
   <link rel="stylesheet" type="text/css" href="/css/order.css">
   <link rel="stylesheet" type="text/css" href="/css/cart.css">
   <link rel="stylesheet" type="text/css" href="/css/profiles.css">
   <link rel="stylesheet" type="text/css" href="/css/forms.css">
   <link rel="stylesheet" type="text/css" href="/css/navbar.css">
   <link rel="stylesheet" type="text/css" href="/css/review.css">
   <title>${title}</title>
   <link rel="preconnect" href="https://fonts.googleapis.com">
   <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
   <link href="https://fonts.googleapis.com/css2?family=Comic+Relief:wght@400;700&family=Open+Sans:ital,wght@0,300..800;1,300..800&display=swap" rel="stylesheet">
</#macro>

<#macro extrastyling>
   <link rel="stylesheet" type="text/css" href="/css/styles.css">
   <!-- TODO more styling pages -->
</#macro>

<#macro navbar>
<nav>
   <ul class="navigation comic-bold">
      <#if !(user??)>
          <li>
            <a href="/home" class="navlink">home</a>
         </li>
         <li>
            <a href="/login">log in</a>
         </li>
         <li>
            <a href="/register">register</a>
         </li>
      <#elseif (user.getSellerID()) ??>
         <li>
            <a href="/sellers/home" class="navlink">home</a>
         </li>
         <li>
            <a href="/sellers/myprofile">view profile</a>
         </li>
         <li>
            <a href="/offers/myoffers">view my offers</a>
         </li>
         <li>
            <a href="/sellers/myorders">view my orders</a>
         </li>
         <li>
            <a href="/offers/create">create an offer</a>
         </li>
         <li>
            <a href="/logout">log out</a>
         </li>
      </#if>
      <#if (user.getShopperID()) ??>
         <li>
            <a href="/shoppers/home">home</a>
         </li>
         <li>
            <a href="/shoppers/myprofile">view profile</a>
         </li>
         <li>
            <a href="/shoppers/cart">view my cart</a>
         </li>
         <li>
            <a href="/ml">view mailing list</a>
         </li>
         <li>
            <a href="/logout">log out</a>
         </li>
      </#if>
      </ul>
</nav>
</#macro>

<#macro shoppernav>
<nav>
      <a href="/shoppers/home">Home</a>
		<a href="/shoppers/myprofile">My Profile</a>
      <a href="/shoppers/cart">Cart</a>
      <a href="/shoppers/mysubscriptions">Subscriptions</a>
		<a href="/"><img src="../images/icons/logout_black_20pt.svg" alt="Log Out"></a>
</nav>
</#macro>