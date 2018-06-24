# Restful Web Services
Simple Rest Service using SpringBoot 2.0

## Social Media Application Resource Mapping 

### User -> Posts
- Retrieve all Users        - GET      /users
- Create a User             - POST     /users
- Retrieve one User         - GET      /users/{id}  -> /users/3
- Delete a User             - DELETE   /users/{id}  -> /users/3 

####Internationalization

##### Configuration

- LocalResolver
 - Default Locale - Locale.US
- ResourceBundleMessageSource

##### Usage
- Autowire MessageSource
- @RequestHeader(value = "Accept-Language" , required = false) Locale locale
- messageSource.getMessage("helloWorls.message" , null , locale)