import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PictureService} from "../shared/picture.service";
import {Picture} from "../shared/picture.model";
import {widthValidator} from "../shared/width-validator";
import {heightValidator} from "../shared/height-validator";

@Component({
  selector: 'app-add-picture',
  templateUrl: './add-picture.component.html',
  styleUrls: ['./add-picture.component.css']
})
export class AddPictureComponent implements OnInit {

  addResponse = '';

  constructor(private pictureService: PictureService, private formBuilder: FormBuilder) {
  }

  addForm: FormGroup;

  ngOnInit(): void {
    this.addForm = this.formBuilder.group({
      "title": ["", Validators.required],
      "description": ["", Validators.required],
      "width": ["", [Validators.required, widthValidator()]],
      "height": ["", [Validators.required, heightValidator()]]
    });
  }

  savePicture(){
    const title = this.addForm.get("title")?.value;
    const description = this.addForm.get("description")?.value;
    const width = this.addForm.get("width")?.value;
    const height = this.addForm.get("height")?.value;
    const picture: Picture = <Picture>{title, description, width, height};
    this.pictureService.savePicture(picture)
      .subscribe( (response =>{
          if(response === null){
            this.addResponse = "Picture added successfully";
          }
        }),
        (error =>{
            this.addResponse = "Picture was not added";}
        )
      );
  }

}
