import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NotepageComponent } from '../app/notepage/notepage.component'

const routes: Routes = [{path: '', component: NotepageComponent},];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
