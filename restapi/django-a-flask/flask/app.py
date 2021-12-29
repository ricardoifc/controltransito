from flask import Flask, render_template
import requests
import json

app = Flask(__name__, template_folder='templates')

@app.route("/")
def hello_world():
    return "<p>Bienvenido control de transito</p>"\
    "<BR><a href='/multa'>Ver Multas </a>"\


@app.route("/multa")
def las_multas():
    """
    """
    r = requests.get("http://127.0.0.1:8000/api/multa/",
            auth=('admin', '123'))
    datosx = json.loads(r.content)['results']
    numero = json.loads(r.content)['count']
    datos = []
    for d in datosx:
        datos.append({'placa': d['placa'],
        'cedula':d['cedula'],
        'fecha': d['fecha'],
         'hora':d['hora'],
         'modelo':d['modelo'],
         'nombre':d['nombre'],
         'velocidad':d['velocidad']
        })
    return render_template("multas.html", datos=datos,
    numero=numero)