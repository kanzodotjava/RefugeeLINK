## Use Case: UC051 - Ban or Verify Mentors

### Actors
- System Administrator (Admin)

### Description
This use case outlines the process by which the system administrator can ban or verify mentors on the RefugeeLink platform to maintain quality and trustworthiness in mentorship offerings.

### Preconditions
- The system administrator has logged into the RefugeeLink admin panel.
- The system administrator has access to the feature for managing mentors within the admin panel.
- Mentors are registered users on the RefugeeLink platform.

### Flow of Events
1. The system administrator accesses the mentor management feature within the RefugeeLink admin panel.
2. The admin panel displays a list of registered mentors along with their profile details.
3. The system administrator selects a mentor from the list to review their account status.
4. For the selected mentor, the admin panel provides options to either ban or verify the mentor's account.
5. If the system administrator chooses to ban the mentor:
    - The mentor's account is suspended, and they lose access to mentorship features.
    - The system logs the action and updates the mentor's account status to "banned."
6. If the system administrator chooses to verify the mentor:
    - The mentor's account is marked as verified, indicating they are approved to provide mentorship.
    - The system logs the action and updates the mentor's account status to "verified."
7. If necessary, the system administrator can reverse the ban or verification action, restoring the mentor's account status.

### Postconditions
- The system administrator has successfully managed the account status of a mentor on the RefugeeLink platform.
- The mentor's account status is updated accordingly, either as "banned" or "verified."
- Mentors with verified status are considered approved to provide mentorship to refugees.
- The system maintains logs of admin actions for accountability and auditing purposes.
- The mentor receives notification of their account status change, if applicable.
