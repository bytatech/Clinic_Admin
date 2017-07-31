import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ClinicOwnerInfo } from './clinic-owner-info.model';
import { ClinicOwnerInfoPopupService } from './clinic-owner-info-popup.service';
import { ClinicOwnerInfoService } from './clinic-owner-info.service';

@Component({
    selector: 'jhi-clinic-owner-info-dialog',
    templateUrl: './clinic-owner-info-dialog.component.html'
})
export class ClinicOwnerInfoDialogComponent implements OnInit {

    clinicOwnerInfo: ClinicOwnerInfo;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private clinicOwnerInfoService: ClinicOwnerInfoService,
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
        if (this.clinicOwnerInfo.id !== undefined) {
            this.subscribeToSaveResponse(
                this.clinicOwnerInfoService.update(this.clinicOwnerInfo));
        } else {
            this.subscribeToSaveResponse(
                this.clinicOwnerInfoService.create(this.clinicOwnerInfo));
        }
    }

    private subscribeToSaveResponse(result: Observable<ClinicOwnerInfo>) {
        result.subscribe((res: ClinicOwnerInfo) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: ClinicOwnerInfo) {
        this.eventManager.broadcast({ name: 'clinicOwnerInfoListModification', content: 'OK'});
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
    selector: 'jhi-clinic-owner-info-popup',
    template: ''
})
export class ClinicOwnerInfoPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private clinicOwnerInfoPopupService: ClinicOwnerInfoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.clinicOwnerInfoPopupService
                    .open(ClinicOwnerInfoDialogComponent, params['id']);
            } else {
                this.modalRef = this.clinicOwnerInfoPopupService
                    .open(ClinicOwnerInfoDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
