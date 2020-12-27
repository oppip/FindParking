# FindParking

FindParking is a university project for the Android Programming course. 
The idea behind this application is to make the ever-growing need for parking spaces a lot easier by integrating all the parking lots and garages in one place available for reservations.


### Usage

To use the application firstly it needs to be installed. That can be achieved by downloading the FindParking.apk or by clicking 
[HERE](https://github.com/oppip/FindParking/raw/master/FindParking.apk "Download FindParking"). After downloading the application on your phone install it by going to your
File Manager and clicking on the file name. The application is now all set up and ready for use, if you don't already have an account you can create one by clicking on the create account button
which will take you to another form for you to fill. After successfully creating an account you will view the available cities and by clicking on them and entering a date and time for the reservation,
a drop-down list of all the parking in the city will appear. If you found a parking lot you like just click on the reserve button and that's it. If you wish you could click navigate and 
navigate to the desired parking or you could delete the reservation from the confirmation screen or by clicking my reservations which will display all of your active reservations.


### How it works

The application was developed in Android studio and as a backend uses its integrated SQLite Database. Every activity, recycler view, and fragment is dynamically populated by 
extracting data from the database. This means that the application is easily scalable by updating the database and inserting new cities and parking lots. 

#### The database
The database has 4 entities: User, City, Parking, and Reservation where the users, cities, parking lots, and reservations are stored respectively. 
The Users table stores the email, password, name, birthday, and phone number for all of the users that have already created an account. By a connection of one-to-many, the Users
table is connected with the Reservations table which holds the date, time, and QR code for a given reservation. Furthermore, the Reservations table has a many-to-one connection with
the Parking table, holding the name, coordinates, fee, number of special needs parking spaces, and regular parking spaces. The last table, City has a one-to-many relationship with Parking.
The values that are stored are the name of the city, its coordinates, and also the country in which the city is situated.
