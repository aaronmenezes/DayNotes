from flask import Flask, request ,jsonify, send_file, Response,send_from_directory
from flask_script import Manager, Server
from DatabaseInstance import DatabaseInstance 
from datetime import datetime
from time import sleep
import sqlite3
import os 
from pandas import DataFrame
 

app = Flask(__name__)   

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


@app.route('/')
@app.route('/home')
def catch_all():
    return send_from_directory(app.static_folder,"index.html")

@app.route("/getAPK")
def get_apk(): 
	return send_file("build/DemoSuite.zip", mimetype='application/zip', as_attachment=True, attachment_filename='archive.zip') 
	
@app.route("/getAPP")
def get_app(): 
	return send_file("build/DemoSuite.apk", mimetype='application/zip', as_attachment=True, attachment_filename='DemoSuite.apk') 
	
@app.route("/.well-known/assetlinks.json")
def get_wellknown(): 
	return send_file(".well-known/assetlinks.json",mimetype='application/json')  
	
@app.route("/getHome")
def get_home():
	cur = DatabaseInstance.getInstance().get_home_category()
	return jsonify(cur)

@app.route("/getFeatured")
def get_featured():
	cur = DatabaseInstance.getInstance().get_featured_category()
	return jsonify(cur)

@app.route("/getVideoModel/<string:cid>")
def get_video_model(cid):
	cur = DatabaseInstance.getInstance().get_video_model(cid)
	return jsonify(cur)

@app.route("/getMediaModel/<string:cid>")
def get_media_model(cid):
	cur = DatabaseInstance.getInstance().get_media_model(cid)
	return jsonify(cur)	
	
@app.route("/getTracklistModel/<string:aggMid>")
def get_tracklist_model(aggMid):
	cur = DatabaseInstance.getInstance().get_tracklist_model(aggMid)
	return jsonify(cur)	
	
@app.route("/getSynopsisModel" ,  methods=['GET'])
def get_synopsis_model():
	print(request.args)
	cur = DatabaseInstance.getInstance().get_synopsis_model( request.args.get('mid') , request.args.get('cid') , request.args.get('scid'))
	return jsonify(cur)	

@app.route("/getSearchMedia/<string:title>")
def get_search_media(title):
	cur = DatabaseInstance.getInstance().get_search_media(title)
	return jsonify(cur)

@app.route("/getSelectionHistory" ,  methods=['GET'])
def get_selection_history():
	filter_variable = request.args.get('filter',default = "all")
	cur = DatabaseInstance.getInstance().get_selection_history()
	names = [ x[0] for x in cur.description]
	graph_frame = DataFrame( cur.fetchall() , columns = names)
	if filter_variable != "all":
		filter_frame = graph_frame['media_'+filter_variable].value_counts().to_frame().reset_index().rename(columns={'index':'template', 'media_'+filter_variable:'count'})
	else:
		filter_frame = graph_frame
	return filter_frame.to_json(orient="records")

@app.teardown_appcontext
def shutdown_opeartions(exception): 
	pass
	DatabaseInstance.getInstance().close_connection(exception)

def allowed_file(filename):
	return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

@app.route('/posterUpdate', methods=['POST'])
def poster_update(): 
	if 'file' not in request.files:
		resp = jsonify({'message' : 'No file part in the request'})
		resp.status_code = 400
		return resp
	file = request.files['file']
	if file.filename == '':
		resp = jsonify({'message' : 'No file selected for uploading'})
		resp.status_code = 400
		return resp
	if file and allowed_file(file.filename):
		filename = secure_filename(file.filename)
		file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
		resp = jsonify({'message' : 'File successfully uploaded'})
		resp.status_code = 201
		DatabaseInstance.getInstance().update_poster_entry(request.form.get('mid'),file.filename)
		return resp
	else:
		resp = jsonify({'message' : 'Allowed file types are  png, jpg, jpeg'})
		resp.status_code = 400
		return resp


@app.route("/getImageAsset/<string:asset_id>")
def getImageAsset(asset_id): 
	try:
		return send_file("assets/posters/"+asset_id, mimetype='image/gif')
	except :
		return send_file("assets/posters/poster_unavailable.jpg", mimetype='image/gif')

@app.route("/getAlbumImageAsset/<string:asset_id>")
def getAlbumImageAsset(asset_id): 
	try:
		return send_file("assets/posters/"+asset_id, mimetype='image/gif')
	except :
		return send_file("assets/posters/album_poster1.jpg", mimetype='image/gif')

@app.route("/getDownloadVideo")
def getDownloadVideo():
	return send_file("./assets/media/Kazan.mp4", mimetype='video/mp4') 

@app.route("/getTestVideo")
def getTestVideo():
	def streamer():
		with open('./assets/media/Kazan.mp4', "rb") as file_object:
			while True:
				# print("<p>{}</p>".format(datetime.now()))
				data = file_object.read(1024)
				if not data:
					break
				yield data  

	return Response(streamer(),headers= {'Content-type':'video/mp4','Accept-Ranges': 'bytes'})
	
@app.route("/getTrailerVideo")
def getTrailerVideo():
	def streamer():
		with open('./assets/media/Mountains.mp4', "rb") as file_object:
			while True:
				# print("<p>{}</p>".format(datetime.now()))
				data = file_object.read(1024)
				if not data:
					break
				yield data  

	return Response(streamer(),headers= {'Content-type':'video/mp4','Accept-Ranges': 'bytes'})

@app.route("/getTestAudio")
def getTestAudio():
	def streamer():
		with open('./assets/media/test_mp3.mp3', "rb") as file_object:
			while True:
				# print("<p>{}</p>".format(datetime.now()))
				data = file_object.read(1024)
				if not data:
					break
				yield data  

	return Response(streamer(),headers= {'Content-type':'audio/mp3','Accept-Ranges': 'bytes'})
	'''


	