# Generated by Django 2.2.1 on 2020-07-13 06:04

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('dap_api', '0006_depressiontest_testlist'),
    ]

    operations = [
        migrations.CreateModel(
            name='WordTest',
            fields=[
                ('textId', models.ForeignKey(max_length=224, on_delete=django.db.models.deletion.CASCADE, primary_key=True, serialize=False, to='dap_api.TestList')),
                ('testResult', models.CharField(max_length=224)),
            ],
            options={
                'db_table': 'WordTest',
            },
        ),
    ]
