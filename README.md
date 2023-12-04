# Daily Tasks API Document

The User Daily Task API provides an endpoint to mark a specified day for a user and add points if not already marked.

## API Endpoint

### Retrieves the monthly mark for a specific user

Marks the specified day for a user and adds points if not already marked.

#### Request

- **Method**: `POST`
- **Endpoint**: `/daily-task/mark-day`
- **Request Body**:
  - `userId` (String): User identifier.
  - `date` (String): Date to be marked.

#### Responses

- **200 OK**
  - Description: Day marked successfully
  - Response Body: JSON representing a success message
  
- **400 Bad Request**
  - Description: Invalid input
  - Response Body: JSON representing an error message

- **500 Internal Server Error**
  - Description: Internal Server Error
  - Response Body: JSON representing an error message

### Request

```
{
  "userId": "exampleUserId",
  "date": "2023-12-01"
}
```
### Response

```
{
  message: Day marked successfully
}
```
### Retrieves the monthly mark for a specific user

Retrieves the monthly mark for a specific user.

#### Request

- **Method**: `GET`
- **Endpoint**: `/daily-task/monthly-mark/{userId}`
- **Path Parameter**:
  - `userId` (Long): Unique identifier of the user

#### Responses

- **200 OK**
  - Description: Mark to receive monthly rewards.
  - Response Body: JSON representing the MonthlyReport object.
  
- **400 Bad Request**
  - Description: Invalid input.
  - Response Body: JSON representing an error message.

- **500 Internal Server Error**
  - Description: Internal Server Error.
  - Response Body: JSON representing an error message.

### Request

```
GET /daily-task/monthly-mark/1
```
### Response
```
{
  "month": "2023-12",
  "dailyTasks": [
    {
      "dateCurrent": "2023-12-25",
      "marked": false
    },
    {
      "dateCurrent": "2023-12-26",
      "marked": false
    },
    {
      "dateCurrent": "2023-12-27",
      "marked": false
    },
    {
      "dateCurrent": "2023-12-28",
      "marked": false
    },
    {
      "dateCurrent": "2023-12-29",
      "marked": true
    },
    {
      "dateCurrent": "2023-12-30",
      "marked": true
    },
    {
      "dateCurrent": "2023-12-31",
      "marked": false
    }
  ]
}
```
## Retrieves the monthly report for a specific user

Retrieves the monthly report for a specific user.

#### Request

- **Method**: `GET`
- **Endpoint**: `/user/history/{userId}`
- **Path Parameter**:
  - `userId` (Long): Unique identifier of the user.

#### Response

- **200 OK**
  - Description: User history retrieved successfully.
  - Response Body: JSON representing a list of DailyTask objects.
  
- **400 Bad Request**
  - Description: Invalid input.
  - Response Body: JSON representing an error message.

- **500 Internal Server Error**
  - Description: Internal Server Error.
  - Response Body: JSON representing an error message.

### Request

```
GET /daily-task/user/history/1
```

#### Response
```
[
  {
    "id": 1,
    "date": "2023-11-28",
    "userId": 1,
    "points": 1000
  },
  {
    "id": 3,
    "date": "2023-11-27",
    "userId": 1,
    "points": 1000
  },
  {
    "id": 5,
    "date": "2023-11-29",
    "userId": 1,
    "points": 1000
  }
]
```
