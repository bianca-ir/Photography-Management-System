import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule} from "./app-routing.module";

import { AppComponent } from './app.component';
import {RouterModule} from "@angular/router";

import { ClientsComponent } from './clients/clients.component';
import { AddClientComponent } from './clients/add-client/add-client.component';
import { UpdateClientComponent } from './clients/update-client/update-client.component';
import { ClientService } from "./clients/shared.clients/client.service";

import { PhotographersComponent } from './photographers/photographers.component';
import { AddPhotographerComponent } from './photographers/add-photographer/add-photographer.component';
import { UpdatePhotographerComponent } from './photographers/update-photographer/update-photographer.component';
import { PhotographerService } from "./photographers/shared.photographer/photographer.service";

import { AlbumsComponent } from './albums/albums.component';
import { AddAlbumComponent } from './albums/add-album/add-album.component';
import { UpdateAlbumComponent } from './albums/update-album/update-album.component';
import { AlbumService } from "./albums/shared.album/album.service";

import { PicturesComponent } from './pictures/pictures.component';
import { AddPictureComponent } from './pictures/add-picture/add-picture.component';
import { UpdatePictureComponent } from './pictures/update-picture/update-picture.component';
import { PictureService } from "./pictures/shared/picture.service";


@NgModule({
  declarations: [
    AppComponent,

    ClientsComponent,
    AddClientComponent,
    UpdateClientComponent,

    PhotographersComponent,
    AddPhotographerComponent,
    UpdatePhotographerComponent,

    AlbumsComponent,
    AddAlbumComponent,
    UpdateAlbumComponent,

    PicturesComponent,
    AddPictureComponent,
    UpdatePictureComponent,

  ],
  imports: [
    BrowserModule,
    RouterModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [ClientService, PhotographerService, AlbumService, PictureService],
  bootstrap: [AppComponent]
})
export class AppModule { }
