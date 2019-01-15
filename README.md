# NasaNeoSearch
NasaNeoSearch is a program that uses Nasa Neo ws api to search the cloest and largest Neo.

# How to build?

git clone https://github.com/jinzhao1981/NasaNeoSearch.git

gradle buildJar

A jar file will be generated at build/libs/NeoSearchApp.jar

# How to run?
java -jar build/libs/NeoSearchApp.jar - the demo key will be used and the rate limit is 30 requests per hour
java -jar build/libs/NeoSearchApp.jar -k yourkey - you can provide an Nasa api key and the rate limit is 1000 requests per hour
java -jar build/libs/NeoSearchApp.jar -t - run with test data
