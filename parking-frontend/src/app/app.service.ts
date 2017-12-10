import {Router} from "@angular/router";
import {Injectable} from "@angular/core";
import 'rxjs/add/operator/toPromise';
import {Headers, Http, RequestOptions, Response} from "@angular/http";

@Injectable()
export class AppService {
  constructor(private http: Http, private router: Router) {
  }

  getDirection(coordinate, radius): Promise<Response> {
    return this.http.post('http://localhost:8080/route/' + radius, coordinate).toPromise();
  }
}
