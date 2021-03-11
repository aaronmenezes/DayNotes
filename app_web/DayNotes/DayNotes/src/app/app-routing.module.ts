import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotepageComponent } from '../app/notepage/notepage.component'
import { NoteArchiveComponent } from './note-archive/note-archive.component';
import { SigninComponent } from './signin/signin.component';


const routes: Routes = [{path: '', component: SigninComponent},
                        {path: 'dash', component: NotepageComponent},
                        {path: 'archive', component: NoteArchiveComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
