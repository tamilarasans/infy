Pre-requisites : Spring Tool Suite  /  eclipse with Java version 8 or above.
Download source code and import as import maven project in STS/eclipse.

Run the project as springboot app.
Populate the customertransactiondatarepo with few records by using data.sql
http://localhost:8080/h2-console

Hit the url : http://localhost:8080/api/rewardpoints/get/2 "2" is customerId and monthly points summary of customerId "2" is fetched

Response : 
{
"customerId": 2,
"monthlySummary": [
{
"yearMonth": "2024-04",
"points": 50
},
{
"yearMonth": "2024-05",
"points": 65
},
{
"yearMonth": "2024-06",
"points": 671
}
],
"totalPoints": 786
}

This shows summary of points for customer with customerId =2 where yearMonth field represent month and the year with earned points and total points.

Points calculation logic : A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

Applicatiom is built using Spring Boot framework with in memory db h2.

URL can be used for other customersId=1 or 3 by replacing 1 at the end of the url.
