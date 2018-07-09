# E-Bot

E-Bot is an emotion detection chatting robot built with the purpose to provide emotional support to independent people especially those suffering from Dementia. 

![alt text](https://github.com/Rahul-Sridhar/E-Bot/blob/master/Images/3.png)

### Software Overview

The entire system consists of three main components: 
1. Raspberry Pi
2. Remote Server
3. Android Phone

The communication between Raspberry Pi and Android Phone and that between Remote Server and Raspberry Pi is based on Client-Server model. A flask development server runs on Raspberry Pi and the Remote Server. 

![alt text](https://github.com/Rahul-Sridhar/E-Bot/blob/master/Images/1.png)

A python script runs on the Raspberry Pi as soon as it boots up. This python script is responsible for the functions performed by E-Bot. The two major libraries used in this python script are Flask and Easygopigo3. Flask is used to send GET requests and files. Easygopigo3 provides functions to control the distance sensor and the motors for the wheels.

A python script runs on the Remote Server which runs a deep learning model to predict the emotion of the image sent by the Raspberry Pi. The two main libraries used in this python script are Tensorflow and Keras. Keras is a deep learning framework. 

The E-Bot interface is developed on Android Studio using Java and deployed on Android Phone.

![alt text](https://github.com/Rahul-Sridhar/E-Bot/blob/master/Images/E-Bot%20Interface%20dataflow.png)
