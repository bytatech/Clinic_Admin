import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ClinicLenusSharedModule } from '../../shared';
import {
    ClinicPatientDataService,
    ClinicPatientDataPopupService,
    ClinicPatientDataComponent,
    ClinicPatientDataDetailComponent,
    ClinicPatientDataDialogComponent,
    ClinicPatientDataPopupComponent,
    ClinicPatientDataDeletePopupComponent,
    ClinicPatientDataDeleteDialogComponent,
    clinicPatientDataRoute,
    clinicPatientDataPopupRoute,
} from './';

const ENTITY_STATES = [
    ...clinicPatientDataRoute,
    ...clinicPatientDataPopupRoute,
];

@NgModule({
    imports: [
        ClinicLenusSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ClinicPatientDataComponent,
        ClinicPatientDataDetailComponent,
        ClinicPatientDataDialogComponent,
        ClinicPatientDataDeleteDialogComponent,
        ClinicPatientDataPopupComponent,
        ClinicPatientDataDeletePopupComponent,
    ],
    entryComponents: [
        ClinicPatientDataComponent,
        ClinicPatientDataDialogComponent,
        ClinicPatientDataPopupComponent,
        ClinicPatientDataDeleteDialogComponent,
        ClinicPatientDataDeletePopupComponent,
    ],
    providers: [
        ClinicPatientDataService,
        ClinicPatientDataPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClinicLenusClinicPatientDataModule {}
