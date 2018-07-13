#! /usr/local/bin/python

distance_from_body = 225	# Distance the GoPiGo will stop from the human body.
gopigo_speed = 150       # Default speed of the motors
str1=""                  # Stores the translated text   
str4=""                  # Stores the emotion
str5=""                  # Stores the language code
str6=""                  # Stores the name
title12="Wavin Flag"     # Stores the title of song to be played. 
music_pos=0              # Stores the position of the song
date_string=""           # Stores the name of the image for the picture taken by take_photo()


import os
import argparse
import urllib2
import base64
import picamera
import json
from subprocess import call
import time
from gtts import gTTS
from pygame import mixer
from googletrans import Translator
import datetime
import easygopigo3 as easy
import tempfile
import glob

gpg=easy.EasyGoPiGo3()

import atexit
atexit.register(gpg.stop())	     # If program exits, stop the motors


from oauth2client.client import GoogleCredentials

from flask import Flask
from flask import send_file
from flask import request
from flask import send_from_directory
from flask import redirect
import requests
app=Flask(__name__, static_folder="home/pi/images/")   # Sets the directory to store the images taken by take_photo()


mixer.init()                                           # Initializes mixer module of Pygame library with default parameters.
mixer.music.load("/home/pi/songs/"+title12+".mp3")     # Loads a song using load() of mixer library.
my_distance_sensor=gpg.init_distance_sensor()		        # Initializes Distance Sensor with default parameters

# This function takes language option and translates text to that language using Translator function of googletrans package
# Converts this text into mp3 file with gTTS which is a Text to Speech converter
# The .mp3 file is then played using mixer library of Pygame package
@app.route("/sound")
def sound(text):
        translator=Translator()
        str1=translator.translate(text, dest=str5).text
        print("cool")
        tts=gTTS(str1, lang=str5)
        tts.save('/home/pi/hello.mp3')
        mixer.init()
        mixer.music.load('/home/pi/hello.mp3')
        mixer.music.play()
        while mixer.music.get_busy()==True:
            continue
    
# The function sets the language for communication by extracting language code from the request
@app.route("/sound_settings")
def sound1():
	global str5
	language=request.args.get('language')
        str5='''{}'''.format(language)
	print(language)
	return language
    
# This  function takes a picture and saves it. It then redirects to the url.
@app.route("/photo", methods=["GET"])
def takephoto():
    global date_string
    date_string = str(datetime.datetime.now())
    camera = picamera.PiCamera()
    camera.resolution = (1600, 1200)
    camera.sharpness = 100
    date_string = 'image'+date_string+'.jpg'
    date_string = date_string.replace(":", "")	# Strip out the colon from date time.
    date_string = date_string.replace(" ", "")	# Strip out the space from date time.
    print("TAKE PICTURE: " + date_string)
    print(date_string)
    camera.capture('/home/pi/image.jpg')
    camera.close()# We need to close off the resources or we'll get an error.
    print("picture taken")
    call([" cp /home/pi/image.jpg "+"/home/pi/images/"+date_string], shell=True)
    print("picture copied")
    url="http://172.17.35.211/photo1"
    return redirect(url)

# This function sends the image stored by takephoto()
@app.route("/sending_photo")
def sending_photo():
    return send_from_directory("images", date_string, as_attachment=True)


#This function extracts the emotion from the request and saves it in a variable
@app.route("/emotion")
def emotion():
    global str4
    str4=""
    emotion=request.args.get('emotions')
    str4='''{}'''.format(emotion)
    return str4

# Measures the distance from the user using distance sensor. If more than threshold moves forward. Stops when reaches the threshold.
# Since the backward() function moves the robot forward it is used instead.
@app.route("/move_bot")
def get_move_bot():
	gpg.stop()
	gpg.set_speed(gopigo_speed)
	distance = my_distance_sensor.read_mm()
	while (distance > distance_from_body):
		try:
			distance = my_distance_sensor.read_mm()
			print ("Distance: " + str(distance))
			time.sleep(.1)
			gpg.backward()
		except IOError:
			gpg.stop()
			return "Error"
	gpg.stop()
	return "ready"
	
# This function moves the robot backwards for three seconds and stops.
# Since the forward() function moves the robot backward it is used instead.
@app.route("/back_away")
def back_away():
	sound("Bye. See you later.")
	gpg.set_speed(gopigo_speed)
	gpg.forward()
	time.sleep(3)
	gpg.stop()
	return "bye"
    
@app.route("/back_away1")
def back_away1():
	sound("Okay. I will stay.")
	return "bye1"
    
# Moves the robot left by 30 degrees.
@app.route("/turn_left")
def turn_left():
        gpg.set_speed(gopigo_speed)
        gpg.turn_degrees(-30)
        gpg.stop()
        return "left"
    
# Moves the robot right by 30 degrees.
@app.route("/turn_right")
def turn_right():
        gpg.set_speed(gopigo_speed)
        gpg.turn_degrees(30)
        gpg.stop()
        return "right"
    
# Extracts the title of the song and stored the song by this name.
# It then opens the file and writes the data (request.get_data()) sent by the request into file using write()
@app.route("/post_songs", methods=['GET', 'POST'])
def post_song():
        title_song=request.args.get('title')
        title='''{}'''.format(title_song)
        try:
            if request.method=="POST":
                with open('/home/pi/songs/'+title+'.mp3', 'w+') as f:
                    f.write(request.get_data())
                return "success"
        except Exception as e:
            return "error"

# Checks for internet connection of the robot.
def internet_on():
	try:
		urllib2.urlopen('http://google.com', timeout=1)
		return True
	except urllib2.URLError as err: 
		return False
		
# Greets the user on receiveing this request.
@app.route("/greeting")
def greeting():
    sound("Hi I am E-Bot")
    sound("How would you like me to call you?")
    return "greeting"

# Sends a message confirming connection between robot and the device
@app.route("/connection")
def check_connection():
    sound("E-Bot is starting..")
    return "E-Bot is starting.."    

# Extracts song to be deleted from the request. Moves to directory where song is places using chdir() of os library. Deletes the song using remove() of os library.
@app.route("/delete_songs")
def delete_songs():
    song_to_be_deleted1=request.args.get('song_to_be_deleted')
    song_to_be_deleted='''{}'''.format(song_to_be_deleted1)
    directory='/home/pi/songs'
    os.chdir(directory)
    files=glob.glob(song_to_be_deleted+'.*')
    for filename in files:
        os.remove(filename)
    directory='/home/pi'
    os.chdir(directory)
        
# Sends a message on receiving confirmation of internet connection of raspberry pi by calling internet_on().
@app.route("/internet_connection")
def internet_connection():
    while (internet_on() != True):
		sound("I am waiting on an internet connection!")
		time.sleep(15)
    name=request.args.get('name')
    global str6
    str7='Hi '
    str6='''{}'''.format(name)
    str7=str7+str6+','
    sound("I am ready")
    sound(str7)
    sound("Would you like me to come")
    return "I am ready"


# Extracts the chatting code from request and continues the conversation and returns the conversation.
@app.route("/chatting")
def get_reply():
    code=request.args.get('code')
    decode='''{}'''.format(code)
    print(decode)
    if(decode=="A1"):
        sound("Thank You.")
        str1="Thank You."
    elif(decode=="A2"):
        sound("Please Do not do that. I have feelings.")
        str1="Please do not do that. I have feelings."
    elif(decode=="D1"):
        sound("It will get better.")
        str1="It will get better."
    elif(decode=="D2"):
        sound("Why? What did I do?")
        str1="Why? What did I do?"
    elif(decode=="H1"):
        sound("Oh! That is great.")
        str1="Oh! That is great."
    elif(decode=="H2"):
        sound("Have fun.")
        str1="Have fun."
    elif(decode=="N1"):
        sound("Nice one! I will find out how you feel the next time.")
        str1="Nice one! I will find out how you feel the next time."
    elif(decode=="N2"):
        sound("Cool.")
        str1="Cool."
    elif(decode=="Sa1"):
        sound("You are the best.")
        str1="You are the best."
    elif(decode=="Sa2"):
        sound("Everything is going to be ok.")
        str1="Everything is going to be ok."
    elif(decode=="Su1"):
        sound("I know. It is quite strange.")
        str1="I know. It is quite strange."
    elif(decode=="Su2"):
        sound("Oh! That is fine. You will get a hang of it.")
        str1="Oh! That is fine. You will get a hang of it."
    elif(decode=="Sc1"):
        sound("That is great!")
        str1="That is great!"
    else:
        sound("I know dat.")
        str1="No. I am not."
    sound("Would you like me to stay?")
    return str1

# Returns the distance of the robot from nearest obstacle using read_mm() of my_distance_sensor.
@app.route("/distance")
def get_distance():
    return my_distance_sensor.read_mm()

@app.route("/distancee")
def get_distance1():
    sound("Look at me. Lets have a chat.")
    return "look"

# Based on saved emotion begins a conversation.
@app.route("/process")
def get_process():
    global str4
    if(str4== "happy"):
	sound("You seem happy!  Tell me why you are so happy!")
	str2="You seem happy!\nTell me why you are so happy!"
    elif(str4 == "angry"):
	sound("Uh oh, you seem angry!  I have kids, please do not hurt me!")
        str2="Uh oh, you seem angry!\nI have kids, please do not hurt me!"
    elif(str4 == "surprise"):
	sound("You seem surprised! What is the reason behind it?")
	str2="You seem surprised!\nWhat is the reason behind it?"
    elif(str4== "disgust"):
	sound("You seem disgusted! I hope I am not the reason behind it.")
	str2="You seem disgusted!\nI hope I am not the reason behind it."
    elif(str4 == "fear"):
	sound("You seem scared! I would not hurt you.")
        str2="You seem scared!\nI would not hurt you."
    elif(str4 == "neutral"):
	sound("You seem to show no emotions! You can express how you feel with me")
	str2="You seem to show no emotions!\nYou can express how you feel with me"
    elif(str4=="sad"):
	sound("You seem sad!  What can I do for you?")
	str2="You seem sad!\nWhat can I do for you?"
    else:
        sound("There seems to be a server error. Would you like to try again?")
        str2="There seems to be a server error.\nWould you like to try again?"
    str4="a"
    return str2

# Sends the image stored by take_photo()
@app.route("/image")
def get_image():
    return send_file("/home/pi/images/"+date_string, attachment_filename="image.jpg")

@app.route("/stay")
def stay():
    sound("Should I play a song for you?")
    return "stay"

# Stops the songs being played on recieving this request using stop() of mixer library.
@app.route("/song_stop")
def song_stop():
    mixer.music.stop()
    return "song_stop"

# Pauses the song and stores the position of song where paused.
@app.route("/song_pause")
def song_pause():
    global music_pos
    mixer.music.pause()
    music_pos=mixer.music.get_pos()
    print(str(music_pos))
    return "song_pause"

# Extracts name of song and plays it from start.
@app.route("/song_prev_next")
def song_prev_next():
    global music_pos
    music_pos=0
    nametitle=request.args.get('nameofsong')
    title='''{}'''.format(nametitle)
    mixer.init()
    mixer.music.load("/home/pi/songs/"+title+".mp3")
    mixer.music.play()
    while mixer.music.get_busy()==True:
        continue
    return "song_prev_next"

# Extracts name of song and plays from last stopped position.
@app.route("/song_play")
def song_play():
    nametitle=request.args.get('nameofsong')
    title='''{}'''.format(nametitle)
    mixer.init()
    mixer.music.load("/home/pi/songs/"+title+".mp3")
    mixer.music.play(0, float(music_pos/1000))
    while mixer.music.get_busy()==True:
        continue
    return "song_play1"


@app.route("/call_ask")
def call_ask():
    sound("Should I call for help?")
    return "call_ask"

@app.route("/go_ask")
def go_ask():
    sound("Can I go now?")
    return "call_ask"

@app.route("/")
def hello():
    return "hi"

# Flask server runs on port 80. Setting threaded to True allows it to process multiple requests at the same time. Setting debug to True runs the server in dubug mode.  
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=80, debug=True, threaded=True)


