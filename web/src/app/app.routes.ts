import { Routes } from '@angular/router';
import { AssetListComponent } from './components/asset-list/asset-list.component';
import { AssetDetailsComponent } from './components/asset-details/asset-details.component';

export const routes: Routes = [
  { path: '', redirectTo: 'assets', pathMatch: 'full' },
  { path: 'assets', title: 'Assets', component: AssetListComponent },
  {
    path: 'assets/:id',
    title: 'Asset Details',
    component: AssetDetailsComponent,
  },
];
