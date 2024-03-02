Use Case: UC051 - Toggle Approval Status of Students in Formations

Actors:
- Organization

Description:
This use case describes the process by which an organization toggles the approval status of students directly from the student list within formations in the RefugeeLink app.

Preconditions:
- The organization user has logged into their RefugeeLink account.
- The organization user has access to the feature for managing student enrollment in formations.

Flow of Events:
1. The organization user navigates to the student list for a specific formation within the RefugeeLink app.
2. The app retrieves and displays a table or list of all students enrolled in the formation, along with their current approval status.
3. The organization user reviews the list of students and their approval status.
4. The organization user selects a student from the list to toggle their approval status.
5. The organization user clicks on the toggle or checkbox next to the selected student's entry to change their approval status.
6. If the approval status is toggled to "approved":
   a. The student is considered officially enrolled in the formation.
   b. The system updates the student's enrollment status and notifies them of their enrollment.
7. If the approval status is toggled to "not approved":
   a. The student's enrollment is rejected.
   b. The system updates the student's enrollment status and notifies them accordingly.
8. The student list dynamically updates to reflect changes in approval status.
9. The organization user can review and update approval status for multiple students efficiently.
10. The system provides feedback or notifications to confirm changes in approval status and inform students of enrollment status updates.

Postconditions:
- The organization user has successfully toggled the approval status of students within the formation.
- Student enrollment status is updated accordingly, and students are notified of enrollment status updates.

Alternative Flow:
- If there are no students currently enrolled in the formation, the app displays a message indicating that there are no students to review.
