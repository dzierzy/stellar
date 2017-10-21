import {Component} from '@angular/core';

@Component({
  moduleId: module.id,
  selector: 'my-content',
  templateUrl: 'home.component.html'
})
export class HomeComponent {
	
	public visible = true;
	
	onNotify(message:string):void {
		this.visible = false;
	}
  
}