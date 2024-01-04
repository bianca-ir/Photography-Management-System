import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Picture} from "../shared/picture.model";
import {PictureService} from "../shared/picture.service";
import {widthValidator} from "../shared/width-validator";
import {heightValidator} from "../shared/height-validator";


@Component({
  selector: 'app-update-picture',
  templateUrl: './update-picture.component.html',
  styleUrls: ['./update-picture.component.css']
})
export class UpdatePictureComponent implements OnInit {

  id: number;
  picture: Picture;

  updateResponse = '';

  constructor(private route: ActivatedRoute, private pictureService: PictureService, private formBuilder: FormBuilder) {
  }

  updateForm: FormGroup = this.formBuilder.group({
    "id": [""],
    "title": ["", Validators.required],
    "description": ["", Validators.required],
    "width": ["", [Validators.required, widthValidator()]],
    "height": ["", [Validators.required, heightValidator()]]
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
    this.pictureService.getPicture(this.id).subscribe(
      picture => {
        this.picture = picture;
        console.log(picture);
      }
    );
  }

  updatePicture(){
    const id = this.updateForm.get("id")?.value;
    const title = this.updateForm.get("title")?.value;
    const description = this.updateForm.get("description")?.value;
    const width = this.updateForm.get("width")?.value;
    const height = this.updateForm.get("height")?.value;
    const picture: Picture = <Picture>{id, title, description, width, height};
    this.pictureService.savePicture(picture)
      .subscribe( (response =>{
          if(response === null){
            this.updateResponse = "Picture updated successfully";
          }
        }),
        (error =>{
            this.updateResponse = "Picture was not updated";}
        )
      );
  }

}
