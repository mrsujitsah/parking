# Parking System

You are tasked with designing a parking system for a multi-level parking lot.
The parking lot can accommodate different types of vehicles, including
motorcycles, cars and buses. the system should efficiently manage the parking
process, including vehicle entry, parking slot assignment and exit

## Requirement

### Parking lot structure
- The parking lot has multiple levels, each with a fixed number of parking slots.
- Slots are categorized based on vehicle types
- <strong>Motorcycle Slot</strong> : Can accommodate motorcycles only.
- <strong>Cars</strong> : Can accommodate cars and motorcycles.
- <strong>Bus Slot</strong> : Can accommodate buses, cars and motorcycles

### Vehicle Types
- <strong>Motorcycle</strong>
- <strong>Car</strong>
- <strong>Bus</strong>

### Parking Process
- A vehicle enters the parking lot and is assigned an available parking slot based on its type.
- If no suitable slot is available, the vehicle cannot enter the parking lot.
- The system should be minimized the time taken to find a parking slot.

### Exit Process
- When vehicle exits, the system should be free up the parking slot.
- The system should be calculated the parking fee based on the vehicle type and the duration of the parking.

### Parking Fee
- <strong>Motorcycle</strong> : $1/hour.
- <strong>Car</strong> : $2/hour
- <strong>Bus</strong> : $5/hour

### Admin Feature
- The admin can view the status of the parking lot(number of free/occupied slots by type)
- The admin can add or remove levels or slots.

## Commands
### Add Parking Level
- `ADD_LEVEL <level_id> <num_motorcycle_slots> <num_car_slots> <num_bus_slots>`
- Example: `ADD_LEVEL 1 10 20 5`
- Output: `Level 1 added with 10 motorcycle slots, 20 car slots, 5 bus slots.`

### Park Vehicle
- `PARK_VEHICLE <vehicle_type> <license_plate_number>`
- Example: `PARK_VEHICLE CAR MH-14-MV-0205`
- Output: `Car with lecense plate MH-14-MV-0205 parked at level 1, slot 15.`

### Exit Vehicle
- `EXIT_VEHICLE <license_plate_number>`
- Example: `EXIT_VEHICLE MH-14-MV-0205`
- Output: `Car with license plate MH-14-MV-0205 exited. Fee: $10.`

### View Parking Lot Status
- `VIEW_STATUS`
- Output: `Level 1: 5/10 motorcycle slots, 10/20 car slots, 2/5 bus slots available.`

### Admin: Add/Remove slots or Levels
- `ADD_SLOTS <level_id> <slot_type> <number_of_slots>`
- `REMOVE_SLOTS <level_id> <slot_type> <number_of_slots>`

## Expectations
### Class Design
- Define Classes for `ParkingLot`,`ParkingLevel`, `ParkingSlot`, `Vehicle`, `Admin`, etc
- Each class should have clear responsibilities, such as managing slots, handling vehicle entry/exit, calculating fee etc.

### Data Structure
- Use efficient data structures to track available slots, occupied slots, adn vehicle information

### Exception Handling
- Handle cases where the parking lot is full, invalid commands are issued, or vehicle information is incorrect.

### Extensibility:
- The system should be designed to easily accommodate future changes, such as adding new vehicle types or modifying parking fees.

### Edge Cases
- Handle scenarios where multiple vehicles try to park at the same time, a vehicle tries to exit without entering, etc.

## Bonus Requirements
### Optimized Slot Allocation
- Design the system to optimize the parking slot assignment to minimize walking distance for the driver.

### Reservation System
- Implement a feature where a vehicle can reserve a parking slot in advance.

### Real-Time Status Monitoring
- Allow real-time monitoring of the parking lot's status, including live updates of available slots.

# Solution

Using Domain-Driven Design(DDD), we would follow a structured approach by identifying the core domains, entities, value objects, aggregates, and services

## Identify Core Domains
In the context of a parking system, we can identify the following core domains

- <strong>Parking Management Domain</strong> : Manages the parking lot, levels, and slots
- <strong>Vehicle Management Domain</strong> : Manages the vehicle entering and exiting the parking lot.
- <strong>Billing Domain</strong> : Manages the calculation of parking fees.

## Define Bounded Contexts
Each core domain can be separated into bounded contexts:

- <strong>Parking Lot Context</strong> :Responsible for parking lot management, including levels and slots.
- <strong>Vehicle Context</strong> : Handles vehicle parking and exiting processes
- <strong>Billing Context </strong> : Calculates fees based on parking duration and vehicle type.

## Identify Aggregates and Entities
Within each bounded context, identify the aggregates and entities:
### Parking Lot Context
- <b>Aggregate</b>: `ParkingLot`
- <b>Entity:</b> `ParkingLevel`
- <b>Entity:</b> `ParkingSlot`

### Vehicle Context
- <b>Aggregate</b> : `Vehicle`
- <b>Entity</b>: `Motorcycle`
- <b>Entity</b>: `Car`
-  <b>Entity</b>: `Bus`

### Billing Context
- <b>Aggregate</b> : `Billing`
- <b>Entity</b>: `ParkingTicket`
- <b>Value Object</b>: `Money`
-  <b>Value Object</b>: `Duration`

## Define Value Objects
Value objects are immutable and represent a concept with no identity:

- <b>Money</b>: Represents the amount to be charged.
- <b>Duration</b>: Represents the time duration a vehicle was parked.

## Define Repositories
Repositories provide an abstraction layer to access data and entities:
- <b>ParkingLotRepository</b> : Handles retrieval and persistence of parking lot, levels and slots.
- <b>VehicleRepository</b> : Handles retrieval and persistence of vehicles.
- <b>BillingRepository</b>: Handles retrieval and persistence of parking tickets.

## Define Domain Services
Domain Service contain business logic that doesn't naturally fit within an entity or value object:

- <b>ParkingService</b> : Manages the logic of parking vehicles, checking slot availability and assigning slots.
- <b>ExitService</b> : Handles the process of exiting vehicles and freeing up slots.
- <b>BillingService</b> : Calculates the parking fee based on the vehicle type and duration