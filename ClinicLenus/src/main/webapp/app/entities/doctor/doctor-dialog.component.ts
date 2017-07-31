import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Doctor } from './doctor.model';
import { DoctorPopupService } from './doctor-popup.service';
import { DoctorService } from './doctor.service';

@Component({
    selector: 'jhi-doctor-dialog',
    templateUrl: './doctor-dialog.component.html'
})
export class DoctorDialogComponent implements OnInit {

    doctor: Doctor;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private doctorService: DoctorService,
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
        if (this.doctor.id !== undefined) {
            this.subscribeToSaveResponse(
                this.doctorService.update(this.doctor));
        } else {
            this.subscribeToSaveResponse(
                this.doctorService.create(this.doctor));
        }
    }

    private subscribeToSaveResponse(result: Observable<Doctor>) {
        result.subscribe((res: Doctor) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Doctor) {
        this.eventManager.broadcast({ name: 'doctorListModification', content: 'OK'});
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
    selector: 'jhi-doctor-popup',
    template: ''
})
export class DoctorPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private doctorPopupService: DoctorPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.doctorPopupService
                    .open(DoctorDialogComponent, params['id']);
            } else {
                this.modalRef = this.doctorPopupService
                    .open(DoctorDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
