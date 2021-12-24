# Task

You are building a food delivery product. Since company is very small and has only one person delivering the orders then
there are limitations how many orders it can deliver per day and per delivery time.

The limits are following:

- on Mondays each delivery time can have up to 4 orders
- other weekdays each delivery time can have up to 2 orders

# What you need to implement

Your task is to implement an application that returns the list of available delivery times that user can choose from
when placing an order.

- you need to implement a method that takes 1 parameter - the date to check. It should return the list of delivery times
  that are available on that date
- the delivery times for each day are following: 10:30, 12:30, 18:30
- to get the list of existing orders you have `OrderServiceClient`. As part of this task you can assume that it will be
  implemented by another team
- you can ignore how the public API of this application will be made available for frontend. As part of this task you
  can assume it will be implemented by another person in your team. Most likely it will be exposed as JSON over HTTP
- you can assume that the date input will be a valid date so you don't need to implement any input validation
- you should not use any other libraries besides JUnit

# Example scenarios

To help you implement this task the imaginary team has come up with following acceptance tests:

```
Given
  System has 2 orders on Tuesday at 10:30
When
  User checks delivery times for Tuesday
Then 
  System will only return 12:30 and 18:30 delivery times

Given
  System has 3 orders on Monday at 12:30 and 4 orders at 18:30
When
  User checks delivery times for Monday
Then
  System will return 10:30 and 12:30 delivery times

Given
  System has no orders for a day
When
  User checks delivery times for a day
Then
  System will return 10:30, 12:30 and 18:30 as available times
```

# Evaluation criteria

- code design (you should assume this code will be maintained long time)
- tests or whatever you think is the best way how to verify that the code works as expected
- correctness - does the code work as expected
