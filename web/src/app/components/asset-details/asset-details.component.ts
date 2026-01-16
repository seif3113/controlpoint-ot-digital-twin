// asset-details.component.ts
import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MaterialModule } from '../../shared/material.module';
import { AssetService } from '../../services/asset.service';
import { SensorService } from '../../services/sensor.service';
import { LoadingOverlayComponent } from '../../shared/loading-overlay/loading-overlay.component';
import { ErrorComponent } from '../../shared/error/error.component';
import { H } from '@angular/cdk/keycodes';

@Component({
  selector: 'app-asset-details',
  standalone: true,
  imports: [MaterialModule, LoadingOverlayComponent, ErrorComponent],
  templateUrl: './asset-details.component.html',
  styleUrls: ['./asset-details.component.css'],
})
export class AssetDetailsComponent implements OnInit {
  asset: any = null;
  error = null;
  errorMessage = '';
  intervalId: any;
  sensorReading: any = null;
  loading = true;
  assetId: number = 0;

  assetService = inject(AssetService);
  sensorService = inject(SensorService);

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.assetId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadAssetDetails();
    this.loadSensorReading();

    this.intervalId = setInterval(() => {
      this.loadSensorReading();
      this.loadAssetDetails();
    }, 5000);
  }

  loadAssetDetails(): void {
    this.assetService.getAssetById(this.assetId).subscribe({
      next: (data) => {
        this.asset = data;
        this.loading = false;
      },
      error: (err) => {
        this.asset = null;
        this.loading = false;
        this.error = err;
        this.errorMessage =
          err.status === 404 ? 'Asset Not Found' : 'Something Went Wrong';
        console.error('Error loading assets:', err);
      },
    });
  }

  loadSensorReading(): void {
    this.sensorService.getLatestReading(this.assetId).subscribe({
      next: (data) => {
        this.sensorReading = data;
        this.loading = false;
      },
      error: (err) => {
        this.loading = false;
        this.error = err;
        console.error('Error loading sensorReading:', err);
      },
    });
  }

  goBack(): void {
    this.router.navigate(['/assets']);
  }

  getStatusClass(): string {
    if (!this.asset) return '';
    switch (this.asset.status) {
      case 'RUNNING':
        return 'status-running';
      case 'STOPPED':
        return 'status-stopped';
      case 'ALARM':
        return 'status-alarm';
      default:
        return '';
    }
  }

  ngOnDestroy(): void {
    clearInterval(this.intervalId);
  }
}
