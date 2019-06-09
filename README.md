# PubNub demo using Twitter Streaming API

This is the backend part of the PubNub demo application which receives live tweets from the
Twitter Streaming API and publishes them using the [PubNub](https://www.pubnub.com/) Java client library.  
  
Source code repository for the frontend:
[TwitterStreamingPubNubDemoFrontend](https://github.com/VictorGil/TwitterStreamingPubNubDemoFrontend).  
  
Explanatory video on [YouTube](https://youtu.be/A0QXLJFLzm4)  

## Requirements

 - Maven 3  
 - Java 8+  

## Usage
After compiling/building the application source code (`mvn clean install`), run the main class: 
[PublisherMain](https://github.com/VictorGil/TwitterStreamingPubNubDemoBackend/blob/master/src/main/java/net/devaction/pubnubdemo/main/PublisherMain.java)  
Please note that the directory `src/main/resources` must be included in the classpath.    
