import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { AssetListComponent } from './components/asset-list/asset-list.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, MatToolbarModule, MatIconModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
  appName = 'AssetVision';
}
