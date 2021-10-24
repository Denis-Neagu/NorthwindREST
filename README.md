# NorthwindREST

## Goals
* Build a REST API for the Northwind Database.
* Test the API using Mockito
* Demonstrate understanding of HTTP, Spring, GET requests and refer to SOLID for the project's structure


### Endpoints
/customers:
get:
summary: "GET customers"
operationId: "getAllCustomers"
parameters:
- name: "contactName"
in: "query"
schema:
type: "string"
responses:
"200":
description: "OK"
---
/customers/city:
get:
summary: "GET customers/city"
operationId: "getCustomerByCity"
parameters:
- name: "cityName"
in: "query"
required: true
schema:
type: "string"
responses:
"200":
description: "OK"
---
/customers/company:
get:
summary: "GET customers/company"
operationId: "getCustomersByCompany"
parameters:
- name: "companyName"
in: "query"
required: true
schema:
type: "string"
responses:
"200":
description: "OK"
---
/customers/title:
get:
summary: "GET customers/title"
operationId: "getCustomersByContactTitle"
parameters:
- name: "contactTitle"
in: "query"
required: true
schema:
type: "string"
responses:
"200":
description: "OK"
---
/customers/{id}:
get:
summary: "GET customers/{id}"
operationId: "getCustomerById"
parameters:
- name: "id"
in: "path"
required: true
schema:
type: "string"
responses:
"200":
description: "OK"
---
/orders:
get:
summary: "GET orders"
operationId: "getOrdersByCustomer"
parameters:
- name: "customerId"
in: "query"
required: true
schema:
type: "string"
responses:
"200":
description: "OK"
---
/orders/customer:
get:
summary: "GET orders/customer"
operationId: "getCustomerByOrder"
parameters:
- name: "orderId"
in: "query"
required: true
schema:
type: "number"
format: "int32"
responses:
"200":
description: "OK"
---
/orders/date:
get:
summary: "GET orders/date"
operationId: "getOrdersByDate"
parameters:
- name: "orderDate"
in: "query"
required: true
schema:
type: "string"
responses:
"200":
description: "OK"
---
/orders/{id}:
get:
summary: "GET orders/{id}"
operationId: "getOrdersById"
parameters:
- name: "id"
in: "path"
required: true
schema:
type: "number"
format: "int32"
responses:
"200":
description: "OK"
---
/products/supplier:
get:
summary: "GET products/supplier"
operationId: "getProductsBySupplierId"
parameters:
- name: "supplierId"
in: "query"
required: true
schema:
type: "number"
format: "int32"
responses:
"200":
description: "OK"
---
/products/{id}:
get:
summary: "GET products/{id}"
operationId: "getProductsById"
parameters:
- name: "id"
in: "path"
required: true
schema:
type: "number"
format: "int32"
responses:
"200":
description: "OK"
