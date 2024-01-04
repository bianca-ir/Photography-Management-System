import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Picture} from "./shared/picture.model";
import {PictureService} from "./shared/picture.service";

@Component({
  selector: 'app-pictures',
  templateUrl: './pictures.component.html',
  styleUrls: ['./pictures.component.css']
})
export class PicturesComponent implements OnInit {

  pictures: Picture[];


  constructor(private pictureService: PictureService, private router: Router) { }

  ngOnInit(): void {
    this.getPictures();
  }

  getPictures(): void{
    this.pictureService.getPictures()
      .subscribe(pictures => this.pictures = pictures);
  }

  updatePicture(id: number): void{
    this.router.navigate(['/pictures/update', id]);
  }

  deletePicture(id: number): void{
    this.pictureService.deletePicture(id).subscribe(
      response =>{
        console.log("delete picture response: " + response);
        this.getPictures();
      }
    )
  }

}
