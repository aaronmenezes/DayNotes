import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Notemodel } from '../notemodel';


@Component({
  selector: 'note-content-dialog',
  templateUrl: './note-content-dialog.component.html',
  styleUrls: ['./note-content-dialog.component.css']
})
export class NoteContentDialogComponent implements OnInit {
  isNewNote:boolean=false;
  noteData : Notemodel ={
    id: 0,
    name: "",
    body:"",
    key:0,
    date:"",
    priority:1
  }
  titleFormControl = new FormControl('', [
    Validators.required
  ]);
  bodyFormControl = new FormControl('', [
    Validators.required
  ]);
  
  constructor( public dialogRef: MatDialogRef<NoteContentDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Notemodel) {  
      if(data!=null){
        this.isNewNote=false;
        this.noteData = data
      }else{
        this.isNewNote=true;
         this.noteData.date= Date.now().toString();
      }  
     }

  ngOnInit(): void { }

}
