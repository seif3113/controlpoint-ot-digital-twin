import { Component, inject, ViewChild } from '@angular/core';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { AssetFormDialogComponent } from '../asset-form-dialog/asset-form-dialog.component';
import { MaterialModule } from '../../shared/material.module';
import { RouterLink } from '@angular/router';
import { Asset } from '../../models/assest.model';
import { AssetService } from '../../services/asset.service';
import { LoadingOverlayComponent } from '../../shared/loading-overlay/loading-overlay.component';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { ErrorComponent } from '../../shared/error/error.component';

@Component({
  selector: 'app-asset-list',
  imports: [
    MaterialModule,
    RouterLink,
    LoadingOverlayComponent,
    MatPaginator,
    MatPaginatorModule,
    ErrorComponent,
  ],
  templateUrl: './asset-list.component.html',
  styleUrl: './asset-list.component.css',
})
export class AssetListComponent {
  loading = true;
  error = null;
  intervalId: any;
  displayedColumns: string[] = ['id', 'name', 'type', 'status'];
  assets = new MatTableDataSource<Asset>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  assetService = inject(AssetService);
  readonly dialog = inject(MatDialog);

  ngOnInit() {
    this.loadAssets();
    this.intervalId = setInterval(() => {
      this.loadAssets();
    }, 5000);
  }

  loadAssets() {
    this.assetService.getAllAssets().subscribe({
      next: (data) => {
        this.loading = false;
        this.assets.data = data;
        this.assets.paginator = this.paginator;
      },
      error: (err) => {
        this.error = err;
        this.loading = false;
        console.error('Error loading assets:', err);
      },
    });
  }

  addData() {
    const dialogRef = this.dialog.open(AssetFormDialogComponent);

    dialogRef.afterClosed().subscribe((result) => {
      if (result === 'success') {
        this.loadAssets();
      }
    });
  }

  getStatusClass(status: string): string {
    switch (status) {
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
