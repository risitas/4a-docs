import json
import jwt
 
from django.test import TestCase
 
from rest_framework import status
from rest_framework.test import APIClient
 
# Estructura de la prueba unitaria 1
class TestAPI(TestCase):
 
   def test_signUp(self):
       client = APIClient()
       response = client.post(
           '/user/',
           {
               "username": "user_prueba_1",
               "password": "password_prueba_1",
               "name": "user prueba",
               "email": "user_prueba_1@misionTIC.com",
               "account": {
                   "lastChangeDate": "2021-09-23T10:25:43.511Z",
                   "balance": 20000,
                   "isActive": "true"
               }
           },
           format='json')
    #   elementos creados para prueba 1
       self.assertEqual(response.status_code, status.HTTP_201_CREATED)
       self.assertEqual('refresh' in  response.data.keys(), True)
       self.assertEqual('access' in  response.data.keys(), True)
 
# Estructura de la prueba unitaria 2
   def test_login(self):
       client = APIClient()
       response = client.post(
           '/login/',
           {
               "username":"Beimer",
               "password":"ortega"
           },
           format='json')
      #   elementos creados para prueba 2
       self.assertEqual(response.status_code, status.HTTP_200_OK)
       self.assertEqual('refresh' in  response.data.keys(), True)
       self.assertEqual('access' in  response.data.keys(), True)
 
# Estructura de la prueba unitaria 3
   def test_user(self):
       client = APIClient()
        # captura del token
       token_access =  client.post(
                           '/login/',
                           {"username":"Beimer", "password":"ortega"},
                           format='json'
                       ).data["access"]
      
       secret = 'django-insecure-l@3+1@r)ub^x8d)^347t^e5#1z*ofj@0u1i)6z&(b%(j9k6uu4'
       user_id =   jwt.decode( token_access,  secret,  algorithms=["HS256"] )["user_id"]
        #capturar user
       url = '/user/'+ str(user_id) + '/'
       auth_headers = {'HTTP_AUTHORIZATION': 'Bearer ' + token_access,}
  
       response = client.get(url,  **auth_headers)
       
      #   elementos creados para prueba 3
       self.assertEqual(response.status_code, status.HTTP_200_OK)
       self.assertEqual(response.data["username"], "pepe")