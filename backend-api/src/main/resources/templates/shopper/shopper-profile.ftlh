<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="../styles.css">
    <link rel="stylesheet" href="../provider-prototype/provider_styles.css">
	<link rel="stylesheet" href="profile.css">
	<link rel="stylesheet" href="shopper_profile.css">

	<title>My Profile</title>
	<link rel="icon" type="image/x-icon" href="../images/icons/favicon.svg">
</head>

<body>
	<#include "/navbars/shopper-nav.html">

	<main>
        <div class="profile">
            <section class="user_info">
				<img src="${shopper.profileImage}" alt="profile picture" class="user_photo">
				<h3>Spartan Shopper</h3>
				<h2>${shopper.username}</h2>
				<p>Member Since: <span>Placeholder</span></p>

				<div class="analytics_preview">
					<p>Bio: <span>${shopper.profileBio}</span></p>
					<p>Items Purchased: <span>Placeholder</span></p>
					<a href="manage_profile.html"><button>Edit Profile</button></a>
				</div>
			</section>

			<div class="profile_features">
				<div class="review_preview">
					<h2>REVIEWS MADE</h2>
					<section class="reviews_container">
						<div class="review">
							<a href="../customer-prototype/cute_eraser.html">
								<ul>
									<li class="review_title">
										<p>Best eraser I've ever had.</p>
										<p>5 / 5</p>
									</li>
									<li class="review_author">
										<p>on Cute Eraser</p>
									</li>
								</ul>
							</a>
						</div>
					</div>
				</section>

				<div>
					<h2>RECENT PURCHASES</h2>
					<div class="offers_container">
						<a href="cute_eraser.html">
							<section class="offer">
								<ul>
									<li class="offer_image"><img src="../images/eraser.jpeg"></li>
									<li class="offer_title">Cute Eraser</li>
									<li class="offer_context">Jane Doe 4hrs $10</li>
								</ul>
							</div>
						</a>
					</div>
			</div>
		</div>
	</main>

	<footer>
		<!-- Footer -->
		<p>About Us</p>
		<p>Contact</p>
		<p>Terms of Service</p>
	</footer>
</body>
</html>