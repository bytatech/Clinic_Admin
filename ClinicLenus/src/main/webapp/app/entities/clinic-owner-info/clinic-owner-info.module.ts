import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ClinicLenusSharedModule } from '../../shared';
import {
    ClinicOwnerInfoService,
    ClinicOwnerInfoPopupService,
    ClinicOwnerInfoComponent,
    ClinicOwnerInfoDetailComponent,
    ClinicOwnerInfoDialogComponent,
    ClinicOwnerInfoPopupComponent,
    ClinicOwnerInfoDeletePopupComponent,
    ClinicOwnerInfoDeleteDialogComponent,
    clinicOwnerInfoRoute,
    clinicOwnerInfoPopupRoute,
} from './';

const ENTITY_STATES = [
    ...clinicOwnerInfoRoute,
    ...clinicOwnerInfoPopupRoute,
];

@NgModule({
    imports: [
        ClinicLenusSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ClinicOwnerInfoComponent,
        ClinicOwnerInfoDetailComponent,
        ClinicOwnerInfoDialogComponent,
        ClinicOwnerInfoDeleteDialogComponent,
        ClinicOwnerInfoPopupComponent,
        ClinicOwnerInfoDeletePopupComponent,
    ],
    entryComponents: [
        ClinicOwnerInfoComponent,
        ClinicOwnerInfoDialogComponent,
        ClinicOwnerInfoPopupComponent,
        ClinicOwnerInfoDeleteDialogComponent,
        ClinicOwnerInfoDeletePopupComponent,
    ],
    providers: [
        ClinicOwnerInfoService,
        ClinicOwnerInfoPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClinicLenusClinicOwnerInfoModule {}
