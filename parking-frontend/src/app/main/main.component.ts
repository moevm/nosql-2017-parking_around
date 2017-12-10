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
  radius: number = 5;

  init() {
    this.coordinate.latitude = 59.991169;
    this.coordinate.longitude = 30.319998;
  }

  click(event) {
    this.coordinatesInRad = [];
    this.route = [];
    let arr = event.coordinate;
    let newCoord = proj4('EPSG:3857', 'EPSG:4326').forward(arr);
    this.coordinate.longitude = newCoord[0];
    this.coordinate.latitude = newCoord[1];
    this.getPoint(this.coordinate.latitude, this.coordinate.longitude, 10);
    console.log(this.coordinatesInRad);
  }

  getPoint(lat, lon, r) {
    let R = 6371; // Radius of the earth in km
    let d_lat = Math.PI * R / 180;
    let d_long = Math.cos(lat) * Math.PI * R / 180;
    let maxChangeLatitude = r / d_lat;
    let maxChangeLongitude = r / d_long;
    let i = 0;
    while (i <= 4) {
      let newLat = lat + (2 * Math.random() - 1) * maxChangeLatitude;
      let newLong = lon + (2 * Math.random() - 1) * maxChangeLongitude;
      let coord = this.createCoordinate(newLat, newLong);
      this.coordinatesInRad.push(coord);
      i++;
    }
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
    this.appService.getDirection(this.radius, this.coordinate)
      .then((data) => {
      this.routeList = data.json() as ICoordinate[];
      this.route = this.convertListCoordinateInArray(this.routeList);
    }).catch(() => {
      this.errorFlag = true;
    });
  }
}
