import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ClinicLenusSharedModule } from '../../shared';
import {
    GoverntmentService,
    GoverntmentPopupService,
    GoverntmentComponent,
    GoverntmentDetailComponent,
    GoverntmentDialogComponent,
    GoverntmentPopupComponent,
    GoverntmentDeletePopupComponent,
    GoverntmentDeleteDialogComponent,
    governtmentRoute,
    governtmentPopupRoute,
} from './';

const ENTITY_STATES = [
    ...governtmentRoute,
    ...governtmentPopupRoute,
];

@NgModule({
    imports: [
        ClinicLenusSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        GoverntmentComponent,
        GoverntmentDetailComponent,
        GoverntmentDialogComponent,
        GoverntmentDeleteDialogComponent,
        GoverntmentPopupComponent,
        GoverntmentDeletePopupComponent,
    ],
    entryComponents: [
        GoverntmentComponent,
        GoverntmentDialogComponent,
        GoverntmentPopupComponent,
        GoverntmentDeleteDialogComponent,
        GoverntmentDeletePopupComponent,
    ],
    providers: [
        GoverntmentService,
        GoverntmentPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClinicLenusGoverntmentModule {}
