import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Album} from "../shared.album/album.model";
import {AlbumService} from "../shared.album/album.service";

@Component({
  selector: 'app-update-album',
  templateUrl: './update-album.component.html',
  styleUrls: ['./update-album.component.css']
})
export class UpdateAlbumComponent implements OnInit {

  id: number;
  album: Album;

  updateResponse = '';

  constructor(private route: ActivatedRoute, private albumService: AlbumService, private formBuilder: FormBuilder) {
  }

  updateForm: FormGroup = this.formBuilder.group({
    "id": [""],
    "photoSessionName": ["", Validators.required],
    "clientId": ["", Validators.required],
    "photographerId": ["", Validators.required]
  });

  ngOnInit(): void {
    this.route.params.subscribe(
      params => {
        this.id = +params.id;
        console.log(this.id);
        this.setForm();
      }
    );
  }

  setForm(): void {
    this.albumService.getAlbum(this.id).subscribe(
      album => {
        this.album = album;
        console.log(album);
      }
    );
  }

  updateAlbum(){
    const id = this.updateForm.get("id")?.value;
    const photoSessionName = this.updateForm.get("photoSessionName")?.value;
    const clientId = this.updateForm.get("clientId")?.value;
    const photographerId = this.updateForm.get("photographerId")?.value;
    const album: Album = <Album>{id,photoSessionName, clientId, photographerId};
    this.albumService.updateAlbum(album)
      .subscribe( (response =>{
          if(response === null){
            this.updateResponse = "Album updated successfully";
          }
        }),
        (error =>{
            this.updateResponse = "Album was not updated";}
        )
      );
  }

}
