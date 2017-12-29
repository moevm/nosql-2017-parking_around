import {Component, OnInit} from '@angular/core';
import {ICoordinate} from "../interface/coordinate";
import * as proj4x from 'proj4';
import {AppService} from "../app.service";
let proj4 = (proj4x as any).default;

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  constructor(private appService: AppService) {
  }

  ngOnInit() {
    this.init();
  }

  coordinate: ICoordinate = {} as ICoordinate;
  coordinatesInRad: ICoordinate[] = [];
  route: [number, number][] = [];
  routeList: ICoordinate[];
  errorFlag: boolean = false;
  radius: number = 500;
  loading: boolean = false;
  finish: boolean = false;
n
  init() {
    this.coordinate.latitude = 59.97732097913405;
    this.coordinate.longitude = 30.319445246184046;
  }

  click(event) {
    this.finish = false;
    this.errorFlag = false;
    this.coordinatesInRad = [];
    this.route = [];
    let arr = event.coordinate;
    let newCoord = proj4('EPSG:3857', 'EPSG:4326').forward(arr);
    this.coordinate.longitude = newCoord[0];
    this.coordinate.latitude = newCoord[1];
    // this.getPoint(this.coordinate.latitude, this.coordinate.longitude, 10);
    // console.log(this.coordinatesInRad);
  }

  private distance(lat1, lon1, lat2, lon2) {
    let p = 0.017453292519943295;    // Math.PI / 180
    let c = Math.cos;
    let a = 0.5 - c((lat2 - lat1) * p)/2 +
      c(lat1 * p) * c(lat2 * p) *
      (1 - c((lon2 - lon1) * p))/2;

    return 12742 * Math.asin(Math.sqrt(a)); // 2 * R; R = 6371 km
  }


  private createCoordinate(lat, long) {
    return {
      latitude: lat,
      longitude: long,
    } as ICoordinate;
  }

  convertListCoordinateInArray(list) {
    let newArray: [number, number][] = [];
    list.forEach((item) => {
        newArray.push([item.longitude, item.latitude]);
      }
    );
    return newArray;
  }

  getDirection() {
    this.loading = true;
    this.appService.getDirection(this.radius, this.coordinate)
      .then((data) => {
        this.routeList = data.json() as ICoordinate[];
        this.route = this.convertListCoordinateInArray(this.routeList);
        console.log(this.route);
        this.loading = false;
        this.finish = true;
      }).catch(() => {
      this.errorFlag = true;
      this.loading = false;
      this.finish = true;
    });
  }

  setRad(event) {
    console.log(event.feature);
    console.log(event.feature.O.geometry.o);
    let arr = event.feature.getGeometry().A;
    console.log(arr);
    let arr1 = [arr[0],arr[1]];
    let arr2 = [arr[2],arr[3]];
    let newCoord = proj4('EPSG:3857', 'EPSG:4326').forward(arr1);
    let newCoord2 = proj4('EPSG:3857', 'EPSG:4326').forward(arr2);
    console.log(newCoord);
    console.log(newCoord2);
    let distance = this.distance(this.coordinate.latitude,this.coordinate.longitude,arr1[1],arr1[0]);
    console.log(distance);
  }
}
