# document-analyzer

Open API yaml:

https://github.com/jerwine/document-analyzer/blob/main/spec/openapi.yaml

Schemas scripts:

https://github.com/jerwine/document-analyzer/blob/main/src/main/resources/db/migration/V1_1_1__DOCUMENTS_SCHEMA.sql

https://github.com/jerwine/document-analyzer/blob/main/src/main/resources/db/migration/V1_1_2__DOCUMENTS_SCHEMA.sql

https://github.com/jerwine/document-analyzer/blob/main/src/main/resources/db/migration/V1_1_3__DOCUMENTS_SCHEMA.sql


http://localhost:8080/h2-console

![img.png](img/img.png)
![img_11.png](img/img_11.png)

http://localhost:8080/users

![img_1.png](img/img_1.png)

http://localhost:8080/users/1

![img_2.png](img/img_2.png)

http://localhost:8080/users/inactive

![img.png](img/img_3.png)

http://localhost:8080/users/inactive?startDate=2024-12-03

![img.png](img/img_4.png)

http://localhost:8080/users/inactive?startDate=2024-11-11&endDate=2025-12-12

![img.png](img/img_5.png)

http://localhost:8080/users/inactive?endDate=2025-12-12

![img.png](img/img_6.png)

http://localhost:8080/documents

![img_7.png](img/img_7.png)

http://localhost:8080/documents/1

![img_8.png](img/img_8.png)

http://localhost:8080/documents/1/words-frequency

![img_12.png](img/img_12.png)

http://localhost:8080/documents/1/words-frequency?sort=desc&size=3

![img_13.png](img/img_13.png)

http://localhost:8080/documents/2/longest-word

![img_14.png](img/img_14.png)