RewardApp is a spring boot application developed to find out customer reward points based on their transaction range per month.

Pre-requisites : Spring Tool Suite / eclipse with Java version 8 or above. Clone the repository and import as  maven project in STS/eclipse.

Run the project as springboot app. 

Once the localhost server is up in 8080 port
Open H2 in memory console (http://localhost:8080/h2-console) supply username as "sa" & password as "password" for  jdbc:h2:mem:dbtest DB and login.

Populate the customer transaction datarepo with few records by using rewardapp/data.sql.

Now the application is ready to serve api requests.

Hit the url : http://localhost:8080/api/rewardpoints/get/2 "2" is customerId and monthly points summary of customerId "2" is fetched

Response : { "customerId": 2, "monthlySummary": [ { "yearMonth": "2024-04", "points": 50 }, { "yearMonth": "2024-05", "points": 65 }, { "yearMonth": "2024-06", "points": 671 } ], "totalPoints": 786 }

This shows summary of points for customer with customerId =2 where yearMonth field represent month and the year with earned points and total points.


Application:
 Request hits controller ,
 Controller invokes service to provide customer reward info with total points.
 Service 
 1. fetch all txn of the customerId.
 2. prepare yearmonth and transaction map
 3. Prepare Monthly summary of points by processing Yearmonth map using 
 4. Build final customerrewardinfo by using monthly summary and total ponts calculation for each transaction of that month.


Points calculation logic : A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction.(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

Application is built using Spring Boot framework with in memory db h2.

URL can be used for other customersId=1 or 3 by replacing 1 at the end of the url.
