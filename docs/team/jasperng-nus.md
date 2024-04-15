---
  layout: default.md
  title: "Jasper's Project Portfolio Page"
---

### Project: InternBook

InternBook is a desktop intern book application used for managing internship companies and contacts. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.
1) Added Non-Functional Requirements and Glossary sections in developer guide in v1.1
2) Worked on the help command in v1.2. This included:
   1) Changed the URL in the help command to the URL of our project's user guide
   2) Added a new 'Open User Guide' button to open the user guide in the default browser
   3) Changed the GUI to reflect the changes above and added some CSS styling
   4) Added a HTML file of our user guide and the 'Open User Guide' button to open the user guide as a popup instead so that users will be able to view it offline
   5) Edited the help command to display the user guide straight away when the user types 'help' instead of displaying the help messages with buttons
3) Changes to Tag class. This included:
   1) Changing the regex to include spaces and '/' in the tag name
   2) To allow case-insensitive comparison of duplicate tags
   3) Added a method to capitalise the first letter of each word in the tag
4) Updated some of the address book context to match our context in v1.3. This included:
   1) Changed the TypicalCompanies class details to match our context
5) Create a mark and unmark command to mark company as applied in v1.3. This included:
   1) Creating a MarkCommand and UnmarkCommand class and creating a MarkCommandParser and UnmarkCommandParser class to parse the user input
   2) Made some GUI changes to reflect the changes above (A checkbox to mark and unmark companies)

