import { Component } from '@angular/core';
import { MaterialModule } from "../material.module";

@Component({
  selector: 'app-loading-overlay',
  imports: [MaterialModule],
  templateUrl: './loading-overlay.component.html',
  styleUrl: './loading-overlay.component.css'
})
export class LoadingOverlayComponent {

}
