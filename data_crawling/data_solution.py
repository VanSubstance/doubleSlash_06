# -*- coding: utf-8 -*-
"""
Created on Sun Aug  2 21:03:59 2020

@author: sungh
"""


import requests
from bs4 import BeautifulSoup
import json
import os



url="http://ecoseoul.or.kr/archives/category/%EC%9D%B4%EC%8A%88/%EC%83%9D%ED%99%9C%ED%99%98%EA%B2%BD"


def move_page(link):
    page="/page/"
    for i in range(1,30):
        num=str(i)
        move_content(link+page+num)
    
    
def move_content(link):
    response=requests.get(link)
    soup=BeautifulSoup(response.text,"html.parser")
    #큰틀
    bf=soup.find('ul','cat-list')
    #작은틀
    mf=bf.select('li')
    for i in mf:
        title_=i.find('a').get('title')
        if '[도와줘요 쓰레기박사]' in title_:
            url=i.find('a').get('href')
            get_info(url)
            print(1)

def get_info(link):
    file_path='/Users/kimjong-gyu/Desktop/data01.json'
    temp_dict={}
    raw=requests.get(link)
    soup=BeautifulSoup(raw.text,"html.parser")
    
    #제목
    container_t =soup.find('div','entry-meta')
    title=container_t.find('h1').get_text().replace('\r','').replace('\n','').replace('\t','')
    
    #타이틀 이미지
    title_c=soup.find('figure','wp-block-image')
    title_image=title_c.find('img').get('src')
    
    #Answer
    temp=""
    container_a=soup.select('div.entry-content>p')
    for a in container_a:
        temp+=a.text
        
    
    temp_dict={'title':title,'titleImage':title_image,"answer":temp}
    re_dict={title:temp_dict}
    
    if not os.path.isfile(file_path):
            with open(file_path,'w',encoding='utf-8') as f:
                json.dump(re_dict,f,ensure_ascii=False,indent='\t')
    else:
        with open(file_path,'r',encoding='utf-8') as s:
            data=json.load(s)
        data.update(re_dict)
        with open(file_path,'w',encoding='utf-8') as f:
            json.dump(data,f,ensure_ascii=False,indent='\t')
        f.close()
    

move_page(url)



