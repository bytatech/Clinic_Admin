import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ClinicPatientPrivateDataComponent } from './clinic-patient-private-data.component';
import { ClinicPatientPrivateDataDetailComponent } from './clinic-patient-private-data-detail.component';
import { ClinicPatientPrivateDataPopupComponent } from './clinic-patient-private-data-dialog.component';
import { ClinicPatientPrivateDataDeletePopupComponent } from './clinic-patient-private-data-delete-dialog.component';

import { Principal } from '../../shared';

export const clinicPatientPrivateDataRoute: Routes = [
    {
        path: 'clinic-patient-private-data',
        component: ClinicPatientPrivateDataComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicPatientPrivateData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'clinic-patient-private-data/:id',
        component: ClinicPatientPrivateDataDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicPatientPrivateData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const clinicPatientPrivateDataPopupRoute: Routes = [
    {
        path: 'clinic-patient-private-data-new',
        component: ClinicPatientPrivateDataPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicPatientPrivateData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'clinic-patient-private-data/:id/edit',
        component: ClinicPatientPrivateDataPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicPatientPrivateData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'clinic-patient-private-data/:id/delete',
        component: ClinicPatientPrivateDataDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicPatientPrivateData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
