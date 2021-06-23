# Moneytree-Light
This project is designed for the User who is to manage their accounts and check thransaction informations.
## Project Infomation 

#### Project Architecture:

 Application architecture based on MVVM pattern.According to the above image these main layers are used in the App. Majorly Activity and Fragments are considered views. Most of the views are linked with dedicated viewmodels. In the viewmodels most of the logic handled related to views.
Repository and other data sources are considered as models.

#### Design decisions:

1) The reason for using Repository layer is that in future we can easily connect remote web service without affecting viewmodel logic.Since viewmodels are dealing with repository any service can be plugged in Repository without changing view model logic. 

2) Always do data fetching and data saving operations decided to do in the background thread without putting much work to the main thread.Since Rx java is used to handle file access and database calls. 

3) Room database was used as local storage since it handled most of the operations and with less code we can achieve expected functionality

4) Dagger2 was used as a dependency injection framework.

5) Created base classes for Activity, Fragment & Viewmodel since in future when the app expands, these base classes handle common methods and that will help to maintain smaller size classes.

#### Main Libraries which are used in project:

1. Room
2. Dagger2
3. RxJava
4. Gson
5. Mockito

#### Completed User stories
1. In the account list, a user should be able to see a list of accounts and the total balance of those accounts in the user's currency. The list should be ordered by the account nickname and grouped by institution. Each row should display the nickname and the balance in that account's currency.

2. In the account list, a user should be able to tap on the account to see the list of transactions on that account. The transactions should be ordered by month, from the newest to the oldest. Each row in the list should display the transaction’s date, description and amount in the account's currency.
3. In the transaction list, a user should see the transactions grouped by month, with each header displaying the month, the year, and the cash flow (money in and money out) during that month.
4. In the transaction list, a user should be able to see the account details, like its name, balance, total transactions in and out in a current month, including its estimated balance in the user’s local currency.

#### Known issues
One Unit test case get failed due to database issue.Could not identify the real issue.
