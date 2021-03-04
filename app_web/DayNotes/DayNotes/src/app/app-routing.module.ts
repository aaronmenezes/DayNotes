import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotepageComponent } from '../app/notepage/notepage.component'
import { NoteArchiveComponent } from './note-archive/note-archive.component';


const routes: Routes = [{path: '', component: NotepageComponent},{path: 'archive', component: NoteArchiveComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
