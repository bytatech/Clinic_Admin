import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ClinicPatientDataComponent } from './clinic-patient-data.component';
import { ClinicPatientDataDetailComponent } from './clinic-patient-data-detail.component';
import { ClinicPatientDataPopupComponent } from './clinic-patient-data-dialog.component';
import { ClinicPatientDataDeletePopupComponent } from './clinic-patient-data-delete-dialog.component';

import { Principal } from '../../shared';

export const clinicPatientDataRoute: Routes = [
    {
        path: 'clinic-patient-data',
        component: ClinicPatientDataComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicPatientData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'clinic-patient-data/:id',
        component: ClinicPatientDataDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicPatientData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const clinicPatientDataPopupRoute: Routes = [
    {
        path: 'clinic-patient-data-new',
        component: ClinicPatientDataPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicPatientData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'clinic-patient-data/:id/edit',
        component: ClinicPatientDataPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicPatientData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'clinic-patient-data/:id/delete',
        component: ClinicPatientDataDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicPatientData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
