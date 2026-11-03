# Summary: Travel Agency Backoffice

## 1. Project overview

**Travel Agency Backoffice** is a **multi-tenant SaaS back-office platform for tourism and travel agencies**. It runs a whole agency's operations in one system:
- **Sales:** tour programs, quotations and itineraries (CRM).
- **Bookings:** reservations with suppliers such as hotels, restaurants, tour operators, glamping sites, transport and guides.
- **Operations:** service orders and transfer control.
- **Finance:** invoices, credit and debit notes, commissions, collections, payments and exchange rates.
- **Administration:** users, roles and permissions, employees, and supplier and customer catalogs.

Agency staff use it day to day to take a trip from first quote to booking, delivery and billing. It also gives them real-time notifications, PDF and Excel documents and reports, and analytics dashboards. The finance side is built for Peruvian rules (IGV tax, detractions, PEN and USD).

The project is split into two repositories:

| Repo | Role |
|---|---|
| `ui-travel-agency-backoffice` | React single-page app (frontend) |
| `ms-travel-agency-backoffice` | Java + Spring Boot microservice / REST API (backend) |

**Functional modules** (the same in frontend and backend):
- **Security**: users, profiles, apps, objects, permissions, password change and password history.
- **Core / Base**: companies, organizations (tenants), countries, regions, provinces, districts, areas, notifications and change requests.
- **CRM**: programs, tours, quotations and itineraries.
- **Booking**: groups, passengers and bookings by supplier type (lodging, restaurant, operator, glamping, institution, lodge, rooms).
- **Account (finance)**: invoices, proformas, credit and debit notes, commissions, collections, payments, income and expense vouchers, exchange rates, cost sheets, tickets and expense guides.
- **Operation**: service orders, service requests and transfer control.
- **Maintenance**: catalogs (customers, banks, IGV, detractions and about 15 supplier types: airline, transport, guide, etc.).
- **HR**: employees and positions.
- **Analytics / Statistics / Dashboard**: reports and charts.

**How it works:** the user logs in and gets a JWT that carries their tenant and permissions. The UI builds its menu from those permissions and calls the REST API through Axios. The backend stores data in MongoDB, creates PDF and Excel files with JasperReports, and sends real-time notifications over WebSocket (STOMP).

**Size:** about 2,800 Java files in the backend and about 490 JS files in the frontend.

---

## 2. Frontend: `ui-travel-agency-backoffice`

### Stack
| Category | Technology |
|---|---|
| Base | **React 18**, set up with **Create React App** (`react-scripts 5`) |
| Template | **CoreUI Free React Admin Template v5** (sidebar, header, layout, light/dark themes) |
| UI components | **CoreUI React** + **Material UI 5** (`@mui/material`, `@mui/x-date-pickers`) + **Bootstrap 5** |
| Tables | **material-react-table v2** (server-side pagination and filters, Spanish labels through `MRT_Localization_ES`) |
| Global state | **Redux 4 + redux-thunk** (`legacy_createStore`, `combineReducers`, Redux DevTools) |
| Routing | **React Router v6** with `HashRouter` and **lazy loading** (`React.lazy` + `Suspense`), about 326 routes |
| HTTP | **Axios** with one shared instance (`ApiConfig.js`) and **interceptors** (`ApiInstance.js`) |
| Real time | **SockJS + STOMP** (`@stomp/stompjs`) |
| Dates | moment, moment-timezone, date-fns |
| Charts | Chart.js (`@coreui/react-chartjs`) |
| UX | SweetAlert2 (confirmations), custom toasts, global spinner, `use-sound` |
| Files | `file-saver` for PDF and Excel downloads (used in about 52 files), several file-upload components |
| Icons | CoreUI Icons, FontAwesome, MUI Icons, Bootstrap Icons |
| Code quality | ESLint + Prettier, Jest + Testing Library (very few tests), Sass |

### Architecture and patterns worth mentioning
- **Organized by business module**: `src/views/module<x>/<entity>/` and `src/service/<module>/<Entity>.js`, with a service layer kept apart from the views.
- **Repeated CRUD pattern**: every entity has three views, `Entity.js` (table list), `EntityAdd.js` and `EntityEdit.js`.
- **`checkRequests(App)` HOC** (`src/service/ApiInstance.js`): a *Higher-Order Component* that registers the Axios interceptors.
  - On each **request** it adds `Authorization: Bearer <jwt>` (read from `localStorage`) and turns on the global spinner.
  - On each **response** it turns the spinner off and handles errors in one place: it shows modals for 500, timeout (408) and expired session, and logs the user out. A 422 (validation error) goes back to the view so the form can show it. It also turns `Blob` error responses into JSON.
- **Permission-based menu**: each item in `_nav.js` has a `permission: 'CODE_PERM_...'` that is checked against the permissions in the JWT. This is role-based access control (RBAC) in the UI.
- **Redux slices**: `auth`, `loading`, `sidebar`, `sidebarUnfoldable`, `asidebar` and `progress` (file-upload progress).
- **Reusable components**: Spinner, CustomToast, CustomNotification, Header, InputText and RealTimeComponent.

### Likely interview questions and weak spots (good to know)
- **JWT stored in `localStorage`**: it can be stolen through XSS. The alternative is an httpOnly cookie.
- **Hardcoded URLs** in `ApiConfig.js` and `WebSocketConfig.js` (the `process.env.REACT_APP_API_URL` line is commented out). Per-environment variables would fix this.
- **CRA is deprecated**: a current project would move to **Vite**.
- **"Classic" Redux**: today you would use **Redux Toolkit**, plus **React Query / TanStack Query** for server state.
- **Very large components**: `InvoiceAdd.js` and `InvoiceEdit.js` are about 1,900 lines each. They could be split into hooks and subcomponents.
- **Three UI libraries mixed together** (CoreUI, MUI and Bootstrap), which makes the bundle bigger.
- `yup` is listed in `package.json` but never used. Validation is either manual or done by the backend.
- The services repeat the same `.then/.catch` code. A generic CRUD service factory would remove the duplication.

---

## 3. Backend: `ms-travel-agency-backoffice`

### Stack
| Category | Technology |
|---|---|
| Language and framework | **Java 21**, **Spring Boot 3.1.4** |
| Build | **Gradle** |
| Database | **MongoDB** (Spring Data MongoDB) with **replica set `rs0`**, which **transactions** need (`MongoTransactionManager`, `@Transactional`) |
| Security | Stateless **Spring Security** + **JWT** (jjwt 0.11.5) + API Key filter (commented out) |
| API docs | **springdoc-openapi / Swagger UI** (`/backoffice-documentation`) |
| Validation | Bean Validation / Hibernate Validator, plus custom validators (`@Password`, `@PasswordRegister`) |
| Real time | **Spring WebSocket + STOMP** (`SimpMessagingTemplate.convertAndSendToUser` → `/queue/notifications`) |
| Async | `@Async` with its own `ThreadPoolTaskExecutor` (`SpringAsyncConfig`) |
| Events | **Spring Application Events** (`ApplicationListener`) |
| Scheduling | `@Scheduled` cron jobs (`SchedulerDateProcess`, currently turned off) |
| Reports | **JasperReports 6.21** (about 230 `.jrxml` templates for invoices, booking, analytics and operations; Arial fonts embedded) |
| Excel | **Apache POI 5.2.5** for import and export (for example nationalities and security objects) |
| Other | Lombok, Actuator (`/actuator/health`), AOP, Logback |

**Configuration:** port 8084, context path `/ms-travel-agency-backoffice/api/v1`, multipart uploads up to 1 GB.

### Architecture: hexagonal / Clean Architecture (ports and adapters)
```
com.saas.tourismagency.admin.backoffice
├── domain/        → domain models (*Domain) per module + enums
├── usecase/       → *UseCase interfaces + impl/*UseCaseImpl (business logic)
├── adapter/
│   ├── entrypoints/restapi/v1/  → Controllers + DTOs (*CreateRequest, *UpdateRequest, *Response, *SearchRequest)
│   ├── entrypoints/soap|streams|webhook  → placeholders (empty)
│   ├── dataproviders/jpa/data/  → entity (@Document), repo, springdata (MongoRepository), converter (Entity↔Domain), defaultfield
│   ├── events/        → cross-module events and listeners (CRM ↔ Booking)
│   ├── exportdata/    → JasperReports (PDF/Excel)
│   ├── importdata/    → Excel through POI
│   ├── notification/  → WebSocket
│   └── scheduled/     → cron jobs
└── core/  → configuration, filters (JWT, API Key, CORS), context (UserResolver, JWTAuthentication), utils, startup
```
There is also an **in-house library** in `dev.mm.javaee.core`:
- `ApiResponse<T>`: standard response wrapper `{status, message, data}`.
- `PageDTO`: pagination.
- Generic repositories (`DefaultJPARepository<Entity, ID, Repo>`, `CRUDBaseRepository`), a `Converter` interface and `SetFieldDefaultEntity` (audit and default fields).
- A hierarchy of HTTP exceptions: `NotFound`, `BadRequest`, `Conflict`, `Unprocessable`, etc.

### Key points for the interview
- **Multi-tenancy**: every document has a `tenant` field, taken from the JWT through `UserResolver.resolveUser().getTenant()`. This is **discriminator-based multi-tenancy** (one shared database filtered by tenant), not one database per tenant.
- **Security**:
  - Stateless `SecurityFilterChain` with CSRF turned off.
  - `JwtTokenVerifierFilter` is added before `UsernamePasswordAuthenticationFilter`.
  - The JWT carries the claims id, username, email, fullName, tenant, apps and permissions. It lasts 12 hours.
  - A custom `AuthenticationEntryPoint` handles 401/403 responses.
  - Tokens are saved (`OAuthAccessTokenEntity`) and checked with `tokensAvailable()`, so they can be revoked.
  - Password history is stored.
- **Same endpoint pattern everywhere**: `/create`, `/edit/{id}`, `/delete/{id}`, `/find-by/{id}`, `/find-all`, `/search-paginate`, `/search-filter-paginate`, endpoints for detail lines (`/{id}/detail/add`, etc.) and `/{id}/export-.../pdf`.
- **Modules decoupled through events**: for example, `PassengerCrmCreateEvent` → `PassengerCreateByCrmListener` (`@Async`) copies the passengers of a CRM quotation into the matching Booking group.
- **Inheritance in Mongo**: several entities share one collection and are told apart with `@TypeAlias`. For example, `ProformaInvoiceEntity extends ProformaEntity` in `account_proformas`.
- **Entity ↔ Domain ↔ DTO mapping** is done with hand-written converters.

### Likely questions and improvements (good to know)
- **Secrets are stored in `application.yml`** (Mongo password, JWT secret, API key). They should live in environment variables or in Vault / AWS Secrets Manager. This matters: don't push that file as-is to a public repository.
- **Security rules are listed one by one**: about 150 lines of `requestMatchers(...).authenticated()`. `anyRequest().authenticated()` plus `@PreAuthorize` per permission would replace them.
- **Field injection with `@Autowired`**: constructor injection (`@RequiredArgsConstructor`) is the recommended practice.
- **Field-by-field manual mapping** in listeners and converters: **MapStruct** would handle this.
- **Almost no tests**: only `BackOfficeApplicationTests`. Unit tests for the use cases and integration tests with Testcontainers for Mongo would help.
- **I didn't find a `@RestControllerAdvice`** in the core library. That came from a quick search only, so check it before you bring it up.
- **Bug risk in the `@Async` listener**: it calls `UserResolver` on a different thread, and the `SecurityContext` doesn't reach that thread unless `DelegatingSecurityContextAsyncTaskExecutor` is set up.
- **Spring Boot 3.1 is out of support**: upgrading to 3.3 or later is advisable.
- **It's called a "microservice" but works as a modular monolith.** If asked, you can explain how you would split it (finance, booking, CRM) using the events it already has.

---

**One-line pitch:** *"A multi-tenant SaaS back office for travel agencies: a React 18 SPA built with CoreUI/MUI and Redux, backed by a Spring Boot 3 / Java 21 REST API with a hexagonal architecture, MongoDB with transactions, JWT security with role-based permissions, real-time notifications over WebSocket/STOMP, and PDF/Excel reports with JasperReports and Apache POI."*
