import { Component }          from '@angular/core';
import { AuthenticationService } from './login/_auth';

@Component({
  moduleId: module.id,
  selector: 'my-app',
  templateUrl: `app.component.html`
})

export class AppComponent {

  title = 'Stellar catalogue';
  loggedIn: any;
  userName = "";


  constructor() {
  }

}


