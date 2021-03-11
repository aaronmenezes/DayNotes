import { Component, OnInit } from '@angular/core'; 
import { Router } from '@angular/router';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  submitted =false;

  userModel = new User("","");
  constructor(private router: Router) { }

  ngOnInit(): void { } 
  onSubmit() {
    this.submitted = true;
    this.router.navigateByUrl('/dash'); 
  } 
}

export class User {

  constructor( 
    public name: string,
    public pass: string  ) {  }

}
