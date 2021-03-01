import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Notemodel } from './notemodel';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  constructor(private http: HttpClient) { }

  public getAllNote(){
    return this.http.get<Notemodel>("http://localhost:8080/getAllNotes") 
  }

  public updateNote(note:Notemodel){
    return this.http.get("http://localhost:8080/updateNote",{
      params:{
        id:note.id.toString(),
        name:note.name,
        body:note.body
      },
      observe: 'response'
    }).subscribe(response => {
      console.log(response);
    })
  }

  public deleteNote(note:Notemodel){
    return this.http.get("http://localhost:8080/deleteNote",{
      params:{
        id:note.id.toString()        
      },
      observe: 'response'
    })
  }

  
  addNewNote(note:Notemodel) {
    return this.http.get("http://localhost:8080/addNewNote",{
      params:{
        name:note.name,
        body:note.body,
        priority :note.priority.toFixed(),
        date:note.date     
      },
      observe: 'response'
    })
  } 
}