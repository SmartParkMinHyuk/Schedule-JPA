# ScheduleSpringDataJPA API 명세서 및 ERD

## 1. API 명세서

### 회원가입

- URL: POST /api/auth/signup
- 요청 형식: application/json
- 설명: 새로운 유저를 등록합니다

#### Request Body
```json
{
  "username": "springuser",
  "email": "spring@boot.com",
  "password": "1234"
}
```

#### Response
```json
{
  "id": 1,
  "username": "springuser",
  "email": "spring@boot.com",
  "createdAt": "2025-04-06T12:00:00",
  "updatedAt": "2025-04-06T12:00:00"
}
```

---

### 로그인

- URL: POST /api/auth/login
- 요청 형식: application/json
- 설명: 이메일/비밀번호로 로그인하고 세션 생성

#### Request Body
```json
{
  "email": "spring@boot.com",
  "password": "1234"
}
```

#### Response (성공)
```
로그인 성공
```

#### Response (실패)
```
로그인 실패: 이메일 또는 비밀번호가 틀렸습니다.
```

---

### 유저 목록 조회

- URL: GET /api/users
- 설명: 전체 유저 리스트 조회

#### Response
```json
[
  {
    "id": 1,
    "username": "springuser",
    "email": "spring@boot.com",
    "createdAt": "2025-04-06T12:00:00",
    "updatedAt": "2025-04-06T12:00:00"
  }
]
```

---

### 일정 등록

- URL: POST /api/schedules
- 설명: 유저가 일정을 등록합니다 (로그인 필요)

#### Request Body
```json
{
  "title": "JPA 복습",
  "content": "연관관계 단방향 설정 복습",
  "userId": 1
}
```

#### Response
```json
{
  "id": 1,
  "title": "JPA 복습",
  "content": "연관관계 단방향 설정 복습",
  "userId": 1,
  "createdAt": "2025-04-06T12:01:00",
  "updatedAt": "2025-04-06T12:01:00"
}
```

---

## 2. ERD 설계

현재 서비스의 Entity 설계는 다음과 같습니다.

```
[User]
- id (PK)
- username
- email (unique)
- password
- createdAt
- updatedAt

[Schedule]
- id (PK)
- title
- content
- user_id (FK → User.id)
- createdAt
- updatedAt

[관계]
User 1 : N Schedule (단방향)
```
