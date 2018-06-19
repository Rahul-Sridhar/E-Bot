#! /usr/local/bin/python

distance_from_body = 225	# Distance the GoPiGo will stop from the human body.
gopigo_speed = 150
str1=""
str4=""
str5=""
str6=""
title12="Wavin Flag"
music_pos=0
date_string="" # Power of the motors.  Increase or decrease depending on how fast you want to go.


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
atexit.register(gpg.stop())	# If we exit, stop the motors

from googleapiclient import discovery
from oauth2client.client import GoogleCredentials

from flask import Flask
from flask import send_file
from flask import request
from flask import send_from_directory
from flask import redirect
import requests
app=Flask(__name__, static_folder="home/pi/images/")


mixer.init()
mixer.music.load("/home/pi/songs/"+title12+".mp3")
my_distance_sensor=gpg.init_distance_sensor()		# Distance Sensor goes on A1

#Calls the Espeak TTS Engine to read aloud a sentence
# This function is going to read aloud some text over the speakers
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
    
@app.route("/sound_settings")
def sound1():
	global str5
	language=request.args.get('language')
        str5='''{}'''.format(language)
	print(language)
	return language
    
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
    url="http://172.17.60.182/photo1"
    return redirect(url)

@app.route("/sending_photo")
def sending_photo():
    return send_from_directory("images", date_string, as_attachment=True)


@app.route("/emotion")
def emotion():
    global str4
    str4=""
    emotion=request.args.get('emotions')
    str4='''{}'''.format(emotion)
    return str4

# Wait for Button Press.
@app.route("/move_bot")
def get_move_bot():
	gpg.stop()
	sound("I am coming "+str6+"!")
	while (1 != 1):
		try:
			time.sleep(.5)
		except IOError:
			print ("Error")
	print("Button pressed!")
	
	gpg.set_speed(gopigo_speed)
	distance = my_distance_sensor.read_mm()
	while (distance > distance_from_body):
		try:
			distance = my_distance_sensor.read_mm()
			print ("Distance: " + str(distance))
			time.sleep(.1)
			gpg.forward()
		except IOError:
			gpg.stop()
			return "Error"
	gpg.stop()
	return "ready"
	
@app.route("/back_away")
def back_away():
	sound("Bye. See you later.")
	gpg.set_speed(gopigo_speed)
	gpg.backward()
	time.sleep(3)
	gpg.stop()
	return "bye"
    
@app.route("/back_away1")
def back_away1():
	sound("Okay. I will stay.")
	return "bye1"
    
@app.route("/turn_left")
def turn_left():
        gpg.set_speed(gopigo_speed)
        gpg.turn_degrees(-30)
        gpg.stop()
        return "left"
    
@app.route("/turn_right")
def turn_right():
        gpg.set_speed(gopigo_speed)
        gpg.turn_degrees(30)
        gpg.stop()
        return "right"
    
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

def internet_on():
	try:
		urllib2.urlopen('http://google.com', timeout=1)
		return True
	except urllib2.URLError as err: 
		return False
		
@app.route("/greeting")
def greeting():
    sound("Hi I am E-Bot")
    sound("How would you like me to call you?")
    return "greeting"

@app.route("/connection")
def check_connection():
    sound("E-Bot is starting..")
    return "E-Bot is starting.."    

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

@app.route("/distance")
def get_distance():
    return my_distance_sensor.read_mm()

@app.route("/distancee")
def get_distance1():
    sound("Look at me. Lets have a chat.")
    return "look"

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

@app.route("/image")
def get_image():
    return send_file("/home/pi/images/"+date_string, attachment_filename="image.jpg")

@app.route("/stay")
def stay():
    sound("Should I play a song for you?")
    return "stay"

@app.route("/song_stop")
def song_stop():
    mixer.music.stop()
    return "song_stop"

@app.route("/song_pause")
def song_pause():
    global music_pos
    mixer.music.pause()
    music_pos=mixer.music.get_pos()
    print(str(music_pos))
    return "song_pause"

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

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=80, debug=True, threaded=True)

#meinheld.listen(("0.0.0.0", 80))
#meinheld.run(app)
