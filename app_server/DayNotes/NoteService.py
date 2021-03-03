from flask import Flask, request ,jsonify, send_file, Response,send_from_directory
from flask_script import Manager, Server
from DatabaseInstance import DatabaseInstance 
from datetime import datetime
from time import sleep
import sqlite3
import os 
from pandas import DataFrame
from flask_cors import CORS

app = Flask(__name__)   
CORS(app)

"""   
https://stream-canvas-va1.herokuapp.com/ | https://git.heroku.com/stream-canvas-va1.git
"""
ALLOWED_EXTENSIONS = set([ 'png', 'jpg', 'jpeg'])

@app.route('/')
@app.route("/test")
def hello():
	return "hello"

@app.route("/getAllNotes")
def get_all_notes():
	cur = DatabaseInstance.getInstance().get_all_notes()
	return jsonify(cur)

@app.route("/getAllNotesList")
def get_all_notes_list():
	cur = DatabaseInstance.getInstance().get_all_notes_list()
	return jsonify(cur)

@app.route("/addNewNote")
def add_new_note():
	print(request.args)
	cur = DatabaseInstance.getInstance().add_new_note(request.args['name'],request.args['date'],request.args['priority'],request.args['body'])
	return jsonify(cur)
	
@app.route("/updateNote")
def update_note():
	print(request.args)
	cur = DatabaseInstance.getInstance().update_note(request.args['id'],request.args['name'],request.args['body'])
	return jsonify(cur)

@app.route("/updateNotePriority")
def update_note_priority():
	print(request.args)
	cur = DatabaseInstance.getInstance().update_note_priority(request.args['id'],request.args['priority'])
	return jsonify(cur)

@app.route("/deleteNote")
def delete_note():
	print(request.args)
	cur = DatabaseInstance.getInstance().delete_note(request.args['id'])
	return jsonify(cur)
	
if __name__ == "__main__":
	port = int(os.environ.get("PORT", 8080))
	app.run(host='0.0.0.0', port=port,debug=True)
	
'''
	insert : http://localhost:8080/addNewNote?name=aaron&date=17-03-2021%205:30:00&priority=1&body={list=[maggi,chips,soda]}
	update : http://localhost:8080/updateNote?id=5&body={list=[maggi,banana,soda]}
			 http://localhost:8080/updateNotePriority?id=5&priority=5
			 
	delete : http://localhost:8080/deleteNote?id=7
'''