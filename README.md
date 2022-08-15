[![Maven Central](https://img.shields.io/maven-central/v/cz.krystofcejchan/lite_weather_library.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22cz.krystofcejchan%22%20AND%20a:%22lite_weather_library%22)
# Lite-Java-Weather-Library
A Java Library for better and easier worldwide Weather data including current condition, forecast, location info etc.

- [Lite-Java-Weather-Library](#lite-java-weather-library)
- [Install & requirements](#install--requirements)
  - [Apache Maven](#apache-maven)
  - [Gradle Groovy DSL](#gradle-groovy-dsl)
- [How to use](#how-to-use)
  - [All in one](#all-in-one)
    - [Description](#description)
    - [Example](#example)
    - [Constructors](#constructors)
  - [Current Condition](#current-condition)
    - [Description](#description-1)
    - [Example](#example-1)
  - [Forecast](#forecast)
    - [Description](#description-2)
      - [WeatherForecast constructors:](#weatherforecast-constructors)
    - [Examples](#examples)
  - [Nearest Area](#nearest-area)
    - [Description](#description-3)
    - [Examples](#examples-1)
  - [Request](#request)
    - [Description](#description-4)
    - [Example](#example-2)

# Install & requirements
[Maven central](https://search.maven.org/artifact/cz.krystofcejchan/lite_weather_library/1.0.0/jar)


**Requirements** Java: 17,
Internet connection
## Apache Maven
```xml
<dependency>
  <groupId>cz.krystofcejchan</groupId>
  <artifactId>lite_weather_library</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Gradle Groovy DSL
```groovy
implementation 'cz.krystofcejchan:lite_weather_library:1.0.0'
```
# How to use

## All in one
### Description
Create an object of `WeatherObject` to access all the data! From this object you can access all the functionality of other classes(`CurrentCondition`, `WeatherForecase` etc.). This class also contains JSON file from which the weather data are gathered from.

### Example
```java
WeatherObject weatherObject = new WeatherObject("Mexico City", DAY.ALL, TIME.AM_6, TIME.AM_9);
System.out.println(weatherObject.getWeatherForecast().getForecastFor(DAY.TOMORROW, TIME.AM_9));
//System.out.println(weatherObject.getJsonAsText());
System.out.println(weatherObject.getCurrentCondition().getVisibility());
```
Output:
```css
---ForecastAtHour---
day=TOMORROW
time=AM_9
dewPointC=12
dewPointF=53
feelsLikeC=16
feelsLikeF=60
heatIndexC=16
heatIndexF=60
windChillC=16
windChillF=60
windGustKmph=1
windGustMiles=1
chanceOfFog=0
chanceOfFrost=0
chanceOfHighTemperature=0
chanceOfOvercast=45
chanceOfRain=0
chanceOfRemdry=80
chanceOfSnow=0
chanceOfSunshine=73
chanceOfThunder=0
chanceOfWindy=0
cloudCover=31
humidity=77
precipInches=0.0
precipMM=0.0
pressure=1018
pressureInches=30
temperatureC=16
temperatureF=60
timeInHundreds=900
uvIndex=5
visibility=10
visibilityMiles=6
weatherCode=116
weatherDescription='Partly cloudy'
windDir16Point='N'
windDirDegree=1
windSpeedKmph=1
windSpeedMiles=1
10
```

### Constructors
![Constructors](http://kys.hys.cz/jo/weather_forecast_constructors.png)

## Current Condition

### Description
Create an object of `CurrentCondition` to access all the data for current condition in provided location(set location in contrustor)
### Example
```java
CurrentCondition currentCondition = new CurrentCondition("Dublin");
System.out.println(currentCondition);
```
Output:
```css
--CurrentCondition--
feelsLikeC=25
feelsLikeF=78
cloudCover=25
humidity=57
localObsDateTime=2022-08-13T16:41
observationTime=15:41
precipInches=0.0
precipMM=0.0
pressure=1014
pressureInches=30
temp_C=24
temp_F=75
uvIndex=6
visibility=10
visibilityMiles=6
weatherCode=116
weatherDescription='Partly cloudy'
windDir16Point='ENE'
winDirDegree=60
windSpeedKmph=17
windSpeedMiles=11
---
```

## Forecast
### Description
Create an object of `WeatherForecast` to access all the data for weather forecast for ***Today, Tomorrow*** and ***the day after Tomorrow***. Each day can be separated into hours ***00:00***, ***3am***, ***6am***, ***9am***, ***12pm***, ***3pm***, ***6pm***, ***9pm***
#### WeatherForecast constructors:
![Constructors](http://kys.hys.cz/jo/weather_forecast_constructors.png)
### Examples
```java
//create a WeatherForecast object for City of Denver, for all days and times  
WeatherForecast weatherForecast = new WeatherForecast("Denver", DAY.ALL, TIME.ALL);
//search for forecast for the day after tomorrow at 6 am  
String toString=weatherForecast.getForecastFor(DAY.AFTER_TOMORROW,TIME.AM_6).toString();
//get average temperature for tomorrow in Celsius  
int averageTemperatureCForTomorrow =weatherForecast.getTomorrow().getAverageTemperatureC();
//get temperature for today at 3 pm in Fahrenheit  
int temperatureFTodayAt3pm = weatherForecast.getForecastFor(DAY.TODAY,TIME.PM_3).getTemperatureF();

System.out.println(toString + "\n\n" + "average temperature for tomorrow: " +
        averageTemperatureCForTomorrow + "\n" + "temperature for today at 3 pm: " + temperatureFTodayAt3pm); 
```
Output:
```css
---ForecastAtHour---
day=AFTER_TOMORROW
time=AM_6
dewPointC=12
dewPointF=53
feelsLikeC=20
feelsLikeF=67
heatIndexC=20
heatIndexF=67
windChillC=20
windChillF=67
windGustKmph=3
windGustMiles=2
chanceOfFog=0
chanceOfFrost=0
chanceOfHighTemperature=0
chanceOfOvercast=0
chanceOfRain=0
chanceOfRemdry=80
chanceOfSnow=0
chanceOfSunshine=92
chanceOfThunder=0
chanceOfWindy=0
cloudCover=11
humidity=61
precipInches=0.0
precipMM=0.0
pressure=1014
pressureInches=30
temperatureC=20
temperatureF=67
timeInHundreds=600
uvIndex=1
visibility=10
visibilityMiles=6
weatherCode=113
weatherDescription='Clear'
windDir16Point='N'
windDirDegree=357
windSpeedKmph=2
windSpeedMiles=1


average temperature for tomorrow: 27
temperature for today at 3 pm: 98
```

## Nearest Area
### Description
Create an object of `NearestArea` to access all the data regarding the city/town/location for which the weather data has been gathered. The `NearestArea` class has a subclass `AreaInfo` containing more detailed information. The data may contain name of the town/city, the country where it is located, the population of the city etc.

### Examples
```java
NearestArea nearestArea = new NearestArea("Dallas");
System.out.println(nearestArea.getCountry() + ", " + nearestArea.getAreaInfo().region() + ", " + nearestArea.getAreaInfo().name());
System.out.println(nearestArea.toString());
```
Output:
```css
United States of America, Texas, Dallas
--NearestArea--
country='United States of America
areaInfo=
--AreaInfo--
name=Dallas
latitude=32.783
longitude=-96.800
population=1211704
region=Texas
```

## Request
### Description
Create an object `Request` to get location request data, mainly Latitude and Longitude
### Example
```java
Request request = new Request("Oslo");
System.out.println(request.toString());
```
Output:
```css
Request{query='Lat 59.91 and Lon 10.74', type='LatLon'}
```

# Data source / disclaimer 
*All weather data taken from https://wttr.in/,
Copyright (c) https://github.com/chubin/wttr.in .*
**No changes in original repository**
