import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { GoverntmentComponent } from './governtment.component';
import { GoverntmentDetailComponent } from './governtment-detail.component';
import { GoverntmentPopupComponent } from './governtment-dialog.component';
import { GoverntmentDeletePopupComponent } from './governtment-delete-dialog.component';

import { Principal } from '../../shared';

export const governtmentRoute: Routes = [
    {
        path: 'governtment',
        component: GoverntmentComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.governtment.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'governtment/:id',
        component: GoverntmentDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.governtment.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const governtmentPopupRoute: Routes = [
    {
        path: 'governtment-new',
        component: GoverntmentPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.governtment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'governtment/:id/edit',
        component: GoverntmentPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.governtment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'governtment/:id/delete',
        component: GoverntmentDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.governtment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
