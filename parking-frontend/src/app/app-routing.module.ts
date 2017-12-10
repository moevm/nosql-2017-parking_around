import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {MainComponent} from "./main/main.component";

const appRoutes: Routes = [
  {path: '', redirectTo: 'app-main', pathMatch: 'full'},
  {path: 'app-main', component: MainComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
