import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ClinicOwnerInfoComponent } from './clinic-owner-info.component';
import { ClinicOwnerInfoDetailComponent } from './clinic-owner-info-detail.component';
import { ClinicOwnerInfoPopupComponent } from './clinic-owner-info-dialog.component';
import { ClinicOwnerInfoDeletePopupComponent } from './clinic-owner-info-delete-dialog.component';

import { Principal } from '../../shared';

export const clinicOwnerInfoRoute: Routes = [
    {
        path: 'clinic-owner-info',
        component: ClinicOwnerInfoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicOwnerInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'clinic-owner-info/:id',
        component: ClinicOwnerInfoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicOwnerInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const clinicOwnerInfoPopupRoute: Routes = [
    {
        path: 'clinic-owner-info-new',
        component: ClinicOwnerInfoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicOwnerInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'clinic-owner-info/:id/edit',
        component: ClinicOwnerInfoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicOwnerInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'clinic-owner-info/:id/delete',
        component: ClinicOwnerInfoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.clinicOwnerInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
