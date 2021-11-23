from django.db import models
from django.contrib.auth.models import AbstractBaseUser, PermissionsMixin, BaseUserManager
from django.contrib.auth.hashers import make_password

class UserManager(BaseUserManager):
    def create_user(self, username, password=None):
        """
        Creates and saves a user with the given username and password.
        """
        if not username:
            raise ValueError('Users must have an username')
        user = self.model(username=username)
        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_superuser(self, username, password):
        """
        Creates and saves a superuser with the given username and password.
        """
        user = self.create_user(
            username=username,
            password=password,
        )
        user.is_admin = True
        user.save(using=self._db)
        return user

#Registro usuario
class User(AbstractBaseUser, PermissionsMixin):
    
    id = models.BigAutoField(primary_key=True)
    username = models.CharField('user_name', max_length = 15, unique=True)
    name = models.CharField('name', max_length = 30)
    last_name = models.CharField('last_name', max_length = 30)
    email = models.EmailField('email', max_length = 100)
    phone = models.CharField('phone', max_length = 30)
    address= models.CharField('address', max_length= 80)
    password = models.CharField('password', max_length = 256, blank=False)
    password_verification = models.CharField('password_verification', max_length = 256,blank=False)

    def save(self, **kwargs):
        some_salt = 'mMUj0DrIK6vgtdIYepkIxN'
        self.password = make_password(self.password, some_salt)
        super().save(**kwargs)

    objects = UserManager()
    USERNAME_FIELD = 'username'
    
    

