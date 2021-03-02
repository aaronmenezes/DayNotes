import { Component, OnInit } from '@angular/core';
import { ConfigService } from '../config.service';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Notemodel } from '../notemodel';
import { MatDialog} from '@angular/material/dialog'; 
import { NoteContentDialogComponent } from '../note-content-dialog/note-content-dialog.component';

@Component({
  selector: 'app-notepage',
  templateUrl: './notepage.component.html',
  styleUrls: ['./notepage.component.css']
})
export class NotepageComponent implements OnInit {
 
  noteList : Notemodel
  constructor(private configService :ConfigService,public dialog: MatDialog) { }

  openDialog(note:Notemodel) { 
    const dialogRef = this.dialog.open(NoteContentDialogComponent,{data:note}); 
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
      if(result[0]=="delete"){
        this.configService.deleteNote(result[1]).subscribe(response => {
          console.log(response);
          this.getAllNotes();
        })
      }else if(result[0]=="update"){        
          this.configService.updateNote(result[1])
      }
      else if(result[0]=="new"){        
        console.log(result[1]);
        this.configService.addNewNote(result[1]).subscribe(response => {
          console.log(response);
          this.getAllNotes();
        })
      }
    });
  }

  menuDeleteNote(note:Notemodel){
    this.configService.deleteNote(note).subscribe(response => {
      console.log(response);
      this.getAllNotes();
    })
  }

  getAllNotes(){
    this.configService.getAllNote().subscribe(data=>{
      this.noteList=data
     });
  }
  
  ngOnInit(): void {    
   this.getAllNotes();
  } 
}
 
