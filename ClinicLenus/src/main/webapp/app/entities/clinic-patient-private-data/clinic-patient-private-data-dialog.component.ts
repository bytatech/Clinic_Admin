import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ClinicPatientPrivateData } from './clinic-patient-private-data.model';
import { ClinicPatientPrivateDataPopupService } from './clinic-patient-private-data-popup.service';
import { ClinicPatientPrivateDataService } from './clinic-patient-private-data.service';

@Component({
    selector: 'jhi-clinic-patient-private-data-dialog',
    templateUrl: './clinic-patient-private-data-dialog.component.html'
})
export class ClinicPatientPrivateDataDialogComponent implements OnInit {

    clinicPatientPrivateData: ClinicPatientPrivateData;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private clinicPatientPrivateDataService: ClinicPatientPrivateDataService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.clinicPatientPrivateData.id !== undefined) {
            this.subscribeToSaveResponse(
                this.clinicPatientPrivateDataService.update(this.clinicPatientPrivateData));
        } else {
            this.subscribeToSaveResponse(
                this.clinicPatientPrivateDataService.create(this.clinicPatientPrivateData));
        }
    }

    private subscribeToSaveResponse(result: Observable<ClinicPatientPrivateData>) {
        result.subscribe((res: ClinicPatientPrivateData) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: ClinicPatientPrivateData) {
        this.eventManager.broadcast({ name: 'clinicPatientPrivateDataListModification', content: 'OK'});
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
}

@Component({
    selector: 'jhi-clinic-patient-private-data-popup',
    template: ''
})
export class ClinicPatientPrivateDataPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private clinicPatientPrivateDataPopupService: ClinicPatientPrivateDataPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.clinicPatientPrivateDataPopupService
                    .open(ClinicPatientPrivateDataDialogComponent, params['id']);
            } else {
                this.modalRef = this.clinicPatientPrivateDataPopupService
                    .open(ClinicPatientPrivateDataDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
