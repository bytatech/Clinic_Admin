import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ClinicLenusSharedModule } from '../../shared';
import {
    ClinicDetailsService,
    ClinicDetailsPopupService,
    ClinicDetailsComponent,
    ClinicDetailsDetailComponent,
    ClinicDetailsDialogComponent,
    ClinicDetailsPopupComponent,
    ClinicDetailsDeletePopupComponent,
    ClinicDetailsDeleteDialogComponent,
    clinicDetailsRoute,
    clinicDetailsPopupRoute,
} from './';

const ENTITY_STATES = [
    ...clinicDetailsRoute,
    ...clinicDetailsPopupRoute,
];

@NgModule({
    imports: [
        ClinicLenusSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ClinicDetailsComponent,
        ClinicDetailsDetailComponent,
        ClinicDetailsDialogComponent,
        ClinicDetailsDeleteDialogComponent,
        ClinicDetailsPopupComponent,
        ClinicDetailsDeletePopupComponent,
    ],
    entryComponents: [
        ClinicDetailsComponent,
        ClinicDetailsDialogComponent,
        ClinicDetailsPopupComponent,
        ClinicDetailsDeleteDialogComponent,
        ClinicDetailsDeletePopupComponent,
    ],
    providers: [
        ClinicDetailsService,
        ClinicDetailsPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClinicLenusClinicDetailsModule {}
