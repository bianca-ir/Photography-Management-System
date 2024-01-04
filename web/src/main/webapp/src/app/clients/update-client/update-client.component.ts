import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Client} from "../shared.clients/client.model";
import {ClientService} from "../shared.clients/client.service";
import {emailValidator} from "../shared.clients/email-validator";
import {phoneNumberValidator} from "../shared.clients/phoneNumber-validator";

@Component({
  selector: 'app-update-client',
  templateUrl: './update-client.component.html',
  styleUrls: ['./update-client.component.css']
})
export class UpdateClientComponent implements OnInit {

  id: number;
  client: Client;

  updateResponse = '';

  constructor(private route: ActivatedRoute, private clientService: ClientService, private formBuilder: FormBuilder) {
  }

  updateForm: FormGroup = this.formBuilder.group({
    "id": [""],
    "name": ["", Validators.required],
    "email": ["", [Validators.required, emailValidator()]],
    "phoneNumber": ["", [Validators.required, phoneNumberValidator()]]
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
    this.clientService.getClient(this.id).subscribe(
      client => {
        this.client = client;
        console.log(client);
      }
    );
  }

  updateClient(){
    const id = this.updateForm.get("id")?.value;
    const name = this.updateForm.get("name")?.value;
    const email = this.updateForm.get("email")?.value;
    const phoneNumber = this.updateForm.get("phoneNumber")?.value;
    const client: Client = <Client>{id, name, email, phoneNumber};
    this.clientService.updateClient(client)
      .subscribe( (response =>{
          if(response === null){
            this.updateResponse = "Client updated successfully";
          }
        }),
        (error =>{
            this.updateResponse = "Client was not updated";}
        )
      );
  }

}
