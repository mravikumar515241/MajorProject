
# Hotel Booking System

This repository contains the source code for a Hotel Booking System, which provides services related to managing customers, hotels, rooms, and bookings.

## Services

### Customer Service

The Customer service manages customer information and provides CRUD (Create, Read, Update, Delete) operations for managing customers.

#### CRUD Operations:
- **Create Customer:** Add a new customer to the system with the following details: first name, second name, email id. **ACCESS LEVEL**: ADMIN, CUSTOMER
- **Update Customer:** Update existing customer information, only the following details are allowed for updation: first name, second name, email id. **ACCESS LEVEL**: ADMIN, CUSTOMER
- **Delete Customer:** Remove a customer from the system. **ACCESS LEVEL**: ADMIN, CUSTOMER
- **Get All Customers:** Retrieve information about all customers. **ACCESS LEVEL**: ADMIN
- **Get Customer by ID:** Retrieve information about a specific customer by ID. **ACCESS LEVEL**: ADMIN

### Hotel Service

The Hotel service manages hotel information and provides CRUD operations for managing hotels.

#### CRUD Operations:
- **Create Hotel:** Add a new hotel to the system with the following details: hotel name, address. **ACCESS LEVEL**: ADMIN
- **Update Hotel:** Update existing hotel information, only the following details are allowed for updation: hotel name, address. **ACCESS LEVEL**: ADMIN
- **Delete Hotel:** Remove a hotel from the system, also remove their corresponding rooms in the room_entity table. **ACCESS LEVEL**: ADMIN
- **Get All Hotels:** Retrieve information about all hotels. **ACCESS LEVEL**: ADMIN, CUSTOMER
- **Get Hotel by ID:** Retrieve information about a specific hotel by ID. **ACCESS LEVEL**: ADMIN, CUSTOMER

### Room Service

The Room service manages room information within hotels and provides CRUD operations for managing rooms.

#### CRUD Operations:
- **Create Room:** Add a new room to a hotel, with default status set to "Vacant". **ACCESS LEVEL**: ADMIN
- **Update Room:** Update existing room information, only the following details are allowed for updation: room type, room status, hotel. **ACCESS LEVEL**: ADMIN
- **Delete Room:** Remove a room from a hotel. **ACCESS LEVEL**: ADMIN
- **Get All Rooms:** Retrieve information about all rooms. **ACCESS LEVEL**: ADMIN, CUSTOMER
- **Get Room by ID:** Retrieve information about a specific room by ID. **ACCESS LEVEL**: ADMIN, CUSTOMER

### Booking Service

The Booking service manages booking information for customers and provides CRUD operations for managing bookings.

#### CRUD Operations:
- **Create Booking:** Make a new booking for a customer with the following details: duration, customer id, hotel id, room id. Operation is only allowed if selected room is Vacant. **ACCESS LEVEL**: ADMIN, CUSTOMER
- **Update Booking:** Update existing booking information, only the following details are allowed for updation: duration, room. Operation is only allowed if selected room is Vacant. 
- and status of both the old and new rooms are updated. **ACCESS LEVEL**: ADMIN, CUSTOMER
- **Delete Booking:** Cancel a booking made by a customer, Room is now set to Vacant. **ACCESS LEVEL**: ADMIN, CUSTOMER
- **Get All Bookings:** Retrieve information about all bookings. **ACCESS LEVEL**: ADMIN
- **Get Booking by ID:** Retrieve information about a specific booking by ID. **ACCESS LEVEL**: ADMIN

## Getting Started

To get started with the Hotel Booking System, follow these steps:

1. Clone the repository to your local machine.
2. Install the required dependencies.
3. Configure the database connection settings.
4. Build and run the application.

## Team
Ravi Kumar M
