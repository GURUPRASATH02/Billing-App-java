AB Coffee & Restaurant - Billing System
A simple desktop-based billing and receipt generation system for a restaurant, built using Java Swing. This application allows restaurant staff to input customer details, select food and drink items with quantity and type (normal/diet), and generate a printable receipt with automatic calculations for tax and discount.

✨ Features
Customer detail entry (Name, Number, Contact)

Menu selection with quantity and type (Normal / Diet)

Automatic price calculation based on type

Tax and discount handling

Printable receipt generation

Reset functionality to clear all input

🧰 Technologies Used
Java (JDK 8+)

Java Swing (GUI)

AWT Print API (for printing receipts)

🧾 Menu Items & Pricing
Food (per item)
Item	Normal Price	Diet Price
Dosa	₹50.00	₹40.00
Porotta	₹30.00	₹25.00
Poori	₹35.00	₹30.00

Drinks (per item)
Item	Normal Price	Diet Price
Lemon	₹20.00	₹15.00
Orange	₹25.00	₹20.00
Apple	₹30.00	₹25.00

💸 Pricing Logic
Discount: 10% discount if subtotal > ₹100

Tax: 5% tax on subtotal

Total = Subtotal - Discount + Tax

📦 How to Run
Make sure you have JDK 8 or higher installed.

Compile the program:

bash
Copy
Edit
javac restra/restra.java
Run the application:

bash
Copy
Edit
java restra.restra
📸 Screenshots
You can add screenshots here if you're uploading this to GitHub or sharing it in a report.

📚 Educational Use
This project was developed as part of academic learning to practice:

Java GUI development

Event-driven programming

Dynamic UI updates and table manipulation

Print handling with Java AWT

📃 License
This project is intended for academic and personal learning purposes. Free to use and modify with credit.
