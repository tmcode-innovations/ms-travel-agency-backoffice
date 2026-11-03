# Travel Agency Backoffice

A **multi-tenant SaaS back-office platform for tourism and travel agencies**. It runs a whole agency's operations in one system:

- **Sales:** tour programs, quotations and itineraries (CRM).
- **Bookings:** reservations with suppliers such as hotels, restaurants, tour operators, glamping sites, transport and guides.
- **Operations:** service orders and transfer control.
- **Finance:** invoices, credit and debit notes, commissions, collections, payments and exchange rates.
- **Administration:** users, roles and permissions, employees, and supplier and customer catalogs.

Agency staff use it day to day to take a trip from first quote to booking, delivery and billing. The platform also provides real-time notifications, PDF and Excel documents and reports, and analytics dashboards. The finance module is built for Peruvian rules (IGV tax, detractions, PEN and USD).

## Repositories

| Repository | Description |
|---|---|
| `ui-travel-agency-backoffice` | React single-page app (frontend) |
| `ms-travel-agency-backoffice` | Spring Boot REST API (backend) |

## Features

| Module | Description |
|---|---|
| **Security** | Users, profiles, apps, objects, permissions, password change and password history |
| **Core / Base** | Companies, organizations (tenants), countries, regions, provinces, districts, areas, notifications and change requests |
| **CRM** | Programs, tours, quotations and itineraries |
| **Booking** | Groups, passengers and bookings by supplier type (lodging, restaurant, operator, glamping, institution, lodge, rooms) |
| **Account (Finance)** | Invoices, proformas, credit and debit notes, commissions, collections, payments, income and expense vouchers, exchange rates, cost sheets, tickets and expense guides |
| **Operation** | Service orders, service requests and transfer control |
| **Maintenance** | Catalogs: customers, banks, IGV, detractions and supplier types (airline, transport, guide, etc.) |
| **HR** | Employees and positions |
| **Analytics / Dashboard** | Reports, statistics and charts |

## How it works

1. The user logs in and receives a **JWT** that carries their tenant, apps and permissions.
2. The frontend builds its menu from those permissions and calls the REST API through Axios.
3. The backend stores data in **MongoDB**, filtered by tenant.
4. Documents and reports are generated as **PDF and Excel** with JasperReports and Apache POI.
5. Real-time notifications are sent over **WebSocket (STOMP)**.

---

## Frontend: `ui-travel-agency-backoffice`

### Tech stack

| Category | Technology |
|---|---|
| Framework | React 18, Create React App (`react-scripts 5`) |
| Template | CoreUI Free React Admin Template v5 |
| UI components | CoreUI React, Material UI 5 (`@mui/material`, `@mui/x-date-pickers`), Bootstrap 5 |
| Tables | material-react-table v2 (server-side pagination and filtering) |
| State management | Redux 4 + redux-thunk |
| Routing | React Router v6 (`HashRouter`, lazy-loaded routes) |
| HTTP client | Axios (shared instance and interceptors) |
| Real time | SockJS + STOMP (`@stomp/stompjs`) |
| Charts | Chart.js (`@coreui/react-chartjs`) |
| Dates | moment, moment-timezone, date-fns |
| UX | SweetAlert2, custom toasts, global spinner |
| Files | file-saver, file-upload components |
| Code quality | ESLint, Prettier, Jest, Testing Library, Sass |

### Project structure

```
src/
├── views/module<name>/<entity>/   # Pages per business module (Entity.js, EntityAdd.js, EntityEdit.js)
├── service/<module>/<Entity>.js   # API calls per entity
├── service/ApiConfig.js           # Axios instance (base URL)
├── service/ApiInstance.js         # HOC with request/response interceptors
├── store/                         # Redux actions and reducers
├── components/                    # Reusable components (Spinner, Toast, Notification, Header...)
├── layout/                        # Main layout (sidebar, header, content, footer)
├── websocket/                     # WebSocket configuration
├── routes.js                      # Application routes
└── _nav.js                        # Sidebar menu (each item linked to a permission code)
```

### Notes

- **Authentication:** an Axios request interceptor adds the `Authorization: Bearer <jwt>` header. The token is stored in `localStorage`.
- **Error handling:** a response interceptor handles errors in one place. It shows dialogs for server errors, timeouts and expired sessions, and returns validation errors (HTTP 422) to the form.
- **Permissions:** each menu item in `_nav.js` has a `permission` code that is checked against the permissions in the user's token.

### Getting started

**Requirements:** Node.js and npm (or Yarn).

```bash
cd ui-travel-agency-backoffice
npm install
npm start        # http://localhost:3000
```

| Script | Description |
|---|---|
| `npm start` | Start the development server |
| `npm run build` | Build for production |
| `npm test` | Run tests |
| `npm run lint` | Run ESLint |

The API and WebSocket URLs are set in `src/service/ApiConfig.js` and `src/websocket/WebSocketConfig.js`. Both default to `http://localhost:8084`.

---

## Backend: `ms-travel-agency-backoffice`

### Tech stack

| Category | Technology |
|---|---|
| Language and framework | Java 21, Spring Boot 3.1.4 |
| Build | Gradle |
| Database | MongoDB (Spring Data MongoDB) with replica set and transactions |
| Security | Spring Security (stateless), JWT (jjwt 0.11.5) |
| API documentation | springdoc-openapi / Swagger UI |
| Validation | Bean Validation / Hibernate Validator, custom validators |
| Real time | Spring WebSocket + STOMP |
| Async and events | `@Async` with a custom thread pool, Spring Application Events |
| Scheduling | `@Scheduled` cron jobs |
| Reports | JasperReports 6.21 (`.jrxml` templates) |
| Excel | Apache POI 5.2.5 |
| Other | Lombok, Spring Boot Actuator, Spring AOP, Logback |

### Architecture

The backend follows a **hexagonal (ports and adapters) architecture**:

```
com.saas.tourismagency.admin.backoffice
├── domain/        # Domain models (*Domain) and enums per module
├── usecase/       # *UseCase interfaces and impl/*UseCaseImpl (business logic)
├── adapter/
│   ├── entrypoints/restapi/v1/  # REST controllers and request/response DTOs
│   ├── dataproviders/jpa/data/  # MongoDB documents, repositories and converters (Entity ↔ Domain)
│   ├── events/                  # Cross-module events and listeners (CRM ↔ Booking)
│   ├── exportdata/              # PDF/Excel export with JasperReports
│   ├── importdata/              # Excel import with Apache POI
│   ├── notification/            # WebSocket notifications
│   └── scheduled/               # Scheduled jobs
└── core/          # Configuration, security filters, user context and utilities
```

The shared package `dev.mm.javaee.core` contains the common building blocks: the `ApiResponse<T>` response wrapper (`{status, message, data}`), pagination (`PageDTO`), generic CRUD repositories, converters and HTTP exceptions.

### Notes

- **Multi-tenancy:** every document has a `tenant` field, which is read from the authenticated user's JWT. All tenants share one database.
- **Security:** stateless JWT authentication. The token carries the user id, username, email, full name, tenant, apps and permissions, and is valid for 12 hours. Issued tokens are stored so they can be revoked.
- **REST conventions:** every resource uses the same endpoint pattern:

  | Endpoint | Description |
  |---|---|
  | `POST /create` | Create |
  | `PUT /edit/{id}` | Update |
  | `DELETE /delete/{id}` | Delete |
  | `GET /find-by/{id}` | Find by id |
  | `GET /find-all` | List all |
  | `POST /search-paginate` | Paginated search |
  | `POST /search-filter-paginate` | Paginated search with filters |
  | `/{id}/detail/...` | Manage detail lines |
  | `/{id}/export-.../pdf` | Export document as PDF |

- **Real-time notifications:** the STOMP endpoint is `/ws`. Private notifications go to `/user/queue/notifications`.

### Getting started

**Requirements:**
- Java 21
- Gradle
- MongoDB running as a **replica set** (`rs0`), which MongoDB transactions require

```bash
cd ms-travel-agency-backoffice
gradle bootRun
```

> The repository includes the `gradlew` scripts but not the `gradle/wrapper/` folder. Run `gradle wrapper` once to generate it if you want to use `./gradlew`.

### Configuration

The configuration is in `src/main/resources/application.yml`:

| Property | Default |
|---|---|
| Server port | `8084` |
| Context path | `/ms-travel-agency-backoffice/api/v1` |
| MongoDB | `localhost:27017`, database `db_ms_travel_agency_backoffice`, replica set `rs0` |
| JWT validity | `43200` seconds (12 hours) |
| Max upload size | `1024MB` |
| Log file | `/var/log/saas-application/application.log` |

Set the MongoDB credentials, the JWT secret and the API key for each environment. Don't commit real values.

### API documentation

With the application running:

- Swagger UI: `http://localhost:8084/ms-travel-agency-backoffice/api/v1/backoffice-documentation`
- OpenAPI spec: `http://localhost:8084/ms-travel-agency-backoffice/api/v1/backoffice-api-docs`
- Health check: `http://localhost:8084/ms-travel-agency-backoffice/api/v1/actuator/health`
