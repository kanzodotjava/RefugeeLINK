# Use Case: Register as a Refugee (UC010)

## Brief Description
This use case describes the process by which a Guest registers as refugee in the system to access available resources and opportunities.

## Actors
- Guest

## Preconditions
The system is accessible and the registration functionality is available.

## Basic Flow
1. Refugee navigates to the registration page.
2. System presents the registration form.
3. Refugee enters basic personal information such as name, date of birth, nationality, and country of origin.
4. Refugee provides contact information, including email address and phone number.
5. Refugee provides information about their refugee status, including any relevant documentation.
6. Refugee indicates their skills, educational background, and professional experience, if applicable.
7. Refugee reviews the information provided.
8. Refugee confirms the registration. 
9. System validates the provided information. 
10. System sends a system message to the refugee . 
11. Use case ends.

## Alternative Flows
- **Invalid Information (Step 10):** If the provided information is invalid, the system prompts the refugee to correct the errors and resubmit.
- **Cancellation (Steps 8-9):** If the refugee decides to cancel the registration, they can return to the homepage or exit the registration process.

## Postconditions
The refugee is successfully registered in the system and can access their account with the provided credentials.
