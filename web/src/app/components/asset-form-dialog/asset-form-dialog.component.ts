import {
  ChangeDetectionStrategy,
  Component,
  inject,
  output,
} from '@angular/core';
import { MaterialModule } from '../../shared/material.module';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AssetService } from '../../services/asset.service';
import { MatDialogRef } from '@angular/material/dialog';
import { LoadingOverlayComponent } from "../../shared/loading-overlay/loading-overlay.component";

@Component({
  selector: 'dialog-elements-example-dialog',
  templateUrl: 'asset-form-dialog.component.html',
  styleUrl: 'asset-form-dialog.component.css',
  imports: [MaterialModule, ReactiveFormsModule, LoadingOverlayComponent],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AssetFormDialogComponent {
  loading = false;
  addAssetForm = new FormGroup({
    name: new FormControl('', Validators.required),
    type: new FormControl('', Validators.required),
    status: new FormControl(null, Validators.required),
  });

  assetService = inject(AssetService);
  dialogRef = inject(MatDialogRef<AssetFormDialogComponent>);

  addAsset() {
    this.loading = true;
    const newAsset = {
      name: this.addAssetForm.value.name!,
      type: this.addAssetForm.value.type!,
      status: this.addAssetForm.value.status!,
    };

    this.assetService.createAsset(newAsset).subscribe({
      next: (response) => {
        console.log('Asset created:', response);
          this.loading = false;
          this.dialogRef.close('success');
      },
      error: (err) => {
        console.error('Error creating asset:', err);
        // Optionally show error message to user
      },
    });
  }
}
