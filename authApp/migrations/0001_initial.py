# Generated by Django 3.2.9 on 2021-11-23 22:00

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        ('auth', '0012_alter_user_first_name_max_length'),
    ]

    operations = [
        migrations.CreateModel(
            name='User',
            fields=[
                ('last_login', models.DateTimeField(blank=True, null=True, verbose_name='last login')),
                ('is_superuser', models.BooleanField(default=False, help_text='Designates that this user has all permissions without explicitly assigning them.', verbose_name='superuser status')),
                ('id', models.BigAutoField(primary_key=True, serialize=False)),
                ('username', models.CharField(max_length=15, unique=True, verbose_name='user_name')),
                ('name', models.CharField(max_length=30, verbose_name='name')),
                ('last_name', models.CharField(max_length=30, verbose_name='last_name')),
                ('email', models.EmailField(max_length=100, verbose_name='email')),
                ('phone', models.CharField(max_length=30, verbose_name='phone')),
                ('address', models.CharField(max_length=80, verbose_name='address')),
                ('password', models.CharField(max_length=256, verbose_name='password')),
                ('password_verification', models.CharField(max_length=256, verbose_name='password_verification')),
                ('groups', models.ManyToManyField(blank=True, help_text='The groups this user belongs to. A user will get all permissions granted to each of their groups.', related_name='user_set', related_query_name='user', to='auth.Group', verbose_name='groups')),
                ('user_permissions', models.ManyToManyField(blank=True, help_text='Specific permissions for this user.', related_name='user_set', related_query_name='user', to='auth.Permission', verbose_name='user permissions')),
            ],
            options={
                'abstract': False,
            },
        ),
    ]
