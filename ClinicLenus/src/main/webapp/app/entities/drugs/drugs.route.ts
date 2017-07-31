import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { DrugsComponent } from './drugs.component';
import { DrugsDetailComponent } from './drugs-detail.component';
import { DrugsPopupComponent } from './drugs-dialog.component';
import { DrugsDeletePopupComponent } from './drugs-delete-dialog.component';

import { Principal } from '../../shared';

export const drugsRoute: Routes = [
    {
        path: 'drugs',
        component: DrugsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.drugs.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'drugs/:id',
        component: DrugsDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.drugs.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const drugsPopupRoute: Routes = [
    {
        path: 'drugs-new',
        component: DrugsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.drugs.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'drugs/:id/edit',
        component: DrugsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.drugs.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'drugs/:id/delete',
        component: DrugsDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.drugs.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
