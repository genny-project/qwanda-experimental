# Requirements 
- Java 11 
- maven 3.6.3_1

# qwanda-experimental project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `qwanda-experimental-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/qwanda-experimental-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/qwanda-experimental-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image-guide.# qwanda-experimental

# Calling the search 

If you want to test the functionality running the search send this json object after the application starts and it is running. 

```
curl -X POST \
  http://localhost:8080/search/baseentitys/search \
  -H 'content-type: application/json' \
  -d '{
   "baseEntityAttributes":[
      {
         "baseEntityCode":"SBE_SEARCHBAR_9A27028F-8D43-4689-8347-4E6C5F944D41",
         "attributeCode":"SCH_TITLE",
         "attributeName":"Title",
         "readonly":false,
         "index":0,
         "pk":{

         },
         "valueString":"Internmatch Search",
         "weight":5.0,
         "inferred":false,
         "privacyFlag":false
      },
      {
         "baseEntityCode":"SBE_SEARCHBAR_9A27028F-8D43-4689-8347-4E6C5F944D41",
         "attributeCode":"PRI_NAME",
         "attributeName":"LIKE",
         "readonly":false,
         "index":0,
         "pk":{

         },
         "valueString":"%hosting%",
         "weight":1.0,
         "inferred":false,
         "privacyFlag":false
      },
      {
         "baseEntityCode":"SBE_SEARCHBAR_9A27028F-8D43-4689-8347-4E6C5F944D41",
         "attributeCode":"COL_PRI_ADDRESS_FULL",
         "attributeName":"Address",
         "readonly":false,
         "index":0,
         "pk":{

         },
         "weight":5.0,
         "inferred":false,
         "privacyFlag":false
      },
      {
         "baseEntityCode":"SBE_SEARCHBAR_9A27028F-8D43-4689-8347-4E6C5F944D41",
         "attributeCode":"SCH_PAGE_SIZE",
         "attributeName":"PageSize",
         "readonly":false,
         "index":0,
         "pk":{

         },
         "valueInteger":20,
         "weight":3.0,
         "inferred":false,
         "privacyFlag":false
      },
      {
         "baseEntityCode":"SBE_SEARCHBAR_9A27028F-8D43-4689-8347-4E6C5F944D41",
         "attributeCode":"SRT_PRI_NAME",
         "attributeName":"Name",
         "readonly":false,
         "index":0,
         "pk":{

         },
         "valueString":"ASC",
         "weight":1.0,
         "inferred":false,
         "privacyFlag":false
      },
      {
         "baseEntityCode":"SBE_SEARCHBAR_9A27028F-8D43-4689-8347-4E6C5F944D41",
         "attributeCode":"COL_PRI_LANDLINE",
         "attributeName":"Phone",
         "readonly":false,
         "index":0,
         "pk":{

         },
         "weight":3.0,
         "inferred":false,
         "privacyFlag":false
      },
      {
         "baseEntityCode":"SBE_SEARCHBAR_9A27028F-8D43-4689-8347-4E6C5F944D41",
         "attributeCode":"COL_PRI_NAME",
         "attributeName":"Name",
         "readonly":false,
         "index":0,
         "pk":{

         },
         "weight":2.0,
         "inferred":false,
         "privacyFlag":false
      },
      {
         "baseEntityCode":"SBE_SEARCHBAR_9A27028F-8D43-4689-8347-4E6C5F944D41",
         "attributeCode":"COL_PRI_EMAIL",
         "attributeName":"Email",
         "readonly":false,
         "index":0,
         "pk":{

         },
         "weight":4.0,
         "inferred":false,
         "privacyFlag":false
      },
      {
         "baseEntityCode":"SBE_SEARCHBAR_9A27028F-8D43-4689-8347-4E6C5F944D41",
         "attributeCode":"SCH_PAGE_START",
         "attributeName":"PageStart",
         "readonly":false,
         "index":0,
         "pk":{

         },
         "valueInteger":0,
         "weight":3.0,
         "inferred":false,
         "privacyFlag":false
      },
      {
         "baseEntityCode":"SBE_SEARCHBAR_9A27028F-8D43-4689-8347-4E6C5F944D41",
         "attributeCode":"COL_PRI_EVENT_VIEW",
         "attributeName":"View",
         "readonly":false,
         "index":0,
         "pk":{

         },

         "weight":1.0,
         "inferred":false,
         "privacyFlag":false
      }
   ],
   "links":[

   ],
   "questions":[

   ],
   "answers":[

   ],
   "code":"SBE_SEARCHBAR_9A27028F-8D43-4689-8347-4E6C5F944D41",
   "index":0,
   "name":"Internmatch Search",
   "realm":"internmatch"
}'
```