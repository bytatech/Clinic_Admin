import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ClinicLenusSharedModule } from '../../shared';
import {
    MedicineSystemService,
    MedicineSystemPopupService,
    MedicineSystemComponent,
    MedicineSystemDetailComponent,
    MedicineSystemDialogComponent,
    MedicineSystemPopupComponent,
    MedicineSystemDeletePopupComponent,
    MedicineSystemDeleteDialogComponent,
    medicineSystemRoute,
    medicineSystemPopupRoute,
} from './';

const ENTITY_STATES = [
    ...medicineSystemRoute,
    ...medicineSystemPopupRoute,
];

@NgModule({
    imports: [
        ClinicLenusSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        MedicineSystemComponent,
        MedicineSystemDetailComponent,
        MedicineSystemDialogComponent,
        MedicineSystemDeleteDialogComponent,
        MedicineSystemPopupComponent,
        MedicineSystemDeletePopupComponent,
    ],
    entryComponents: [
        MedicineSystemComponent,
        MedicineSystemDialogComponent,
        MedicineSystemPopupComponent,
        MedicineSystemDeleteDialogComponent,
        MedicineSystemDeletePopupComponent,
    ],
    providers: [
        MedicineSystemService,
        MedicineSystemPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClinicLenusMedicineSystemModule {}
