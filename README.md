# Moneytree-Light
This project is designed for the user who needs to manage their accounts and check thransaction informations.

## Project Infomation 

 ## Project Architecture:

 Application architecture based on MVVM pattern.According to the below image these main layers are used in the App. Majorly Activity and Fragments are considered as views. Most of the views are linked with dedicated viewmodels. In the viewmodels most of the logic handled related to views.
Repository and other data sources are considered as models.

![Project diagram (1)](https://user-images.githubusercontent.com/9447556/123100179-d67d6d00-d46d-11eb-90af-61dd9610d047.png)

## Design decisions:

1) The reason for using Repository layer is that in future we can easily connect remote web service without affecting viewmodel logic.Since viewmodels are dealing with repository any service can be plugged in Repository without changing view model logic. 

2) Always do data fetching and data saving operations decided to do in the background thread without putting much work to the main thread.Since Rx java is used to handle file access and database calls. 

3) Room database was used as local storage since it handled most of the operations and with less code we can achieve expected functionality

4) Dagger2 was used as a dependency injection framework.

5) Created base classes for Activity, Fragment & Viewmodel since in future when the app expands, these base classes handle common methods and that will help to maintain smaller size classes.

## Main Libraries which are used in project:

1. Room
2. Dagger2
3. RxJava
4. Gson
5. Mockito

## Completed User stories:
1. In the account list, a user should be able to see a list of accounts and the total balance of those accounts in the user's currency. The list should be ordered by the account nickname and grouped by institution. Each row should display the nickname and the balance in that account's currency.

2. In the account list, a user should be able to tap on the account to see the list of transactions on that account. The transactions should be ordered by month, from the newest to the oldest. Each row in the list should display the transaction’s date, description and amount in the account's currency.
3. In the transaction list, a user should see the transactions grouped by month, with each header displaying the month, the year, and the cash flow (money in and money out) during that month.
4. In the transaction list, a user should be able to see the account details, like its name, balance, total transactions in and out in a current month, including its estimated balance in the user’s local currency.

## Known issues:
One unit test case get failed due to database issue.Could not identify the real issue.

## Unhandled warnings:

```sh
The 'kotlin-android-extensions' Gradle plugin is deprecated.
```
This warning get due to depricated Gradle plugin.I tried to replace using Android view binding.Seems like it took some time to refactor 
code for now I keep this as same.

```sh
API 'BaseVariant.getApplicationIdTextResource' is obsolete and has been replaced with 'VariantProperties.applicationId'.
```
I searched about this warning and in some places mentioned this because of Android Studio version.Since I did not put much effort to resolve it.

## Future plans:

1) Could not cover all of the unit test in this phase.I like to cover all unit test cases if I have more time.

2) View Holder classes and Adapter classes does not have base classes and those are not lifecycle aware.Feel like it will better to seperate some of logics
   which are currently inside in Adapter classes and View Holder classes

3) Complete remain user cases


✨ Thank you! ✨
