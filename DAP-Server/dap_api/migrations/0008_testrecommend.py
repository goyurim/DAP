# Generated by Django 2.2.1 on 2020-08-25 13:39

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('dap_api', '0007_wordtest'),
    ]

    operations = [
        migrations.CreateModel(
            name='TestRecommend',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('emotionPoint', models.IntegerField()),
                ('depressionPoint', models.IntegerField()),
                ('TestCode', models.CharField(max_length=244)),
                ('FeedbackPoint', models.CharField(max_length=244)),
            ],
            options={
                'db_table': 'TestRecommend',
            },
        ),
    ]