import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ClinicLenusSharedModule } from '../../shared';
import {
    PrescriptionService,
    PrescriptionPopupService,
    PrescriptionComponent,
    PrescriptionDetailComponent,
    PrescriptionDialogComponent,
    PrescriptionPopupComponent,
    PrescriptionDeletePopupComponent,
    PrescriptionDeleteDialogComponent,
    prescriptionRoute,
    prescriptionPopupRoute,
} from './';

const ENTITY_STATES = [
    ...prescriptionRoute,
    ...prescriptionPopupRoute,
];

@NgModule({
    imports: [
        ClinicLenusSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        PrescriptionComponent,
        PrescriptionDetailComponent,
        PrescriptionDialogComponent,
        PrescriptionDeleteDialogComponent,
        PrescriptionPopupComponent,
        PrescriptionDeletePopupComponent,
    ],
    entryComponents: [
        PrescriptionComponent,
        PrescriptionDialogComponent,
        PrescriptionPopupComponent,
        PrescriptionDeleteDialogComponent,
        PrescriptionDeletePopupComponent,
    ],
    providers: [
        PrescriptionService,
        PrescriptionPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClinicLenusPrescriptionModule {}
