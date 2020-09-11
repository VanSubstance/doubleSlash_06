# -*- coding: utf-8 -*-
"""
Created on Fri Sep 11 15:33:40 2020

@author: sungh
"""

import matplotlib.font_manager as fm
import matplotlib

f_loc = 'C:/Windows/Fonts/malgunsl.ttf'
f_name = fm.FontProperties(fname = f_loc).get_name()
matplotlib.rc('font', family = f_name)

import pandas as pd
import matplotlib.pyplot as plt

data1 = pd.read_csv('생활폐기물_현황.csv', encoding = 'utf-8')
data1.keys()
gus = list(data1['구'].unique())
gus.remove('계')
years = list(data1['년도'].unique())

fig = plt.figure(figsize = (12, 8))
plt.title("주민 1인당 생활폐기물(쓰레기) 배출량(㎏/인/일) ~ 년")
plt.ylabel("kg / 인 / 일")
plt.xlabel("년도")
for gu in gus:
    plt.plot(data1[data1['구'] == gu]['년도'], 
             data1[data1['구'] == gu]['주민 1인당 생활폐기물(쓰레기) 배출량(㎏/인/일)'])

for year in years:
    print(data1[data1['년도'] == year]['주민 1인당 생활폐기물(쓰레기) 배출량(㎏/인/일)'].max())
plt.legend(gus, loc = "upper right")

    
#%%
    
    
data2 = pd.read_csv('서울시 폐기물 재활용.csv')
print(data2.keys())


