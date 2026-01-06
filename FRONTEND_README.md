Frontend for Demo backend

This is a minimal static frontend placed under `src/main/resources/static` so Spring Boot serves it automatically from the same origin.

How to run
1. Start your backend (from project root):

   ./mvnw spring-boot:run

2. Open http://localhost:8080/ in your browser. The registration page is served at `/`.

Notes
- The frontend sends POST /api/users/register with JSON matching `UserRegistrationRequestDto` fields: email, firstName, lastName, birthDate (YYYY-MM-DD), password, country, city, phoneNumber.
- No build tools required.
- If you want to develop frontend separately, you can copy the static files into a different folder and serve them via any static server. If served from a different origin, enable CORS or use a reverse proxy.

