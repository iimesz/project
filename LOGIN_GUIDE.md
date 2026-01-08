# Spring Security Login Implementation Guide

## Overview
Your application now has a complete JWT-based authentication system with user registration and login functionality.

## Features Implemented

### 1. **User Registration** (`POST /api/users/register`)
- Accepts user details and contact information
- Encrypts password using BCrypt
- Returns user details with timestamps
- Email uniqueness validation

### 2. **User Login** (`POST /api/users/login`)
- Authenticates users with email and password
- Generates JWT access tokens (15 minutes expiration)
- Generates JWT refresh tokens (7 days expiration)
- Stores refresh token in database

### 3. **JWT Security**
- Access tokens for API authentication
- Refresh tokens for token renewal
- HMAC-SHA256 signing algorithm
- Configurable token expiration times

### 4. **Spring Security Configuration**
- CORS enabled for frontend integration
- CSRF protection disabled (suitable for JWT-based APIs)
- Session management set to STATELESS
- Public endpoints for register/login
- All other endpoints require authentication

---

## Testing the Login System

### Step 1: Start the Application
```bash
cd "C:\Users\KhaledMunser\Downloads\demo (2)\demo"
mvn spring-boot:run
```

### Step 2: Register a User

**Endpoint:** `POST http://localhost:8080/api/users/register`

**Request Body:**
```json
{
  "user": {
    "email": "khaled@example.com",
    "firstName": "Khaled",
    "lastName": "Munser",
    "password": "Password123",
    "birthDate": "2000-05-15"
  },
  "contact": {
    "country": "Jordan",
    "city": "Amman",
    "phoneNumber": "3886655"
  }
}
```

**Expected Response (201 Created):**
```json
{
  "id": 1,
  "email": "khaled@example.com",
  "firstName": "Khaled",
  "lastName": "Munser",
  "birthDate": "2000-05-15",
  "createdAt": "2026-01-07T14:35:00",
  "updatedAt": "2026-01-07T14:35:00"
}
```

### Step 3: Login with Credentials

**Endpoint:** `POST http://localhost:8080/api/users/login`

**Request Body:**
```json
{
  "email": "khaled@example.com",
  "password": "Password123"
}
```

**Expected Response (200 OK):**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFsZWRAZXhhbXBsZS5jb20iLCJpYXQiOjE2MzIzNDU2MzAsImV4cCI6MTYzMjM0NjUzMH0.xxxxxxxxxxx",
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFsZWRAZXhhbXBsZS5jb20iLCJpYXQiOjE2MzIzNDU2MzAsImV4cCI6MTYzMjk1MDQzMH0.xxxxxxxxxxx",
  "email": "khaled@example.com",
  "firstName": "Khaled",
  "lastName": "Munser"
}
```

### Step 4: Use Access Token for Protected Endpoints

Include the access token in the `Authorization` header:

```
Authorization: Bearer <your_access_token>
```

---

## Configuration

### JWT Settings in `application.properties`

```properties
# JWT Configuration
app.jwt.secret=mySecretKeyThatIsAtLeast32CharactersLongForHS256AlgorithmPleaseUpdateThis
app.jwt.access-token-expiration=900000      # 15 minutes in milliseconds
app.jwt.refresh-token-expiration=604800000  # 7 days in milliseconds
```

**⚠️ IMPORTANT:** Change the `app.jwt.secret` in production to a secure, random key!

---

## Security Best Practices

1. **Change JWT Secret:** Update `app.jwt.secret` in production
2. **HTTPS Only:** Always use HTTPS in production
3. **Secure Password Policy:** Enforce strong password requirements
4. **Token Storage:** Store tokens securely in the frontend (httpOnly cookies recommended)
5. **CORS Configuration:** Update allowed origins for your production domain
6. **Refresh Token Rotation:** Implement token refresh logic in your frontend

---

## Files Created/Modified

### New Files:
- `src/main/java/com/khaled/demo/security/JwtTokenProvider.java` - JWT token generation/validation
- `src/main/java/com/khaled/demo/security/CustomUserDetailsService.java` - User details service
- `src/main/java/com/khaled/demo/model/dto/LoginRequestDto.java` - Login request DTO
- `src/main/java/com/khaled/demo/model/dto/LoginResponseDto.java` - Login response DTO

### Modified Files:
- `src/main/java/com/khaled/demo/config/SecurityConfig.java` - Spring Security configuration
- `src/main/java/com/khaled/demo/controller/UserController.java` - Added login endpoint
- `src/main/java/com/khaled/demo/repository/UserRepository.java` - Added findByEmail method
- `src/main/java/com/khaled/demo/service/UserService.java` - Added login method
- `src/main/java/com/khaled/demo/service/impl/UserServiceImpl.java` - Implemented login logic
- `pom.xml` - Added JWT and Spring Security dependencies
- `src/main/resources/application.properties` - Added JWT configuration

---

## Error Handling

- **Invalid Email/Password:** Returns 401 Unauthorized with message
- **Email Already Exists:** Returns 409 Conflict during registration
- **Missing Fields:** Returns 400 Bad Request with validation errors
- **Invalid Token:** Returns 401 Unauthorized for protected endpoints

---

## Next Steps (Optional Enhancements)

1. Add JWT refresh token endpoint
2. Implement token blacklist for logout
3. Add role-based access control (RBAC)
4. Add email verification
5. Implement password reset functionality
6. Add 2FA (Two-Factor Authentication)
7. Add rate limiting for login attempts
8. Implement audit logging

---

## Troubleshooting

**Issue:** Token validation fails
- Verify JWT secret matches between token generation and validation
- Check token expiration time

**Issue:** CORS errors
- Update `corsConfigurationSource()` in SecurityConfig with your frontend URL
- Ensure frontend sends requests with proper Content-Type header

**Issue:** UserDetailsService not found
- Ensure `CustomUserDetailsService` is annotated with `@Service`
- Check package scanning includes the security package

