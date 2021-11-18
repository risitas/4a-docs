from rest_framework import generics, status
from authApp.models.user import User
from authApp.serializers.userserializer import UserSerializer

class UserDetailView(generics.RetrieveAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer