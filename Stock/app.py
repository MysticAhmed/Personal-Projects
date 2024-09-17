import numpy as np
import pandas as pd
import yfinance as yf
import streamlit as st
from tensorflow import keras
from tensorflow.keras.models import load_model
import matplotlib.pyplot as plt

st.set_page_config(page_title= "Stock Predictor", page_icon= 'https://cdn-icons-png.flaticon.com/512/9226/9226414.png', layout="centered", 
                   initial_sidebar_state="auto", menu_items=None)
model = load_model(r'D:\College\Sophomore VT\Comp Sci\Personal Projects\Stock\Stock Predictions Model.keras')
st.title('Stock Market Predictor')
stock = st.text_input('Enter Stock Symbol', 'GOOG')
start = '2014-01-01'
end = '2024-09-15'
 
data = yf.download(stock, start, end)


st.subheader('Stock Data')
st.write(data)

st.header('MA = Moving Average')

train = pd.DataFrame(data.Close[:int(len(data)*0.80)])
test = pd.DataFrame(data.Close[int(len(data)*0.80):len(data)])

from sklearn.preprocessing import MinMaxScaler
scaler = MinMaxScaler(feature_range = (0,1))
past_100_days = train.tail(100)
test = pd.concat([past_100_days, test], ignore_index = True)
test_scale = scaler.fit_transform(test)

#Price vs MA50
st.subheader('Price vs MA50')
ma_50_days = data.Close.rolling(50).mean()
fig1 = plt.figure(figsize=(9,6))
plt.plot(ma_50_days, color = 'red', label = "MA 50")
plt.plot(data.Close, color = 'green', label = "Original Price")
plt.legend()
plt.show()
st.pyplot(fig1)

#Price vs MA50 vs MA200
st.subheader('Price vs MA50 vs MA200')
ma_200_days = data.Close.rolling(200).mean()
fig2 = plt.figure(figsize=(9,6))
plt.plot(ma_50_days, color = 'red', label = "MA 50")
plt.plot(ma_200_days, color = 'blue', label = "MA 200")
plt.plot(data.Close, color = 'green', label = "Original Price")
plt.legend()
plt.show()
st.pyplot(fig2)


#Model Prediction on test
x = []
y = []

for i in range(100, test_scale.shape[0]):
  x.append(test_scale[i-100:i])  # before the colon determines the start index
                                 # i determines the last index in the train_scale
                                 # array to add to x. i can never be below 100
                                 #so the window remains at 100 all the time

  y.append(test_scale[i,0]) #i is the row and 0 is the column
x,y = np.array(x), np.array(y)

predict = model.predict(x)

scale = 1/scaler.scale_

predict = predict * scale
y = y * scale


#Original Price vs Predicted Price
st.subheader('Original Price vs Predicted Price')
fig4 = plt.figure(figsize=(9,6))
plt.plot(predict, 'r', label = "Predicted")
plt.plot(y, color = 'green', label = "Original")
plt.xlabel('Time')
plt.ylabel('Price')
plt.legend()
plt.show()
st.pyplot(fig4)