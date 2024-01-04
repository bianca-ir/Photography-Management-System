import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Client} from "./shared.clients/client.model";
import {ClientService} from "./shared.clients/client.service";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent implements OnInit {

  clients: Client[];

  filterForm: FormGroup;

  constructor(private clientService: ClientService, private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.filterForm = this.formBuilder.group({
      "name_filter": [""]
    });
    this.getClients();
  }

  getClients(): void{
    this.clientService.getClients()
      .subscribe(clients => this.clients = clients);
  }

  updateClient(id: number): void{
    this.router.navigate(['/clients/update', id]);
  }

  deleteClient(id: number): void{
    this.clientService.deleteClient(id).subscribe(
      response =>{
        console.log("delete client response: " + response);
        this.getClients();
      }
    )
  }

  filterClients(){
    const name_filter = this.filterForm.get("name_filter")?.value;
    this.clientService.filterClients(name_filter)
      .subscribe(clients => this.clients = clients);
  }

}
