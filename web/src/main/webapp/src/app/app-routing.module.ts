import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {ClientsComponent} from "./clients/clients.component";
import {AddClientComponent} from "./clients/add-client/add-client.component";
import {UpdateClientComponent} from "./clients/update-client/update-client.component";


import {PhotographersComponent} from "./photographers/photographers.component";
import {AddPhotographerComponent} from "./photographers/add-photographer/add-photographer.component";
import {UpdatePhotographerComponent} from "./photographers/update-photographer/update-photographer.component";

import {AlbumsComponent} from "./albums/albums.component";
import {AddAlbumComponent} from "./albums/add-album/add-album.component";
import {UpdateAlbumComponent} from "./albums/update-album/update-album.component";

import {UpdatePictureComponent} from "./pictures/update-picture/update-picture.component";
import {PicturesComponent} from "./pictures/pictures.component";
import {AddPictureComponent} from "./pictures/add-picture/add-picture.component";




const routes: Routes = [
  // { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'clients', component: ClientsComponent},
  {path: 'add-client', component: AddClientComponent},
  {path: 'clients/update/:id', component: UpdateClientComponent},

  {path: 'photographers', component: PhotographersComponent},
  {path: 'add-photographer', component: AddPhotographerComponent},
  {path: 'photographers/update/:id', component: UpdatePhotographerComponent},

  {path: 'albums', component: AlbumsComponent},
  {path: 'add-album', component: AddAlbumComponent},
  {path: 'albums/update/:id', component: UpdateAlbumComponent},

  {path: 'pictures', component: PicturesComponent},
  {path: 'add-picture', component: AddPictureComponent},
  {path: 'pictures/update/:id', component: UpdatePictureComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
