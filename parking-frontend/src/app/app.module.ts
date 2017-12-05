import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {RouterModule} from "@angular/router";
import {AngularOpenlayersModule} from "angular2-openlayers";


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule,
    // ClarityModule.forRoot(),
    BrowserAnimationsModule,
    AngularOpenlayersModule,
  ],
  providers: [

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
