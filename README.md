<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

</head>
<body>
    <h1>🏨 Hotel Reservation System (Java + JDBC + MySQL)</h1>

  <p>
        This is a console-based <strong>Hotel Reservation Management System</strong> built using 
        <strong>Java</strong>, <strong>JDBC</strong>, and <strong>MySQL</strong>. It allows hotel staff to 
        manage room reservations efficiently with a simple text-based interface.<br>
      <br>
        <strong>The main Java file HotelReservationSystem.java is located in the src/ directory of the project.</strong>
      
</p>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

</head>
<body>

<h1>🌟 Features</h1>
    <ul>
        <li>Create new room reservations.</li>
        <li>View all current reservations in a formatted table.</li>
        <li>Retrieve room number by reservation ID and guest name.</li>
        <li>Update reservation details (name, room number, contact).</li>
        <li>Delete a reservation by ID.</li>
        <li>Simple menu-driven CLI interface.</li>
        <li>MySQL integration using JDBC.</li>
        <li>Timestamp auto-generation for reservation date.</li>
    </ul>

  <h1>🧰 Technologies Used</h1>
    <table border="1" cellpadding="6">
        <thead>
            <tr>
                <th>Technology</th>
                <th>Description</th>
            </tr>
        </thead>
        <tbody>
            <tr><td>Java</td><td>Programming language used</td></tr>
            <tr><td>JDBC</td><td>Java Database Connectivity API</td></tr>
            <tr><td>MySQL</td><td>Relational database for storing reservation data</td></tr>
            <tr><td>IntelliJ / Eclipse</td><td>Recommended IDE for development</td></tr>
        </tbody>
    </table>

  <h1>🗃️ Database Schema</h1>
    <p>Create the following table in your MySQL database:</p>
    <pre><code>
CREATE DATABASE IF NOT EXISTS hotel_db;

USE hotel_db;

CREATE TABLE IF NOT EXISTS reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_name VARCHAR(255) NOT NULL,
    room_num INT NOT NULL,
    contact_num VARCHAR(20) NOT NULL,
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
</code></pre>

   <h1>🛠️ Setup Instructions</h1>
    <ol>
        <li>Clone this repository or download the source code.</li>
        <li>Set up MySQL database:
            <ul>
                <li>Run the SQL script above.</li>
                <li>Make sure MySQL is running locally on port 3306.</li>
            </ul>
        </li>
        <li>Update DB credentials in the code:</li>
    </ol>

<pre><code>
In HotelReservationSystem.java:

private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
private static final String username = "your_mysql_username";
private static final String password = "your_mysql_password";
    </code></pre>

   <ol start="4">
        <li>Add JDBC MySQL driver to your project:
            <ul>
                <li>Download the MySQL Connector JAR.</li>
                <li>Add it to your project’s classpath (in IntelliJ: File → Project Structure → Libraries).</li>
            </ul>
        </li>
        <li>Compile and Run the Program:
            <ul>
                <li>From your IDE or using <code>javac</code> and <code>java</code> commands.</li>
            </ul>
        </li>
    </ol>

  <h1>📌 Main Tasks / Functionalities</h1>
    <ol>
        <li><strong>Reserve a Room</strong>
            <ul>
                <li>Input guest name, room number, and contact number.</li>
                <li>Saves the reservation to the database.</li>
            </ul>
        </li>
        <li><strong>View Reservations</strong>
            <ul>
                <li>Lists all reservations in a formatted table.</li>
                <li>Includes reservation ID, name, room, contact, and timestamp.</li>
            </ul>
        </li>
        <li><strong>Get Room Number</strong>
            <ul>
                <li>Input reservation ID and guest name.</li>
                <li>Retrieves the room number if the reservation exists.</li>
            </ul>
        </li>
        <li><strong>Update Reservation</strong>
            <ul>
                <li>Update guest name, room number, or contact info by providing reservation ID.</li>
            </ul>
        </li>
        <li><strong>Delete Reservation</strong>
            <ul>
                <li>Deletes a reservation based on the ID after verifying its existence.</li>
            </ul>
        </li>
        <li><strong>Exit</strong>
            <ul>
                <li>Exits the application gracefully with a loading animation.</li>
            </ul>
        </li>
    </ol>

  <h1>▶️ How to Run</h1>
    <h3>Option 1: Using Terminal</h3>
    <pre><code>
javac HotelReservationSystem.java
java HotelReservationSystem
    </code></pre>

   <h3>Option 2: Using IntelliJ or Eclipse</h3>
    <ul>
        <li>Open the project folder.</li>
        <li>Make sure JDBC driver is added as a library.</li>
        <li>Run <code>HotelReservationSystem.java</code>.</li>
    </ul>

   <h1>🔐 Security Notes</h1>
    <p><strong>⚠️ WARNING:</strong> The current implementation uses SQL string concatenation, which is vulnerable to SQL injection.</p>

   <h3>Recommendations:</h3>
    <ul>
        <li>Refactor code to use <code>PreparedStatement</code> instead of <code>Statement</code>.</li>
        <li>Avoid storing credentials in the source code. Use:
            <ul>
                <li>Environment variables.</li>
                <li><code>.properties</code> config file.</li>
            </ul>
        </li>
        <li>Validate all user input.</li>
    </ul>
</body>
</html>
