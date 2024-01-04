import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AlbumService} from "../shared.album/album.service";
import {Album} from "../shared.album/album.model";

@Component({
  selector: 'app-add-album',
  templateUrl: './add-album.component.html',
  styleUrls: ['./add-album.component.css']
})
export class AddAlbumComponent implements OnInit {
  album: Album = new Album();

  addResponse = '';

  constructor(private albumService: AlbumService) {
  }


  ngOnInit(): void {
  }

  saveAlbum(){
    this.albumService.saveAlbum(this.album)
      .subscribe( (response =>{
          if(response === null){
            this.addResponse = "Album added successfully";
          }
        }),
        (error =>{
            this.addResponse = "Album was not added";}
        )
      );
  }

}
