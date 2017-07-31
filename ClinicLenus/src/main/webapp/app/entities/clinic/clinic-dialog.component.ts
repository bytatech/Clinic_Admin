import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Clinic } from './clinic.model';
import { ClinicPopupService } from './clinic-popup.service';
import { ClinicService } from './clinic.service';
import { ClinicDetails, ClinicDetailsService } from '../clinic-details';
import { Receptionist, ReceptionistService } from '../receptionist';
import { Doctor, DoctorService } from '../doctor';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-clinic-dialog',
    templateUrl: './clinic-dialog.component.html'
})
export class ClinicDialogComponent implements OnInit {

    clinic: Clinic;
    authorities: any[];
    isSaving: boolean;

    clinicdetails: ClinicDetails[];

    receptionists: Receptionist[];

    doctors: Doctor[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private clinicService: ClinicService,
        private clinicDetailsService: ClinicDetailsService,
        private receptionistService: ReceptionistService,
        private doctorService: DoctorService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.clinicDetailsService
            .query({filter: 'clinic-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.clinic.clinicDetails || !this.clinic.clinicDetails.id) {
                    this.clinicdetails = res.json;
                } else {
                    this.clinicDetailsService
                        .find(this.clinic.clinicDetails.id)
                        .subscribe((subRes: ClinicDetails) => {
                            this.clinicdetails = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.receptionistService
            .query({filter: 'clinic-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.clinic.receptionist || !this.clinic.receptionist.id) {
                    this.receptionists = res.json;
                } else {
                    this.receptionistService
                        .find(this.clinic.receptionist.id)
                        .subscribe((subRes: Receptionist) => {
                            this.receptionists = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.doctorService
            .query({filter: 'clinic-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.clinic.doctor || !this.clinic.doctor.id) {
                    this.doctors = res.json;
                } else {
                    this.doctorService
                        .find(this.clinic.doctor.id)
                        .subscribe((subRes: Doctor) => {
                            this.doctors = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.clinic.id !== undefined) {
            this.subscribeToSaveResponse(
                this.clinicService.update(this.clinic));
        } else {
            this.subscribeToSaveResponse(
                this.clinicService.create(this.clinic));
        }
    }

    private subscribeToSaveResponse(result: Observable<Clinic>) {
        result.subscribe((res: Clinic) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Clinic) {
        this.eventManager.broadcast({ name: 'clinicListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    trackClinicDetailsById(index: number, item: ClinicDetails) {
        return item.id;
    }

    trackReceptionistById(index: number, item: Receptionist) {
        return item.id;
    }

    trackDoctorById(index: number, item: Doctor) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-clinic-popup',
    template: ''
})
export class ClinicPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private clinicPopupService: ClinicPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.clinicPopupService
                    .open(ClinicDialogComponent, params['id']);
            } else {
                this.modalRef = this.clinicPopupService
                    .open(ClinicDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
