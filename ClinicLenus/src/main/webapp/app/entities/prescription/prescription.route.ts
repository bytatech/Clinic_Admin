import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { PrescriptionComponent } from './prescription.component';
import { PrescriptionDetailComponent } from './prescription-detail.component';
import { PrescriptionPopupComponent } from './prescription-dialog.component';
import { PrescriptionDeletePopupComponent } from './prescription-delete-dialog.component';

import { Principal } from '../../shared';

export const prescriptionRoute: Routes = [
    {
        path: 'prescription',
        component: PrescriptionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.prescription.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'prescription/:id',
        component: PrescriptionDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.prescription.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const prescriptionPopupRoute: Routes = [
    {
        path: 'prescription-new',
        component: PrescriptionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.prescription.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'prescription/:id/edit',
        component: PrescriptionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.prescription.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'prescription/:id/delete',
        component: PrescriptionDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.prescription.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
