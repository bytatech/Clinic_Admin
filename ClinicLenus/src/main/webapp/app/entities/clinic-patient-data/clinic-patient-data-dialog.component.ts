import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ClinicPatientData } from './clinic-patient-data.model';
import { ClinicPatientDataPopupService } from './clinic-patient-data-popup.service';
import { ClinicPatientDataService } from './clinic-patient-data.service';
import { Clinic, ClinicService } from '../clinic';
import { ClinicPatientPrivateData, ClinicPatientPrivateDataService } from '../clinic-patient-private-data';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-clinic-patient-data-dialog',
    templateUrl: './clinic-patient-data-dialog.component.html'
})
export class ClinicPatientDataDialogComponent implements OnInit {

    clinicPatientData: ClinicPatientData;
    authorities: any[];
    isSaving: boolean;

    clinics: Clinic[];

    clinicprivatedata: ClinicPatientPrivateData[];
    dateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private clinicPatientDataService: ClinicPatientDataService,
        private clinicService: ClinicService,
        private clinicPatientPrivateDataService: ClinicPatientPrivateDataService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.clinicService.query()
            .subscribe((res: ResponseWrapper) => { this.clinics = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.clinicPatientPrivateDataService
            .query({filter: 'clinicpatientdata-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.clinicPatientData.clinicPrivateData || !this.clinicPatientData.clinicPrivateData.id) {
                    this.clinicprivatedata = res.json;
                } else {
                    this.clinicPatientPrivateDataService
                        .find(this.clinicPatientData.clinicPrivateData.id)
                        .subscribe((subRes: ClinicPatientPrivateData) => {
                            this.clinicprivatedata = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.clinicPatientData.id !== undefined) {
            this.subscribeToSaveResponse(
                this.clinicPatientDataService.update(this.clinicPatientData));
        } else {
            this.subscribeToSaveResponse(
                this.clinicPatientDataService.create(this.clinicPatientData));
        }
    }

    private subscribeToSaveResponse(result: Observable<ClinicPatientData>) {
        result.subscribe((res: ClinicPatientData) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: ClinicPatientData) {
        this.eventManager.broadcast({ name: 'clinicPatientDataListModification', content: 'OK'});
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

    trackClinicById(index: number, item: Clinic) {
        return item.id;
    }

    trackClinicPatientPrivateDataById(index: number, item: ClinicPatientPrivateData) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-clinic-patient-data-popup',
    template: ''
})
export class ClinicPatientDataPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private clinicPatientDataPopupService: ClinicPatientDataPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.clinicPatientDataPopupService
                    .open(ClinicPatientDataDialogComponent, params['id']);
            } else {
                this.modalRef = this.clinicPatientDataPopupService
                    .open(ClinicPatientDataDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
