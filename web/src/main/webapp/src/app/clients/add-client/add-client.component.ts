import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ClientService} from "../shared.clients/client.service";
import {Client} from "../shared.clients/client.model";
import {emailValidator} from "../shared.clients/email-validator";
import {phoneNumberValidator} from "../shared.clients/phoneNumber-validator";

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css']
})
export class AddClientComponent implements OnInit {

  addResponse = '';

  constructor(private clientService: ClientService, private formBuilder: FormBuilder) {
  }

  addForm: FormGroup;

  ngOnInit(): void {
    this.addForm = this.formBuilder.group({
      "name": ["", Validators.required],
      "email": ["", [Validators.required, emailValidator()]],
      "phoneNumber": ["", [Validators.required, phoneNumberValidator()]]
    });
  }

  saveClient(){
    const name = this.addForm.get("name")?.value;
    const email = this.addForm.get("email")?.value;
    const phoneNumber = this.addForm.get("phoneNumber")?.value;
    const client: Client = <Client>{name, email, phoneNumber};
    this.clientService.saveClient(client)
      .subscribe( (response =>{
          if(response === null){
            this.addResponse = "Client added successfully";
          }
        }),
        (error =>{
            this.addResponse = "Client was not added";}
        )
      );
  }

}
