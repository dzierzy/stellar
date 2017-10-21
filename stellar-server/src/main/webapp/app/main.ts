//import {bootstrap} from 'angular2/platform/browser';
//import {HTTP_PROVIDERS} from 'angular2/http';
//import {provideRouter}   from '@angular/router';
//import {ContactsList} from './contacts';
//import {UserDataService} from './service';


//import { AppRoutes } from './routes';

//bootstrap(ContactsList,[HTTP_PROVIDERS, UserDataService, provideRouter(AppRoutes)]);

// main entry point
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app.module';

platformBrowserDynamic().bootstrapModule(AppModule);



