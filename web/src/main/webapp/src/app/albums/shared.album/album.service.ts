import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Album} from "./album.model";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AlbumService {
  private albumsUrl = 'http://localhost:8080/Gradle___org_example___web_1_0_SNAPSHOT_war/api/albums';

  constructor(private httpClient: HttpClient) { }

  getAlbums(): Observable<Album[]>{
    return this.httpClient
      .get<Array<Album>>(this.albumsUrl);
  }

  getAlbum(id: number): Observable<Album>{
    return this.getAlbums()
      .pipe(
        map(albums => albums.find(album => album.id === id))
      );
  }

  saveAlbum(album: Album):Observable<any>{
    return this.httpClient
      .post<Album>(this.albumsUrl, album);
  }

  updateAlbum(album: Album): Observable<any>{
    const url = `${this.albumsUrl}/${album.id}`;
    return this.httpClient
      .put<Album>(url, album);
  }

  deleteAlbum(id: number): Observable<any>{
    const url = `${this.albumsUrl}/${id}`;
    return this.httpClient
      .delete(url);
  }

  //filterAlbums()
  //sortAlbums()
}
