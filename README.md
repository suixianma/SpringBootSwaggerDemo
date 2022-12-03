# SpringBootSwaggerDemo
This project is to show several approches to integrate Swagger in Spring Boot project.<br>
Each folder is an standalone and runnable project.

## SpringdfoxAutoGeneratedSwagger
1) This project is showing how to utilize the Swagger annotation to generate Swagger UI automaticly.
2) The advantage for this approach is no need predefinied OpenAPI specificattion yaml or json.
3) It is suitable for projects which has simple request and response structure.
4) Access http://localhost:8088/swagger-ui.html

## SpringdocStaticSwaggerJson
1) This project is showing how to load your existing OpenAPI specification yaml or json file.
2) The advantage is easy to setup, you only need to configure the dependency library, configuration and predefinied OpenAPI speficication。
3) Please be aware of the Swagger-ui.html could not work at specific Browser. <br>
   On my local machine, it showed me a blank screen in Chrome, but worked fine in Safari.
4) Access http://localhost:8088/swagger-ui/index.html

## SpringfoxStaticSwaggerJson
1) This project is also loading a static OpenAPI specification yaml or json file.
2) It is a little bit complex than Project 2, but I didn't notice Browser compatibality issue.
3) Access http://localhost:8088/swagger-ui.html
