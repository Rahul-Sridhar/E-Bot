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
    global date_string
    date_string=str(datetime.datetime.now())
    date_string='image'+date_string+'.jpg'
    date_string=date_string.replace(":", "")
    date_string=date_string.replace(" ", "")
    url="http://172.17.59.97/sending_photo"
    r = requests.get(url, auth=('pi', 'raspberry'))
    with open("C:\\Users\\RAHUL\\Downloads\\" + date_string, 'wb+') as f:
          f.write(r.content)

    with open(r"C:\Users\RAHUL\Downloads\facial_expression_model_structure.json", "r") as f:
        model = model_from_json(f.read())

    # Load weights into the new model
    model.load_weights(r"C:\Users\RAHUL\Downloads\facial_expression_model_weights.h5")

    img = image.load_img("C:\\Users\\RAHUL\\Downloads\\"+date_string, grayscale=True, target_size=(48, 48))

    x = image.img_to_array(img)
    x = np.expand_dims(x, axis = 0)

    x /= 255

    custom = model.predict(x)

    #function for drawing bar chart for emotion preditions
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
    urla="http://172.17.59.97/emotion?emotions="+s
    r1 = requests.get(urla, auth=('pi', 'raspberry'))
    K.clear_session()
    return s

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=80, debug=True)
