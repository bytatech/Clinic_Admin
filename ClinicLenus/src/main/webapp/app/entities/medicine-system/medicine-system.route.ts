import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { MedicineSystemComponent } from './medicine-system.component';
import { MedicineSystemDetailComponent } from './medicine-system-detail.component';
import { MedicineSystemPopupComponent } from './medicine-system-dialog.component';
import { MedicineSystemDeletePopupComponent } from './medicine-system-delete-dialog.component';

import { Principal } from '../../shared';

export const medicineSystemRoute: Routes = [
    {
        path: 'medicine-system',
        component: MedicineSystemComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.medicineSystem.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'medicine-system/:id',
        component: MedicineSystemDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.medicineSystem.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const medicineSystemPopupRoute: Routes = [
    {
        path: 'medicine-system-new',
        component: MedicineSystemPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.medicineSystem.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'medicine-system/:id/edit',
        component: MedicineSystemPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.medicineSystem.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'medicine-system/:id/delete',
        component: MedicineSystemDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'clinicLenusApp.medicineSystem.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
