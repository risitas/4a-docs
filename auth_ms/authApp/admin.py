# importar clases
from django.contrib import admin
from .models.user import User


# usar clases
admin.site.register(User)
