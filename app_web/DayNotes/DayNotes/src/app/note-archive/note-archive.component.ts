import { Component, OnInit } from '@angular/core';
import { ConfigService } from '../config.service';
import { Notemodel } from '../notemodel';
import {animate, state, style, transition, trigger} from '@angular/animations';

@Component({
  selector: 'app-note-archive',
  templateUrl: './note-archive.component.html',
  styleUrls: ['./note-archive.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class NoteArchiveComponent implements OnInit {

  dataSource :Notemodel;
  expandedElement: Notemodel | null;
  columnsToDisplay = ['name', 'date', 'priority',"action"];
  constructor(private configService :ConfigService) {}

  ngOnInit(): void {
    this.getArchive();
  }

  convertEpochToDate(time:string){ 
    return  new Date(Number(time));
  }
  getArchive(){
    this.configService.getArchive().subscribe(data=>{
      this.dataSource=data
     });
  }
  
  restoreNote(note:Notemodel){
      console.log(note);
  }
}
