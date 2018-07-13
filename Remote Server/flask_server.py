date_string=""


from flask import Flask, request, Response, send_file
import jsonpickle
import numpy as np
import io
from numpy.doc import misc
import requests
import tempfile
import argparse
import base64
import json
from subprocess import call
import time
import datetime
from PIL import Image
import tensorflow as tf
import keras
from keras.models import model_from_json
from keras.preprocessing import image
from keras import backend as K
import numpy as np
import matplotlib.pyplot as plt

app = Flask(__name__)

@app.route("/")
def hello():
    return "hi"

@app.route('/photo1', methods=["GET"])
def get_photo():

    # Creates a string and stores it in a variable. To be used later to save an image file by this string
    global date_string
    date_string=str(datetime.datetime.now())
    date_string='image'+date_string+'.jpg'
    date_string=date_string.replace(":", "")
    date_string=date_string.replace(" ", "")

    # Sends GET request to raspberry pi for an image using get() of requests library by passing url and authentication details (username and password) as parameters 
    url="http://172.17.35.212/sending_photo"
    r = requests.get(url, auth=('pi', 'raspberry'))

    # Store the image by extracting the data (using r.content) from the request 
    with open("C:\\Users\\RAHUL\\Downloads\\" + date_string, 'wb+') as f:
          f.write(r.content)

    # Used model_from_json() to store the model structure from the json file
    with open(r"C:\Users\RAHUL\Downloads\dev\HelloWorld\tensorflow-101-master\model\facial_expression_model_structure.json", "r") as f:
        model = model_from_json(f.read())

    # Load weights into the new model.
    model.load_weights(r"C:\Users\RAHUL\Downloads\dev\HelloWorld\tensorflow-101-master\model\facial_expression_model_weights.h5")

    # Convert image into 48*48 grayscale image
    img = image.load_img("C:\\Users\\RAHUL\\Downloads\\"+date_string, grayscale=True, target_size=(48, 48))

    x = image.img_to_array(img)
    x = np.expand_dims(x, axis = 0)

    x /= 255

    custom = model.predict(x)

    # Returns the emotion based on the prediction of the deep learning model
    def emotion_analysis(emotions):
        objects = ('angry', 'disgust', 'fear', 'happy', 'sad', 'surprise', 'neutral')
        max1=0
        pos=0
        for i in range(7):
            if(emotions[i]>max1):
                max1=emotions[i]
                pos=i
        return objects[pos]

    s=emotion_analysis(custom[0])

    # Stores the emotion in emotions parameter of the url and sends a GET request with the URL and authentication details (Username and Password) as parameters 
    urla="http://172.17.35.212/emotion?emotions="+s
    r1 = requests.get(urla, auth=('pi', 'raspberry'))

    K.clear_session()
    return s

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=80, debug=True)
