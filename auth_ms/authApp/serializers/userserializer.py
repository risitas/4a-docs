from rest_framework import serializers
from authApp.models.user import User


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['id', 'username', 'name','last_name','email','phone','address','password']

    
    def create(self, validated_data):
        userInstance = User.objects.create(**validated_data)
        return userInstance

    def to_representation(self, obj):
        user = User.objects.get(id=obj.id)
        return {
                    'id': user.id,
                    'username': user.username,                    
                    'name': user.name,
                    'last_name': user.last_name,
                    'email': user.email,
                    'phone': user.phone,
                    'address': user.address,                    
                }
