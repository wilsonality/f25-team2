# Spartan's List - Software Design 

Version 0.2  
Prepared by Sage Zhang\
Spartan's List\
Oct 21, 2025

Table of Contents
=================
* [Revision History](#revision-history)
* 1 [Product Overview](#1-product-overview)
* 2 [Use Cases](#2-use-cases)
  * 2.1 [Use Case Model](#21-use-case-model)
  * 2.2 [Use Case Descriptions](#22-use-case-descriptions)
    * 2.2.1 [Actor: Seller](#221-actor-seller)
    * 2.2.2 [Actor: Shopper](#222-actor-shopper) 
* 3 [UML Class Diagram](#3-uml-class-diagram)
* 4 [Database Schema](#4-database-schema)

## Revision History
| Name | Date    | Reason For Changes  | Version   |
| ---- | ------- | ------------------- | --------- |
|  SZ  |10/20    | Initial Design      |    0.1    |
|  SZ  |10/21    | Shopper Desc.       |    0.2    |
|      |         |                     |           |

## 1. Product Overview
Spartan's List is a website not unlike Craigslist, where users can advertise goods and services for sale, and contact the individuals who list them.

## 2. Use Cases
### 2.1 Use Case Model
![Use Case Model](use-case-diagram.jpg)

### 2.2 Use Case Descriptions

#### 2.2.1 Actor: Seller
##### 2.2.1.1 Sign Up
A farmer can sign up to create their profile with their name, email, password, and phone number. Emails must be unique.
##### 2.2.1.2 Log In
A farmer shall be able to sign in using their registred email and password. After logging in, the farmer shall be directed their dashboard where they see an overview of their farm, boxes and stats.
##### 2.2.1.3 Update Profile
A farmer shall be to modify their profile by going to their profile page. They can change their email, password, and farm.
##### 2.2.1.4 Create Produce Boxes
The farmer shall be able to create a new produce box listing. They would provide a box name, description, and price. This box will be created to be associated with only this farmer and their farm.
##### 2.2.1.4 View Customer Stats
A farmer will be able to view several statistics such as total revenue, total subscribers, and average ratings.

#### 2.2.2 Actor: Shopper
##### 2.2.2.1 Sign Up
A Shopper can sign up using a username and password. The username must be unique.
##### 2.2.2.2 Log In
A Shopper can log in using username and password. They will them be redirected to the shopper homepage. 
##### 2.2.2.3 Edit Profile
A Shopper can edit their profile, including their profile picutre, bio, phone number, username, and password.
##### 2.2.1.4 View Offer
A Shopper can view offers made by Sellers on the Shopper home page. 
##### 2.2.1.5 View Seller Data
A Shopper can view Seller data such as their rating and bio on an offer's detail page.
##### 2.2.1.6 Request Offer
A Shopper can request to purchase an offer on an offer's respective detail page. This will then notify the Seller that the Shopper wishes to purchase the item.
##### 2.2.1.7 Write Review
A Shopper can write a review for an offer after they purchase it. The review will be publically listed on the offer's detail page.
##### 2.2.1.8 Follow Seller
A Shopper can follow a Seller. This will add the Shopper to the Seller's amialing list, which will notify the Shopper of any new products or services the Shopper makes in the future. 

## 3. UML Class Diagram
![UML Class Diagram](https://github.com/csc340-uncg/f25-team0/blob/main/doc/Object-Oriented-Design/class-diagram.png)

## 4. Database Schema
![UML Class Diagram](database-schema.jpg)