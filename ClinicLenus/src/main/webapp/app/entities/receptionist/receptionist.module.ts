import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ClinicLenusSharedModule } from '../../shared';
import {
    ReceptionistService,
    ReceptionistPopupService,
    ReceptionistComponent,
    ReceptionistDetailComponent,
    ReceptionistDialogComponent,
    ReceptionistPopupComponent,
    ReceptionistDeletePopupComponent,
    ReceptionistDeleteDialogComponent,
    receptionistRoute,
    receptionistPopupRoute,
} from './';

const ENTITY_STATES = [
    ...receptionistRoute,
    ...receptionistPopupRoute,
];

@NgModule({
    imports: [
        ClinicLenusSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ReceptionistComponent,
        ReceptionistDetailComponent,
        ReceptionistDialogComponent,
        ReceptionistDeleteDialogComponent,
        ReceptionistPopupComponent,
        ReceptionistDeletePopupComponent,
    ],
    entryComponents: [
        ReceptionistComponent,
        ReceptionistDialogComponent,
        ReceptionistPopupComponent,
        ReceptionistDeleteDialogComponent,
        ReceptionistDeletePopupComponent,
    ],
    providers: [
        ReceptionistService,
        ReceptionistPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClinicLenusReceptionistModule {}
