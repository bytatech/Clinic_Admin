import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ReceptionistComponent } from './receptionist.component';
import { ReceptionistDetailComponent } from './receptionist-detail.component';
import { ReceptionistPopupComponent } from './receptionist-dialog.component';
import { ReceptionistDeletePopupComponent } from './receptionist-delete-dialog.component';

import { Principal } from '../../shared';

export const receptionistRoute: Routes = [
    {
        path: 'receptionist',
        component: ReceptionistComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.receptionist.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'receptionist/:id',
        component: ReceptionistDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.receptionist.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const receptionistPopupRoute: Routes = [
    {
        path: 'receptionist-new',
        component: ReceptionistPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.receptionist.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'receptionist/:id/edit',
        component: ReceptionistPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.receptionist.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'receptionist/:id/delete',
        component: ReceptionistDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.receptionist.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
