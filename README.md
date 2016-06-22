uuid-demo
=========

This is a demo of using a UUID as an API id and an integer ID internally. More discussion: https://compwron.github.io/2016/06/15/uuids-ids-and-primary-keys.html

To launch locally:
````
./gradlew bootRun
````

To run tests:
````
./gradlew test
````

To post against the launched API:
````
 echo '{"bar": "some value"}' | curl -H 'Content-Type: application/json' -d @- http://localhost:8080/foos
````

To use this codebase in Intellij you may need to enable "Use Annotation Processing" in order for Intellij to recognize the Lombok generated code.