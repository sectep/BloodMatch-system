# BloodMatch

A simple **CLI-based Java application** that matches blood donors with patient requests based on blood type, city, and urgency (priority). The system uses multithreading to simulate urgency-based scheduling for real-life donor matching scenarios.

---

## 📁 Prepare data files

Before starting the application, make sure the following files exist inside the `data` directory. You can create them as empty files:

```
data/donors.txt  
data/patients.txt
```

---

## ⚙️ Compile the source

```
javac -d out src/main/java/BloodMatch/src/bloodmatch/*.java
```

---

## ▶️ Run the application

```
java -cp out BloodMatch.src.bloodmatch.Main
```

---

## 🧭 How to Use

- Use the menu to:
  - Add a new **donor**
  - Add a new **patient request**
  - Display all **donors** or **patients**
  - Match donors with patient requests based on urgency

---

## 💡 Example Output

```
===BLOOD MATCH SYSTEM===
1. Add donor
2. Add patient
3. Show all donors
4. Show all requests
5. Match donors with requests
6. Exit
Choose: 1

Enter id: D100
Enter name: Alice Smith
Enter email: alice@example.com
Enter city: Denver
Enter blood type: O-

Alice Smith with id D100 has been written to our database successfully.

===BLOOD MATCH SYSTEM===
Choose: 2

Enter id: P200
Enter name: Bob Jones
Enter city: Denver
Enter blood type: O-
Enter urgency(1-10): 8

P200 has been written to our database successfully.

Choose: 5

To quit the buffer, enter 'exit'
Enter id: P200
D100 is suitable for P200 with O-
[NEW THREADS STARTED – suspended...]
Making operation for P200...
Operation for P200 is completed.

Match processing complete. Queue empty.

Choose: 6
Terminating the program.
```

---

## ✅ Notes

- This project demonstrates use of:
  - File I/O
  - Collections (`List`)
  - Custom `Thread` logic (`suspend`/`resume`)
  - Simple matching logic based on real-life hospital workflow

---

## 🏁 End

Developed for educational purposes to simulate a basic blood donation management system.
