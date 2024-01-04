import { Component, OnInit } from '@angular/core';
import {PhotographerService} from "../shared.photographer/photographer.service";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Photographer} from "../shared.photographer/photographer.model";
import {ageValidator} from "../shared.photographer/age-validator";
import {ratingValidator} from "../shared.photographer/rating-validator";

@Component({
  selector: 'app-add-photographer',
  templateUrl: './add-photographer.component.html',
  styleUrls: ['./add-photographer.component.css']
})
export class AddPhotographerComponent implements OnInit {

  addResponse = '';

  constructor(private photographerService: PhotographerService, private formBuilder: FormBuilder) {
  }

  addForm: FormGroup;

  ngOnInit(): void {
    this.addForm = this.formBuilder.group({
    "name": ["", Validators.required],
    "age": ["", [Validators.required, ageValidator()]],
    "cameraBrand": ["", Validators.required],
    "rating": ["", [Validators.required, ratingValidator()]]
  });
  }

  savePhotographer(){
    const name = this.addForm.get("name")?.value;
    const age = this.addForm.get("age")?.value;
    const cameraBrand = this.addForm.get("cameraBrand")?.value;
    const rating = this.addForm.get("rating")?.value;
    const photographer: Photographer = <Photographer>{name, age, cameraBrand, rating};
    this.photographerService.savePhotographer(photographer)
      .subscribe( (response =>{
        if(response === null){
          this.addResponse = "Photographer added successfully";
        }
      }),
        (error =>{
          this.addResponse = "Photographer was not added";}
        )
      );
  }

}
