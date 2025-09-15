# Software Requirements Specification
## For <Spartan's List>

Version 0.2  
Prepared by <Team 2>    
<Sept. 15> 

Table of Contents
=================
* [Revision History](#revision-history)
* 1 [Introduction](#1-introduction)
  * 1.1 [Document Purpose](#11-document-purpose)
  * 1.2 [Product Scope](#12-product-scope)
  * 1.3 [Definitions, Acronyms and Abbreviations](#13-definitions-acronyms-and-abbreviations)
  * 1.4 [References](#14-references)
  * 1.5 [Document Overview](#15-document-overview)
* 2 [Product Overview](#2-product-overview)
  * 2.1 [Product Functions](#21-product-functions)
  * 2.2 [Product Constraints](#22-product-constraints)
  * 2.3 [User Characteristics](#23-user-characteristics)
  * 2.4 [Assumptions and Dependencies](#24-assumptions-and-dependencies)
* 3 [Requirements](#3-requirements)
  * 3.1 [Functional Requirements](#31-functional-requirements)
    * 3.1.1 [User Interfaces](#311-user-interfaces)
    * 3.1.2 [Hardware Interfaces](#312-hardware-interfaces)
    * 3.1.3 [Software Interfaces](#313-software-interfaces)
  * 3.2 [Non-Functional Requirements](#32-non-functional-requirements)
    * 3.2.1 [Performance](#321-performance)
    * 3.2.2 [Security](#322-security)
    * 3.2.3 [Reliability](#323-reliability)
    * 3.2.4 [Availability](#324-availability)
    * 3.2.5 [Compliance](#325-compliance)
    * 3.2.6 [Cost](#326-cost)
    * 3.2.7 [Deadline](#327-deadline)

## Revision History
| Name | Date    | Reason For Changes  | Version   |
| ---- | ------- | ------------------- | --------- |
|W.G.  |Sept. 15 2025      |Issues 7, 8, 9, 11                     |0.1           |
|S.Z.      |Sept. 15 2025         |Issue 10                      |0.2           |
|      |         |                     |           |

## 1. Introduction
### 1.1 Document Purpose
Spartan's List is an online marketplace dedicated to UNCG students and Almuni. Spartan's List aims to help students exchange goods and services, in order to better support fellow Spartans.

### 1.2 Product Scope
Spartan's List is a convenient and free web-based application to allow Spartans to post offers or shop for offers posted, these offers being goods or services. This is a web-based application as to permit account creation, offer postage, offer requesting, and account data tracking.

### 1.3 Definitions, Acronyms and Abbreviations
| Reference | Definition   |
| ---- | ------- | 
|   Java   |   An object oriented programming language developed in 1995 by James Gosling. This is the primary programming langauage that will be used to develop Spartan's List.      | 
|   PostgreSQL   | Open-source relational database management system. |
|   Spring MVC   | Model-View-Controller. This is the architectural pattern that will be used to implement the system. |
| Spring Web  |  This is used to build our web application with Spring MVC.  |
| API | Application programming interface. This will be used in the front-end and back-end of the system. |
| HTML | Hypertext Markup Language. This is the language used to structure the web pages of Spartan's List.|
| CSS | Cascading Style Sheets. This is used to add styling and appearances to the web application. |
|JavaScript| An object-oriented computer programming language  used to create interactive elements within web browsers. This will be used with HTML and CSS to make the application. |
|Visual Studio Code | An IDE (Integrated Developer Environment|
|MDN | Mozilla Developer Network. A web forum with resources about web-development tools. |

### 1.4 References
Spring Documentation,  — https://docs.spring.io/spring-framework/reference/index.html
MDN (Developer Resources) — https://developer.mozilla.org/en-US/

### 1.5 Document Overview
Section 1 provides an overview of the product, for all audiences. Section 2 describes the product, its functions, and its features. This section is aimed for users, Shoppers and Sellers. Section 3 is describes the development process, with the products requirements and constraints.

## 2. Product Overview
### 2.1 Product Functions
Spartan's List is a website not unlike Craigslist, where users can advertise goods and services for sale, and contact the individuals who list them.

### 2.2 Product Constraints
* Spartan's List will be hosted on a website.
* Spartan's List must be mobile friendly.
* Spartan's List must protect user's privacy.
  
### 2.3 User Characteristics
#### Shopper
* The Shopper is a user who visits Spartan's List to view listings of products and services for sale. They can also see the reviews for the products and Sellers. They should be using the website to browse for things that they might need. 
#### Seller
* The Seller is a user who lists products or services for sale on Spartan's List. They should use the website for the purpose of advertising and monetary gain.
* 
### 2.4 Assumptions and Dependencies
* We will be using an external API (that has not been decided yet)
* The user interface should look similar for all pages
* Shoppers and Sellers have separate profiles
* Spartan's List expects some technical prowess from our users. We expect all users to have familiarity with only a web-browser and common social media platforms, such as Facebook, Reddit, and X (formerly known as Twitter).
* The product is accessible to those who have never used e-commerce sites. Shoppers are not expected to be familiar with setting up digital offers
* Payment and messaging will be handled privately.

## 3. Requirements
### 3.1 Functional Requirements 
This section specifies the software product's requirements. Specify all of the software requirements to a level of detail sufficient to enable designers to design a software system to satisfy those requirements, and to enable testers to test that the software system satisfies those requirements.

The specific requirements should:
* FR0: The system shall allow for user creation as either a Seller or a Shopper.
    * Each account will have a unique AccountID, user name, and password.
    * Each user profile will show user name and account age.
* FR1: The system shall allow Sellers to create a new offer by providing details including title, offer type, price, availability, images, payment method, and description.
* FR2: The system shall allow Shoppers to browse available offers.
   * Shoppers shall be able to search and filter offers by title, offer type, price, and availability.
* FR3: The system shall allow Shoppers to view an offer.
  * Shoppers shall be able to view offer details, previous reviews, and the profile of the Seller.
* FR4: The system shall allow Shoppers to view any Seller's profile, follower count, and offers for sale.
* FR5: The system shall allow Shoppers shall be able to request offers and leave reviews on purchased offers.
* FR6: The system shall allow Sellers to accept offer requests to mark them as purchased.
  * Sellers will be able to edit availability after an offer request
* FR7: THe system shall allow Sellers to respond to reviews and select reviews to feature on their profile.
* FR8: The system shall allow Sellers to view their follower count, their average rating, previous orders, and pending orders.
* FR9: The system shall allow Shoppers to request an offer.
  * Shoppers shall be able to see the status of their offers: requested, denied, and complete.
* FR10: The system shall allow Shoppers to follow a Seller profile.

#### 3.1.1 User interfaces
The system will require web pages using HTML, CSS, and JavaScript, which must be accessed through an internet browser.

#### 3.1.2 Hardware interfaces
The system will require devices with web browser capabilities.

#### 3.1.3 Software interfaces
* Java JDK 21
* PostgreSQL 17
* SpringBoot 3.5.5

### 3.2 Non Functional Requirements 
#### 3.2.1 Performance
* NFR0: The system shall consume no more than 100 MB of memory.
* NFR1: The novice Shopper shall be able to search for, view, and request a given offer in less than 5 minutes.
* NFR2: The novice Seller shall be able to create an offer, add details, and request the offer in less than 5 minutes.
* NFR3: The novice Shopper shall be able to find a completed offer and leave a review in less than two minutes.
* NFR4: The novice Seller shall be able to open reviews and respond to a review in less than a minute.

#### 3.2.2 Security
* NFR5: The system shall not allow offer postage, request, or purchase to non-authorized users.
* NFR6: The system shall not handle payment within the application.

#### 3.2.3 Reliability
* NFR7: The system shall be able to handle ten users at once.
* NFR8: The system shall be able to display 

#### 3.2.4 Availability
* NFR9: The system shall be available at all times. Maintenance will be scheduled during low traffic hours to impact user experiences as little as possible.

#### 3.2.5 Compliance
* NFR12: The final product shall follow the rubric outlined on Canvas.

#### 3.2.6 Cost
* NFR10: The system shall be developed with zero cost.

#### 3.2.7 Deadline
* NFR11: The final product will be delivered by December 2025.
