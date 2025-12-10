<#macro header title>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link rel="stylesheet" type="text/css" href="/css/styles.css">
   <link rel="stylesheet" type="text/css" href="/css/offer.css">
   <link rel="stylesheet" type="text/css" href="/css/order.css">
   <link rel="stylesheet" type="text/css" href="/css/cart.css">
   <link rel="stylesheet" type="text/css" href="/css/login.css">
   <link rel="stylesheet" type="text/css" href="/css/guest.css">
   <title>${title}</title>
   <link rel="preconnect" href="https://fonts.googleapis.com">
   <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
   <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,700;1,700&display=swap" rel="stylesheet">
</#macro>

<#macro extrastyling>
   <link rel="stylesheet" type="text/css" href="/css/styles.css">
   <!-- TODO more styling pages -->
</#macro>

<#macro navbar>
<nav>
   <ul class="navigation open-sans-bold">
      <#if !(shopper?? || seller??)>
          <li>
            <a href="/home" class="navlink">home</a>
         </li>
         <li>
            <a href="/login">log in</a>
         </li>
         <li>
            <a href="/register">register</a>
         </li>
      <#elseif (seller??)>
         <li>
            <a href="/sellers/home" class="navlink">home</a>
         </li>
         <li>
            <a href="/sellers/1">view profile</a>
         </li>
         <li>
            <a href="/offers/seller/1">view my offers</a>
         </li>
         <li>
            <a href="/offers/create">create an offer</a>
         </li>
         <li>
            <a href="/home">log out</a>
         </li>
      </#if>
      </ul>
</nav>
</#macro>

<#macro guestnav>
<nav>
   <ul class="navigation open-sans-bold">
        
      </ul>
</nav>
</#macro>