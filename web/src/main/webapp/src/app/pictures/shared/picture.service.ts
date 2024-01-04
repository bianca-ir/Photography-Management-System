import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Picture} from "./picture.model";

@Injectable({
  providedIn: 'root'
})
export class PictureService {
  private picturesUrl = 'http://localhost:8080/Gradle___org_example___web_1_0_SNAPSHOT_war/api/pictures';

  constructor(private httpClient: HttpClient) { }

  getPictures(): Observable<Picture[]>{
    return this.httpClient
      .get<Array<Picture>>(this.picturesUrl);
  }

  getPicture(id: number): Observable<Picture>{
    return this.getPictures()
      .pipe(
        map(pictures => pictures.find(picture => picture.id === id))
      );
  }

  savePicture(picture: Picture):Observable<any>{
    return this.httpClient
      .post<Picture>(this.picturesUrl, picture);
  }

  updatePicture(picture: Picture): Observable<any>{
    const url = `${this.picturesUrl}/${picture.id}`;
    return this.httpClient
      .put<Picture>(url, picture);
  }

  deletePicture(id: number): Observable<any>{
    const url = `${this.picturesUrl}/${id}`;
    return this.httpClient
      .delete(url);
  }

  //filterPictures()
  //sortPictures()
}
