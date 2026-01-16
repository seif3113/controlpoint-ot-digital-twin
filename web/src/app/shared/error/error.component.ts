import { Component, Input } from '@angular/core';
import { MaterialModule } from "../material.module";
import { Router } from '@angular/router';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css'],
  imports: [MaterialModule]
})
export class ErrorComponent {
  // The error message to show
  @Input() message: string = 'Something went wrong';
  constructor(private router: Router) {}
}
