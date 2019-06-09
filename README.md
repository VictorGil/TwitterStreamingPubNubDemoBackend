# PubNub demo application using Twitter Streaming API - backend

This is the backend part of the PubNub demo application which receives live tweets from the
Twitter Streaming API and publishes them using the [PubNub](https://www.pubnub.com/) Java client library.  
  
Source code repository for the frontend:
[TwitterStreamingPubNubDemoFrontend](https://github.com/VictorGil/TwitterStreamingPubNubDemoFrontend).  
  
Explanatory video on [YouTube](https://youtu.be/A0QXLJFLzm4)  

## Requirements

 - Maven 3  
 - Java 8+  

## Usage

 - Sign up for a free developer account on [pubnub.com](https://www.pubnub.com/) in order to get your 
**Publish** **key** and your **Subscribe** **key**.  
 - Checkout the source code and set the values of the `pubnub_publish_key` and the `pubnub_subscribe_key` properties 
in the [config.json](https://github.com/VictorGil/TwitterStreamingPubNubDemoBackend/blob/master/src/main/resources/config.json#L4) 
file to the ones you got from PubNub after registering.  
 - You also need one or more (up to three) sets of **Twitter Web API keys and tokens** in order to connect to the Twitter Streaming API, 
you may watch [this video](https://youtu.be/KPHC2ygBak4) for the instructions. Then set the values accordingly in the 
[config.json](https://github.com/VictorGil/TwitterStreamingPubNubDemoBackend/blob/master/src/main/resources/config.json) file.  
 - Compile/build the application source code using Maven (`mvn clean install`) 
 - Run the main class: [PublisherMain](https://github.com/VictorGil/TwitterStreamingPubNubDemoBackend/blob/master/src/main/java/net/devaction/pubnubdemo/main/PublisherMain.java)  
Please note that the directory `src/main/resources` must be included in the classpath.  
  
To gracefully shutdown the application on a Linux system, send the `WINCH` signal to the running Java process, i.e.:
```
$ kill -WINCH <PID>
```
