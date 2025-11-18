<#import "../macros.ftlh" as macros>

<!DOCTYPE html>
<html lang="en">
<head>
    <@macros.header title="${title}"/>
</head>
<body>
	<#include "/navbars/seller-nav.html">

	<aside>
		<div class="order_preview">
			<!-- shows pending orders -->
			 <h2>Pending Orders</h2>
			 <section class="orders_container">

				<div class="order">
					<a href="manage_orders.html">
						<ul>
							<li class="order_name">ERASER</li>
							<li class="order_customer">Jimmy Dean</li>
							<li class="order_time">2H</li>
						</ul>
					</a>
				</div>

				<div class="order">
					<a href="manage_orders.html">
						<ul>
							<li class="order_name">STATIONARY SET</li>
							<li class="order_customer">Emily Snicket</li>
							<li class="order_time">1D</li>
						</ul>
					</a>
				</div>
			</section>
		</div>
		<div class="review_preview">
			<h2>Recent Reviews</h2>
			<section class="reviews_container">
				<div class="review">
					<a href="view_offer.html">
						<ul>
							<li class="review_title">
								<p>Great Working Pens</p>
								<p>3 / 5</p>
							</li>
							<li class="review_author">
								<p>Samantha Brown</p>
							</li>
						</ul>
					</a>
				</div>

				<div class="review">
					<a href="view_offer.html">
						<ul>
							<li class="review_title">
								<p>Chair Too Old</p>
								<p>1 / 5</p>
							</li>
							<li class="review_author">
								<p>John Smith</p>
							</li>
						</ul>
					</a>
				</div>
			</section>
		</div>
	</aside>

	<main>
        <div class="profile">
            <section class="user_info">
				<img src="${seller.profileImage}" alt="" class="user_photo">
				<h3>Spartan Seller</h3>
				<h2>${seller.username}</h2>
				<p>Member Since: <span>Placeholder</span></p>
				<p>Bio: <strong>${seller.profileBio}</strong></p>
				<a href="manage_profile.html">
					<button>Edit Profile</button>
				</a>

				<div class="analytics_preview">
					<h2>Profile Analytics</h2>
					<h3>Monthly:</h3>
					<p>Purchases This Month: <span>3</span></p>
					<p>Requests This Month: <span>12</span></p>
					<h3>All-Time</h3>
					<p>Follow Count: <span>1</span></p>
				</div>
			</section>
			<section class="profile_features">
				<h2>FEATURED OFFERS</h2>
				<div class="offers_container">
					<div class="offer">
						<ul>
							<li class="offer_image"><img src="../images/eraser.jpeg"></li>
							<li class="offer_title">Cute Eraser</li>
							<li class="offer_context">Jane Doe 4hrs $10</li>
						</ul>
					</div>

					<div class="offer">
						<ul>
							<li class="offer_image"><img src="../images/stationary.jpg"></li>
							<li class="offer_title">Stationary Set</li>
							<li class="offer_context">Jane Doe 3d $20</li>
						</ul>
					</div>
				</div>
				<h2>FEATURED REVIEWS</h2>
				<div class="reviews_cards">
					<div class="review">
						<a href="view_offer.html">
							<ul>
								<li class="review_title">
									<p>Great Working Pens</p>
									<p>3 / 5</p>
								</li>
								<li class="review_author">
									<p>Samantha Brown</p>
								</li>
							</ul>
						</a>
					</div>
					<div class="review">
						<a href="view_offer.html">
							<ul>
								<li class="review_title">
									<p>Chair Too Old</p>
									<p>1 / 5</p>
								</li>
								<li class="review_author">
									<p>John Smith</p>
								</li>
							</ul>
						</a>
					</div>
				</div>
			</section>
        </div>


	</main>

	<footer>
		<p>About Us</p>
		<p>Contact</p>
		<p>Terms of Service</p>
	</footer>
</body>
</html>