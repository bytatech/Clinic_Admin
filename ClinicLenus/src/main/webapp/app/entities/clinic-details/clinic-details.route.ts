import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ClinicDetailsComponent } from './clinic-details.component';
import { ClinicDetailsDetailComponent } from './clinic-details-detail.component';
import { ClinicDetailsPopupComponent } from './clinic-details-dialog.component';
import { ClinicDetailsDeletePopupComponent } from './clinic-details-delete-dialog.component';

import { Principal } from '../../shared';

export const clinicDetailsRoute: Routes = [
    {
        path: 'clinic-details',
        component: ClinicDetailsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'clinic-details/:id',
        component: ClinicDetailsDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicDetails.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const clinicDetailsPopupRoute: Routes = [
    {
        path: 'clinic-details-new',
        component: ClinicDetailsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicDetails.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'clinic-details/:id/edit',
        component: ClinicDetailsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicDetails.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'clinic-details/:id/delete',
        component: ClinicDetailsDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicDetails.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
