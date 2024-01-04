import { Component, OnInit } from '@angular/core';
import {PhotographerService} from "../shared.photographer/photographer.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ageValidator} from "../shared.photographer/age-validator";
import {ratingValidator} from "../shared.photographer/rating-validator";
import {Photographer} from "../shared.photographer/photographer.model";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-update-photographer',
  templateUrl: './update-photographer.component.html',
  styleUrls: ['./update-photographer.component.css']
})
export class UpdatePhotographerComponent implements OnInit {

  id: number;
  photographer: Photographer;

  updateResponse = '';

  constructor(private route: ActivatedRoute, private photographerService: PhotographerService, private formBuilder: FormBuilder) {
  }

  updateForm: FormGroup = this.formBuilder.group({
    "id": [""],
    "name": ["", Validators.required],
    "age": ["", [Validators.required, ageValidator()]],
    "cameraBrand": ["", Validators.required],
    "rating": ["", [Validators.required, ratingValidator()]]
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
    this.photographerService.getPhotographer(this.id).subscribe(
      photographer => {
        this.photographer = photographer;
        console.log(photographer);
      }
    );
  }

  updatePhotographer(){
    const id = this.updateForm.get("id")?.value;
    const name = this.updateForm.get("name")?.value;
    const age = this.updateForm.get("age")?.value;
    const cameraBrand = this.updateForm.get("cameraBrand")?.value;
    const rating = this.updateForm.get("rating")?.value;
    const photographer: Photographer = <Photographer>{id, name, age, cameraBrand, rating};
    this.photographerService.updatePhotographer(photographer)
      .subscribe( (response =>{
          if(response === null){
            this.updateResponse = "Photographer updated successfully";
          }
        }),
        (error =>{
            this.updateResponse = "Photographer was not updated";}
        )
      );
  }

}
