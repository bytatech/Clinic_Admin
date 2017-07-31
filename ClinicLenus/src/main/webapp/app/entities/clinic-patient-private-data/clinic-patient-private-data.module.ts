import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ClinicLenusSharedModule } from '../../shared';
import {
    ClinicPatientPrivateDataService,
    ClinicPatientPrivateDataPopupService,
    ClinicPatientPrivateDataComponent,
    ClinicPatientPrivateDataDetailComponent,
    ClinicPatientPrivateDataDialogComponent,
    ClinicPatientPrivateDataPopupComponent,
    ClinicPatientPrivateDataDeletePopupComponent,
    ClinicPatientPrivateDataDeleteDialogComponent,
    clinicPatientPrivateDataRoute,
    clinicPatientPrivateDataPopupRoute,
} from './';

const ENTITY_STATES = [
    ...clinicPatientPrivateDataRoute,
    ...clinicPatientPrivateDataPopupRoute,
];

@NgModule({
    imports: [
        ClinicLenusSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ClinicPatientPrivateDataComponent,
        ClinicPatientPrivateDataDetailComponent,
        ClinicPatientPrivateDataDialogComponent,
        ClinicPatientPrivateDataDeleteDialogComponent,
        ClinicPatientPrivateDataPopupComponent,
        ClinicPatientPrivateDataDeletePopupComponent,
    ],
    entryComponents: [
        ClinicPatientPrivateDataComponent,
        ClinicPatientPrivateDataDialogComponent,
        ClinicPatientPrivateDataPopupComponent,
        ClinicPatientPrivateDataDeleteDialogComponent,
        ClinicPatientPrivateDataDeletePopupComponent,
    ],
    providers: [
        ClinicPatientPrivateDataService,
        ClinicPatientPrivateDataPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClinicLenusClinicPatientPrivateDataModule {}
