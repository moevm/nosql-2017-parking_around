import {Headers, Http, RequestOptions, Response} from "@angular/http";
import {Injectable} from "@angular/core";
import 'rxjs/add/operator/toPromise';
import {Router} from "@angular/router";

@Injectable()
export class AppService {
  constructor(private http: Http, private router: Router) {
  }

  getDirection(radius, coordinate): Promise<Response> {
    return this.http.get('http://localhost:8080/route/build/' + radius + '?latitude='
      + coordinate.latitude + '&longitude=' + coordinate.longitude).toPromise();
  }
}
