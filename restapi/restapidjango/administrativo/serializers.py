from django.contrib.auth.models import User, Group
from administrativo.models import Multa

from rest_framework import serializers

class UserSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = User
        fields = ['url', 'username', 'email', 'groups']


class GroupSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Group
        fields = ['url', 'name']


class MultaSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Multa
        fields = '__all__'

