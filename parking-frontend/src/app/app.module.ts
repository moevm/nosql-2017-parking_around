import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {AngularOpenlayersModule} from "ngx-openlayers";
import {MainComponent} from './main/main.component';
import {AppRoutingModule} from "./app-routing.module";
import {ClarityModule} from "clarity-angular";
import {HttpModule} from "@angular/http";
import {AppService} from "./app.service";
import * as ol from 'openlayers';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule,
    AppRoutingModule,
    ClarityModule.forRoot(),
    BrowserAnimationsModule,
    AngularOpenlayersModule,
  ],
  providers: [
    AppService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
