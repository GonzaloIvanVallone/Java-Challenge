# Java-Challenge
A company is looking to create a website to sell its products online. The visual interface is already made, and all that remains is to implement its operation.

The company currently sells keyboards, mouses and monitors. They consider it possible to start selling joysticks and headphones in the future. For each product, we know its model name, its brand, whether it is imported or not (its price will be in dollars or pesos respectively), and its stock. In addition, each type of product has its own characteristics:

Keyboards: Number of keys, weight, mechanical or not.<br>
Mouses: DPI, weight, number of buttons.<br>
Monitors: Inches, panel type, weight.<br>

## The application will need to be able to fulfill the following functions:

◾Report all the data of a specific product.<br>
◾Report the model name of all keyboards; report the of all mice; report that of all monitors.<br>
◾Be able to upload a new product.<br>
◾Being able to sell a product, reducing its stock.<br>

Implement in Java, with Spring Boot, a REST API that can meet the above requirements. Any other technology deemed necessary may be freely used.

## Extra points:

◾For imported products check the current price of the blue dollar at the time of the query, consuming the following API: https://api.bluelytics.com.ar/v2/latest (value_sell field of the official dollar )

◾Be able to delete a product.


## Glossary:

Types of monitor panel: IPS, VA, TN.<br>
The DPI of a mouse can range from 100 to 25600.
