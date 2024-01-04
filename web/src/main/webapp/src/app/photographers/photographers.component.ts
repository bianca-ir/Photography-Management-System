import { Component, OnInit } from '@angular/core';
import {Photographer} from "./shared.photographer/photographer.model";
import {PhotographerService} from "./shared.photographer/photographer.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-photographers',
  templateUrl: './photographers.component.html',
  styleUrls: ['./photographers.component.css']
})
export class PhotographersComponent implements OnInit {

  photographers: Photographer[];

  order = true;


  constructor(private photographerService: PhotographerService, private router: Router) { }

  ngOnInit(): void {
    this.getPhotographers();
  }

  getPhotographers(): void{
    this.photographerService.getPhotographers()
      .subscribe(photographers => this.photographers = photographers);
  }

  updatePhotographer(id: number): void{
    this.router.navigate(['/photographers/update', id]);
  }

  deletePhotographer(id: number): void{
    this.photographerService.deletePhotographer(id).subscribe(
      response =>{
        console.log("delete photographer response: " + response);
        this.getPhotographers();
      }
    )
  }

  sortPhotographers(columnNumber: number): void{
    var sortedPhotographers: Photographer[] = this.photographers.sort((p1,p2) => {
      if(columnNumber == 0){
        if (p1.id > p2.id) {
          if(this.order)
            return 1;
          else
            return -1;
        }

        if (p1.id < p2.id) {
          if(this.order)
            return -1;
          else
            return 1;
        }

        return 0;
      }
      if(columnNumber == 1){
        if (p1.name > p2.name) {
          if(this.order)
            return 1;
          else
            return -1;
        }

        if (p1.name < p2.name) {
          if(this.order)
            return -1;
          else
            return 1;
        }

        return 0;
      }
      if(columnNumber == 2){
        if (p1.age > p2.age) {
          if(this.order)
            return 1;
          else
            return -1;
        }

        if (p1.age < p2.age) {
          if(this.order)
            return -1;
          else
            return 1;
        }

        return 0;
      }
      if(columnNumber == 3){
        if (p1.cameraBrand > p2.cameraBrand) {
          if(this.order)
            return 1;
          else
            return -1;
        }

        if (p1.cameraBrand < p2.cameraBrand) {
          if(this.order)
            return -1;
          else
            return 1;
        }

        return 0;
      }
      if(columnNumber == 4){
        if (p1.rating > p2.rating) {
          if(this.order)
            return 1;
          else
            return -1;
        }

        if (p1.rating < p2.rating) {
          if(this.order)
            return -1;
          else
            return 1;
        }

        return 0;
      }
    });
    this.order = !this.order;
    this.photographers = sortedPhotographers;
  }

}
