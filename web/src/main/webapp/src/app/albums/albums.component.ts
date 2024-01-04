import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Album} from "./shared.album/album.model";
import {AlbumService} from "./shared.album/album.service";

@Component({
  selector: 'app-albums',
  templateUrl: './albums.component.html',
  styleUrls: ['./albums.component.css']
})
export class AlbumsComponent implements OnInit {
  albums: Album[];


  constructor(private albumService: AlbumService, private router: Router) { }

  ngOnInit(): void {
    this.getAlbums();
  }

  getAlbums(): void{
    this.albumService.getAlbums()
      .subscribe(albums => this.albums = albums);
  }

  updateAlbum(id: number): void{
    this.router.navigate(['/albums/update', id]);
  }

  deleteAlbum(id: number): void{
    this.albumService.deleteAlbum(id).subscribe(
      response =>{
        console.log("delete album response: " + response);
        this.getAlbums();
      }
    )
  }


}
