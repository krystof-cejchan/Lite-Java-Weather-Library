# Lite-Java-Weather-Library
A Java Library for better and easier worldwide Weather data including current condition, forecast, location info etc.


# How to use

## Current Condition
### Description
Create an object of `CurrentCondition` to access all the data for current condition in provided location(set location in contrustor)
### Example
```java CurrentCondition currentCondition = new CurrentCondition("Dublin");  
System.out.println(currentCondition);```
Output:
```
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
...
```

## Forecast
### Description
Create an object of `WeatherForecast` to access all the data for weather forecast for ***Today, Tomorrow and the day after Tomorrow***. Each day can be separated into hours ***00:00, 3am, 6am, 9am, 12pm, 3pm, 6pm, 9pm***
#### WeatherForecast constructors:
![Constructors](http://kys.hys.cz/jo/weather_forecast_constructors.png)
### Examples
```
    //create a WeatherForecast object for City of Denver, for all days and times  
    WeatherForecast weatherForecast = new WeatherForecast("Denver", DAY.ALL, TIME.ALL);  
    //search for forecast for the day after tomorrow at 6 am  
    String toString = weatherForecast.getForecastFor(DAY.AFTER_TOMORROW, TIME.AM_6).toString();  
    //get average temperature for tomorrow in Celsius  
    int averageTemperatureCForTomorrow = weatherForecast.getTomorrow().getAverageTemperatureC();  
    //get temperature for today at 3 pm in Fahrenheit  
    int temperatureFTodayAt3pm = weatherForecast.getForecastFor(DAY.TODAY, TIME.PM_3).getTemperatureF();  
      
    System.out.println(toString + "\n\n" + "average temperature for tomorrow: " +  
      averageTemperatureCForTomorrow + "\n" + "temperature for today at 3 pm: " + temperatureFTodayAt3pm);```
Output:
```---ForecastAtHour---
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
temperature for today at 3 pm: 98```


