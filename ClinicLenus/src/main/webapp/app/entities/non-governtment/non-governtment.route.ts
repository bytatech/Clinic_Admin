import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { NonGoverntmentComponent } from './non-governtment.component';
import { NonGoverntmentDetailComponent } from './non-governtment-detail.component';
import { NonGoverntmentPopupComponent } from './non-governtment-dialog.component';
import { NonGoverntmentDeletePopupComponent } from './non-governtment-delete-dialog.component';

import { Principal } from '../../shared';

export const nonGoverntmentRoute: Routes = [
    {
        path: 'non-governtment',
        component: NonGoverntmentComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.nonGoverntment.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'non-governtment/:id',
        component: NonGoverntmentDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.nonGoverntment.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const nonGoverntmentPopupRoute: Routes = [
    {
        path: 'non-governtment-new',
        component: NonGoverntmentPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.nonGoverntment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'non-governtment/:id/edit',
        component: NonGoverntmentPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.nonGoverntment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'non-governtment/:id/delete',
        component: NonGoverntmentDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.nonGoverntment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
