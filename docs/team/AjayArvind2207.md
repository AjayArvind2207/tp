---
  layout: default.md
  title: "Ajay's Project Portfolio Page"
---

### Project: InternBook

InternBook is a desktop intern book application used for managing internship companies and contacts. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.
1) Developed the Getting Started section of the user guide in v1.1
2) Worked on the edit command in v1.2. This included:
   1) Modifying the command to fit the requirements of our project
   2) Refactoring code to change the Person class to the Company class
   3) Deleting the address field since it was redundant
   4) Deleting and modifying all test-cases affected by the deletion of the address
   5) Changing the fxml file to reflect the deletion of the address field.
3) Worked on updating the find command in v1.3. This included
   1) Changing the matching algorithm that find uses
   2) Modifying tests to reflect the new behaviour
   3) Changing default values used in tests to better reflect behaviour
   4) Fixed bugs in other functionalities as well, including changing error messages and allowing empty tags through edit.
   5) Updated UML Diagrams
