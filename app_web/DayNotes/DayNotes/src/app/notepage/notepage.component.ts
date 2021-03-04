import { Component, OnInit } from '@angular/core';
import { ConfigService } from '../config.service';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Notemodel } from '../notemodel';
import { MatDialog} from '@angular/material/dialog'; 
import { NoteContentDialogComponent } from '../note-content-dialog/note-content-dialog.component';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-notepage',
  templateUrl: './notepage.component.html',
  styleUrls: ['./notepage.component.css']
})
export class NotepageComponent implements OnInit {
 
  noteList : Notemodel
  constructor(private configService :ConfigService,public dialog: MatDialog,private snackBar:MatSnackBar) { }

  openDialog(note:Notemodel) { 
    const dialogRef = this.dialog.open(NoteContentDialogComponent,{data:note}); 
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
      if(result[0]=="delete"){
        this.configService.deleteNote(result[1]).subscribe(response => { 
          this.getAllNotes();
          this.showStatusSnackbar('Note Deleted',"hide"); 
        })
      }else if(result[0]=="update"){        
          this.configService.updateNote(result[1])
          this.showStatusSnackbar('Note updated', "hide")
      }
      else if(result[0]=="new"){       
        this.configService.addNewNote(result[1]).subscribe(response => { 
          this.showStatusSnackbar('New Note Added', "hide")
          this.getAllNotes();
        })
      }
    });
  }

  showStatusSnackbar(title:string,action:string){
    let snackBarRef = this.snackBar.open(title, action, {  duration:  10000 }); 
  }

  menuDeleteNote(note:string){
   console.log("==============="+note)
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
 
