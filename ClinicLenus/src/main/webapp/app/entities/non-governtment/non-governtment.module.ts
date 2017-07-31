import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ClinicLenusSharedModule } from '../../shared';
import {
    NonGoverntmentService,
    NonGoverntmentPopupService,
    NonGoverntmentComponent,
    NonGoverntmentDetailComponent,
    NonGoverntmentDialogComponent,
    NonGoverntmentPopupComponent,
    NonGoverntmentDeletePopupComponent,
    NonGoverntmentDeleteDialogComponent,
    nonGoverntmentRoute,
    nonGoverntmentPopupRoute,
} from './';

const ENTITY_STATES = [
    ...nonGoverntmentRoute,
    ...nonGoverntmentPopupRoute,
];

@NgModule({
    imports: [
        ClinicLenusSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        NonGoverntmentComponent,
        NonGoverntmentDetailComponent,
        NonGoverntmentDialogComponent,
        NonGoverntmentDeleteDialogComponent,
        NonGoverntmentPopupComponent,
        NonGoverntmentDeletePopupComponent,
    ],
    entryComponents: [
        NonGoverntmentComponent,
        NonGoverntmentDialogComponent,
        NonGoverntmentPopupComponent,
        NonGoverntmentDeleteDialogComponent,
        NonGoverntmentDeletePopupComponent,
    ],
    providers: [
        NonGoverntmentService,
        NonGoverntmentPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClinicLenusNonGoverntmentModule {}
