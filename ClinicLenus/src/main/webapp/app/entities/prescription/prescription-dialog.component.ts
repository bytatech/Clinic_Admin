import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Prescription } from './prescription.model';
import { PrescriptionPopupService } from './prescription-popup.service';
import { PrescriptionService } from './prescription.service';
import { ClinicPatientData, ClinicPatientDataService } from '../clinic-patient-data';
import { Drugs, DrugsService } from '../drugs';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-prescription-dialog',
    templateUrl: './prescription-dialog.component.html'
})
export class PrescriptionDialogComponent implements OnInit {

    prescription: Prescription;
    authorities: any[];
    isSaving: boolean;

    clinicpatientdata: ClinicPatientData[];

    drugs: Drugs[];
    dateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private prescriptionService: PrescriptionService,
        private clinicPatientDataService: ClinicPatientDataService,
        private drugsService: DrugsService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.clinicPatientDataService.query()
            .subscribe((res: ResponseWrapper) => { this.clinicpatientdata = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.drugsService.query()
            .subscribe((res: ResponseWrapper) => { this.drugs = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.prescription.id !== undefined) {
            this.subscribeToSaveResponse(
                this.prescriptionService.update(this.prescription));
        } else {
            this.subscribeToSaveResponse(
                this.prescriptionService.create(this.prescription));
        }
    }

    private subscribeToSaveResponse(result: Observable<Prescription>) {
        result.subscribe((res: Prescription) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Prescription) {
        this.eventManager.broadcast({ name: 'prescriptionListModification', content: 'OK'});
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

    trackClinicPatientDataById(index: number, item: ClinicPatientData) {
        return item.id;
    }

    trackDrugsById(index: number, item: Drugs) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-prescription-popup',
    template: ''
})
export class PrescriptionPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private prescriptionPopupService: PrescriptionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.prescriptionPopupService
                    .open(PrescriptionDialogComponent, params['id']);
            } else {
                this.modalRef = this.prescriptionPopupService
                    .open(PrescriptionDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
