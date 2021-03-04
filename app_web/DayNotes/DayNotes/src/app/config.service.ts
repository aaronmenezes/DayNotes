import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Notemodel } from './notemodel';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  apiString: "https://day-notes.herokuapp.com"; 
  apiLocalString :"https://localhost:8080";

  constructor(private http: HttpClient) { 
    this.apiString="https://day-notes.herokuapp.com";
  }

  public getAllNote(){ 
    return this.http.get<Notemodel>(this.apiString+"/getAllNotes") 
  }

  public getArchive(){ 
    return this.http.get<Notemodel>(this.apiString+"/getNoteArchive") 
  }


  public updateNote(note:Notemodel){
    return this.http.get(this.apiString+"/updateNote",{
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
    return this.http.get(this.apiString+"/deleteNote",{
      params:{
        id:note.id.toString()        
      },
      observe: 'response'
    })
  }

  
  addNewNote(note:Notemodel) {
    return this.http.get(this.apiString+"/addNewNote",{
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