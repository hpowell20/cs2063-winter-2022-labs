# Lab 5 - Scrolling lists

## Pre-lab reading

This lab makes use of `RecyclerView`.  Please take some time to read the following documentation [here](https://developer.android.com/guide/topics/ui/layout/recyclerview.html)

## Pair Programming

For the remainder of the labs we will complete the assignments using pair programming.  Additional details can be found at [Pair Programming](../docs/PAIR_PROGRAMMING.md).  For this activity you will need to work in pairs. You can work with anybody of your choosing, however, to obtain the most benefit from the activity it is recommended you work with someone other than your partner for the course project.  This is not a requirement and will not be strictly enforced.

## Introduction

Many mobile applications need to display a scrollable list of items where the user can select an item to take some action specific to it. In this lab you will build an app that displays a scrolling list of UNB CS courses (including course numbers, titles and credit hours).

![Main Activity](https://i.imgur.com/8vQZmXf.png?1)

Selecting a course from the list of courses in the main activity takes the user to a detail activity showing the description for the selected course. (Yes, the course information we're using in this lab is a bit out of date)

![Detail Activity](https://i.imgur.com/qaqnSXb.png?1)

There are two common ways to display a scrolling list of items in Android
1. [`ListView`](https://developer.android.com/guide/topics/ui/layout/listview.html)
2. [`RecyclerView`](https://developer.android.com/guide/topics/ui/layout/recyclerview.html)
* The `RecyclerView` provides greater flexibility and will be the focus today

## Understanding the Skeleton Code

**Task 1**

1. Importing the Lab's skeleton project.  Note that there are four Java classes:
  * `Course` represents a course
    * Although there are no optional properties in the class new objects are created using the Builder pattern which helps to set immutability for the class data
    * As a result only getter methods to retrieve the course title and description are provided

  * `JsonUtils` is a class used to work with ```JSON``` files
    * The app will make use of the contents included in an included asset file (```assets/CS.json```) which contains information about Computer Science courses available at UNB
    * ```JsonUtils``` processes the ```JSON``` to create ```Course``` items
      * Note in particular the ```JsonUtils``` constructor and the argument it takes along with the  ```JsonUtils.getCourses()``` method

  * `DetailActivity` corresponds to the second screenshot above, displaying information about a specific course
    * It doesn't do much yet as you will be completing it during the lab

  * `MainActivity` presents the scrolling list of courses using a `RecyclerView`
    * It contains an inner class `MyAdapter` that extends `RecyclerView.Adapter`
    * The `RecyclerView.Adapter` class provides a layer of abstraction between the `RecyclerView`'s `LayoutManager` and the underlying data that is being displayed which in this case a list of `Course` objects
    * `MyAdapter` itself contains an inner class `ViewHolder` which represents an individual item to display in the scrolling list
      * `onCreateViewHolder` creates `ViewHolder` objects by inflating the corresponding XML layout resource file; it's already implemented for you
      * `onBindViewHolder` will be called when a particular item in the dataset needs to be displayed in the scrolling list, i.e., the user has scrolled and a new item comes into view
      * This method sets up the `ViewHolder` to display the corresponding item in the dataset.
        * It is incomplete; you will finish it below.

2. Take note of the layout files  
  * `activity_detail.xml` and `activity_main.xml` are layouts for `DetailActivity` and `MainActivity` respectively
    * You can verify this by looking at the calls these classes make to `setContentView()`
  * `activity_main.xml` includes a `RecyclerView` where it's `LayoutManager` has been set to a `LinearLayoutManager` to give a list of items
    * This differs from a a `GridLayoutManager` which would present the items in a grid)
  * `item_layout.xml` will be used to display each item in the scrolling list in `MainActivity` and contains just a single `TextView`

## Implementation

**Task 2**

Complete the TODOs in `MainActivity`

**Task 3**

Complete the TODOs in `DetailActivity`

NOTE:
* The `ActionBar` corresponds to the text at the top of the detail activity in the screen shot above (i.e., "CS2063 Introduction to M...")
  * This might help you -  [```getSupportActionBar()```](http://developer.android.com/reference/android/support/v7/app/AppCompatActivity.html#getSupportActionBar%28%29)

**Task 4**

On a smaller device (or for a very long course description) all of the text won't fit on the screen so we need to be able to scroll the text. You will also notice that there is no vertical scroll bar when the content is too long.

1. Update one of the course descriptions in the CS.json file to ensure that it is long enough to scroll (if one is not already long enough)
2. Fix the `TextView` in the `DetailActivity` to include a scrollbar.

**Lab Completion**

* Show the working app running on an emulator to the instructor or TA
* Keep a copy of your project work and answers for future reference
