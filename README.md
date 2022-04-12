# Converter API
Simple Java Web-app and API, that is parsing National bank
website. There are some popular currencies. 

### Available currencies
- UAH
- CHF
- EUR
- DKK
- CAD
- USD
- RUB
- BYN
- CZK
- PLN

### Mappings

##### API
localhost:8080/api/count?fromCurrencyCode=<string>&toCurrencyCode=<string>&value=<float>

#### WEB APP
localhost:8080/ - mapping of main app page


### Running app
This app can be runned from any Java IDE or from command line with jar-file.
	1. git clone https://github.com/maximdziuba/custom-converter-api.git 
	2. cd <Project path>
	3. java -jar converter.jar
	4. go to localhost:8080 to test web app or to localhost:8080/api/count to test api
