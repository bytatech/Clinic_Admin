import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ClinicLenusSharedModule } from '../../shared';
import {
    DrugsService,
    DrugsPopupService,
    DrugsComponent,
    DrugsDetailComponent,
    DrugsDialogComponent,
    DrugsPopupComponent,
    DrugsDeletePopupComponent,
    DrugsDeleteDialogComponent,
    drugsRoute,
    drugsPopupRoute,
} from './';

const ENTITY_STATES = [
    ...drugsRoute,
    ...drugsPopupRoute,
];

@NgModule({
    imports: [
        ClinicLenusSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        DrugsComponent,
        DrugsDetailComponent,
        DrugsDialogComponent,
        DrugsDeleteDialogComponent,
        DrugsPopupComponent,
        DrugsDeletePopupComponent,
    ],
    entryComponents: [
        DrugsComponent,
        DrugsDialogComponent,
        DrugsPopupComponent,
        DrugsDeleteDialogComponent,
        DrugsDeletePopupComponent,
    ],
    providers: [
        DrugsService,
        DrugsPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClinicLenusDrugsModule {}
