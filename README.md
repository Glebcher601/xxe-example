Simple web application which demonstrates XXE vulnerabitlity.

Start web app:

    $ mvn jetty:run

Retrieve all books:

    $ curl http://localhost:8080/rest/model

Retrieve model by id:

    $ curl http://localhost:8080/rest/model/1

Delete model by id:

    $ curl -v -X DELETE http://localhost:8080/rest/model/4

Reflect:

    $ curl -X POST -v -H "Content-Type:application/xml" --upload-file xml/xxe/model-xxe.xml http://localhost:8080/rest/model/reflect 

Directory listing

    $ curl -X POST -v -H "Content-Type:application/xml" --upload-file xml/xxe/model-xxe-dirlist.xml http://localhost:8080/rest/model/reflect 

Outband

    $ curl -X POST -v -H "Content-Type:application/xml" --upload-file xml/oob/model-outband-xxe.xml http://localhost:8080/rest/model/reflect

Parametrized outband

    $ curl -X POST -v -H "Content-Type:application/xml" --upload-file xml/oob-parametrized/model-outband-xxe2.xml http://localhost:8080/rest/model/reflect

Exponential attack

    $ curl -X POST -v -H "Content-Type:application/xml" --upload-file xml/billion_laughs/xxe-bl_exp.xml http://localhost:8080/rest/model/reflect

Huge entity attack

    $ curl -X POST -v -H "Content-Type:application/xml" --upload-file xml/billion_laughs/xxe-bl_big.xml http://localhost:8080/rest/model/reflect

